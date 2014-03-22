package org.com.location.service.nlp;

import java.io.*;
import java.util.*;

import opennlp.tools.cmdline.postag.POSModelLoader;
import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSSample;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.tokenize.WhitespaceTokenizer;
import opennlp.tools.util.ObjectStream;
import opennlp.tools.util.PlainTextByLineStream;
import opennlp.tools.util.Span;
import org.com.location.model.nlp.POSResult;
import org.com.location.service.LocationService;

public class OpenNLPService implements LocationService {

    private POSTaggerME tagger = null;
    private NameFinderME nameFinder;

    public OpenNLPService(){
        try {
            init();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void init() throws IOException {
        POSModel model = new POSModelLoader().load(new File("en-pos-maxent.bin"));
        tagger = new POSTaggerME(model);
        nameFinder =new NameFinderME(new TokenNameFinderModel(new FileInputStream("en-ner-location.bin")));

    }

    public POSResult parseQuery(String input) throws IOException {
        POSResult posResult = null;

        if(tagger == null || nameFinder == null || input == null || input.isEmpty()) {
            return posResult;
        }

        String line;
        POSSample sample;
        ObjectStream<String> lineStream = new PlainTextByLineStream(new StringReader(input));

        while ((line = lineStream.read()) != null) {
            String whitespaceTokenizerLine[] = WhitespaceTokenizer.INSTANCE.tokenize(line);
            String[] tags = tagger.tag(whitespaceTokenizerLine);
            sample = new POSSample(whitespaceTokenizerLine, tags);
            String tokenString = sample.toString();
            String tokenTags[]=tokenString.split(" ");

            if(tokenTags.length<1) {
                return posResult;
            }

            posResult = new POSResult();

            for(int i = 0; i < tokenTags.length; i++) {
                String posToken = tokenTags[i];
                String posElements[] = posToken.split("_");

                if(posElements[1].startsWith("NN")) {
                    posResult.addCommonNoun(posElements[0]);
                } else if(posElements[1].equals("NNP")) {
                    posResult.addProperNoun(posElements[0]);
                } else if(posElements[1].equals("IN")) {
                    posResult.addPreposition(posElements[0]);
                } else if(posElements[1].equals("CD")) {
                    posResult.addNumber(posElements[0]);
                } else {
                    posResult.addOthers(posElements[0]);
                }
            }

            posResult.getLocations().addAll(extractLocations(posResult.getCommonNouns()));
            posResult.getLocations().addAll(extractLocations(posResult.getProperNouns()));

        }

        return posResult;
    }

    private List<String> extractLocations(List<String> posEntities) {
        String[] candidateArray = new String[posEntities.size()];
        candidateArray = posEntities.toArray(candidateArray);
        Span[] locationSpans = nameFinder.find(candidateArray);
        return(Arrays.asList(Span.spansToStrings(locationSpans, candidateArray)));
    }

}

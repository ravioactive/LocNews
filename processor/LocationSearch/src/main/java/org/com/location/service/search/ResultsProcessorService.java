package org.com.location.service.search;

import org.com.location.model.search.PreparedWikiNews;
import org.com.location.model.search.QueryFilters;
import org.com.location.model.search.SearchResult;
import org.com.location.model.solr.SolrWikiNews;
import org.com.location.model.solr.schema.WikiNews;
import org.com.location.service.LocationService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: ravioactive
 * Date: 11/27/13
 * Time: 8:49 PM
 */

public class ResultsProcessorService implements LocationService {
    List<WikiNews> wikiNewsList = null;
    QueryFilters queryFilters;
    SearchResult searchResult = null;
    Map<String, List<String>> suggestionMeta = null;
    public String getMessage() {
        return message;
    }

    String message = null;

    public ResultsProcessorService() {

    }

    public ResultsProcessorService(QueryFilters queryFilters, List<WikiNews> wikiNewsList, Map<String,List<String>> locationsMeta) {
        //use this to know current value of d for feedback type 5
        this.queryFilters = queryFilters;
        this.wikiNewsList = wikiNewsList;
        this.suggestionMeta = locationsMeta;
    }

    public SearchResult prepareSearchResults() /*Throw custom exceptions*/ {
        List<PreparedWikiNews> preparedWikiNews = prepareWikiNewsResult(wikiNewsList);
        Map<String, List<String>> suggestions = generateSearchSuggestions();
        searchResult = new SearchResult(preparedWikiNews, queryFilters);
        searchResult.setSearchSuggestions(suggestions);
        return searchResult;
    }

    public Map<String, List<String>> generateSearchSuggestions() {
        return suggestionMeta;
    }

    public List<PreparedWikiNews> prepareWikiNewsResult(List<WikiNews> wikiNewsList) {
        List<PreparedWikiNews> preparedWikiNewsList = new ArrayList<PreparedWikiNews>();
        if(wikiNewsList!=null && !wikiNewsList.isEmpty()) {
            for(WikiNews wikiNews : wikiNewsList) {
                try {
                    String parsedText = ResultsParsingService.pareseWikiNewsContent(wikiNews.getText());
                    String summary = ResultsParsingService.generateSummary(parsedText);
                    wikiNews.setText(parsedText);
                    wikiNews.setSummary(summary);
                } catch (Exception e) {
                    message += "Could not parse body for wikipedia doc "+wikiNews.getId();
                }

                PreparedWikiNews preparedWikiNews = new PreparedWikiNews(wikiNews);
                preparedWikiNewsList.add(preparedWikiNews);
            }
        }

        return preparedWikiNewsList;
    }


}

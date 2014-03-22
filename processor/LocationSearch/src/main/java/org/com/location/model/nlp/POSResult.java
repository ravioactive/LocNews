package org.com.location.model.nlp;

import java.util.ArrayList;
import java.util.List;

/**
 * User: ravioactive
 * Date: 11/27/13
 * Time: 8:04 PM
 */

public class POSResult  {
    List<String> commonNouns = null;
    List<String> properNouns = null;
    List<String> locations = null;
    List<String> numbers = null;
    List<String> others = null;
    List<String> prepositions = null;
    boolean isSpatial = false;

    public List<String> getCommonNouns() {
        return commonNouns;
    }

    public void setCommonNouns(List<String> commonNouns) {
        this.commonNouns = commonNouns;
    }

    public List<String> getProperNouns() {
        return properNouns;
    }

    public void setProperNouns(List<String> properNouns) {
        this.properNouns = properNouns;
    }

    public List<String> getLocations() {
        return locations;
    }

    public void setLocations(List<String> locations) {
        this.locations = locations;
    }

    public List<String> getNumbers() {
        return numbers;
    }

    public void setNumbers(List<String> numbers) {
        this.numbers = numbers;
    }

    public List<String> getOthers() {
        return others;
    }

    public void setOthers(List<String> others) {
        this.others = others;
    }

    public List<String> getPrepositions() {
        return prepositions;
    }

    public void setPrepositions(List<String> prepositions) {
        this.prepositions = prepositions;
    }

    public boolean isSpatial() {
        return isSpatial;
    }

    public void setSpatial(boolean spatial) {
        isSpatial = spatial;
    }


    public POSResult() {
        commonNouns = new ArrayList<String>();
        properNouns = new ArrayList<String>();
        locations = new ArrayList<String>();
        numbers = new ArrayList<String>();
        others = new ArrayList<String>();
        prepositions = new ArrayList<String>();
    }

    public void addCommonNoun(String commonNoun) {
        if(commonNouns == null) {
            commonNouns = new ArrayList<String>();
        }
        commonNouns.add(commonNoun);
    }

    public void addProperNoun(String properNoun) {
        if(properNouns == null) {
            properNouns = new ArrayList<String>();
        }
        properNouns.add(properNoun);
    }

    public void addLocation(String location) {
        if(locations == null) {
            locations = new ArrayList<String>();
        }
        locations.add(location);
    }

    public void addPreposition(String preposition) {
        if(prepositions == null) {
            prepositions = new ArrayList<String>();
        }
        prepositions.add(preposition);
    }

    public void addOthers(String other) {
        if(others == null) {
            others = new ArrayList<String>();
        }
        others.add(other);
    }

    public void addNumber(String number) {
        if(numbers == null) {
            numbers = new ArrayList<String>();
        }
        numbers.add(number);
    }


}

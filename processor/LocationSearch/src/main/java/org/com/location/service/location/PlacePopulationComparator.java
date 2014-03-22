package org.com.location.service.location;

import org.com.location.model.solr.schema.Place;

import java.util.Comparator;

/**
 * Created with IntelliJ IDEA.
 * User: ravioactive
 * Date: 12/1/13
 * Time: 12:37 PM
 * To change this template use File | Settings | File Templates.
 */
public class PlacePopulationComparator implements Comparator<Place> {
    @Override
    public int compare(Place p1, Place p2) {
        if(p1==null) {
            if(p2==null) {
                return 0;
            } else {
                return 1;
            }
        } else {
            if(p2==null) {
                return -1;
            } else {
                if(p1.getPopulation() < p2.getPopulation()) {
                    return 1;
                } else if(p1.getPopulation() > p2.getPopulation()) {
                    return -1;
                } else {
                    return 0;
                }
            }
        }
    }
}

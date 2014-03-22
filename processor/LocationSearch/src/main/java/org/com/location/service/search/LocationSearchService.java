package org.com.location.service.search;

import org.com.location.LocationSearchConstants;
import org.com.location.model.search.SearchResult;
import org.com.location.model.solr.LocationQuery;
import org.com.location.model.solr.SolrLocationType;
import org.com.location.model.solr.SolrSchemaType;
import org.com.location.model.solr.schema.*;
import org.com.location.service.LocationService;
import org.com.location.service.solr.SolrSearchService;

import java.util.*;

/**
 * User: ravioactive
 * Date: 11/27/13
 * Time: 9:03 PM
 */

public class LocationSearchService implements LocationService {
    List<LocationQuery> userQueries = null;

    SolrSearchService solrSeacher = null;

    /*boolean isHeirarchial = false;*/
    boolean isSpatial = false;
    int distance = -1;
    int distanceSort = -1;
    int timeSort = -1;
    List<String> candidateLocations = null;
    List<Place> locationsFound = null;

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    int currentPage = 1;
    List<String> wikiNewsSearchKeyWords = null;
    List<String> wikiNewsSearchLocations = null;

    public String getQueryTime() {
        return queryTime;
    }

    public void setQueryTime(String queryTime) {
        this.queryTime = queryTime;
    }

    String queryTime = null;

    public String getNumFound() {
        return numFound;
    }

    public void setNumFound(String numFound) {
        this.numFound = numFound;
    }

    String numFound = null;

    public String getCurrentWikiNewsLocQueryString() {
        return currentWikiNewsLocQueryString;
    }

    public void setCurrentWikiNewsLocQueryString(String currentWikiNewsLocQueryString) {
        this.currentWikiNewsLocQueryString = currentWikiNewsLocQueryString;
    }

    private String currentWikiNewsLocQueryString;

    public String getCurrentWikiNewsQueryString() {
        return currentWikiNewsQueryString;
    }

    private String currentWikiNewsQueryString;

    public Map<String, List<String>> getHeirarchialInfo() {
        return heirarchialInfo;
    }

    Map<String, List<String>> heirarchialInfo = new HashMap<String, List<String>>();

    public String getMessage() {
        return message;
    }

    String message = "";


    public LocationSearchService() throws Exception  {
        try {
            solrSeacher = new SolrSearchService(LocationSearchConstants.SOLR_BASE_URL);
        } catch (Exception e) {
            message += "Error initializing cores.";
            throw e;
        }
    }

    public  LocationSearchService(List<LocationQuery> userQueries) throws Exception {
        this();
        this.userQueries = userQueries;
    }

    public List<WikiNews> doSearch() /*Add Custom Exceptions*/ {
        initializeLocationSearch(userQueries);
        List<String> locations = doLocationSearch(userQueries);

        initializeWikiNewsSearch(locations, userQueries);
        List<WikiNews> wikiNewsList = doWikiNewsSearch();

        return wikiNewsList;
    }

    public List<WikiNews> doFeedback(SearchResult feedbackSearchResult) {
        List<String> locations = doFeedbackLocationSearch(feedbackSearchResult);

        initializeFeedbackWikiNewsSearch(locations, feedbackSearchResult);
        List<WikiNews> wikiNewsList = doWikiNewsSearch();

        return wikiNewsList;
    }

    public List<WikiNews> getNextPage(SearchResult searchResult) {
        currentWikiNewsQueryString = searchResult.getCurrentQuery();
        currentWikiNewsLocQueryString = searchResult.getCurrentLocQuery();
        int nextPage = searchResult.getCurrentPage();
        int nextAt = (nextPage-1)*LocationSearchConstants._RESULT_COUNT_VALUE_INT;
        List<WikiNews> wikiNewsList = solrSeacher.findWikiNewsFor(currentWikiNewsQueryString, currentWikiNewsLocQueryString, timeSort, nextAt);

        return wikiNewsList;
    }

    private void initializeFeedbackWikiNewsSearch(List<String> locations, SearchResult feedbackSearchResult) {
        wikiNewsSearchLocations = locations;
        wikiNewsSearchKeyWords = new ArrayList<String>();
        if(feedbackSearchResult.getQueryFilters().getIsAdvanced()) {
            wikiNewsSearchKeyWords.add(feedbackSearchResult.getQueryFilters().getKeyWords());
        } else {
            wikiNewsSearchKeyWords.add(feedbackSearchResult.getQueryFilters().getQueryStr());
        }
    }

    private List<String> doFeedbackLocationSearch(SearchResult locationFeedback) {
        List<Feature> featuresFound = solrSeacher.findFeatures(locationFeedback.getQueryPOSResult().getCommonNouns());
        List<Language> languagesFound = solrSeacher.findLanguages(locationFeedback.getQueryPOSResult().getProperNouns());
        List<Timezone> timezonesFound = solrSeacher.findTimezones(locationFeedback.getQueryPOSResult().getProperNouns());

        Map<String, List<? extends SolrSchemaType>> metaFilters = new HashMap<String, List<? extends SolrSchemaType>>();
        metaFilters.put(LocationSearchConstants.FEATURE_CORE_NAME, featuresFound);
        metaFilters.put(LocationSearchConstants.LANGUAGE_CORE_NAME, languagesFound);
        metaFilters.put(LocationSearchConstants.TIMEZONE_CORE_NAME, timezonesFound);

        distance = locationFeedback.getQueryFilters().getDistance(); //update in Controller in case of incremental spatial
        distanceSort = 1;
        locationsFound = new ArrayList<Place>();
        Map<String, String> chosenFeedback = locationFeedback.getFeedback();
        for(Map.Entry<String, String> feedbackEntry : chosenFeedback.entrySet()) {
            List<Place> locationsFoundForFeedbackEntry = performFeedbackOnLocation(feedbackEntry.getKey(), feedbackEntry.getValue(), metaFilters, locationFeedback);
            if(locationsFoundForFeedbackEntry!=null && locationsFoundForFeedbackEntry.size()>0) {
                locationsFound.addAll(locationsFoundForFeedbackEntry);
            }
        }

        Set<String> locationNamesSet = new HashSet<String>();
        if(locationsFound!=null) {
            Iterator<Place> locationsIterator = locationsFound.iterator();
            while(locationsIterator.hasNext()) {
                Place place = locationsIterator.next();
                String[] nameSplitter = place.getNameascii().split(" ");
                for(String nameSplit : nameSplitter) {
                    locationNamesSet.add(nameSplit);
                }
            }
        }

        List<String> locationNames = new ArrayList<String>();
        locationNames.addAll(locationNamesSet);
        return locationNames;
    }

    private List<Place> performFeedbackOnLocation(String locationId, String operationCode, Map<String, List<? extends SolrSchemaType>> metaFilters, SearchResult locationFeedback) {
        List<Place> placesForFeedback = new ArrayList<Place>();
        int opCode = Integer.valueOf(operationCode);
        switch(opCode) {
            case LocationSearchConstants.FEEDBACK_TYPE_IS_CONTINENT :
                placesForFeedback = findLocationsByIdWithHierarchy(locationId, LocationSearchConstants.HIERARCHY_CONTINENT_VALUE, metaFilters);
                break;
            case LocationSearchConstants.FEEDBACK_TYPE_IS_COUNTRY :
                placesForFeedback = findLocationsByIdWithHierarchy(locationId, LocationSearchConstants.HIERARCHY_COUNTRY_VALUE, metaFilters);
                break;
            case LocationSearchConstants.FEEDBACK_TYPE_IS_STATE :
                placesForFeedback = findLocationsByIdWithHierarchy(locationId, LocationSearchConstants.HIERARCHY_STATE_VALUE, metaFilters);
                break;
            case LocationSearchConstants.FEEDBACK_TYPE_IS_DISTRICT :
                placesForFeedback = findLocationsByIdWithHierarchy(locationId, LocationSearchConstants.HIERARCHY_DISTRICT_VALUE, metaFilters);
                break;
            case LocationSearchConstants.FEEDBACK_TYPE_INC_SPATIAL :
                Place placeIncrSpatial = solrSeacher.findLocationById(locationId);
                recordHeirarchialInfo(placeIncrSpatial.getNameascii(), placeIncrSpatial.getId()+"|"+solrSeacher.completeName(placeIncrSpatial)+"|"+LocationSearchConstants.FEEDBACK_TYPE_INC_SPATIAL);
                locationFeedback.getQueryFilters().setDistance(distance + LocationSearchConstants.SPATIAL_SEARCH_INCREMEMENT_D);
                placesForFeedback = findLocationsWithSpatial(placeIncrSpatial, distance+LocationSearchConstants.SPATIAL_SEARCH_INCREMEMENT_D, distanceSort, metaFilters);
                break;
            case LocationSearchConstants.FEEDBACK_TYPE_BEGIN_SPATIAL :
                Place placeBeginSpatial = solrSeacher.findLocationById(locationId);
                recordHeirarchialInfo(placeBeginSpatial.getNameascii(), placeBeginSpatial.getId()+"|"+solrSeacher.completeName(placeBeginSpatial)+"|"+LocationSearchConstants.FEEDBACK_TYPE_INC_SPATIAL);
                locationFeedback.getQueryFilters().setDistance(LocationSearchConstants.SPATIAL_SEARCH_BEGIN_D);
                placesForFeedback = findLocationsWithSpatial(placeBeginSpatial, LocationSearchConstants.SPATIAL_SEARCH_BEGIN_D, distanceSort, metaFilters);
                break;
        }
        return placesForFeedback;
    }

    private List<Place> findLocationsWithSpatial(Place place, long distance, int distanceSort, Map<String, List<? extends SolrSchemaType>> filters) {
        List<Place> places = new ArrayList<Place>();
        if(solrSeacher.isSpatialSearchPlausible(place, distance)) {
            List<Place> spatialPlaces = solrSeacher.doSpatialSearch(place, distance, distanceSort, filters);
            if(spatialPlaces!=null && spatialPlaces.size()>0) {
                places.addAll(spatialPlaces);
            }
        } else {
            message += "Location Search implausible for: "+place.getNameascii();
        }
        return places;
    }

    private List<Place> findLocationsByIdWithHierarchy(String locationId, int locationHierarchyValue, Map<String, List<? extends SolrSchemaType>> filters) {
        List<Place> fetchedPlaces = new ArrayList<Place>();

        switch (locationHierarchyValue) {
            case LocationSearchConstants.HIERARCHY_CONTINENT_VALUE :
                Continent continent = solrSeacher.findContinentById(locationId);
                if(continent!=null) {
                    List<Country> countries = solrSeacher.findCountriesForContinent(continent, filters);
                    if(countries!=null && countries.size()>0) {
                        for(Country country : countries) {
                            List<State> states = solrSeacher.findStatesForCountry(country);
                            if(states!=null && states.size()>0) {
                                for(State state : states) {
                                    List<District> districts = solrSeacher.findDistrictsForState(state);
                                    if(districts!=null && districts.size()>0) {
                                        for(District district : districts) {
                                            List<Place> places = solrSeacher.findPlacesForDistrict(district, filters);
                                            if(places!=null && places.size()>0) {
                                                fetchedPlaces.addAll(places);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                break;
            case LocationSearchConstants.HIERARCHY_COUNTRY_VALUE :
                Country country = solrSeacher.findCountryById(locationId);
                if(country!=null) {
                    List<State> states = solrSeacher.findStatesForCountry(country);
                    if(states!=null && states.size()>0) {
                        for(State state : states) {
                            List<District> districts = solrSeacher.findDistrictsForState(state);
                            if(districts!=null && districts.size()>0) {
                                for(District district : districts) {
                                    List<Place> places = solrSeacher.findPlacesForDistrict(district, filters);
                                    if(places!=null && places.size()>0) {
                                        fetchedPlaces.addAll(places);
                                    }
                                }
                            }
                        }
                    }
                }
                break;
            case LocationSearchConstants.HIERARCHY_STATE_VALUE :
                State state = solrSeacher.findStateById(locationId);
                if(state!=null) {
                    List<District> districts = solrSeacher.findDistrictsForState(state);
                    if(districts!=null && districts.size()>0) {
                        for(District district : districts) {
                            List<Place> places = solrSeacher.findPlacesForDistrict(district, filters);
                            if(places!=null && places.size()>0) {
                                fetchedPlaces.addAll(places);
                            }
                        }
                    }
                }
                break;
            case LocationSearchConstants.HIERARCHY_DISTRICT_VALUE :
                District district = solrSeacher.findDistrictById(locationId);
                List<Place> places = solrSeacher.findPlacesForDistrict(district, filters);
                if(places!=null && places.size()>0) {
                    fetchedPlaces.addAll(places);
                }
                break;


        }
        return fetchedPlaces;
    }

    private void recordHeirarchialInfo(String location, String heirarchy) {
        if(!heirarchialInfo.containsKey(location)) {
            heirarchialInfo.put(location, new ArrayList<String>());
        }
        heirarchialInfo.get(location).add(heirarchy);
    }

    private void recordHeirarchialInfoList(String location, List<String> heirarchies) {
        if(!heirarchialInfo.containsKey(location)) {
            heirarchialInfo.put(location, new ArrayList<String>());
        }
        heirarchialInfo.get(location).addAll(heirarchies);
    }

    private List<WikiNews> doWikiNewsSearch() {
        StringBuilder wikiUserQueryBuilder = new StringBuilder();
        StringBuilder wikiLocQueryBuilder = new StringBuilder();

        for(String wikiStr : wikiNewsSearchKeyWords) {
            wikiUserQueryBuilder.append(wikiStr).append(" ");
        }

        for(String location : wikiNewsSearchLocations) {
            wikiLocQueryBuilder.append(location).append(" ");
        }

        currentWikiNewsQueryString = wikiUserQueryBuilder.toString();
        currentWikiNewsLocQueryString = wikiLocQueryBuilder.toString();

        int nextAt = (currentPage-1)*LocationSearchConstants._RESULT_COUNT_VALUE_INT;
        List<WikiNews> wikiNewsList = solrSeacher.findWikiNewsFor(currentWikiNewsQueryString, currentWikiNewsLocQueryString, timeSort, nextAt);
        queryTime = solrSeacher.getWikiNewsQueryTime();
        numFound = solrSeacher.getWikiNewsNumRetrieved();

        return wikiNewsList;
    }

    private void initializeWikiNewsSearch(List<String> locations, List<LocationQuery> userQueries) {
        wikiNewsSearchLocations = locations;
        wikiNewsSearchKeyWords = userQueries.get(0).fetchParam(LocationSearchConstants.USER_QUERY_ORIGINAL_QUERY_STR);
    }

    private List<String> doLocationSearch(List<LocationQuery> rawQueries) {
        LocationQuery aQuery = rawQueries.get(0);
        List<Feature> featuresFound = solrSeacher.findFeatures(aQuery.fetchParam(LocationSearchConstants.USER_QUERY_POS_RESULT_COMMONS));
        List<Language> languagesFound = solrSeacher.findLanguages(aQuery.fetchParam(LocationSearchConstants.USER_QUERY_POS_RESULT_PROPERS));
        List<Timezone> timezonesFound = solrSeacher.findTimezones(aQuery.fetchParam(LocationSearchConstants.USER_QUERY_POS_RESULT_PROPERS));

        Map<String, List<? extends SolrSchemaType>> metaFilters = new HashMap<String, List<? extends SolrSchemaType>>();
        metaFilters.put(LocationSearchConstants.FEATURE_CORE_NAME, featuresFound);
        metaFilters.put(LocationSearchConstants.LANGUAGE_CORE_NAME, languagesFound);
        metaFilters.put(LocationSearchConstants.TIMEZONE_CORE_NAME, timezonesFound);

        locationsFound = findLocations(candidateLocations, metaFilters);

        if(isSpatial) {
            List<Place> spatialLocationsFound = findLocationsWithSpatialAsList(locationsFound, distance, distanceSort, metaFilters);
            if(spatialLocationsFound!=null && spatialLocationsFound.size()>0) {
                locationsFound.addAll(spatialLocationsFound);
            } else {
                message += "Spatial Search did not find anything.";
            }
        }

        Set<String> locationNamesSet = new HashSet<String>();
        if(locationsFound!=null) {
            Iterator<Place> locationsIterator = locationsFound.iterator();
            while(locationsIterator.hasNext()) {
                Place place = locationsIterator.next();
                String[] nameSplitter = place.getNameascii().split(" ");
                for(String nameSplit : nameSplitter) {
                    locationNamesSet.add(nameSplit);
                }
            }
        }

        List<String> locationNames = new ArrayList<String>();
        locationNames.addAll(locationNamesSet);
        return locationNames;
    }

    private List<Place> findLocations(List<String> locationNames, Map<String, List<? extends SolrSchemaType>> filters) {
        List<Place> placesFound = new ArrayList<Place>();

        Map<String, SolrLocationType> uniqueLocationsFound = new HashMap<String, SolrLocationType>();

        for(String locationName : locationNames) {
            //recordHeirarchiesForPlaceAt(locationName, LocationSearchConstants.HIERARCHY_CONTINENT_VALUE);
            Place mostSuitablePlace = solrSeacher.findMostSuitablePlace(locationName, filters);
            placesFound.add(mostSuitablePlace);
            List<SolrLocationType> placesWithLocationName = findAllLocationsTypesByName(locationName, filters, LocationSearchConstants.HIERARCHY_CONTINENT_VALUE);
            if(placesWithLocationName!=null && placesWithLocationName.size()>0) {
                for(SolrLocationType placeForLocationName : placesWithLocationName) {
                    uniqueLocationsFound.put(String.valueOf(placeForLocationName.getCode()), placeForLocationName);
                }
            } else {
                message += "No locations found for name: "+locationName;
            }

        }

        List<SolrLocationType> uniqueLocations = new ArrayList<SolrLocationType>();
        for(Map.Entry<String, SolrLocationType> uniqueLocationEntry : uniqueLocationsFound.entrySet()) {
            uniqueLocations.add(uniqueLocationEntry.getValue());
        }

        List<Place> correlatedLocations = solrSeacher.getCorrelatedLocations(uniqueLocations);
        placesFound.addAll(correlatedLocations);

        return  placesFound;
    }

    private List<SolrLocationType> findAllLocationsTypesByName(String locationName, Map<String, List<? extends SolrSchemaType>> filters, int granuality) {
        List<SolrLocationType> locationTypesFound = new ArrayList<SolrLocationType>();

        if(granuality < LocationSearchConstants.HIERARCHY_PLACE_VALUE) {
            return locationTypesFound;
        }

        switch(granuality) {
            case LocationSearchConstants.HIERARCHY_CONTINENT_VALUE:
                List<Continent> asContinent = solrSeacher.findAllContinentsByName(locationName);
                locationTypesFound.addAll(findAllLocationsTypesByName(locationName, filters, LocationSearchConstants.HIERARCHY_COUNTRY_VALUE));
                if(asContinent!=null && asContinent.size()>0) {
                    for(Continent continent : asContinent) {
                        recordHeirarchialInfo(locationName, solrSeacher.impression(continent));
                    }
                }
                locationTypesFound.addAll(asContinent);
                break;
            case LocationSearchConstants.HIERARCHY_COUNTRY_VALUE:
                List<Country> asCountry = solrSeacher.findAllCountriesByName(locationName, filters);
                locationTypesFound.addAll(findAllLocationsTypesByName(locationName, filters, LocationSearchConstants.HIERARCHY_STATE_VALUE));
                if(asCountry!=null && asCountry.size()>0) {
                    for(Country country : asCountry) {
                        recordHeirarchialInfo(locationName, solrSeacher.impression(country));
                    }
                }
                locationTypesFound.addAll(asCountry);
                break;
            case LocationSearchConstants.HIERARCHY_STATE_VALUE:
                List<State> asState = solrSeacher.findAllStatesByName(locationName);
                locationTypesFound.addAll(findAllLocationsTypesByName(locationName, filters, LocationSearchConstants.HIERARCHY_DISTRICT_VALUE));
                if(asState!=null && asState.size()>0) {
                    for(State state : asState) {
                        recordHeirarchialInfo(locationName, solrSeacher.impression(state));
                    }
                }
                locationTypesFound.addAll(asState);
                break;
            case LocationSearchConstants.HIERARCHY_DISTRICT_VALUE:
                List<District> asDistrict = solrSeacher.findAllDistrictsByName(locationName);
                locationTypesFound.addAll(findAllLocationsTypesByName(locationName, filters, LocationSearchConstants.HIERARCHY_PLACE_VALUE));
                if(asDistrict!=null && asDistrict.size()>0) {
                    for(District district : asDistrict) {
                        recordHeirarchialInfo(locationName, solrSeacher.impression(district));
                    }
                }
                locationTypesFound.addAll(asDistrict);
                break;
            case LocationSearchConstants.HIERARCHY_PLACE_VALUE:
                List<Place> asPlace = solrSeacher.findAllPlacesByName(locationName, filters);
                if(asPlace!=null && asPlace.size()>0) {
                    for(Place place : asPlace) {
                        recordHeirarchialInfo(locationName, solrSeacher.impression(place));
                    }
                }
                locationTypesFound.addAll(asPlace);
                break;
            default: break;
        }

        return locationTypesFound;
    }

    /**
     * Useful but not right now
     * */
    private void recordHeirarchiesForPlaceAt(String locationName, int beginHeirarchyLevel) {
        if(beginHeirarchyLevel < LocationSearchConstants.HIERARCHY_PLACE_VALUE) {
            return;
        }

        switch(beginHeirarchyLevel) {
            case LocationSearchConstants.HIERARCHY_CONTINENT_VALUE :
                List<Continent> asContinent = solrSeacher.findAllContinentsByName(locationName);
                if(asContinent!=null && asContinent.size()>0) {
                    for(Continent continent : asContinent) {
                        recordHeirarchialInfo(locationName, solrSeacher.impression(continent));
                    }
                }
                recordHeirarchiesForPlaceAt(locationName, LocationSearchConstants.HIERARCHY_COUNTRY_VALUE);
                break;

            case LocationSearchConstants.HIERARCHY_COUNTRY_VALUE :
                List<Country> asCountry = solrSeacher.findAllCountriesByName(locationName, null);
                if(asCountry!=null && asCountry.size()>0) {
                    for(Country country : asCountry) {
                        recordHeirarchialInfo(locationName, solrSeacher.impression(country));
                    }
                }
                recordHeirarchiesForPlaceAt(locationName, LocationSearchConstants.HIERARCHY_STATE_VALUE);
                break;

            case LocationSearchConstants.HIERARCHY_STATE_VALUE :
                List<State> asState = solrSeacher.findAllStatesByName(locationName);
                if(asState!=null && asState.size()>0) {
                    for(State state : asState) {
                        recordHeirarchialInfo(locationName, solrSeacher.impression(state));
                    }
                }
                recordHeirarchiesForPlaceAt(locationName, LocationSearchConstants.HIERARCHY_DISTRICT_VALUE);
                break;

            case LocationSearchConstants.HIERARCHY_DISTRICT_VALUE :
                List<District> asDistrict = solrSeacher.findAllDistrictsByName(locationName);
                if(asDistrict!=null && asDistrict.size()>0) {
                    for(District district : asDistrict) {
                        recordHeirarchialInfo(locationName, solrSeacher.impression(district));
                    }
                }
                recordHeirarchiesForPlaceAt(locationName, LocationSearchConstants.HIERARCHY_PLACE_VALUE);
                break;

            case LocationSearchConstants.HIERARCHY_PLACE_VALUE :
                List<Place> asPlace = solrSeacher.findAllPlacesByName(locationName, null);
                if(asPlace!=null && asPlace.size()>0) {
                    for(Place place : asPlace) {
                        recordHeirarchialInfo(locationName, solrSeacher.impression(place));
                    }
                }
                break;

            default:

                break;
        }

    }


    private List<Place> findLocationsWithSpatialAsList(List<Place> locations, long distance, int distanceSort, Map<String, List<? extends SolrSchemaType>> filters) {
        List<Place> places = new ArrayList<Place>();
        Map<String, Place> allSpatialPlaces = new HashMap<String, Place>();
        for(Place place : locations) {
            List<Place> spatialPlaces = findLocationsWithSpatial(place, distance, distanceSort, filters);
            if(spatialPlaces!=null && spatialPlaces.size()>0) {
                for(Place spatialPlace : spatialPlaces) {
                    allSpatialPlaces.put(String.valueOf(spatialPlace.getId()), spatialPlace);
                }
            }
        }

        for(Map.Entry<String, Place> placeEntry : allSpatialPlaces.entrySet()) {

            places.add(placeEntry.getValue());
        }

        return places;
    }

    private void initializeLocationSearch(List<LocationQuery> userQueries) {
        Set<String> candidateLocationSet = new HashSet<String>();
        Iterator<LocationQuery> userQueriesIterator = userQueries.iterator();
        while(userQueriesIterator.hasNext()) {
            LocationQuery userQuery = userQueriesIterator.next();
            if(userQuery.containsParam(LocationSearchConstants.USER_QUERY_LOCATION_KEY)) {
                List<String> locationInQuery = userQueriesIterator.next().fetchParam(LocationSearchConstants.USER_QUERY_LOCATION_KEY);
                if(locationInQuery!=null || locationInQuery.size()>0) {
                    candidateLocationSet.addAll(locationInQuery);
                }
            }
        }

        LocationQuery aQuery = userQueries.get(0);
        candidateLocationSet.addAll(aQuery.fetchParam(LocationSearchConstants.USER_QUERY_POS_RESULT_COMMONS));
        candidateLocationSet.addAll(aQuery.fetchParam(LocationSearchConstants.USER_QUERY_POS_RESULT_PROPERS));
        //candidateLocationSet.addAll(aQuery.fetchParam(LocationSearchConstants.USER_QUERY_POS_RESULT_OTHERS));

        if(candidateLocations==null) {
            candidateLocations = new ArrayList<String>();
        }
        candidateLocations.addAll(candidateLocationSet);

        if(aQuery.containsParam(LocationSearchConstants.USER_QUERY_DISTANCE_KEY)) {
            isSpatial = true;
            if(aQuery.containsParam(LocationSearchConstants.USER_QUERY_DISTANCE_KEY)) {
                try {
                    distance = Integer.valueOf(aQuery.fetchParam(LocationSearchConstants.USER_QUERY_DISTANCE_KEY).get(0));
                    if(aQuery.containsParam(LocationSearchConstants.USER_QUERY_DISTANCE_SORT_KEY)) {
                        distanceSort = Integer.valueOf(aQuery.fetchParam(LocationSearchConstants.USER_QUERY_DISTANCE_SORT_KEY).get(0));
                    }
                } catch (NumberFormatException nfe) {
                    isSpatial = false;
                    distance = LocationSearchConstants.SPATIAL_SEARCH_DEFAULT_D;
                    message += " "+"Distance could not be parsed.";
                }
            }
        } else if(aQuery.containsParam(LocationSearchConstants.USER_QUERY_POS_RESULT_PREPS)) {
            isSpatial = true;
            distance = LocationSearchConstants.SPATIAL_SEARCH_DEFAULT_D;
            distanceSort = 1;
        }

        if(aQuery.containsParam(LocationSearchConstants.USER_QUERY_CURRENT_PAGE_NUMBER_KEY)) {
            currentPage = Integer.parseInt(aQuery.fetchParam(LocationSearchConstants.USER_QUERY_CURRENT_PAGE_NUMBER_KEY).get(0));
        }
        if(aQuery.containsParam(LocationSearchConstants.USER_QUERY_TIME_SORT_KEY)) {
            timeSort = Integer.valueOf(aQuery.fetchParam(LocationSearchConstants.USER_QUERY_TIME_SORT_KEY).get(0));
        }

    }


    /*private List<Place> findLocationsByNameWithHierarchy(List<String> locationNames, Map<String, List<? extends SolrLocationType>> filters) {
        List<Place> places = new ArrayList<Place>();
        Map<String, Place> allHierarchialPlaces = new HashMap<String, Place>();
        for(String name : locationNames) {
            List<Place> allPlaces = fetchAllPlacesFromTopHeirachy(name, filters);
            for(Place spatialPlace : allPlaces) {
                allHierarchialPlaces.put(String.valueOf(spatialPlace.getId()), spatialPlace);
            }
        }

        for(Map.Entry<String, Place> placeEntry : allHierarchialPlaces.entrySet()) {
            places.add(placeEntry.getValue());
        }
        return places;
    }*/


    /*private List<Place> fetchAllPlacesFromTopHeirachy(String locationName, Map<String, List<? extends SolrLocationType>> filters) {
        int startHierarchyLevel = LocationSearchConstants.HIERARCHY_CONTINENT_VALUE;
        List<Place> containedPlaces = fetchPlacesFromHierarchyLevel(locationName, startHierarchyLevel, filters);
        return containedPlaces;
    }*/

    /*private List<Place> fetchPlacesFromHierarchyLevel(String locationName, int beginAtHeirarchyLevel, Map<String, List<? extends SolrLocationType>> filters) {
        if(beginAtHeirarchyLevel < LocationSearchConstants.HIERARCHY_PLACE_VALUE) {
            return new ArrayList<Place>();
        }

        List<Place> fetchedPlaces = null;
        switch(beginAtHeirarchyLevel) {

            case LocationSearchConstants.HIERARCHY_CONTINENT_VALUE :
                List<Continent> asContinent = solrSeacher.findAllContinentsByName(locationName);
                if(asContinent!=null && asContinent.size()>0) {
                    for(Continent continent : asContinent) {
                        List<Country> countries = solrSeacher.findCountriesForContinent(continent);
                        if(countries!=null && countries.size()>0) {
                            for(Country country : countries) {
                                List<State> states = solrSeacher.findStatesForCountry(country);
                                if(states!=null && states.size()>0) {
                                    for(State state : states) {
                                        List<District> districts = solrSeacher.findDistrictsForState(state);
                                        if(districts!=null && districts.size()>0) {
                                            for(District district : districts) {
                                                List<Place> places = solrSeacher.findPlacesForDistrict(district, filters);
                                                if(places!=null && places.size()>0) {
                                                    fetchedPlaces.addAll(places);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                } else {
                    fetchedPlaces = fetchPlacesFromHierarchyLevel(locationName, LocationSearchConstants.HIERARCHY_COUNTRY_VALUE, filters);
                }

                break;

            case LocationSearchConstants.HIERARCHY_COUNTRY_VALUE :
                List<Country> asCountry = solrSeacher.findCountriestByName(locationName);
                if(asCountry!=null && asCountry.size()>0) {
                    for(Country country : asCountry) {
                        List<State> states = solrSeacher.findStatesForCountry(country);
                        if(states!=null && states.size()>0) {
                            for(State state : states) {
                                List<District> districts = solrSeacher.findDistrictsForState(state);
                                if(districts!=null && districts.size()>0) {
                                    for(District district : districts) {
                                        List<Place> places = solrSeacher.findPlacesForDistrict(district, filters);
                                        if(places!=null && places.size()>0) {
                                            fetchedPlaces.addAll(places);
                                        }
                                    }
                                }
                            }
                        }
                    }
                } else {
                    fetchedPlaces = fetchPlacesFromHierarchyLevel(locationName, LocationSearchConstants.HIERARCHY_STATE_VALUE, filters);
                }

                break;

            case LocationSearchConstants.HIERARCHY_STATE_VALUE :
                List<State> asState = solrSeacher.findStatesByName(locationName);
                if(asState!=null && asState.size()>0) {
                    for(State state : asState) {
                        List<District> districts = solrSeacher.findDistrictsForState(state);
                        if(districts!=null && districts.size()>0) {
                            for(District district : districts) {
                                List<Place> places = solrSeacher.findPlacesForDistrict(district, filters);
                                if(places!=null && places.size()>0) {
                                    fetchedPlaces.addAll(places);
                                }
                            }
                        }
                    }
                } else {
                    fetchedPlaces = fetchPlacesFromHierarchyLevel(locationName, LocationSearchConstants.HIERARCHY_DISTRICT_VALUE, filters);
                }

                break;

            case LocationSearchConstants.HIERARCHY_DISTRICT_VALUE :
                List<District> asDistrict = solrSeacher.findDistrictsByName(locationName);
                if(asDistrict!=null && asDistrict.size()>0) {
                    for(District district : asDistrict) {
                        List<Place> places = solrSeacher.findPlacesForDistrict(district, filters);
                        if(places!=null && places.size()>0) {
                            fetchedPlaces.addAll(places);
                        }
                    }
                } else {
                    fetchedPlaces = fetchPlacesFromHierarchyLevel(locationName, LocationSearchConstants.HIERARCHY_PLACE_VALUE, filters);
                }

                break;

            case LocationSearchConstants.HIERARCHY_PLACE_VALUE :
                fetchedPlaces = solrSeacher.findLocationByName(locationName, filters);
                break;

            default:

                break;
        }

        return fetchedPlaces;
    }
*/

}

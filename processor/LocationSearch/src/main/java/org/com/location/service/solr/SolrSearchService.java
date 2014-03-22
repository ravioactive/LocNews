package org.com.location.service.solr;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.com.location.LocationSearchConstants;
import org.com.location.model.solr.LocationQuery;
import org.com.location.model.solr.SolrLocationType;
import org.com.location.model.solr.SolrSchemaType;
import org.com.location.model.solr.schema.*;
import org.com.location.service.LocationService;
import org.com.location.service.location.PlacePopulationComparator;

import java.util.*;

public class SolrSearchService implements LocationService {
    private String SOLR_SERVER = null;

    private HttpSolrServer SOLR_CONTINENT_CORE = null;
    private HttpSolrServer SOLR_COUNTRY_CORE = null;
    private HttpSolrServer SOLR_STATE_CORE = null;
    private HttpSolrServer SOLR_DISTRICT_CORE = null;
    private HttpSolrServer SOLR_PLACES_CORE = null;

    private HttpSolrServer SOLR_FEATURE_CORE = null;
    private HttpSolrServer SOLR_LANGUAGE_CORE = null;
    private HttpSolrServer SOLR_TIMEZONE_CORE = null;

    private HttpSolrServer SOLR_WIKI_CORE = null;

    public String getWikiNewsQueryTime() {
        return wikiNewsQueryTime;
    }

    public void setWikiNewsQueryTime(String wikiNewsQueryTime) {
        this.wikiNewsQueryTime = wikiNewsQueryTime;
    }

    private String wikiNewsQueryTime;

    public String getWikiNewsNumRetrieved() {
        return wikiNewsNumRetrieved;
    }

    public void setWikiNewsNumRetrieved(String wikiNewsNumRetrieved) {
        this.wikiNewsNumRetrieved = wikiNewsNumRetrieved;
    }

    private String wikiNewsNumRetrieved;

    /*int continentTopK = LocationSearchConstants.GLOBAL_TOP_K_LESS;
    int countryTopK = LocationSearchConstants.GLOBAL_TOP_K_LESS;
    int stateTopK = LocationSearchConstants.GLOBAL_TOP_K_LESS;
    int districtTopK = LocationSearchConstants.GLOBAL_TOP_K_LESS;
    int placesTopK = LocationSearchConstants.GLOBAL_TOP_K_LESS;
    int featuresTopK = LocationSearchConstants.GLOBAL_TOP_K_LESS;
    int languagesTopK = LocationSearchConstants.GLOBAL_TOP_K_LESS;
    int timezoneTopK = LocationSearchConstants.GLOBAL_TOP_K_LESS;
    int wikipediaTopK = LocationSearchConstants.GLOBAL_TOP_K_LESS;
    int spatialTopK = LocationSearchConstants.GLOBAL_TOP_K_LESS;*/

    public SolrSearchService(String solrServerBaseRL) throws Exception {
        this.SOLR_SERVER = solrServerBaseRL;
        initializeAllCores();
    }

    private void initializeAllCores() throws Exception {
        if(SOLR_SERVER==null) {
            return;
        }

        try {
            SOLR_CONTINENT_CORE = new HttpSolrServer(SOLR_SERVER+LocationSearchConstants.CONTINENT_CORE_NAME);
            SOLR_COUNTRY_CORE = new HttpSolrServer(SOLR_SERVER+LocationSearchConstants.COUNTRY_CORE_NAME);
            SOLR_STATE_CORE = new HttpSolrServer(SOLR_SERVER+LocationSearchConstants.STATE_CORE_NAME);
            SOLR_DISTRICT_CORE = new HttpSolrServer(SOLR_SERVER+LocationSearchConstants.DISTRICT_CORE_NAME);
            SOLR_PLACES_CORE = new HttpSolrServer(SOLR_SERVER+LocationSearchConstants.PLACES_CORE_NAME);

            SOLR_FEATURE_CORE = new HttpSolrServer(SOLR_SERVER+LocationSearchConstants.FEATURE_CORE_NAME);
            SOLR_LANGUAGE_CORE = new HttpSolrServer(SOLR_SERVER+LocationSearchConstants.LANGUAGE_CORE_NAME);
            SOLR_TIMEZONE_CORE = new HttpSolrServer(SOLR_SERVER+LocationSearchConstants.TIMEZONE_CORE_NAME);

            SOLR_WIKI_CORE = new HttpSolrServer(SOLR_SERVER+LocationSearchConstants.WIKINEWS_CORE_NAME);
        } catch(Exception e) {
            throw e;
        }
    }

    private SolrQuery getFeatureQuery() {
        SolrQuery featureQuery = new SolrQuery();
        return featureQuery;
    }

    private SolrQuery getLanguageQuery() {
        SolrQuery languageQuery = new SolrQuery();
        return languageQuery;
    }


    private SolrQuery getTimezoneQuery() {
        SolrQuery timezoneQuery = new SolrQuery();
        return timezoneQuery;
    }


    private SolrQuery getWikiNewsQuery() {
        SolrQuery wikiNewsQuery = new SolrQuery();
        return wikiNewsQuery;
    }

    private SolrQuery getPlaceQuery() {
        SolrQuery placeQuery = new SolrQuery();
        return placeQuery;
    }

    private SolrQuery getDistrictQuery() {
        SolrQuery districtQuery = new SolrQuery();
        return districtQuery;
    }

    private SolrQuery getStateQuery() {
        SolrQuery stateQuery = new SolrQuery();
        return stateQuery;
    }

    private SolrQuery getCountryQuery() {
        SolrQuery countryQuery = new SolrQuery();
        return countryQuery;
    }

    private SolrQuery getContinentQuery() {
        SolrQuery continentQuery = new SolrQuery();
        return continentQuery;
    }


    private QueryResponse searchSolrCore(HttpSolrServer core, SolrQuery coreQuery) {
        QueryResponse response = null;
        try {
            response = core.query(coreQuery);
        } catch (SolrServerException e) {
            e.printStackTrace();
        }

        return response;
    }


    public List<Feature> findFeatures(List<String> featureNames) {
        List<Feature> featuresFound = new ArrayList<Feature>();
        if(featureNames==null || featureNames.size()<1) {
            return featuresFound;
        }

        SolrQuery featureQuery = getFeatureQuery();
        StringBuilder featureQueryBuilder = new StringBuilder();
        featureQueryBuilder.append(LocationSearchConstants.FEATURE_ALL_FIELD).append(":(");
        for(String featureString : featureNames) {
            if(featureString!=null && !featureString.isEmpty()) {
                featureQueryBuilder.append(featureString);
            }
        }
        featureQueryBuilder.append(")");

        featureQuery.setQuery(featureQueryBuilder.toString());

        QueryResponse featureResponse = searchSolrCore(SOLR_FEATURE_CORE, featureQuery);
        if(featureResponse!=null) {
            featuresFound = featureResponse.getBeans(Feature.class);
        }

        return featuresFound;
    }



    public List<Language> findLanguages(List<String> languageNames) {
        List<Language> languagesFound = new ArrayList<Language>();

        if(languageNames==null || languageNames.size()<1) {
            return languagesFound;
        }

        SolrQuery languageQuery = getLanguageQuery();
        StringBuilder languageQueryBuilder = new StringBuilder();
        languageQueryBuilder.append(LocationSearchConstants.LANG_NAME_FIELD).append(":(");
        for(String languageName : languageNames) {
            if(languageName!=null && !languageName.isEmpty()) {
                languageQueryBuilder.append(languageName);
            }
        }
        languageQueryBuilder.append("(");

        languageQuery.setQuery(languageQueryBuilder.toString());

        QueryResponse languageResponse = searchSolrCore(SOLR_LANGUAGE_CORE, languageQuery);
        if(languageResponse!=null) {
            languagesFound = languageResponse.getBeans(Language.class);
        }

        return languagesFound;
    }



    public List<Timezone> findTimezones(List<String> timeZonenames) {
        List<Timezone> timezonesFound = new ArrayList<Timezone>();

        if(timeZonenames==null || timeZonenames.size()<1) {
            return timezonesFound;
        }

        SolrQuery timezoneQuery = getTimezoneQuery();
        StringBuilder timezoneQueryBuilder = new StringBuilder();
        timezoneQueryBuilder.append(LocationSearchConstants.TIMEZONE_NAME_FIELD).append(":(");
        for(String timezoneName : timeZonenames) {
            if(timezoneName!=null && !timezoneName.isEmpty()) {
                timezoneQueryBuilder.append(timezoneName);
            }
        }
        timezoneQueryBuilder.append("(");

        timezoneQuery.setQuery(timezoneQueryBuilder.toString());

        QueryResponse timezoneResponse = searchSolrCore(SOLR_TIMEZONE_CORE, timezoneQuery);
        if(timezoneResponse!=null) {
            timezonesFound = timezoneResponse.getBeans(Timezone.class);
        }

        return timezonesFound;
    }


    public List<WikiNews> findWikiNewsFor(String userQueryString, String locationString, int timeSort, int beginAt) {
        List<WikiNews> wikiNewsFound = new ArrayList<WikiNews>();

        if((userQueryString==null || userQueryString.isEmpty()) || (locationString==null) || locationString.isEmpty()) {
            return wikiNewsFound;
        }

        SolrQuery wikiNewsQuery = getWikiNewsQuery();
        StringBuilder wikiNewsQueryBuilder = new StringBuilder();
        if(userQueryString!=null && !userQueryString.isEmpty()) {
            wikiNewsQueryBuilder.append(LocationSearchConstants.WIKINEWS_TITLETEXT_FIELD).append(":(");
            wikiNewsQueryBuilder.append(userQueryString).append(")");
        }

        if((locationString!=null) && !locationString.isEmpty()) {
            if(wikiNewsQueryBuilder.length()>0) {
                wikiNewsQueryBuilder.append(" AND ");
            }

           wikiNewsQueryBuilder.append(LocationSearchConstants.WIKINEWS_ALLTEXT_FIELD).append(":(");
           wikiNewsQueryBuilder.append(locationString).append(" ").append(userQueryString).append(")");
        }

        wikiNewsQuery.setQuery(wikiNewsQueryBuilder.toString());
        wikiNewsQuery.set(LocationSearchConstants._RESULT_COUNT, LocationSearchConstants._RESULT_COUNT_VALUE);
        wikiNewsQuery.set(LocationSearchConstants._RESULT_START, String.valueOf(beginAt));
        //wikiNewsQuery.addSortField(LocationSearchConstants.WIKINEWS_TIMESTAMP_FIELD, SolrQuery.ORDER.desc);

        QueryResponse wikiNewsResponse = searchSolrCore(SOLR_WIKI_CORE, wikiNewsQuery);
        if(wikiNewsResponse!=null) {
            wikiNewsFound = wikiNewsResponse.getBeans(WikiNews.class);

            wikiNewsNumRetrieved = String.valueOf(wikiNewsResponse.getResults().getNumFound());
            wikiNewsQueryTime = String.valueOf(wikiNewsResponse.getElapsedTime());
        }
        return wikiNewsFound;
    }


    public Continent findContinentById(String locationId) {
        SolrQuery continentQuery = getContinentQuery();
        StringBuilder continentQueryBuilder = new StringBuilder();
        continentQueryBuilder.append(LocationSearchConstants.CONTINENT_ID_FIELD).append(":").append(locationId);
        continentQuery.setQuery(continentQueryBuilder.toString());

        QueryResponse continentResponse = searchSolrCore(SOLR_CONTINENT_CORE, continentQuery);
        List<Continent> continents = new ArrayList<Continent>();
        if(continentResponse!=null) {
            continents = continentResponse.getBeans(Continent.class);
        }

        Continent continent = null;
        if(continents!=null && continents.size()>0) {
            continent = continents.get(0);
        }

        return continent;
    }

    public Country findCountryById(String locationId) {
        SolrQuery countryQuery = getCountryQuery();
        StringBuilder countryQueryBuilder = new StringBuilder();
        countryQueryBuilder.append(LocationSearchConstants.COUNTRY_ID_FIELD).append(":").append(locationId);
        countryQuery.setQuery(countryQueryBuilder.toString());

        QueryResponse countryResponse = searchSolrCore(SOLR_COUNTRY_CORE, countryQuery);
        List<Country> countries = new ArrayList<Country>();
        if(countryResponse!=null) {
            countries = countryResponse.getBeans(Country.class);
        }

        Country country = null;
        if(countries!=null && countries.size()>0) {
            country = countries.get(0);
        }

        return country;
    }

    public State findStateById(String locationId) {
        SolrQuery stateQuery = getStateQuery();
        StringBuilder stateQueryBuilder = new StringBuilder();
        stateQueryBuilder.append(LocationSearchConstants.STATE_ID_FIELD).append(":").append(locationId);
        stateQuery.setQuery(stateQueryBuilder.toString());

        QueryResponse stateResponse = searchSolrCore(SOLR_STATE_CORE, stateQuery);
        List<State> states = new ArrayList<State>();
        if(stateResponse!=null) {
            states = stateResponse.getBeans(State.class);
        }

        State state = null;
        if(states!=null && states.size()>0) {
            state = states.get(0);
        }

        return state;
    }



    public District findDistrictById(String locationId) {
        SolrQuery districtQuery = getDistrictQuery();
        StringBuilder districtQueryBuilder = new StringBuilder();
        districtQueryBuilder.append(LocationSearchConstants.DISTRICT_ID_FIELD).append(":").append(locationId);
        districtQuery.setQuery(districtQueryBuilder.toString());

        QueryResponse districtResponse = searchSolrCore(SOLR_DISTRICT_CORE, districtQuery);
        List<District> districts = new ArrayList<District>();
        if(districtResponse!=null) {
            districts = districtResponse.getBeans(District.class);
        }

        District district = null;
        if(districts!=null && districts.size()>0) {
            district = districts.get(0);
        }

        return district;
    }


    public Place findLocationById(String locationId) {
        SolrQuery placeQuery = getPlaceQuery();
        StringBuilder placeQueryBuilder = new StringBuilder();
        placeQueryBuilder.append(LocationSearchConstants.PLACE_ID_FIELD).append(":").append(locationId);
        placeQuery.setQuery(placeQueryBuilder.toString());

        QueryResponse placeResponse = searchSolrCore(SOLR_PLACES_CORE, placeQuery);

        List<Place> places = new ArrayList<Place>();
        if(placeResponse!=null) {
            places = placeResponse.getBeans(Place.class);
        }

        Place place = null;
        if(places!=null && places.size()>0) {
            place = places.get(0);
        }

        return place;
    }



    public List<Continent> findAllContinentsByName(String locationName) {
        SolrQuery continentQuery = getContinentQuery();
        StringBuilder continentQueryBuilder = new StringBuilder();
        continentQueryBuilder.append(LocationSearchConstants.CONTINENT_NAME_FIELD).append(":").append(locationName);
        continentQuery.setQuery(continentQueryBuilder.toString());

        QueryResponse continentResponse = searchSolrCore(SOLR_CONTINENT_CORE, continentQuery);

        List<Continent> continents = new ArrayList<Continent>();
        if(continentResponse!=null) {
            continents = continentResponse.getBeans(Continent.class);
        }

        return continents;
    }

    public List<Country> findAllCountriesByName(String locationName, Map<String, List<? extends SolrSchemaType>> filters) {
        SolrQuery countryQuery = getCountryQuery();
        StringBuilder countryQueryBuilder = new StringBuilder();
        countryQueryBuilder.append(LocationSearchConstants.COUNTRY_NAME_FIELD).append(":").append(locationName);

        String languageFilterQuery = addFiltersToLocationQueryStr(filters, LocationSearchConstants.LANGUAGE_CORE_NAME);
        if(languageFilterQuery!=null && !languageFilterQuery.isEmpty()) {
            countryQueryBuilder.append(" AND ");
            countryQueryBuilder.append(languageFilterQuery);
        }
        countryQuery.setQuery(countryQueryBuilder.toString());

        QueryResponse countryResponse = searchSolrCore(SOLR_COUNTRY_CORE, countryQuery);
        List<Country> countries = new ArrayList<Country>();
        if(countryResponse!=null) {
            countries = countryResponse.getBeans(Country.class);
        }

        return countries;
    }

    public List<State> findAllStatesByName(String locationName) {
        SolrQuery stateQuery = getStateQuery();
        StringBuilder stateQueryBuilder = new StringBuilder();
        stateQueryBuilder.append(LocationSearchConstants.STATE_NAME_FIELD).append(":").append(locationName);
        stateQuery.setQuery(stateQueryBuilder.toString());

        QueryResponse stateResponse = searchSolrCore(SOLR_STATE_CORE, stateQuery);
        List<State> states = new ArrayList<State>();
        if(stateResponse!=null) {
            states = stateResponse.getBeans(State.class);
        }

        return states;
    }

    public List<District> findAllDistrictsByName(String locationName) {
        SolrQuery districtQuery = getDistrictQuery();
        StringBuilder districtQueryBuilder = new StringBuilder();
        districtQueryBuilder.append(LocationSearchConstants.DISTRICT_NAME_FIELD).append(":").append(locationName);
        districtQuery.setQuery(districtQueryBuilder.toString());

        QueryResponse districtResponse = searchSolrCore(SOLR_DISTRICT_CORE, districtQuery);
        List<District> districts = new ArrayList<District>();
        if(districtResponse!=null) {
            districts = districtResponse.getBeans(District.class);
        }

        return districts;
    }

    public List<Place> findAllPlacesByName(String locationName, Map<String, List<? extends SolrSchemaType>> filters) {
        SolrQuery placeQuery = getPlaceQuery();
        StringBuilder placeQueryBuilder = new StringBuilder();
        placeQueryBuilder.append(LocationSearchConstants.PLACE_ALLNAMES_FIELD).append(":").append(locationName);

        String featureFilterQuery = addFiltersToLocationQueryStr(filters, LocationSearchConstants.FEATURE_CORE_NAME);
        if(featureFilterQuery!=null && !featureFilterQuery.isEmpty()) {
            placeQueryBuilder.append(" AND ");
            placeQueryBuilder.append(featureFilterQuery);
        }

        String timezoneFilterQuery = addFiltersToLocationQueryStr(filters, LocationSearchConstants.TIMEZONE_CORE_NAME);
        if(timezoneFilterQuery!=null && !timezoneFilterQuery.isEmpty()) {
            placeQueryBuilder.append(" AND ");
            placeQueryBuilder.append(timezoneFilterQuery);
        }
        System.out.println(placeQueryBuilder.toString());
        placeQuery.setQuery(placeQueryBuilder.toString());
        placeQuery.addSortField("population", SolrQuery.ORDER.desc);

        QueryResponse placeResponse = searchSolrCore(SOLR_PLACES_CORE, placeQuery);
        List<Place> places = new ArrayList<Place>();
        if(placeResponse!=null) {
            places = placeResponse.getBeans(Place.class);
        }

        return places;
    }

    private String addFiltersToLocationQueryStr(Map<String, List<? extends SolrSchemaType>> filters, String key) {
        StringBuilder filterQueryStrBuilder = new StringBuilder();

        if(filters!=null && filters.size()>0) {
            if(key.equalsIgnoreCase(LocationSearchConstants.FEATURE_CORE_NAME)) {
                if(filters.containsKey(LocationSearchConstants.FEATURE_CORE_NAME)) {
                    List<Feature> features = (List<Feature>) filters.get(LocationSearchConstants.FEATURE_CORE_NAME);
                    if(features!=null && features!=null) {
                        StringBuilder enclosingFeatureTypeBuilder = new StringBuilder();
                        enclosingFeatureTypeBuilder.append(LocationSearchConstants.PLACE_FEATURE_CLASS_FIELD+":(");

                        StringBuilder enclosingFeatureCodeBuilder = new StringBuilder();
                        enclosingFeatureCodeBuilder.append(LocationSearchConstants.PLACE_FEATURE_CODE_FIELD+":(");

                        StringBuilder featureTypeFilterBuilder = new StringBuilder();
                        StringBuilder featureCodeFilterBuilder = new StringBuilder();

                        Iterator<Feature> featureIterator = features.iterator();
                        while(featureIterator.hasNext()) {
                            Feature feature = featureIterator.next();
                            if(feature.getFeaturetype()!=null && !feature.getFeaturetype().isEmpty()) {
                                featureTypeFilterBuilder.append(feature.getFeaturetype());
                                if(featureIterator.hasNext()) {
                                    featureTypeFilterBuilder.append(" OR ");
                                }
                            }
                            if(feature.getFeaturecode()!=null && !feature.getFeaturecode().isEmpty()) {
                                featureCodeFilterBuilder.append(feature.getFeaturecode());
                                if(featureIterator.hasNext()) {
                                    featureCodeFilterBuilder.append(" OR ");
                                }
                            }
                        }

                        if(featureTypeFilterBuilder.length()>0) {
                            enclosingFeatureTypeBuilder.append(featureTypeFilterBuilder.toString());
                            enclosingFeatureTypeBuilder.append(")");
                            filterQueryStrBuilder.append(enclosingFeatureTypeBuilder.toString());
                        }

                        if(featureCodeFilterBuilder.length()>0) {
                            enclosingFeatureCodeBuilder.append(featureCodeFilterBuilder.toString());
                            enclosingFeatureCodeBuilder.append(")");

                            filterQueryStrBuilder.append(" AND ");
                            filterQueryStrBuilder.append(enclosingFeatureCodeBuilder.toString());
                        }
                    }
                }
            }
            else if(key.equalsIgnoreCase(LocationSearchConstants.TIMEZONE_CORE_NAME)) {
                if(filters.containsKey(LocationSearchConstants.TIMEZONE_CORE_NAME)) {
                    List<Timezone> timezones = (List<Timezone>) filters.get(LocationSearchConstants.TIMEZONE_CORE_NAME);
                    if(timezones!=null & timezones.size()>0) {
                        StringBuilder enclosingTimezoneBuilder = new StringBuilder();
                        enclosingTimezoneBuilder.append(LocationSearchConstants.PLACE_TIMEZONE_FIELD).append(":(");

                        StringBuilder timezoneBuilder = new StringBuilder();

                        Iterator<Timezone> timezoneIterator = timezones.iterator();
                        while(timezoneIterator.hasNext()) {
                            Timezone timezone = timezoneIterator.next();
                            if(timezone.getTimezone()!=null && !timezone.getTimezone().isEmpty()) {
                                timezoneBuilder.append(timezone.getTimezone());
                                if(timezoneIterator.hasNext()) {
                                    timezoneBuilder.append(" OR ");
                                }
                            }
                        }

                        if(timezoneBuilder.length()>0) {
                            enclosingTimezoneBuilder.append(timezoneBuilder.toString());
                            enclosingTimezoneBuilder.append(")");
                            filterQueryStrBuilder.append(enclosingTimezoneBuilder.toString());
                        }
                    }
                }
            }

            else if(key.equalsIgnoreCase(LocationSearchConstants.LANGUAGE_CORE_NAME)) {
                if(filters.containsKey(LocationSearchConstants.LANGUAGE_CORE_NAME)) {
                    List<Language> languages = (List<Language>) filters.get(LocationSearchConstants.LANGUAGE_CORE_NAME);
                    if(languages!=null && languages.size()>0) {
                        StringBuilder enclosingLanguageBuilder = new StringBuilder();
                        enclosingLanguageBuilder.append(LocationSearchConstants.COUNTRY_LANGUAGE_FIELD).append(":(");
                        StringBuilder languageBuilder = new StringBuilder();

                        Iterator<Language> languageIterator = languages.iterator();

                        while(languageIterator.hasNext()){
                            Language language = languageIterator.next();
                            if(language.getLangcode3()!=null && !language.getLangcode3().isEmpty()) {
                                languageBuilder.append(language.getLangcode3());
                                if(languageIterator.hasNext()) {
                                    languageBuilder.append(" OR ");
                                }
                            }
                        }

                        if(languageBuilder.length()>0) {
                            enclosingLanguageBuilder.append(languageBuilder.toString());
                            enclosingLanguageBuilder.append(")");
                            filterQueryStrBuilder.append(enclosingLanguageBuilder.toString());
                        }
                    }
                }
            }

        }

        return filterQueryStrBuilder.toString();
    }

    public String completeName(Place place) {
        StringBuilder placeNameBuilder = new StringBuilder();
        if(place==null) {
            return placeNameBuilder.toString();
        }

        placeNameBuilder.append(place.getNameascii());

        District district = findDistrictForPlace(place);
        if(district!=null) {
            placeNameBuilder.append(" ");
            placeNameBuilder.append(completeName(district));
        } else {
            State state = findStateForPlace(place);
            if(state!=null) {
                placeNameBuilder.append(" ");
                placeNameBuilder.append(completeName(state));
            } else {
                Country country = findCountryForPlace(place);
                if(country!=null) {
                    placeNameBuilder.append(" ");
                    placeNameBuilder.append(completeName(country));
                }
            }
        }

        return placeNameBuilder.toString();
    }

    public String impression(Place place) {
        StringBuilder placeImpression = new StringBuilder();

        if(place==null) {
            return placeImpression.toString();
        }

        placeImpression.append(place.getId());
        placeImpression.append("|");
        placeImpression.append(completeName(place));
        placeImpression.append("|");
        placeImpression.append(place.getLocationType());
        return placeImpression.toString();
    }

    public String completeName(Continent continent) {
        String continentName = continent.getCntname();
        return continentName;
    }

    public String impression(Continent continent) {
        StringBuilder continentImpression = new StringBuilder();

        if(continent==null) {
            return continentImpression.toString();
        }

        continentImpression.append(continent.getId());
        continentImpression.append("|");
        continentImpression.append(completeName(continent));
        continentImpression.append("|");
        continentImpression.append(continent.getLocationType());
        return continentImpression.toString();
    }

    public String completeName(Country country) {
        StringBuilder countryNameBuilder = new StringBuilder();
        if(country==null) {
            return countryNameBuilder.toString();
        }

        countryNameBuilder.append(country.getCntryname());

        Continent continent = findContinentForCountry(country);
        if(continent!=null) {
            countryNameBuilder.append(" ");
            countryNameBuilder.append(completeName(continent));
        }

        return countryNameBuilder.toString();
    }

    public String impression(Country country) {
        StringBuilder countryImpression = new StringBuilder();

        if(country==null) {
            return countryImpression.toString();
        }

        countryImpression.append(country.getId());
        countryImpression.append("|");
        countryImpression.append(completeName(country));
        countryImpression.append("|");
        countryImpression.append(country.getLocationType());
        return countryImpression.toString();
    }

    public String completeName(State state) {
        StringBuilder stateNameBuilder = new StringBuilder();
        if(state==null) {
            return stateNameBuilder.toString();
        }

        stateNameBuilder.append(state.getStatenameascii());
        Country country = findCountryForState(state);
        if(country!=null) {
            stateNameBuilder.append(" ");
            stateNameBuilder.append(completeName(country));
        }

        return stateNameBuilder.toString();
    }

    public String impression(State state) {
        StringBuilder stateImpression = new StringBuilder();

        if(state==null) {
            return stateImpression.toString();
        }

        stateImpression.append(state.getId());
        stateImpression.append("|");
        stateImpression.append(completeName(state));
        stateImpression.append("|");
        stateImpression.append(state.getLocationType());
        return stateImpression.toString();
    }

    public String completeName(District district) {
        StringBuilder districtNameBuilder = new StringBuilder();
        if(district==null) {
            return districtNameBuilder.toString();
        }

        districtNameBuilder.append(district.getCountynameascii());

        State state = findStateForDistrict(district);
        if(state!=null) {
            districtNameBuilder.append(" ");
            districtNameBuilder.append(completeName(state));
        }

        return districtNameBuilder.toString();
    }

    public String impression(District district) {
        StringBuilder districtImpression = new StringBuilder();
        if(district==null) {
            return districtImpression.toString();
        }

        districtImpression.append(district.getId());
        districtImpression.append("|");
        districtImpression.append(completeName(district));
        districtImpression.append("|");
        districtImpression.append(district.getLocationType());
        return districtImpression.toString();
    }

    public Continent findContinentForCountry(Country country) {
        if(country==null) {
            return null;
        }

        SolrQuery continentQuery = getContinentQuery();
        String continentQueryStr = LocationSearchConstants.CONTINENT_NAME_FIELD+":"+country.getCntrycontinent();
        continentQuery.setQuery(continentQueryStr);

        QueryResponse continentResponse = searchSolrCore(SOLR_CONTINENT_CORE, continentQuery);
        List<Continent> continents = new ArrayList<Continent>();
        if(continentResponse!=null) {
            continents = continentResponse.getBeans(Continent.class);
        }

        Continent continent = null;
        if(continents!=null && continents.size()>0) {
            continent = continents.get(0);
        }

        return continent;
    }


    public Country findCountryForState(State state) {
        if(state == null) {
            return null;
        }

        SolrQuery countryQuery = getCountryQuery();
        String countryQueryStr = LocationSearchConstants.COUNTRY_CODE_FIELD+":"+state.getStatecountry();
        countryQuery.setQuery(countryQueryStr);

        QueryResponse countryResponse = searchSolrCore(SOLR_COUNTRY_CORE, countryQuery);
        List<Country> countries = new ArrayList<Country>();
        if(countryResponse!=null) {
            countries = countryResponse.getBeans(Country.class);
        }

        Country country = null;
        if(countries!=null && countries.size()>0) {
            country = countries.get(0);
        }

        return country;
    }



    public State findStateForDistrict(District district) {
        if(district==null) {
            return null;
        }

        SolrQuery stateQuery = getStateQuery();
        String stateQueryStr = LocationSearchConstants.STATE_CODE_FIELD+":"+district.getCountystate();
        stateQuery.setQuery(stateQueryStr);

        QueryResponse stateResponse = searchSolrCore(SOLR_STATE_CORE, stateQuery);
        List<State> states = new ArrayList<State>();
        if(stateResponse!=null) {
            states = stateResponse.getBeans(State.class);
        }

        State state = null;
        if(states!=null && states.size()>0) {
            state = states.get(0);
        }

        return state;
    }

    public District findDistrictForPlace(Place place) {
        if(place==null) {
            return null;
        }

        SolrQuery districtQuery = getDistrictQuery();
        String districtCode = place.getCountrycode()+"."+place.getAdmin1code()+"."+place.getAdmin2code();
        String districtQueryStr = LocationSearchConstants.DISTRICT_CODE_COMBINED_FIELD+":"+districtCode;
        districtQuery.setQuery(districtQueryStr);

        QueryResponse districtResponse = searchSolrCore(SOLR_DISTRICT_CORE, districtQuery);
        List<District> districts = new ArrayList<District>();
        if(districtResponse!=null) {
            districts = districtResponse.getBeans(District.class);
        }

        District district = null;
        if(districts!=null && districts.size()>0) {
            district = districts.get(0);
        }

        return district;
    }

    public State findStateForPlace(Place place) {
        if(place==null) {
            return null;
        }

        SolrQuery stateQuery = getStateQuery();
        String stateCode = place.getCountrycode()+"."+place.getAdmin1code();
        String stateQueryStr = LocationSearchConstants.STATE_CODE_COMBINED_FIELD+":"+stateCode;
        stateQuery.setQuery(stateQueryStr);

        QueryResponse stateResponse = searchSolrCore(SOLR_STATE_CORE, stateQuery);
        List<State> states = new ArrayList<State>();
        if(stateResponse!=null) {
            states = stateResponse.getBeans(State.class);
        }

        State state = null;
        if(states!=null && states.size()>0) {
            state = states.get(0);
        }

        return state;
    }

    public Country findCountryForPlace(Place place) {
        if(place==null) {
            return null;
        }

        SolrQuery countryQuery = getCountryQuery();
        String countryQueryStr = "";
        if(place.getCountrycode()!=null && !place.getCountrycode().isEmpty()) {
            countryQueryStr = LocationSearchConstants.COUNTRY_CODE_FIELD+":"+place.getCountrycode();
            countryQuery.setQuery(countryQueryStr);
        }

        QueryResponse countryResponse = searchSolrCore(SOLR_COUNTRY_CORE, countryQuery);
        List<Country> countries = new ArrayList<Country>();
        if(countryResponse!=null) {
            countries = countryResponse.getBeans(Country.class);
        }

        Country country = null;
        if(countries!=null && countries.size()>0) {
            country = countries.get(0);
        }

        return country;
    }

    public SolrLocationType findParent(SolrLocationType location, int depth) {
        SolrLocationType parent = null;

        switch (location.getLocationType()) {
            case LocationSearchConstants.HIERARCHY_CONTINENT_VALUE:
                Continent continent = (Continent) location;
                parent = continent;
                break;

            case LocationSearchConstants.HIERARCHY_COUNTRY_VALUE:
                Country countryForState = (Country) location;
                Continent countryParent = findContinentForCountry(countryForState);
                if((depth-1)>0) {
                    parent = findParent(countryParent, depth-1);
                } else {
                    parent = countryParent;
                }
                break;

            case LocationSearchConstants.HIERARCHY_STATE_VALUE:
                State stateForDistrict = (State) location;
                Country stateParent = findCountryForState(stateForDistrict);
                if((depth-1)>0) {
                    parent = findParent(stateParent, depth-1);
                } else {
                    parent = stateParent;
                }
                break;

            case LocationSearchConstants.HIERARCHY_DISTRICT_VALUE:
                District districtForPlace = (District) location;
                State districtParent = findStateForDistrict(districtForPlace);
                if((depth-1)>0) {
                    parent = findParent(districtParent, depth-1);
                } else {
                    parent = districtParent;
                }
                break;

            case LocationSearchConstants.HIERARCHY_PLACE_VALUE:
                Place place = (Place) location;
                District placeParent = findDistrictForPlace(place);
                if((depth-1)<0) {
                    parent = findParent(placeParent, depth-1);
                } else {
                    parent = placeParent;
                }
                break;
        }
        return parent;
    }

    public List<Country> findCountriesForContinent(Continent continent, Map<String, List<? extends SolrSchemaType>> filters) {
        List<Country> countries = new ArrayList<Country>();

        if(continent==null) {
            return countries;
        }

        SolrQuery countryQuery = getCountryQuery();
        StringBuilder countryQueryBuilder = new StringBuilder();
        countryQueryBuilder.append(LocationSearchConstants.COUNTRY_CONTINENT_FIELD).append(":").append(continent.getCntcode());

        String languageFilterQuery = addFiltersToLocationQueryStr(filters, LocationSearchConstants.LANGUAGE_CORE_NAME);
        if(languageFilterQuery!=null && !languageFilterQuery.isEmpty()) {
            countryQueryBuilder.append(" AND ");
            countryQueryBuilder.append(languageFilterQuery);
        }
        countryQuery.setQuery(countryQueryBuilder.toString());

        QueryResponse countryResponse = searchSolrCore(SOLR_COUNTRY_CORE, countryQuery);
        if(countryResponse!=null) {
            countries = countryResponse.getBeans(Country.class);
        }

        return countries;
    }

    public List<State> findStatesForCountry(Country country) {
        List<State> states = new ArrayList<State>();

        if(country==null) {
            return states;
        }

        SolrQuery stateQuery = getStateQuery();
        String stateQueryStr = LocationSearchConstants.STATE_COUNTRY_FIELD+":"+country.getCntrycode();
        stateQuery.setQuery(stateQueryStr);

        QueryResponse stateResponse = searchSolrCore(SOLR_STATE_CORE, stateQuery);
        if(stateResponse!=null) {
            states = stateResponse.getBeans(State.class);
        }

        return states;
    }

    public List<District> findDistrictsForState(State state) {
        List<District> districts = new ArrayList<District>();

        if(state==null) {
            return districts;
        }

        SolrQuery districtQuery = getDistrictQuery();
        String districtQueryStr = LocationSearchConstants.DISTRICT_STATE_FIELD+":"+state.getStatecode();
        districtQuery.setQuery(districtQueryStr);

        QueryResponse districtResponse = searchSolrCore(SOLR_DISTRICT_CORE, districtQuery);
        if(districtResponse!=null) {
            districts = districtResponse.getBeans(District.class);
        }
        return districts;
    }

    public List<Place> findPlacesForDistrict(District district, Map<String, List<? extends SolrSchemaType>> filters) {
        List<Place> places = new ArrayList<Place>();

        if(district==null) {
            return places;
        }

        SolrQuery placeQuery = getPlaceQuery();
        StringBuilder placeQueryBuilder = new StringBuilder();
        placeQueryBuilder.append(LocationSearchConstants.PLACE_ADMIN_2_CODE_FIELD).append(":").append(district.getCountycode());

        String featureFilterQuery = addFiltersToLocationQueryStr(filters, LocationSearchConstants.FEATURE_CORE_NAME);
        if(featureFilterQuery!=null && !featureFilterQuery.isEmpty()) {
            placeQueryBuilder.append(" AND ");
            placeQueryBuilder.append(featureFilterQuery);
        }

        String timezoneFilterQuery = addFiltersToLocationQueryStr(filters, LocationSearchConstants.TIMEZONE_CORE_NAME);
        if(timezoneFilterQuery!=null && !timezoneFilterQuery.isEmpty()) {
            placeQueryBuilder.append(" AND ");
            placeQueryBuilder.append(timezoneFilterQuery);
        }

        placeQuery.setQuery(placeQueryBuilder.toString());

        QueryResponse placeResponse = searchSolrCore(SOLR_PLACES_CORE, placeQuery);
        if(placeResponse!=null) {
            places = placeResponse.getBeans(Place.class);
        }

        return places;
    }

    List<Place> findPlacesForState(State state, Map<String, List<? extends SolrSchemaType>> filters) {
        List<Place> places = new ArrayList<Place>();

        if(state==null) {
            return places;
        }

        SolrQuery placeQuery = getPlaceQuery();
        StringBuilder placeQueryBuilder = new StringBuilder();
        placeQueryBuilder.append(LocationSearchConstants.PLACE_ADMIN_1_CODE_FIELD).append(":").append(state.getStatecode());

        String featureFilterQuery = addFiltersToLocationQueryStr(filters, LocationSearchConstants.FEATURE_CORE_NAME);
        if(featureFilterQuery!=null && !featureFilterQuery.isEmpty()) {
            placeQueryBuilder.append(" AND ");
            placeQueryBuilder.append(featureFilterQuery);
        }

        String timezoneFilterQuery = addFiltersToLocationQueryStr(filters, LocationSearchConstants.TIMEZONE_CORE_NAME);
        if(timezoneFilterQuery!=null && !timezoneFilterQuery.isEmpty()) {
            placeQueryBuilder.append(" AND ");
            placeQueryBuilder.append(timezoneFilterQuery);
        }

        placeQuery.setQuery(placeQueryBuilder.toString());

        QueryResponse placeResponse = searchSolrCore(SOLR_PLACES_CORE, placeQuery);
        if(placeResponse!=null) {
            places = placeResponse.getBeans(Place.class);
        }

        return places;
    }

    List<Place> findPlacesForCountry(Country country, Map<String, List<? extends SolrSchemaType>> filters) {
        List<Place> places = new ArrayList<Place>();

        if(country==null) {
            return places;
        }

        SolrQuery placeQuery = getPlaceQuery();
        StringBuilder placeQueryBuilder = new StringBuilder();
        placeQueryBuilder.append(LocationSearchConstants.PLACE_COUNTRY_CODE_FIELD).append(":").append(country.getCntrycode());

        String featureFilterQuery = addFiltersToLocationQueryStr(filters, LocationSearchConstants.FEATURE_CORE_NAME);
        if(featureFilterQuery!=null && !featureFilterQuery.isEmpty()) {
            placeQueryBuilder.append(" AND ");
            placeQueryBuilder.append(featureFilterQuery);
        }

        String timezoneFilterQuery = addFiltersToLocationQueryStr(filters, LocationSearchConstants.TIMEZONE_CORE_NAME);
        if(timezoneFilterQuery!=null && !timezoneFilterQuery.isEmpty()) {
            placeQueryBuilder.append(" AND ");
            placeQueryBuilder.append(timezoneFilterQuery);
        }

        placeQuery.setQuery(placeQueryBuilder.toString());

        QueryResponse placeResponse = searchSolrCore(SOLR_PLACES_CORE, placeQuery);
        if(placeResponse!=null) {
            places = placeResponse.getBeans(Place.class);
        }

        return places;
    }

    public Place findMostSuitablePlace(String locationName, Map<String, List<? extends SolrSchemaType>> filters) {
        Place mostSuitablePlace = null;
        List<Place> places = findAllPlacesByName(locationName, filters);
        if(places==null || places.size()<1) {
            return mostSuitablePlace;
        }
        Collections.sort(places, new PlacePopulationComparator());
        mostSuitablePlace = places.get(0);
        return mostSuitablePlace;
    }

    public List<Place> getCorrelatedLocations(List<SolrLocationType> uniqueLocations) {
        Map<String, Continent> continentsFound = new HashMap<String, Continent>();
        Map<String, Country> countriesFound = new HashMap<String, Country>();
        Map<String, State> statesFound = new HashMap<String, State>();
        Map<String, District> districtsFound = new HashMap<String, District>();
        Map<String, Place> placesFound = new HashMap<String, Place>();

        for(SolrLocationType location : uniqueLocations) {
            switch (location.getLocationType()) {
                case LocationSearchConstants.HIERARCHY_CONTINENT_VALUE:
                    continentsFound.put(location.getCode(), (Continent)location);
                    break;
                case LocationSearchConstants.HIERARCHY_COUNTRY_VALUE:
                    countriesFound.put(location.getCode(), (Country)location);
                    break;
                case LocationSearchConstants.HIERARCHY_STATE_VALUE:
                    statesFound.put(location.getCode(), (State)location);
                    break;
                case LocationSearchConstants.HIERARCHY_DISTRICT_VALUE:
                    districtsFound.put(location.getCode(), (District)location);
                    break;
                case LocationSearchConstants.HIERARCHY_PLACE_VALUE:
                    placesFound.put(location.getCode(), (Place)location);
                    break;
            }
        }

        for(Map.Entry<String, Continent> continentsFoundEntry : continentsFound.entrySet()) {
            if(placesFound.containsKey(continentsFoundEntry.getKey())) {
                placesFound.remove(continentsFoundEntry.getKey());
            }
        }

        for(Map.Entry<String, Country> countriesFoundEntry : countriesFound.entrySet()) {
            if(placesFound.containsKey(countriesFoundEntry.getKey())) {
                placesFound.remove(countriesFoundEntry.getKey());
            }
        }

        for(Map.Entry<String, State> statesFoundEntry : statesFound.entrySet()) {
            if(placesFound.containsKey(statesFoundEntry.getKey())) {
                placesFound.remove(statesFoundEntry.getKey());
            }
        }

        for(Map.Entry<String, District> districtsFoundEntry : districtsFound.entrySet()) {
            if(placesFound.containsKey(districtsFoundEntry.getKey())) {
                placesFound.remove(districtsFoundEntry.getKey());
            }
        }

        List<Place> correlatedLocations = new ArrayList<Place>();
        for(Map.Entry<String, Place> placesFoundEntry : placesFound.entrySet()) {
            Place placeFound = placesFoundEntry.getValue();

            District districtForPlace = findDistrictForPlace(placeFound);
            State stateForPlace = findStateForPlace(placeFound);
            Country countryForPlace = findCountryForPlace(placeFound);

            if(districtForPlace!=null && districtsFound.containsKey(districtForPlace.getCode()) ||
                    (stateForPlace!=null && statesFound.containsKey(stateForPlace.getCode())) ||
                    (countryForPlace!=null && countriesFound.containsKey(countryForPlace.getCode()))) {
                correlatedLocations.add(placeFound);
            }
        }

        return correlatedLocations;
    }

    public boolean isSpatialSearchPlausible(Place place, long distance) {
        if(distance >= LocationSearchConstants.SPATIAL_SEARCH_BEGIN_D &&
                distance <= LocationSearchConstants.SPATIAL_SEARCH_LIMIT_D) {
            return true;
        }
        return false;
    }

    public List<Place> doSpatialSearch(Place place, long distance, int distanceSort, Map<String, List<? extends SolrSchemaType>> filters) {
        List<Place> spatialResults = new ArrayList<Place>();

        if(place==null) {
            return spatialResults;
        }

        SolrQuery placeQuery = getPlaceQuery();
        placeQuery.setQuery("*:*");
        placeQuery.addFilterQuery("{!geofilt}");
        placeQuery.set("sfield", LocationSearchConstants.PLACE_LOCATION_FIELD);
        placeQuery.set("pt", place.getLocation());
        placeQuery.set("d", String.valueOf(distance));
        placeQuery.addSortField("population", SolrQuery.ORDER.desc);
        QueryResponse placeResponse = searchSolrCore(SOLR_PLACES_CORE, placeQuery);
        if(placeResponse!=null) {
            spatialResults = placeResponse.getBeans(Place.class);
        }

        return spatialResults;
    }


}

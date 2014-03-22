package org.com.location.service.search;

import org.com.location.model.nlp.POSResult;
import org.com.location.model.search.QueryFilters;
import org.com.location.model.solr.LocationQuery;
import org.com.location.service.LocationService;
import org.com.location.service.nlp.OpenNLPService;
import org.com.location.LocationSearchConstants;

import java.io.IOException;
import java.util.*;

public class QueryProcessorService implements LocationService {

	QueryFilters queryFilters = null;

    public POSResult getQueryPOSResult() {
        return queryPOSResult;
    }

    public void setQueryPOSResult(POSResult queryPOSResult) {
        this.queryPOSResult = queryPOSResult;
    }

    POSResult queryPOSResult = null;
    public String getMessage() {
        return message;
    }

    String message = "";

    public QueryProcessorService(QueryFilters queryFilters) {
        this.queryFilters = queryFilters;
    }

    private POSResult parseNLP(String input) throws IOException {
        OpenNLPService openNLPService = new OpenNLPService();
        POSResult posResult = null;
        try {
            posResult = openNLPService.parseQuery(queryFilters.getQueryStr());
            System.out.println("pos==null?"+((posResult==null)?"yes":"no"));
        } catch(IOException ioe) {
            message = "Exception in NLP Processing: "+ioe.getMessage();
            throw ioe;
        }
        return posResult;
    }

    public List<LocationQuery> constructSolrQuery() throws IOException {
        /*
        * 1. Normal Query
        * 2. Advanced Query with given params
        * */
        List<LocationQuery> solrQueries = null;

        if(queryFilters.getIsAdvanced()) {
            if((queryFilters.getKeyWords()!=null && !queryFilters.getKeyWords().isEmpty()) ||
                    (queryFilters.getLocations()!=null && !queryFilters.getLocations().isEmpty()))
            solrQueries = makeAdvancedQuery(queryFilters);
        } else {
            if(queryFilters.getQueryStr()!=null && !queryFilters.getQueryStr().isEmpty()) {
                solrQueries = makeSimpleQuery(queryFilters);
            }
        }

         return solrQueries;
    }

    private List<LocationQuery> makeAdvancedQuery(QueryFilters queryFilters) throws IOException {
        String keyWords = queryFilters.getKeyWords();
        queryPOSResult = parseNLP(keyWords);
        List<LocationQuery> queryList = new ArrayList<LocationQuery>();

        List<String> keyWordList = Collections.EMPTY_LIST;
        List<String> locations = Collections.EMPTY_LIST;
        if(queryFilters.getKeyWords()!=null && !queryFilters.getKeyWords().isEmpty()) {
            keyWordList = Arrays.asList(queryFilters.getKeyWords().split(","));
        }

        if(queryFilters.getLocations()!=null && !queryFilters.getLocations().isEmpty()) {
            locations = Arrays.asList(queryFilters.getLocations().split(","));
        }

        if(!locations.isEmpty()) {
            Iterator<String> locationsIterator = locations.iterator();
            while(locationsIterator.hasNext()) {
                LocationQuery query = new LocationQuery();
                query.addParam(LocationSearchConstants.USER_QUERY_ORIGINAL_QUERY_STR, keyWords);
                query.addParam(LocationSearchConstants.USER_QUERY_IS_ADVANCED_KEY, "true");
                query.addParamAsList(LocationSearchConstants.USER_QUERY_KEYWORDS_KEY, keyWordList);
                query.addParam(LocationSearchConstants.USER_QUERY_LOCATION_KEY, locationsIterator.next());
                if(queryFilters.getDistance()>0) {
                    query.addParam(LocationSearchConstants.USER_QUERY_DISTANCE_KEY, String.valueOf(queryFilters.getDistance()));
                    query.addParam(LocationSearchConstants.USER_QUERY_DISTANCE_SORT_KEY, String.valueOf(queryFilters.getLocationSort()));
                } else {
                    message += "Distance should be > 0. ";
                }
                query.addParam(LocationSearchConstants.USER_QUERY_TIME_SORT_KEY, String.valueOf(queryFilters.getTimeSort()));
                query.addParam(LocationSearchConstants.USER_QUERY_CURRENT_PAGE_NUMBER_KEY, String.valueOf(queryFilters.getCurrentPage()));

                query.addParamAsList(LocationSearchConstants.USER_QUERY_POS_RESULT_COMMONS, queryPOSResult.getCommonNouns());
                query.addParamAsList(LocationSearchConstants.USER_QUERY_POS_RESULT_PROPERS, queryPOSResult.getProperNouns());
                query.addParamAsList(LocationSearchConstants.USER_QUERY_POS_RESULT_PREPS, queryPOSResult.getPrepositions());
                query.addParamAsList(LocationSearchConstants.USER_QUERY_POS_RESULT_OTHERS, queryPOSResult.getOthers());

                /*if(queryFilters.getIsHeirarchial()) {
                    query.addParam(LocationSearchConstants.USER_QUERY_IS_HEIRARCHIAL_KEY, "true");
                }*/
                queryList.add(query);
            }
        } else {
            LocationQuery query = new LocationQuery();
            query.addParam(LocationSearchConstants.USER_QUERY_ORIGINAL_QUERY_STR, keyWords);
            query.addParam(LocationSearchConstants.USER_QUERY_IS_ADVANCED_KEY, "true");
            query.addParamAsList(LocationSearchConstants.USER_QUERY_KEYWORDS_KEY, keyWordList);
            if(queryFilters.getDistance()>0) {
                query.addParam(LocationSearchConstants.USER_QUERY_DISTANCE_KEY, String.valueOf(queryFilters.getDistance()));
                query.addParam(LocationSearchConstants.USER_QUERY_DISTANCE_SORT_KEY, String.valueOf(queryFilters.getLocationSort()));
            } else {
                message += "Distance should be > 0. ";
            }
            query.addParam(LocationSearchConstants.USER_QUERY_TIME_SORT_KEY, String.valueOf(queryFilters.getTimeSort()));
            query.addParam(LocationSearchConstants.USER_QUERY_CURRENT_PAGE_NUMBER_KEY, String.valueOf(queryFilters.getCurrentPage()));

            query.addParamAsList(LocationSearchConstants.USER_QUERY_POS_RESULT_COMMONS, queryPOSResult.getCommonNouns());
            query.addParamAsList(LocationSearchConstants.USER_QUERY_POS_RESULT_PROPERS, queryPOSResult.getProperNouns());
            query.addParamAsList(LocationSearchConstants.USER_QUERY_POS_RESULT_PREPS, queryPOSResult.getPrepositions());
            query.addParamAsList(LocationSearchConstants.USER_QUERY_POS_RESULT_OTHERS, queryPOSResult.getOthers());

            /*if(queryFilters.getIsHeirarchial()) {
                query.addParam(LocationSearchConstants.USER_QUERY_IS_HEIRARCHIAL_KEY, "true");
            }*/

            queryList.add(query);
        }

        return queryList;
    }

   /* private List<LocationQuery> makeFunctionalQuery(QueryFilters queryFilters) {
        List<LocationQuery> queryList = new ArrayList<LocationQuery>();
        LocationQuery query = new LocationQuery();
        query.addParam(LocationSearchConstants.USER_QUERY_IS_FUNCTIONAL_KEY, "true");
        query.addParam(LocationSearchConstants.USER_QUERY_ORIGINAL_QUERY_STR, queryFilters.getQueryStr());
        query.addParam(LocationSearchConstants.USER_QUERY_RAW_QUERY_STRING_KEY, queryFilters.getQueryStr());
        queryList.add(query);
        return queryList;
    }
*/
    private List<LocationQuery> makeSimpleQuery(QueryFilters queryFilters) throws IOException {
        String queryStr = queryFilters.getQueryStr();
        queryPOSResult = parseNLP(queryStr);
        List<LocationQuery> queryList = new ArrayList<LocationQuery>();
        /*if(posResult==null) {
            return queryList;
        }
        */
        if(queryPOSResult.getLocations()!=null && queryPOSResult.getLocations().size()>0) {
            Iterator<String> locationsIterator = queryPOSResult.getLocations().iterator();
            while(locationsIterator.hasNext()) {
                LocationQuery query = new LocationQuery();
                query.addParam(LocationSearchConstants.USER_QUERY_ORIGINAL_QUERY_STR, queryStr);
                query.addParamAsList(LocationSearchConstants.USER_QUERY_KEYWORDS_KEY, queryPOSResult.getCommonNouns());
                query.addParamAsList(LocationSearchConstants.USER_QUERY_KEYWORDS_KEY, queryPOSResult.getProperNouns());
                query.addParam(LocationSearchConstants.USER_QUERY_LOCATION_KEY, locationsIterator.next());
                if(queryFilters.getDistance()>0) {
                    query.addParam(LocationSearchConstants.USER_QUERY_DISTANCE_KEY, String.valueOf(queryFilters.getDistance()));
                    query.addParam(LocationSearchConstants.USER_QUERY_DISTANCE_SORT_KEY, String.valueOf(queryFilters.getLocationSort()));
                } else {
                    message += "Distance should be > 0. ";
                }
                query.addParam(LocationSearchConstants.USER_QUERY_TIME_SORT_KEY, String.valueOf(queryFilters.getTimeSort()));
                query.addParam(LocationSearchConstants.USER_QUERY_CURRENT_PAGE_NUMBER_KEY, String.valueOf(queryFilters.getCurrentPage()));
                query.addParamAsList(LocationSearchConstants.USER_QUERY_POS_RESULT_COMMONS, queryPOSResult.getCommonNouns());
                query.addParamAsList(LocationSearchConstants.USER_QUERY_POS_RESULT_PROPERS, queryPOSResult.getProperNouns());
                query.addParamAsList(LocationSearchConstants.USER_QUERY_POS_RESULT_PREPS, queryPOSResult.getPrepositions());
                query.addParamAsList(LocationSearchConstants.USER_QUERY_POS_RESULT_OTHERS, queryPOSResult.getOthers());

                /*if(queryFilters.getIsHeirarchial()) {
                    query.addParam(LocationSearchConstants.USER_QUERY_IS_HEIRARCHIAL_KEY, "true");
                }*/

                queryList.add(query);
            }
        } else {
            LocationQuery query = new LocationQuery();
            query.addParam(LocationSearchConstants.USER_QUERY_ORIGINAL_QUERY_STR, queryStr);
            query.addParamAsList(LocationSearchConstants.USER_QUERY_KEYWORDS_KEY, queryPOSResult.getCommonNouns());
            query.addParamAsList(LocationSearchConstants.USER_QUERY_KEYWORDS_KEY, queryPOSResult.getProperNouns());
            if(queryFilters.getDistance()>0) {
                query.addParam(LocationSearchConstants.USER_QUERY_DISTANCE_KEY, String.valueOf(queryFilters.getDistance()));
                query.addParam(LocationSearchConstants.USER_QUERY_DISTANCE_SORT_KEY, String.valueOf(queryFilters.getLocationSort()));
            } else {
                message += "Distance should be > 0. ";
            }
            query.addParam(LocationSearchConstants.USER_QUERY_TIME_SORT_KEY, String.valueOf(queryFilters.getTimeSort()));
            query.addParam(LocationSearchConstants.USER_QUERY_CURRENT_PAGE_NUMBER_KEY, String.valueOf(queryFilters.getCurrentPage()));

            query.addParamAsList(LocationSearchConstants.USER_QUERY_POS_RESULT_COMMONS, queryPOSResult.getCommonNouns());
            query.addParamAsList(LocationSearchConstants.USER_QUERY_POS_RESULT_PROPERS, queryPOSResult.getProperNouns());
            query.addParamAsList(LocationSearchConstants.USER_QUERY_POS_RESULT_PREPS, queryPOSResult.getPrepositions());
            query.addParamAsList(LocationSearchConstants.USER_QUERY_POS_RESULT_OTHERS, queryPOSResult.getOthers());

            /*if(queryFilters.getIsHeirarchial()) {
                query.addParam(LocationSearchConstants.USER_QUERY_IS_HEIRARCHIAL_KEY, "true");
            }*/

            queryList.add(query);
        }
        return queryList;
    }

}

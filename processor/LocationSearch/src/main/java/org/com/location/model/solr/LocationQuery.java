package org.com.location.model.solr;

import org.com.location.LocationSearchConstants;

import java.util.*;

/**
 * User: ravioactive
 * Date: 11/27/13
 * Time: 8:52 PM
 */


public class LocationQuery {

    Map<String, List<String>> paramValues = null;

    public LocationQuery() {
        paramValues = new HashMap<String, List<String>>();
    }

    public LocationQuery(Map<String, List<String>> paramValues) {
        if(paramValues!=null)
            this.paramValues = paramValues;
        else
            paramValues = new HashMap<String, List<String>>();
    }

    public void addParamAsList(String paramName, List<String> paramValueList) {
        if(!paramValues.containsKey(paramName)) {
            paramValues.put(paramName, new ArrayList<String>());
        }
        paramValues.get(paramName).addAll(paramValueList);
    }

    public void addParam(String paramName, String paramValue) {
        if(!paramValues.containsKey(paramName)) {
            paramValues.put(paramName, new ArrayList<String>());
        }
        paramValues.get(paramName).add(paramValue);
    }

    public void removeParam(String paramName) {
        paramValues.remove(paramName);
    }

    public List<String> fetchParam(String paramName) {
        return paramValues.get(paramName);
    }

    public Set<Map.Entry<String, List<String>>> getAllParams() {
        return paramValues.entrySet();
    }

    public boolean containsParam(String param) {
        return paramValues.containsKey(param);
    }

    public String makeUrl() {
        String url = null;
        if(paramValues.containsKey(LocationSearchConstants.USER_QUERY_RAW_QUERY_STRING_KEY)) {
            url = paramValues.get(LocationSearchConstants.USER_QUERY_RAW_QUERY_STRING_KEY).get(0);
        } else {
            if(paramValues==null || paramValues.isEmpty()) {
                return url;
            }
            StringBuilder urlBuilder = new StringBuilder();
            Iterator<Map.Entry< String, List<String>>> paramsIterator = paramValues.entrySet().iterator();
            while(paramsIterator.hasNext()) {
                Map.Entry<String, List<String>> entry = paramsIterator.next();
                Iterator<String> valueListIterator = entry.getValue().listIterator();
                urlBuilder.append("&").append(entry.getKey()).append("=");
                while(valueListIterator.hasNext()) {
                    urlBuilder.append(valueListIterator.next()).append(",");
                }
            }
            url = urlBuilder.toString();
        }

        return url;
    }
}

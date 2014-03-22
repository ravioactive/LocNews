package org.com.location.model.search;

/**
 * User: ravioactive
 * Date: 11/27/13
 * Time: 2:28 AM
 */

public class QueryFilters {
    String queryStr;

    boolean isAdvanced = false;
    String keyWords;
    String locations;
    int distance = 0;

    int timeSort = -1;
    int locationSort = -1;

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    int currentPage = 1;
  /*  public boolean getIsHeirarchial() {
        return isHeirarchial;
    }

    public void setHeirarchial(boolean heirarchial) {
        isHeirarchial = heirarchial;
    }

    boolean isHeirarchial = false;*/

    public QueryFilters() {

    }

    public QueryFilters(QueryFilters filters) {

    }

    public String getQueryStr() {
        return queryStr;
    }

    public void setQueryStr(String queryStr) {
        this.queryStr = queryStr;
    }

    public boolean getIsAdvanced() {
        return isAdvanced;
    }

    public void setIsAdvanced(boolean advanced) {
        isAdvanced = advanced;
    }

    public String getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(String keyWords) {
        this.keyWords = keyWords;
    }

    public String getLocations() {
        return locations;
    }

    public void setLocations(String locations) {
        this.locations = locations;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getTimeSort() {
        return timeSort;
    }

    public void setTimeSort(int timeSort) {
        this.timeSort = timeSort;
    }

    public int getLocationSort() {
        return locationSort;
    }

    public void setLocationSort(int locationSort) {
        this.locationSort = locationSort;
    }

    @Override
    public String toString() {
        return "QueryFilters{" +
                "queryStr='" + queryStr + '\'' +
                ", isAdvanced=" + isAdvanced +
                ", keyWords='" + keyWords + '\'' +
                ", locations='" + locations + '\'' +
                ", distance=" + distance +
                ", timeSort=" + timeSort +
                ", locationSort=" + locationSort +
                '}';
    }
}

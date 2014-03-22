package org.com.location.model.search;

import org.com.location.model.nlp.POSResult;

import java.util.List;
import java.util.Map;

public class SearchResult {

    public List<PreparedWikiNews> getPreparedWikiNewsResults() {
        return preparedWikiNewsResults;
    }

    public void setPreparedWikiNewsResults(List<PreparedWikiNews> preparedWikiNewsResults) {
        this.preparedWikiNewsResults = preparedWikiNewsResults;
    }

    List<PreparedWikiNews> preparedWikiNewsResults = null;
    Map<String,List<String>> searchSuggestions = null;
    Map<String,String> searchMessages = null;
    POSResult queryPOSResult = null;
    boolean isFeedback = false;
    Map<String, String> feedback = null;

    String currentQuery = null;

    String currentLocQuery = null;

    int currentPage = 0;

    String resultCount = "0";
    String queryTime = "0.0";

    public String getResultCount() {
        return resultCount;
    }

    public void setResultCount(String resultCount) {
        this.resultCount = resultCount;
    }

    public String getQueryTime() {
        return queryTime;
    }

    public void setQueryTime(String queryTime) {
        this.queryTime = queryTime;
    }

    QueryFilters queryFilters = null;

    public String getCurrentLocQuery() {
        return currentLocQuery;
    }

    public void setCurrentLocQuery(String currentLocQuery) {
        this.currentLocQuery = currentLocQuery;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public String getCurrentQuery() {
        return currentQuery;
    }

    public void setCurrentQuery(String currentQuery) {
        this.currentQuery = currentQuery;
    }

    public Map<String, String> getFeedback() {
        return feedback;
    }
    public void setFeedback(Map<String, String> feedback) {
        this.feedback = feedback;
    }

    public boolean isFeedback() {
        return isFeedback;
    }

    public void setFeedback(boolean feedback) {
        isFeedback = feedback;
    }

    public POSResult getQueryPOSResult() {
        return queryPOSResult;
    }

    public void setQueryPOSResult(POSResult queryPOSResult) {
        this.queryPOSResult = queryPOSResult;
    }

    public Map<String, List<String>> getSearchSuggestions() {
        return searchSuggestions;
    }

    public void setSearchSuggestions(Map<String, List<String>> searchSuggestions) {
        this.searchSuggestions = searchSuggestions;
    }

    public Map<String, String> getSearchMessages() {
        return searchMessages;
    }

    public void setSearchMessages(Map<String, String> searchMessages) {
        this.searchMessages = searchMessages;
    }

    public SearchResult() {

    }

    public SearchResult(QueryFilters queryFilters) {
        this.queryFilters = queryFilters;
    }

    public SearchResult(List<PreparedWikiNews> preparedWikiNewsResults) {
        this.preparedWikiNewsResults = preparedWikiNewsResults;
    }

    public SearchResult(List<PreparedWikiNews> preparedWikiNewsResults, QueryFilters queryFilters) {
        this.queryFilters = queryFilters;
        this.preparedWikiNewsResults = preparedWikiNewsResults;
    }

    public QueryFilters getQueryFilters() {
        return queryFilters;
    }

    public void setQueryFilters(QueryFilters queryFilters) {
        this.queryFilters = queryFilters;
    }

    @Override
    public String toString() {
        return "SearchResult{" +
                "preparedWikiNewsResults=" + preparedWikiNewsResults +
                ", searchSuggestions=" + searchSuggestions +
                ", searchMessages=" + searchMessages +
                ", queryPOSResult=" + queryPOSResult +
                ", isFeedback=" + isFeedback +
                ", feedback=" + feedback +
                ", currentQuery='" + currentQuery + '\'' +
                ", currentPage=" + currentPage +
                ", queryFilters=" + queryFilters +
                '}';
    }
}

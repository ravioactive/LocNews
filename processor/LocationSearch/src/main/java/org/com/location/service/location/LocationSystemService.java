package org.com.location.service.location;

import java.io.IOException;
import java.util.List;

import org.com.location.model.search.PreparedWikiNews;
import org.com.location.model.search.QueryFilters;
import org.com.location.model.search.SearchResult;
import org.com.location.model.solr.LocationQuery;
import org.com.location.model.solr.SolrWikiNews;
import org.com.location.model.solr.schema.WikiNews;
import org.com.location.service.LocationService;
import org.com.location.service.search.LocationSearchService;
import org.com.location.service.search.QueryProcessorService;
import org.com.location.service.search.ResultsProcessorService;

public class LocationSystemService implements LocationService {
    QueryFilters queryFilters;

    public String getMessage() {
        return message;
    }

    String message = "";

    public LocationSystemService() {

    }

    public LocationSystemService(QueryFilters queryFilters) {
        this.queryFilters = queryFilters;
    }

    public SearchResult getSearchResults() throws IOException {
        QueryProcessorService queryProcessor = new QueryProcessorService(queryFilters);
        List<LocationQuery> queries = queryProcessor.constructSolrQuery();
        message += queryProcessor.getMessage();

        LocationSearchService locationSearcher = null;
        try {
            locationSearcher = new LocationSearchService(queries);
        } catch (Exception e) {
            message += "Exception in initializing location service : "+locationSearcher.getMessage();
            e.printStackTrace();
        }
        List<WikiNews> wikiNewsList = locationSearcher.doSearch();
        message += locationSearcher.getMessage();

        ResultsProcessorService resultProcessor = new ResultsProcessorService(queryFilters, wikiNewsList, locationSearcher.getHeirarchialInfo());
        SearchResult searchResult = resultProcessor.prepareSearchResults();
        searchResult.setQueryPOSResult(queryProcessor.getQueryPOSResult());
        searchResult.setCurrentPage(locationSearcher.getCurrentPage());
        searchResult.setCurrentQuery(locationSearcher.getCurrentWikiNewsQueryString());
        searchResult.setCurrentLocQuery(locationSearcher.getCurrentWikiNewsLocQueryString());
        searchResult.setQueryTime(locationSearcher.getQueryTime());
        searchResult.setResultCount(locationSearcher.getNumFound());
        message += resultProcessor.getMessage();

        return searchResult;
    }

    public SearchResult getFeedback(SearchResult feedbackSarchResult) throws Exception {
        LocationSearchService locationSearcher = null;
        try {
            locationSearcher = new LocationSearchService();
        } catch (Exception e) {
            message += locationSearcher.getMessage();
            throw e;
        }
        List<WikiNews> wikiNewsList = locationSearcher.doFeedback(feedbackSarchResult);
        message += locationSearcher.getMessage();

        ResultsProcessorService resultProcessor = new ResultsProcessorService(feedbackSarchResult.getQueryFilters(),
                                                wikiNewsList, locationSearcher.getHeirarchialInfo());
        SearchResult searchResult = resultProcessor.prepareSearchResults();
        searchResult.setQueryPOSResult(feedbackSarchResult.getQueryPOSResult());
        searchResult.setCurrentQuery(locationSearcher.getCurrentWikiNewsQueryString());
        searchResult.setCurrentLocQuery(locationSearcher.getCurrentWikiNewsLocQueryString());
        searchResult.setQueryTime(locationSearcher.getQueryTime());
        searchResult.setResultCount(locationSearcher.getNumFound());
        message += resultProcessor.getMessage();

        return searchResult;
    }

    public SearchResult getNextPage(SearchResult currentSearchResult, int pageNo) {
        currentSearchResult.setCurrentPage(pageNo);
        LocationSearchService locationSearcher = null;
        try {
            locationSearcher = new LocationSearchService();
        } catch (Exception e) {
            message += locationSearcher.getMessage();
            e.printStackTrace();
        }
        List<WikiNews> nextPage = locationSearcher.getNextPage(currentSearchResult);
        ResultsProcessorService resultsProcessor = new ResultsProcessorService();
        List<PreparedWikiNews> nextPreparedWikiNewsList = resultsProcessor.prepareWikiNewsResult(nextPage);

        currentSearchResult.setPreparedWikiNewsResults(nextPreparedWikiNewsList);

        return currentSearchResult;
    }

}

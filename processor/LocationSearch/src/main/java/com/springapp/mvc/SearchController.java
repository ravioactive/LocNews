package com.springapp.mvc;

import org.apache.http.HttpRequest;
import org.com.location.model.search.QueryFilters;
import org.com.location.model.search.SearchResult;
import org.com.location.service.location.LocationSystemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/search")
public class SearchController {

    @RequestMapping(method = RequestMethod.GET)
    public String initSearch(ModelMap model) {
        QueryFilters queryFilters = new QueryFilters();
        model.put("filters", queryFilters);
        return "Welcome";
    }

    @RequestMapping(value = "/{queryText}/{pageNo}", method = RequestMethod.GET)
    public String doSearch(@ModelAttribute SearchResult searchResult, @PathVariable String queryText,
                           @PathVariable int pageNo, ModelMap model, HttpServletRequest request, HttpServletResponse response) {
        SearchResult result = (SearchResult) request.getSession().getAttribute("results");
        if(pageNo==1 || result==null) {
            if(result!=null) {
                request.getSession().removeAttribute("results");
            }
            QueryFilters queryFilters = new QueryFilters();
            queryFilters.setQueryStr(queryText);
            queryFilters.setCurrentPage(pageNo);
            LocationSystemService locationSystemService = new LocationSystemService(queryFilters);
            SearchResult results = null;
            try {
                results = locationSystemService.getSearchResults();

            } catch (IOException e) {
                String message = locationSystemService.getMessage();
                model.put("errors", message);
            }
            if(results!=null) {
                //System.out.println(results.toString());
            }
            model.put("results", results);
            request.getSession().setAttribute("results", results);
        } else if (pageNo>1) {
            //result = (SearchResult) request.getSession().getAttribute("results");
            if(searchResult!=null) {
                LocationSystemService locationSystemService = new LocationSystemService();
                SearchResult nextSearchResult = locationSystemService.getNextPage(result, pageNo);
                nextSearchResult.toString();
                model.put("results", nextSearchResult);
                request.getSession().setAttribute("results", nextSearchResult);
            }
        }
        return "Search";
    }

    @RequestMapping(value = "/advanced/{location}/{keyWords}/{distance}/{pageNo}", method = RequestMethod.GET)
    public String doAdvancedSearch(@ModelAttribute SearchResult searchResult, @PathVariable String location,
                                   @PathVariable String keyWords,@PathVariable int distance, @PathVariable int pageNo,
                                   ModelMap model, HttpServletRequest request, HttpServletResponse response) {


        SearchResult result = (SearchResult) request.getSession().getAttribute("results");
        if(pageNo==1 || result==null) {

            request.getSession().removeAttribute("results");

            QueryFilters queryFilters = new QueryFilters();
            queryFilters.setKeyWords(keyWords);
            queryFilters.setLocations(location);
            queryFilters.setDistance(distance);
            queryFilters.setCurrentPage(pageNo);
            LocationSystemService locationSystemService = new LocationSystemService(queryFilters);
            SearchResult results = null;
            try {
                results = locationSystemService.getSearchResults();
            } catch (IOException e) {
                String message = locationSystemService.getMessage();
                model.put("errors", message);
            }
            model.put("results", results);
            request.getSession().setAttribute("results", results);
        } else if (pageNo>1) {

            if(result!=null) {
                LocationSystemService locationSystemService = new LocationSystemService();
                SearchResult nextSearchResult = locationSystemService.getNextPage(result, pageNo);
                model.put("results", nextSearchResult);
                request.getSession().setAttribute("results", nextSearchResult);
            }
        }
        return "Search";
    }

    @RequestMapping(value = "/suggestions/{locationId}/{fbCode}/{distance}/{pageNo}", method = RequestMethod.GET)
    public String doSuggestionsSearch(@ModelAttribute SearchResult searchResult, @PathVariable String locationId,
                                      @PathVariable int fbCode, @PathVariable int pageNo,
                                      @PathVariable int distance, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {

        SearchResult result = (SearchResult) request.getSession().getAttribute("results");
        System.out.println(result==null?"NULL":"NOT NULL");
        Map<String, String> feedbackEntry = new HashMap<String, String>();
        feedbackEntry.put(locationId, String.valueOf(fbCode));
        result.setFeedback(feedbackEntry);
        result.getQueryFilters().setDistance(distance);
        LocationSystemService locationSystemService = new LocationSystemService();
        SearchResult feedbackSearchResults = locationSystemService.getFeedback(result);
        model.put("results", feedbackSearchResults);
        request.getSession().setAttribute("results", feedbackSearchResults);
        return "Search";
    }
}
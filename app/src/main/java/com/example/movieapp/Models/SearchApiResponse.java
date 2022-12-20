package com.example.movieapp.Models;

import java.util.List;

public class SearchApiResponse {
    String searchType = "";
    String expression = "";
    List<SearchArrayObject> results = null;
    String errorMessage = "";

    public String getSearchType() {
        return searchType;
    }

    public void setSearchType(String searchType) {
        this.searchType = searchType;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public List<SearchArrayObject> getResults() {
        return results;
    }

    public void setResults(List<SearchArrayObject> results) {
        this.results = results;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}

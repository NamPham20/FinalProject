package com.example.finalproject.module;

import java.util.ArrayList;
import java.util.List;

public class PatientListFromApi {
    Links LinksObject;
    private float count;
    private float total_pages;
    List< PatientFromAPI > results = new ArrayList<>();


    // Getter Methods

    public Links getLinks() {
        return LinksObject;
    }

    public float getCount() {
        return count;
    }

    public float getTotal_pages() {
        return total_pages;
    }

    // Setter Methods

    public void setLinks(Links linksObject) {
        this.LinksObject = linksObject;
    }

    public void setCount(float count) {
        this.count = count;
    }

    public void setTotal_pages(float total_pages) {
        this.total_pages = total_pages;
    }

    public Links getLinksObject() {
        return LinksObject;
    }

    public void setLinksObject(Links linksObject) {
        LinksObject = linksObject;
    }

    public List<PatientFromAPI> getResults() {
        return results;
    }

    public void setResults(List<PatientFromAPI> results) {
        this.results = results;
    }
}
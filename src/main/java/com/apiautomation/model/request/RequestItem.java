package com.apiautomation.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RequestItem {


    @JsonProperty("title")
    public String title;

    @JsonProperty("description")
    public String description;

    @JsonProperty("category")
    public String category;

    @JsonProperty("price")
    public int price;

    @JsonProperty("stock")
    public int stock;

    @JsonProperty("tags")
    public String[] tags;

    @JsonProperty("discountPercentage")
    public float discountPercentage;

    @JsonProperty("dimensions")
    public Dimensions dimensions;

    @JsonProperty("rating")
    public float rating;


    public static class Dimensions {
        @JsonProperty("width")
        public float width;

        @JsonProperty("height")
        public float height;

        @JsonProperty("depth")
        public float depth;

    }
}

package com.akhyar.tokopediascraper.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class AbstractHandphone {
    @JsonProperty("Name")
    abstract String getName();

    @JsonProperty("Description")
    abstract String getDescription();

    @JsonProperty("Image Link")
    abstract String getImageLink();

    @JsonProperty("Price")
    abstract Double getPrice();

    @JsonProperty("Rating")
    abstract Double getRating();

    @JsonProperty("Merchant Name")
    abstract String getMerchant();
}

package com.akhyar.tokopediascraper.model;

public class Handphone extends AbstractHandphone {
    private String name;
    private String description;
    private String imageLink;
    private Double price;
    private Double rating;
    private String merchant;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Handphone setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Handphone setImageLink(String imageLink) {
        this.imageLink = imageLink;
        return this;
    }

    public String getImageLink() {
        return imageLink;
    }

    public Handphone setPrice(Double price) {
        this.price = price;
        return this;
    }

    public Double getPrice() {
        return price;
    }

    public Handphone setRating(Double rating) {
        this.rating = rating;
        return this;
    }

    public Double getRating() {
        return rating;
    }

    public Handphone setMerchant(String merchant) {
        this.merchant = merchant;
        return this;
    }

    public String getMerchant() {
        return merchant;
    }
}

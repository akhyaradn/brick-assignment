package com.akhyar.tokopediascraper.tokopedia;

public final class TokopediaWebDocument {
    public static final String HANDPHONE_NAME = "//h1[@data-testid='lblPDPDetailProductName']";
    public static final String HANDPHONE_DESCRIPTION = "//*[@data-testid='lblPDPDescriptionProduk']";
    public static final String HANDPHONE_IMAGE_LINK = "//*[@data-testid='PDPImageMain']//img";
    public static final String HANDPHONE_PRICE = "//*[@data-testid='lblPDPDetailProductPrice']";
    public static final String HANDPHONE_RATING = "//*[@data-testid='lblPDPDetailProductRatingNumber']";
    public static final String HANDPHONE_MERCHANT_NAME = "//*[@data-testid='llbPDPFooterShopName']//h2";

    public static final String HANDPHONE_LIST = "//div[@data-testid='lstCL2ProductList']/div";
    public static final String HANDPHONE_LINK = "a[@data-testid='lnkProductContainer']";

    public static final Integer TOTAL_HANDPHONES = 100;
}

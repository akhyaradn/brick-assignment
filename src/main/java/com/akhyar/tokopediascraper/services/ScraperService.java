package com.akhyar.tokopediascraper.services;

import com.akhyar.tokopediascraper.exception.ScraperException;
import com.akhyar.tokopediascraper.html.HtmlTag;
import com.akhyar.tokopediascraper.model.Handphone;
import com.akhyar.tokopediascraper.tokopedia.TokopediaUrl;
import com.akhyar.tokopediascraper.tokopedia.TokopediaWebDocument;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ScraperService {
    private BrowserService browserService;
    private List<String> tabs;

    public ScraperService() {
        browserService = new BrowserService();
        tabs = browserService.initializeTabs();
    }

    public Set<Handphone> getList() throws ScraperException {
        Set<Handphone> handphones = new HashSet<>();
        String url = TokopediaUrl.BASE_URL + TokopediaUrl.HANDPHONE_PATH;

        try {
            for(int i = 1; handphones.size() < TokopediaWebDocument.TOTAL_HANDPHONES; i++) {
                String page = url + TokopediaUrl.PAGE + i + TokopediaUrl.TOP_PRODUCT_FLAG;

                final List<WebElement> elements = browserService.getUrlHandphones(page, TokopediaWebDocument.HANDPHONE_LIST, this.tabs.get(0));

                getEachElements(elements, handphones);
            }
        } catch (Exception e) {
            throw new ScraperException(e.getMessage());
        } finally {
            browserService.quit();
        }

        return handphones;
    }

    private void getEachElements(List<WebElement> elements, Set<Handphone> handphones) throws IOException {
        for(WebElement element : elements) {
            String handphoneUrl = element.findElement(By.xpath(TokopediaWebDocument.HANDPHONE_LINK)).getAttribute(HtmlTag.HREF);

            if(handphoneUrl.contains(TokopediaUrl.TA_TOKOPEDIA)) {
                handphoneUrl = this.extractUrl(handphoneUrl);
            }

            browserService.getPageDocument(handphoneUrl, tabs.get(1));
            browserService.scroll300();
            browserService.waitToFinishLoad(TokopediaWebDocument.HANDPHONE_MERCHANT_NAME);

            Handphone handphone = extractProduct(browserService);

            handphones.add(handphone);

            System.out.println("Added " + handphone.getName() + " to the list..");

            if (handphones.size() == TokopediaWebDocument.TOTAL_HANDPHONES) {
                break;
            }

            browserService.switchTab(tabs.get(0));
        }
    }

    private Handphone extractProduct(BrowserService browserService) {
        String name = browserService.getText(TokopediaWebDocument.HANDPHONE_NAME);
        String description = browserService.getText(TokopediaWebDocument.HANDPHONE_DESCRIPTION);
        String imgLink = browserService.getText(TokopediaWebDocument.HANDPHONE_IMAGE_LINK, HtmlTag.SRC);
        Double price = Double.parseDouble(browserService.getText(TokopediaWebDocument.HANDPHONE_PRICE).replaceAll("[^\\d]", ""));
        String strRating = browserService.getText(TokopediaWebDocument.HANDPHONE_RATING);
        strRating = strRating == null || strRating.isEmpty() ? "0.0" : strRating;
        Double rating = Double.parseDouble(strRating);
        String merchant = browserService.getText(TokopediaWebDocument.HANDPHONE_MERCHANT_NAME);

        Handphone handphone = new Handphone();

        handphone.setName(name);
        handphone.setDescription(description);
        handphone.setImageLink(imgLink);
        handphone.setPrice(price);
        handphone.setRating(rating);
        handphone.setMerchant(merchant);

        return handphone;
    }

    private String extractUrl(String path) throws IOException {
        return URLDecoder.decode(path.substring(path.indexOf(HtmlTag.R) + 2).split(HtmlTag.AMP)[0],
                StandardCharsets.UTF_8.name());
    }
}

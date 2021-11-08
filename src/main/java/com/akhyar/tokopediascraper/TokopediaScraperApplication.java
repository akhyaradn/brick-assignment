package com.akhyar.tokopediascraper;

import com.akhyar.tokopediascraper.exception.ScraperException;
import com.akhyar.tokopediascraper.services.ExportService;
import com.akhyar.tokopediascraper.services.ScraperService;

public class TokopediaScraperApplication{

	public static void main(String[] args) {
		if(args.length > 0) {
			System.setProperty("webdriver.chrome.driver", args[0]);
		}

		System.out.println("Starting to extract top 100 handphone products...");
		try {
			String export = new ExportService().exportToCSV();
			System.out.println("Top 100 handphones extracted to " + export);
		} catch(ScraperException e) {
			e.printStackTrace();
		}
	}
}

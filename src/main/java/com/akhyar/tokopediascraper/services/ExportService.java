package com.akhyar.tokopediascraper.services;

import com.akhyar.tokopediascraper.exception.ScraperException;
import com.akhyar.tokopediascraper.file.Filename;
import com.akhyar.tokopediascraper.model.AbstractHandphone;
import com.akhyar.tokopediascraper.model.Handphone;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import java.io.File;
import java.io.IOException;
import java.util.Set;

public class ExportService {
    private ScraperService scraperService;

    public ExportService() {
        scraperService = new ScraperService();
    }

    public String exportToCSV() throws ScraperException {
        String filename = Filename.PRODUCT + Filename.SEPARATOR + System.currentTimeMillis() + Filename.SEPARATOR + Filename.EXTENSION;
        Set<Handphone> handphones = scraperService.getList();

        CsvMapper csvMapper = new CsvMapper();
        csvMapper.enable(JsonGenerator.Feature.IGNORE_UNKNOWN);
        csvMapper.addMixIn(Handphone.class, AbstractHandphone.class);
        CsvSchema schema = csvMapper.schemaFor(Handphone.class).withHeader();

        try {
            File file = new File(filename);
            csvMapper.writer(schema).writeValue(file, handphones);
            return filename;
        } catch (IOException | RuntimeException e) {
            throw new ScraperException(e.getMessage());
        }
    }
}

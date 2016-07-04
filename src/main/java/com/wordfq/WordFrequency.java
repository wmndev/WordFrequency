package com.wordfq;

import com.google.gson.Gson;
import com.wordfq.service.builder.QueryEntityBuilder;
import com.wordfq.service.builder.RecordEntityBuilder;
import com.wordfq.service.container.QueryResultContainer;
import com.wordfq.service.loader.BufferLoaderService;
import com.wordfq.service.loader.FileLoaderService;
import com.wordfq.service.processor.QueryEntityProcessor;
import com.wordfq.service.processor.RecordEntityProcessor;
import org.apache.log4j.Logger;

import java.io.IOException;


public class WordFrequency {

    private final static Logger logger = Logger.getLogger(WordFrequency.class);

    public static void main(String[] args) {

        FileLoaderService queriesLoader = new BufferLoaderService(new QueryEntityBuilder(),
                new QueryEntityProcessor());
        FileLoaderService recordsLoader = new BufferLoaderService(new RecordEntityBuilder(),
                new RecordEntityProcessor());

        try {
            logger.info("Loading Queries...");
            queriesLoader.loadFile("queries.txt");
            logger.info("Loading Records...");
            recordsLoader.loadFile("records.txt");

            //print results
            printResults();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Printing queries results. in order (as result is a TreeMap).
     */
    private static void printResults() {
        System.out.println("Results:");
        System.out.println("--------");

        Gson gson = new Gson();
        QueryResultContainer.getInstance().getComponents().forEach(
                c -> System.out.println(gson.toJson(c.getResult())));
    }
}

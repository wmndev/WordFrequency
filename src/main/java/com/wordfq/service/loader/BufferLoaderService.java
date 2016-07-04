package com.wordfq.service.loader;

import com.wordfq.entity.DataEntity;
import com.wordfq.service.builder.EntityBuilder;
import com.wordfq.service.processor.EntityProcessor;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

/**
 * Created by wiseman on 7/1/16.
 */
public class BufferLoaderService extends AbstractFileLoader {

    private final static Logger logger = Logger.getLogger(BufferLoaderService.class);

    private final int CUT_SIZE = 3;

    private final int FIXED_THREAD_POOL_SIZE = 3;

    private final int MAX_LINES_FOR_SYNC_PROCESSING = 1000;

    private final int OK = 1;

    public BufferLoaderService(EntityBuilder builder, EntityProcessor processor) {
        super(builder, processor);
    }

    @Override
    protected void perform() throws IOException {

        List<String> list;
        try (BufferedReader br = Files.newBufferedReader(Paths.get(fileName), StandardCharsets.US_ASCII)) {

            //br returns as stream and convert it into a List
            list = br.lines().collect(Collectors.toList());

            if (list.size() > MAX_LINES_FOR_SYNC_PROCESSING) {
                logger.info("Recognized file with more than " + MAX_LINES_FOR_SYNC_PROCESSING + " rows");

                int size = list.size();
                int cut = size / CUT_SIZE;

                //---
                //Diving the lines processing into 3 separated async callbacks
                //---
                ExecutorService es = Executors.newFixedThreadPool(FIXED_THREAD_POOL_SIZE);
                try {
                    logger.info("Executing callback Futures");
                    Future<Integer> fut1 = es.submit(() -> compute(list.subList(0, cut)));

                    Future<Integer> fut2 = es.submit(() -> compute(list.subList(cut, 2 * cut)));

                    Future<Integer> fut3 = es.submit(() -> compute(list.subList(2 * cut, size)));

                    while (!fut1.isDone() ||
                            !fut2.isDone() ||
                            !fut3.isDone()) {

                        //waiting to all promises to end
                        logger.debug("Future 1:" + fut1.isDone() + "|Future2:" + fut2.isDone() + "|Future3:" + fut3.isDone());
                    }
                    logger.info("All Futures returned...");

                } finally {
                    es.shutdown();
                }
            } else {
                compute(list);
            }
        }
    }

    private int compute(List<String> list) {
        list.forEach(line -> {
            DataEntity entity = builder.build(line);
            processor.process(entity);
        });
        return OK;
    }
}
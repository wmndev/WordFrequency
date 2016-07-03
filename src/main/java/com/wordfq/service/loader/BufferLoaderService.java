package com.wordfq.service.loader;

import com.wordfq.entity.DataEntity;
import com.wordfq.service.builder.EntityBuilder;
import com.wordfq.service.processor.EntityProcessor;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by wiseman on 7/1/16.
 */
public class BufferLoaderService extends AbstractFileLoader {

    final static Logger logger = Logger.getLogger(BufferLoaderService.class);

    public BufferLoaderService(EntityBuilder builder, EntityProcessor processor) {
        super(builder, processor);
    }

    @Override
    protected void perform() throws IOException {

        List<String> list;
        try (BufferedReader br = Files.newBufferedReader(Paths.get(fileName), Charset.forName("ASCII"))) {
            //br returns as stream and convert it into a List
            list = br.lines().collect(Collectors.toList());

            list.forEach(line -> {
                DataEntity entity = builder.build(line);
                processor.process(entity);
            });
        }
    }
}

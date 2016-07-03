package com.wordfq.service.builder;

import com.wordfq.entity.DataEntity;

import java.util.Arrays;
import java.util.HashSet;

/**
 * Created by wiseman on 7/1/16.
 */
public class SimpleEntityBuilder implements EntityBuilder {

    @Override
    public DataEntity<HashSet<String>> build(String line) {
        return new DataEntity<>(new HashSet<>(Arrays.asList(line.split(COMMA))));
    }
}

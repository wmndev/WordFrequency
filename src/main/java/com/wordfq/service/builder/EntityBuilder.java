package com.wordfq.service.builder;

import com.wordfq.entity.DataEntity;

/**
 * Created by wiseman on 7/1/16.
 */
public interface EntityBuilder {

    String COMMA = ",";

    /**
     * Encapsulate the file line data into a dedicated object
     *
     * @param line an individual file line
     * @return the encapsulated object
     */
    DataEntity build(String line);
}

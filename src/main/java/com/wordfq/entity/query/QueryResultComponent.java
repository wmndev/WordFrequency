package com.wordfq.entity.query;

import com.wordfq.entity.DataEntity;

import java.util.Map;

/**
 * Created by wiseman on 7/1/16.
 */
public interface QueryResultComponent {


    /**
     * Returns the result model
     *
     * @return the result model
     */
    Map<String, Integer> getResult();

    /**
     * Update the result model with a new word
     *
     * @param word the word to update the result with
     */
    void updateResult(String word);

    /**
     * Return the enclosed entity
     *
     * @return the entity
     */
    DataEntity getEntity();

}

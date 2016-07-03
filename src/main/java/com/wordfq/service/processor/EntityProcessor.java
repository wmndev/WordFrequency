package com.wordfq.service.processor;

/**
 * Created by wiseman on 7/1/16.
 */
public interface EntityProcessor<T> {

    /**
     * process the given entity
     *
     * @param entity the given entity
     */
    void process(T entity);
}

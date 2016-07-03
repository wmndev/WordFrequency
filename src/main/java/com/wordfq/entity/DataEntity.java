package com.wordfq.entity;

import java.util.Collection;

/**
 * Created by wiseman on 7/1/16.
 */
public class DataEntity<T extends Collection> {

    private T content;

    public DataEntity(T content) {
        this.content = content;
    }

    public T getContent() {
        return content;
    }
}

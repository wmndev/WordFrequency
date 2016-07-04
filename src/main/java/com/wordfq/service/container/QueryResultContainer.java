package com.wordfq.service.container;

import com.wordfq.entity.query.QueryResultComponentImpl;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by wiseman on 7/1/16.
 */
public class QueryResultContainer {

    private List<QueryResultComponentImpl> components;

    private static QueryResultContainer instance = new QueryResultContainer();

    public static QueryResultContainer getInstance() {
        return instance;
    }

    private QueryResultContainer() {
        components = new LinkedList<>();
    }

    public void addComponent(QueryResultComponentImpl component) {
        components.add(component);
    }

    public List<QueryResultComponentImpl> getComponents() {
        return components;
    }

    /**
     * clear all components (testing purposes)
     */
    public void clear() {
        components = new LinkedList<>();
    }
}

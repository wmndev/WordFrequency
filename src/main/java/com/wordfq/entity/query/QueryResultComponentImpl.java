package com.wordfq.entity.query;

import com.wordfq.entity.DataEntity;

import java.util.HashSet;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by wiseman on 7/1/16.
 */
public class QueryResultComponentImpl implements QueryResultComponent {

    private DataEntity<HashSet<String>> query;

    private Map<String, Integer> result;

    public QueryResultComponentImpl(DataEntity<HashSet<String>> query) {
        this.query = query;
        result = new TreeMap<>(); //TreeMap to keep keys sorted
    }

    @Override
    public Map<String, Integer> getResult() {
        return result;
    }

    @Override
    public synchronized void updateResult(String word) {
        //updating result map with the current word counter
        result.compute(word,
                (k, v) -> v == null ? 1 : ++v);
    }

    @Override
    public DataEntity<HashSet<String>> getEntity() {
        return query;
    }
}

package com.wordfq.service.processor;

import com.wordfq.entity.DataEntity;
import com.wordfq.entity.query.QueryResultComponentImpl;
import com.wordfq.service.container.QueryResultContainer;

import java.util.HashSet;

/**
 * Created by wiseman on 7/1/16.
 */
public class QueryEntityProcessor implements EntityProcessor<DataEntity<HashSet<String>>> {

    @Override
    public void process(DataEntity<HashSet<String>> query) {
        QueryResultContainer.getInstance().
                addComponent(new QueryResultComponentImpl(query));
    }
}

package com.wordfq.service.processor;

import com.wordfq.entity.DataEntity;
import com.wordfq.entity.query.QueryResultComponentImpl;
import com.wordfq.service.container.QueryResultContainer;

import java.util.HashSet;

/**
 * Created by wiseman on 7/1/16.
 */
public class RecordEntityProcessor implements EntityProcessor<DataEntity<HashSet<String>>> {

    @Override
    public void process(DataEntity<HashSet<String>> record) {

        QueryResultContainer.getInstance().getComponents().forEach(c -> {
            if (record.getContent().containsAll(c.getEntity().getContent())) {
                //record contains all query words
                updateResults(record, c);
            }
        });
    }

    private void updateResults(DataEntity<HashSet<String>> record, QueryResultComponentImpl qrComponent) {
        record.getContent().forEach(rWord -> {
            if (!qrComponent.getEntity().getContent().contains(rWord)) {
                qrComponent.updateResult(rWord);
            }
        });
    }
}

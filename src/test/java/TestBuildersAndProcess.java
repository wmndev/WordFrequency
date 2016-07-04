import com.wordfq.entity.DataEntity;
import com.wordfq.entity.query.QueryResultComponentImpl;
import com.wordfq.service.builder.QueryEntityBuilder;
import com.wordfq.service.builder.RecordEntityBuilder;
import com.wordfq.service.container.QueryResultContainer;
import com.wordfq.service.processor.QueryEntityProcessor;
import com.wordfq.service.processor.RecordEntityProcessor;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

/**
 * Created by wiseman on 7/1/16.
 */
public class TestBuildersAndProcess {

    HashSet<String> data;
    QueryResultComponentImpl qrc;
    DataEntity<HashSet<String>> dataEntity;

    @Before
    public void beforeTest() {
        QueryResultContainer.getInstance().clear();
        data = new HashSet<>();
        data.add("apple");
        data.add("cat");
        data.add("ball");
        dataEntity = new DataEntity<>(data);
        qrc = new QueryResultComponentImpl(new DataEntity<>(data));
    }

    @Test
    public void testQueryBuilder() {
        QueryEntityBuilder qeb = new QueryEntityBuilder();
        DataEntity de = qeb.build("apple,cat,ball");
        assertEquals(dataEntity.getContent(), de.getContent());
    }

    @Test
    public void testRecordBuilder() {
        RecordEntityBuilder qeb = new RecordEntityBuilder();
        DataEntity de = qeb.build("apple,cat,ball");
        assertEquals(dataEntity.getContent(), de.getContent());
    }

    @Test
    public void testQueryEntityProcessor() {
        QueryEntityProcessor qep = new QueryEntityProcessor();
        qep.process(dataEntity);

        List<QueryResultComponentImpl> items = QueryResultContainer.getInstance().getComponents();
        assertThat(items.size(), is(1));
    }

    @Test
    public void testRecordEntityProcessor() {
        QueryResultContainer.getInstance().clear();

        RecordEntityProcessor rep = new RecordEntityProcessor();
        rep.process(dataEntity);

        //no queries
        assertThat(QueryResultContainer.getInstance().getComponents().size(), is(0));
    }
}

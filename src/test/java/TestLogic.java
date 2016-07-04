import com.google.gson.Gson;
import com.wordfq.entity.DataEntity;
import com.wordfq.service.builder.QueryEntityBuilder;
import com.wordfq.service.builder.RecordEntityBuilder;
import com.wordfq.service.container.QueryResultContainer;
import com.wordfq.service.processor.QueryEntityProcessor;
import com.wordfq.service.processor.RecordEntityProcessor;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;


/**
 * Created by wiseman on 7/4/16.
 */
public class TestLogic {

    private final String QUERY_LINE_NOT_INCLUDED = "apple,cat,ball,dog";
    private final String QUERY_LINE_INCLUDED = "apple,dog";

    private final String RECORD_LINE = "apple,dog,door,chair";

    private final String RESULT = "{\"chair\":1,\"door\":1}";
    private final String EMPTY_RESULT = "{}";

    private DataEntity<HashSet<String>> queryIncluded;
    private DataEntity<HashSet<String>> queryNotIncluded;

    private DataEntity<HashSet<String>> record;

    private Gson gson;

    @Before
    public void beforeTest(){
        QueryResultContainer.getInstance().clear();
        record = new RecordEntityBuilder().build(RECORD_LINE);
        queryIncluded = new QueryEntityBuilder().build(QUERY_LINE_INCLUDED);
        queryNotIncluded = new QueryEntityBuilder().build(QUERY_LINE_NOT_INCLUDED);
        gson = new Gson();
    }

    @Test
    public void testEmptyCorrectResult(){
        new QueryEntityProcessor().process(queryNotIncluded);
        new RecordEntityProcessor().process(record);

        assertThat(QueryResultContainer.getInstance().getComponents().size(), is(1));

        Map<String, Integer> result = QueryResultContainer.getInstance().getComponents().get(0).getResult();
        assertThat(result.size(), is(0));

        assertEquals(EMPTY_RESULT, gson.toJson(result));
    }

    @Test
    public void testNotEmptyCorrectResult(){
        QueryResultContainer.getInstance().clear();
        new QueryEntityProcessor().process(queryIncluded);
        new RecordEntityProcessor().process(record);

        Map<String, Integer> result = QueryResultContainer.getInstance().getComponents().get(0).getResult();
        assertThat(QueryResultContainer.getInstance().getComponents().size(), is(1));
        assertThat(result.size(), is(2));

        assertEquals(RESULT, gson.toJson(result));
    }
}

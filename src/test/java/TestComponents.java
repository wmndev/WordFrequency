import com.wordfq.entity.DataEntity;
import com.wordfq.entity.query.QueryResultComponentImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by wiseman on 7/4/16.
 */
public class TestComponents {

    HashSet<String> data;

    @Before
    public void runBeforeTestMethod() {
        data = new HashSet<>();
        data.add("apple");
        data.add("cat");
        data.add("ball");

    }

    @Test
    public void testDataEntityConstructor() {
        DataEntity<HashSet<String>> testDataEntity = new DataEntity<>(data);
        assertTrue(data.containsAll(testDataEntity.getContent()));
    }

    @Test
    public void testQRComponentConstructor() {
        DataEntity<HashSet<String>> testDataEntity = new DataEntity<>(data);
        QueryResultComponentImpl qrc = new QueryResultComponentImpl(testDataEntity);
        assertTrue(data.containsAll(qrc.getEntity().getContent()));
        assertNotNull(qrc.getResult());
        assertThat(qrc.getResult().size(), is(0));
    }
}

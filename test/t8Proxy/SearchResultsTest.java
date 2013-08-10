package t8Proxy;

import org.junit.Rule;
import org.junit.Test;
import org.rococoa.okeydoke.junit.ApprovalsRule;
import t8Proxy.SearchResults;

import java.util.Arrays;

public class SearchResultsTest {

    @Rule public final ApprovalsRule approver = ApprovalsRule.fileSystemRule("test");

    @Test public void test() {
        String json = SearchResults.format(Arrays.asList("apple", "banana", "kumquat"));
        approver.assertApproved(json);
    }

}

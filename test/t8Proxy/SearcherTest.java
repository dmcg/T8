package t8Proxy;

import org.junit.Test;
import t8Proxy.HttpFetcher;
import t8Proxy.Searcher;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import static org.junit.Assert.assertFalse;

public class SearcherTest {

    private final Searcher searcher = new Searcher("services.stb.search.sky.com", 80, new HttpFetcher());

    @Test public void test() throws IOException, URISyntaxException {
        List<String> results = searcher.searchFor("news");
        assertFalse(results.isEmpty());
    }
}

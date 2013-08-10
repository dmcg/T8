package t8Proxy;

import org.junit.Test;
import t8Proxy.HttpFetcher;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;


public class HttpFetcherTest {

    private final HttpFetcher fetcher = new HttpFetcher();

    @Test
    public void fetch() throws IOException, URISyntaxException {
        String response = fetcher.fetch(new URI("http://www.example.com/"));
        assertThat(response, containsString("Example Domain"));
    }
}

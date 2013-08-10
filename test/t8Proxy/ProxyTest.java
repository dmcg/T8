package t8Proxy;

import org.junit.Ignore;
import org.junit.Test;
import picasso.t8.FileReader;
import picasso.t8.T8;
import t8Proxy.HttpFetcher;
import t8Proxy.Proxy;
import t8Proxy.Searcher;

import java.io.Console;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import static org.junit.Assert.assertFalse;

public class ProxyTest {

    private final Proxy proxy;
    private final HttpFetcher fetcher = new HttpFetcher();

    public ProxyTest() throws IOException {
        proxy = new Proxy(8000, "services.stb.search.sky.com", 80,
                new Searcher("services.stb.search.sky.com", 80, fetcher), fetcher,
                new T8(FileReader.readWords(new File("wordlist.txt"))));
    }

    @Test public void test() throws IOException, URISyntaxException {
        proxy.start();
        List<String> result = new Searcher("localhost", 8000, fetcher).searchFor("ne");
        System.out.println(result);
        assertFalse(result.isEmpty());
    }

    @Ignore("manual")
    @Test public void run() throws IOException {
        proxy.start();
        System.out.println("Proxy running, kill test");
        System.in.read();
    }
}

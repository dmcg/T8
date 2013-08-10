package t8Proxy;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import picasso.t8.Root;
import picasso.t8.T8;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

public class Proxy {

    private final HttpServer server;
    private final HttpFetcher fetcher;
    private final T8 t8;
    private final String host;
    private final int port;
    private Searcher searcher;

    public Proxy(int myPort, String host, int port, Searcher searcher, HttpFetcher fetcher, T8 t8) throws IOException {
        this.host = host;
        this.port = port;
        this.fetcher = fetcher;
        this.searcher = searcher;
        this.t8 = t8;

        server = HttpServer.create(new InetSocketAddress(myPort), 0);
        server.setExecutor(null);
        server.createContext("/", new MyHandler());
    }

    public void start() {
        server.start();
    }

    private class MyHandler implements HttpHandler {
        public void handle(HttpExchange t) throws IOException {
            String searchTerm = queryParametersFrom(t.getRequestURI()).get("term");
            List<String> combinedResults = fetchResultsFor(t8.rootsFor(t8.inputFor(searchTerm)).subList(0, 3));
            String response = SearchResults.format(combinedResults);
            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }

    private List<String> fetchResultsFor(List<Root> roots) {
        List<String> combined = Lists.newArrayList();
        for (Root root : roots) {
            try {
                combined.addAll(searcher.searchFor(root.root));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return combined;
    }

    private URI uri(URI uri) throws IOException {
        try {
            return new URI("http://" + host + ":" + port + uri);
        } catch (URISyntaxException e) {
            throw new IOException(e);
        }
    }

    private ImmutableMap<String, String> queryParametersFrom(URI uri) {
        Map<String, String> result = Maps.newHashMap();
        String[] pairs = uri.getQuery().split("&");
        for (String pair : pairs) {
            String[] keyValue = pair.split("=");
            result.put(keyValue[0], keyValue[1]);
        }
        return ImmutableMap.copyOf(result);
    }

}

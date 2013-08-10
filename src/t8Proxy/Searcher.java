package t8Proxy;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Searcher {

    private static final String URL_TEMPLATE = "http://%s:%s/auto-suggest/stb?term=%s&epgInfoBits=00020000&epgInfoBitsMask=001a0000&uid=1742948066";

    private final String host;
    private int port;
    private final HttpFetcher fetcher;

    public Searcher(String host, int port, HttpFetcher fetcher) {
        this.host = host;
        this.port = port;
        this.fetcher = fetcher;
    }

    public List<String> searchFor(String term) throws IOException, URISyntaxException {
        String response = fetcher.fetch(new URI(String.format(URL_TEMPLATE, host, port, term)));
        Matcher matcher = Pattern.compile("\\{\"t\":\"(.*?)\"").matcher(response);
        ArrayList<String> result = Lists.newArrayList();
        while (matcher.find()) {
            result.add(matcher.group(1));
        }
        return ImmutableList.copyOf(result);
    }
}

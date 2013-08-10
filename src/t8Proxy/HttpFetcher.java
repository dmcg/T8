package t8Proxy;

import com.google.common.base.Charsets;
import com.google.common.io.CharStreams;
import com.google.common.io.Closeables;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;

public class HttpFetcher {

    public String fetch(URI uri) throws IOException {
        InputStream is = uri.toURL().openStream();
        try {
            return CharStreams.toString(new InputStreamReader(is, Charsets.UTF_8));
        } finally {
            Closeables.closeQuietly(is);
        }
    }
}

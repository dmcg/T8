package t8Proxy;

import java.util.List;

public class SearchResults {
    public static String format(List<String> terms) {
        StringBuilder result = new StringBuilder("{\"terms\":[");
        for (String term : terms) {
            result.append("{\"t\":\"").append(term).append("\",\"s\":\"general\"},");
        }
        result.deleteCharAt(result.length() - 1);
        result.append("],\"products\":[]}");
        return result.toString();
    }
}

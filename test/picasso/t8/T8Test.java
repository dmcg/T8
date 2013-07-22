package picasso.t8;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.io.Files;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.rococoa.okeydoke.Transcript;
import org.rococoa.okeydoke.junit.ApprovalsRule;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertTrue;

public class T8Test {

    @Rule public final ApprovalsRule approver = ApprovalsRule.fileSystemRule("test");
    private Transcript transcript;

    @Before public void setup() throws IOException {
        transcript = approver.transcript();
    }


    @Test public void generateLookup() throws IOException {
        List<String> words = readWords(new File("words.small.txt"));

        Transcript transcript = approver.transcript();
        given("a wordlist", words);
        when("I enter", "a");
        then("the search is made for ", lookupFor("a"));
    }

    private List<String> lookupFor(String a) {
        return Arrays.asList("a", "b");
    }

    private Transcript given(String given, Object thing) throws IOException {
        return term("given", given, thing);
    }

    private Transcript when(String when, Object thing) throws IOException {
        return term("when", when, thing);
    }

    private Transcript then(String when, Object thing) throws IOException {
        return term("then", when, thing);
    }

    private Transcript term(String term, String description, Object thing) {
        transcript.appendLine(term + " " + description).appendFormatted(thing).endl();
        return transcript;
    }

    private Map<String, Integer> generateLookup(List<String> words) {
        Map<String, Integer> result = Maps.newHashMap();

        result.put("a", 3);
        result.put("b", 4);
        for (String word : words) {
            for (char c : word.toCharArray()) {

            }

        }
        return result;
    }

    public List<String> readWords(File file) throws IOException {
        return Files.readLines(file, Charset.defaultCharset());
    }


}

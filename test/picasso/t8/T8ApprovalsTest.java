package picasso.t8;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.common.io.Files;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.rococoa.okeydoke.Transcript;
import org.rococoa.okeydoke.junit.ApprovalsRule;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@Ignore("broken for now")
public class T8ApprovalsTest {

    @Rule public final ApprovalsRule approver = ApprovalsRule.fileSystemRule("test");
    private Transcript transcript;

    @Before public void setup() throws IOException {
        transcript = approver.transcript();
    }

//    @Test public void lookup() throws IOException {
//        List<String> words = readWords(new File("words.small.txt"));
//
//        given("a word list", words);
//        when("I enter", "a");
//        then("the search is made for ", lookupFor(words, "a"));
//    }


    private String inputFor(String word) {
        StringBuilder result = new StringBuilder(word.length());
        for (char c : word.toCharArray()) {
            result.append(inputCharFor(c));
        }
        return result.toString();
    }

    private ImmutableList<String> stemsFor(String word) {
        List<String> result = Lists.newArrayList();
        for (int i = 1; i <= word.length(); i++) {
            result.add(word.substring(0, i));
        }
        return ImmutableList.copyOf(result);
    }

    private char inputCharFor(char c) {
        switch (c) {
            case 'a':case 'b':case 'c' : return 'a';
            case 'd':case 'e':case 'f' : return 'd';
        }
        return ' ';
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
        transcript.append(term + " " + description).appendFormatted(thing).endl();
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

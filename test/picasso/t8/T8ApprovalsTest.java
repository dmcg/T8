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

public class T8ApprovalsTest {

    @Rule public final ApprovalsRule approver = ApprovalsRule.fileSystemRule("test");
    private Transcript transcript;

    @Before public void setup() throws IOException {
        transcript = approver.transcript();
    }

    @Test public void suggestions() throws IOException {
        File file = new File("wordlist.txt");
        T8 t8 = new T8(readWords(file));

        given("the words in ", file);
        when("I enter", "2");
        then("the search is made for ", t8.suggestionsFor("2"));
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
        transcript.append(term + " " + description + " ").appendFormatted(thing).endl();
        return transcript;
    }

    public List<String> readWords(File file) throws IOException {
        return Files.readLines(file, Charset.defaultCharset());
    }

}

package picasso.t8;

import com.google.common.base.Function;
import com.google.common.io.Files;
import org.junit.Rule;
import org.junit.Test;
import org.rococoa.okeydoke.Transcript;
import org.rococoa.okeydoke.bdd.Pickle;
import org.rococoa.okeydoke.bdd.Scenario;
import org.rococoa.okeydoke.junit.ApprovalsRule;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class T8ApprovalsTest {

    @Rule public final ApprovalsRule approver = ApprovalsRule.fileSystemRule("test");
    private final File file = new File("wordlist.txt");
    private final T8 t8 = new T8(readWords(file));

    @Test public void suggestions() throws IOException {
        Scenario scenario = new Pickle(approver.transcript()).scenario("Suggestions");
        scenario.given("the words in", file);
        whenThen(scenario, "I enter", "duck", "the suggestions are", new Function<String, Object>() {
            public Object apply(String input) {
                return t8.suggestionsFor(input);
            }
        });
    }

    @Test public void roots() throws IOException {
        Scenario scenario = new Pickle(approver.transcript()).scenario("Roots");
        scenario.given("the words in", file);
        whenThen(scenario, "I enter", "duck", "the roots are", new Function<String, Object>() {
            public Object apply(String input) {
                return t8.rootsFor(input);
            }
        });
    }

    private void whenThen(Scenario scenario, String when, String word, String then, Function<String, Object> f) throws IOException {
        scenario.when(when, word);
        String input = t8.inputFor(word);
        scenario.then(then, f.apply(input));
    }

    public List<String> readWords(File file) {
        try {
            return Files.readLines(file, Charset.defaultCharset());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}

package picasso.t8;

import com.google.common.base.Function;
import org.junit.Rule;
import org.junit.Test;
import org.rococoa.okeydoke.formatters.TableFormatter;
import org.rococoa.okeydoke.internal.MappingIterable;
import org.rococoa.okeydoke.junit.ApprovalsRule;
import org.rococoa.okeydoke.pickle.Pickle;
import org.rococoa.okeydoke.pickle.Scenario;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class T8ApprovalsTest {

    @Rule public final ApprovalsRule approver = ApprovalsRule.fileSystemRule("test");
    private final File file = new File("wordlist.txt");
    private final T8 t8 = new T8(FileReader.readWords(file));

    @Test public void suggestions() throws IOException {
        Scenario scenario = new Pickle(approver.transcript()).scenario("Suggestions");
        scenario.given("the words in", file);
        whenThen(scenario, "I enter", "duck", "the suggestions are", new Function<String, Iterable<?>>() {
            public Iterable<String> apply(String input) {
                return t8.suggestionsFor(input);
            }
        });
    }

    @Test public void roots() throws IOException {
        Scenario scenario = new Pickle(approver.transcript()).scenario("Roots");
        scenario.given("the words in", file);
        whenThen(scenario, "I enter", "duck", "the roots are", new Function<String, Iterable<?>>() {
            public Iterable<?> apply(String input) {
                return columnise(t8.rootsFor(input));
            }
        });
    }

    private Iterable<?> columnise(final List<Root> roots) {
        return new MappingIterable<Object, Root>(roots) {
            @Override protected Object map(Root thing) {
                return new Object[] {thing.root, thing.frequency};
            }
        };
    }

    private void whenThen(Scenario scenario, String when, String word, String then, Function<String, Iterable<?>> f) throws IOException {
        scenario.when(when, word);
        String input = t8.inputFor(word);
        scenario.then(then);
        scenario.appendFormatted(f.apply(input), TableFormatter.instance());
    }

}

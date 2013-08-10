package picasso.t8;

import com.google.common.base.Function;
import org.jmock.api.Invocation;
import org.jmock.api.Invokable;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.rococoa.okeydoke.Formatter;
import org.rococoa.okeydoke.formatters.DefaultInvocationFormatter;
import org.rococoa.okeydoke.formatters.TableFormatter;
import org.rococoa.okeydoke.internal.Fred;
import org.rococoa.okeydoke.internal.Mapper;
import org.rococoa.okeydoke.internal.MappingIterable;
import org.rococoa.okeydoke.pickle.Feature;
import org.rococoa.okeydoke.pickle.FeatureRule;
import org.rococoa.okeydoke.pickle.ScenarioRule;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.List;

import static org.rococoa.okeydoke.junit.ApprovalsRule.fileSystemRule;

@Feature("T8")
public class T8ApprovalsTest {

    @ClassRule public static final FeatureRule feature = new FeatureRule(fileSystemRule("test"));
    @Rule public final ScenarioRule scenario = feature.scenarioRule();

    private T8 t8;

    @Before public void given() {
        File file = new File("wordlist.txt");
        scenario.given("the words in", file);
        t8 = new T8(FileReader.readWords(file));
    }

    @Test public void suggestions() throws IOException {
        String input = t8.inputFor("duck");
        scenario.given("the input for", "duck", "is", input);
        scenario.when("I enter", input);
        thenWith(t8, "the suggestions are\n", TableFormatter.instance()).suggestionsFor(input);
    }

    @Test public void roots() throws IOException {
        String input = t8.inputFor("duck");
        scenario.given("the input for", "duck", "is", input);
        scenario.when("I enter", input);
        thenWith(t8, "the suggestions are\n",
                new TableFormatter().withMapper(rootMapper()).withHeaders("root", "freq")).rootsFor(input);
    }

    private Mapper<Root, Object[]> rootMapper() {
        return new Mapper<Root, Object[]>() {
            @Override
            public Object[] map(Root next) {
                return new Object[] {next.root, next.frequency};            }
        };
    }

    private <T> T thenWith(final T object, final String then, final Formatter<Object, String> formatter) {
        InvocationHandler handler = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Object result = method.invoke(object, args);
                scenario.then(then, formatter, result);
                return result;
            }
        };
        return (T) Fred.newProxyInstance(object.getClass(), handler);
    }

}

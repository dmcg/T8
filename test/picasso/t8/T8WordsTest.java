package picasso.t8;

import com.sun.tools.javac.util.Pair;
import org.junit.Test;

import java.util.Collection;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static picasso.t8.T8.Root;

public class T8WordsTest {

    private final T8 t8 = new T8(asList("a", "aa", "ab", "ac", "ad", "aab", "aad", "d"));

    @Test public void suggestionsFor() {
        checkSuggestions("1", new String[0]);
        checkSuggestions("3", "d");
        checkSuggestions("2", "a", "aa", "ab", "ac", "ad", "aab", "aad");
        checkSuggestions("22", "aa", "ab", "ac", "aab", "aad");
        checkSuggestions("222", "aab");
    }

    @Test public void rootsFor() {
        checkRoots("1", new Root[0]);
        checkRoots("3", root("d", 1));
        checkRoots("2", root("a", 7));
        checkRoots("22", root("aa", 3), root("ab", 1), root("ac", 1));
    }

    private Root root(String root, int frequency) {
        return new Root(root, frequency);
    }

    private void checkRoots(String input, Root... roots) {
        assertEquals(asList(roots), t8.rootsFor(input));

    }

    private void checkSuggestions(String input, String... suggestions) {
        assertEquals(asList(suggestions), t8.suggestionsFor(input));
    }

}

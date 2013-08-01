package picasso.t8;

import com.google.common.collect.*;

import java.util.*;

public class T8 {

    private final Map<Character, Character> charToInput = charToInput();
    private final Multimap<String, String> inputToWords = ArrayListMultimap.create();


    public T8(List<String> words) {
        for (String word : words) {
            for (String stem : inputStemsFor(word)) {
                inputToWords.put(stem, word);
            }
        }
    }

    public List<Root> rootsFor(String input) {
        Multimap<String, String> intermediate = ArrayListMultimap.create();
        for (String suggestion : suggestionsFor(input)) {
            String rooted = suggestion.substring(0, input.length());
            intermediate.put(rooted, rooted);
        }

        List<Root> result = Lists.newArrayList();
        for (Map.Entry<String, Collection<String>> entry : intermediate.asMap().entrySet()) {
            result.add(new Root(entry.getKey(), entry.getValue().size()));
        }
        Collections.sort(result);
        return result;
    }

    public Collection<String> suggestionsFor(String input) {
        return inputToWords.get(input);
    }

    public char inputCharFor(char c) {
        Character result = charToInput.get(c);
        return result == null ? '1' : result;
    }

    public String inputFor(String chars) {
        StringBuilder result = new StringBuilder(chars.length());
        for (char c : chars.toCharArray()) {
            result.append(inputCharFor(c));
        }
        return result.toString();
    }

    protected ImmutableList<String> inputStemsFor(String word) {
        List<String> result = Lists.newArrayList();
        for (String stem : stemsFor(word)) {
            result.add(inputFor(stem));
        }
        return ImmutableList.copyOf(result);
    }

    protected ImmutableList<String> stemsFor(String word) {
        List<String> result = Lists.newArrayList();
        for (int i = 1; i <= word.length(); i++) {
            result.add(word.substring(0, i));
        }
        return ImmutableList.copyOf(result);
    }

    private HashMap<Character, Character> charToInput() {
        HashMap<Character, Character> result = Maps.newHashMap();
        result.put('a', '2');
        result.put('b', '2');
        result.put('c', '2');
        result.put('d', '3');
        result.put('e', '3');
        result.put('f', '3');
        result.put('g', '4');
        result.put('h', '4');
        result.put('i', '4');
        result.put('j', '5');
        result.put('k', '5');
        result.put('l', '6');
        result.put('m', '6');
        result.put('n', '6');
        result.put('o', '6');
        result.put('p', '7');
        result.put('q', '7');
        result.put('r', '7');
        result.put('s', '8');
        result.put('t', '8');
        result.put('u', '8');
        result.put('v', '9');
        result.put('w', '9');
        result.put('x', '9');
        result.put('y', '0');
        result.put('z', '0');
        return result;
    }

}



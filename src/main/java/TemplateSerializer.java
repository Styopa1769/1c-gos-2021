import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TemplateSerializer {
    private final static Character DELIMITER = '#';
    private final static String FORMATTER = "%s";
    private final List<Pattern> patterns;
    private final String format;
    private final boolean hasValueAtStart;
    private final boolean hasValueAtEnd;

    public TemplateSerializer(String template) {
        String[] subStrings = template.split(String.valueOf(DELIMITER), -1);
        List<Pattern> patterns = new ArrayList<>(subStrings.length);
        for (String subString : subStrings) {
            if (!subString.isEmpty())
                patterns.add(new Pattern(subString));
        }

        if (patterns.size() == 1 && patterns.get(0).getText().equals(template) || template.isEmpty())
            throw new IllegalArgumentException("Неправильный вид шаблона");

        this.patterns = patterns;

        boolean hasValueAtStart = false;
        boolean hasValueAtEnd = false;
        String format = String.join(FORMATTER, subStrings);

        if (template.charAt(0) == DELIMITER){
            hasValueAtStart = true;
        }
        if (template.charAt(template.length()-1) == DELIMITER){
            hasValueAtEnd = true;
        }

        this.format = format;
        this.hasValueAtStart = hasValueAtStart;
        this.hasValueAtEnd = hasValueAtEnd;
    }

    public List<String> serialize(String text){
        List<String> result = new ArrayList<>();

        Map<Pattern, Integer> indexCache = new HashMap<>(patterns.size());

        patterns.forEach(pattern -> indexCache.put(pattern, KMP.search(pattern, text)));

        if (hasValueAtStart)
            result.add(text.substring(0, indexCache.get(patterns.get(0))));

        for (int i = 0; i < patterns.size() - 1; i++) {
            Pattern leftPattern = patterns.get(i);
            Pattern rightPattern = patterns.get(i+1);
            result.add(text.substring(indexCache.get(leftPattern) + leftPattern.getLength(), indexCache.get(rightPattern)));
        }

        if (hasValueAtEnd)
            result.add(text.substring(indexCache.get(patterns.get(patterns.size() - 1)) + patterns.get(patterns.size() - 1).getLength()));

        return result;
    }

    public String deserialize(List<String> values){
        return String.format(format, values.toArray());
    }
}

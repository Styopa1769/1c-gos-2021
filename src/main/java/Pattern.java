import java.util.Objects;

/**
 * Вспомогательный класс для работы с {@link KMP}
 */
public class Pattern {
    private final String text;
    private final int[] prefixTable;
    private final int length;

    /**
     * Конструктор
     *
     * @param pattern паттерн
     */
    public Pattern(String pattern) {
        this.text = pattern;
        this.prefixTable = computePrefixTable(pattern);
        this.length = pattern.length();
    }

    /**
     * Составляет таблицу префиксов для паттерна
     * @param pattern паттерн
     * @return таблица префиксов
     */
    private static int[] computePrefixTable(String pattern)
    {
        int[] result = new int[pattern.length()];
        if (pattern.isEmpty())
            return result;
        int prevLongestPrefixLength = 0;
        int i = 1;
        result[0] = 0;

        while (i < pattern.length()) {
            if (pattern.charAt(i) == pattern.charAt(prevLongestPrefixLength)) {
                prevLongestPrefixLength++;
                result[i] = prevLongestPrefixLength;
                i++;
            }
            else
            {
                if (prevLongestPrefixLength != 0) {
                    prevLongestPrefixLength = result[prevLongestPrefixLength - 1];
                }
                else
                {
                    result[i] = prevLongestPrefixLength;
                    i++;
                }
            }
        }

        return result;
    }

    /**
     * @return сам паттерн
     */
    public String getText() {
        return text;
    }

    /**
     * @return таблица префиксов для паттерна
     */
    public int[] getPrefixTable() {
        return prefixTable;
    }

    /**
     * @return длина паттерна
     */
    public int getLength() {
        return length;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pattern pattern = (Pattern) o;
        return text.equals(pattern.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text);
    }
}

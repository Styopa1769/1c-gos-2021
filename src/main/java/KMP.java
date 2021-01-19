/**
 * Реализация алгоритма Кнута-Морриса-Пратта
 * @see <a href="https://en.wikipedia.org/wiki/Knuth-Morris-Pratt_algorithm">Knuth-Morris-Pratt algorithm</a>
 */
public class KMP {
    /**
     * Функция для поиска индекса первого вхождения паттерна в строку
     * @param pattern паттерн
     * @param text строка
     * @return индекс первого вхождения или -1, если вхождение не найдено
     */
    public static int search(Pattern pattern, String text){
        int patternIndex = 0;

        int textIndex = 0;
        while (textIndex < text.length() && !pattern.getText().isEmpty()) {
            if (pattern.getText().charAt(patternIndex) == text.charAt(textIndex)) {
                patternIndex++;
                textIndex++;
            }
            if (patternIndex == pattern.getLength()) {
                return textIndex - patternIndex;
            }
            else if (textIndex < text.length() && pattern.getText().charAt(patternIndex) != text.charAt(textIndex)) {
                if (patternIndex != 0)
                    patternIndex = pattern.getPrefixTable()[patternIndex - 1];
                else
                    textIndex = textIndex + 1;
            }
        }

        return -1;
    }
}

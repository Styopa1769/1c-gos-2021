import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Тестирует различные вариации паттернов
 */
public class TemplateSerializerTest {

    @DisplayName("Тест паттерна такси")
    @Test
    void test_taxi_pattern(){
        TemplateSerializer templateSerializer = new TemplateSerializer("В модуле # произошла ошибка # водителя #");
        List<String> texts = Arrays.asList(
                "В модуле такси произошла ошибка доступа водителя Ивана",
                "В модуле доставки произошла ошибка подключения водителя Степана");
        testPattern(templateSerializer, texts);
    }

    @DisplayName("Тест специфичных паттернов")
    @Test
    void test_specific_patterns(){
        TemplateSerializer chatTemplateSerializer = new TemplateSerializer("# зашел в чат");
        List<String> chatTexts = Arrays.asList("Иван зашел в чат", "Степан зашел в чат");
        testPattern(chatTemplateSerializer, chatTexts);

        TemplateSerializer happyBirthdayTemplateSerializer = new TemplateSerializer("С Днем Рождения, #");
        List<String> hbTexts = Arrays.asList("С Днем Рождения, Иван", "С Днем Рождения, Степан");
        testPattern(happyBirthdayTemplateSerializer, hbTexts);
    }

    @DisplayName("Тест неверно заданного шаблона")
    @Test
    void test_wrong_template(){
        assertThrows(IllegalArgumentException.class, () -> new TemplateSerializer("Шаблон без переменных"));
        assertThrows(IllegalArgumentException.class, () -> new TemplateSerializer(""));
    }

    private static void testPattern(TemplateSerializer templateSerializer, List<String> texts){
        texts.forEach(text -> assertEquals(text, templateSerializer.deserialize(templateSerializer.serialize(text))));
    }
}

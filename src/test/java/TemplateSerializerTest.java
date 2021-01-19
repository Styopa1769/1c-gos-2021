import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Тестирует различные вариации паттернов
 */
public class TemplateSerializerTest {

    @DisplayName("Тест сериализации из файла и десериализации в файл")
    @Test
    void test_file_serialization(){
        TemplateSerializer taxiTemplateSerializer = new TemplateSerializer("В модуле # произошла ошибка # водителя #");
        testFromFile(taxiTemplateSerializer, "taxi.example");
    }

    @DisplayName("Тест специфичных паттернов")
    @Test
    void test_specific_patterns(){
        TemplateSerializer chatTemplateSerializer = new TemplateSerializer("# зашел в чат");
        testFromFile(chatTemplateSerializer, "chat.example");

        TemplateSerializer happyBirthdayTemplateSerializer = new TemplateSerializer("С Днем Рождения, #");
        testFromFile(happyBirthdayTemplateSerializer, "hb.example");
    }

    @DisplayName("Тест неверно заданного шаблона")
    @Test
    void test_wrong_template(){
        assertThrows(IllegalArgumentException.class, () -> new TemplateSerializer("Шаблон без переменных"));
        assertThrows(IllegalArgumentException.class, () -> new TemplateSerializer(""));
    }

    /**
     * Вспомогательный класс для тестирования сериализации из файла и десериализации во временный файл
     * @param templateSerializer шаблон для сериализации
     * @param inputFileName имя файла с данными из ресурсов
     */
    private static void testFromFile(TemplateSerializer templateSerializer, String inputFileName){
        try {
            Path input = Paths.get(TemplateSerializerTest.class.getResource(inputFileName).toURI());
            List<List<String>> serialized = templateSerializer.serialize(input);

            Path tmp = Files.createTempFile("test"+ UUID.randomUUID(), ".example");
            templateSerializer.deserialize(serialized, tmp);

            assertEquals(Files.readAllLines(input), Files.readAllLines(tmp));

            Files.delete(tmp);
        } catch (URISyntaxException | IOException e) {
            Assertions.fail("Тест провалился из-за проблем с файлами", e);
        }
    }
}

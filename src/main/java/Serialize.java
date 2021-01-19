import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Serialize {

    public static void main(String[] args) {
        File input = new File(args[0]);
        File output = new File(args[1]);

        TemplateSerializer templateSerializer = new TemplateSerializer("В модуле # произошла ошибка # водителя #");

        try(BufferedReader br = Files.newBufferedReader(Paths.get(input.toURI()));
            BufferedWriter bw = Files.newBufferedWriter(Paths.get(output.toURI()))) {
            while (br.ready()){
                bw.write(String.join(",", templateSerializer.serializeLine(br.readLine())));
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class Deserialize {
    public static void main(String[] args) {
        File input = new File(args[0]);
        File output = new File(args[1]);

        TemplateSerializer templateSerializer = new TemplateSerializer("В модуле # произошла ошибка # водителя #");

        try(BufferedReader br = Files.newBufferedReader(Paths.get(input.toURI()));
            BufferedWriter bw = Files.newBufferedWriter(Paths.get(output.toURI()))) {
            while (br.ready()){
                String line = templateSerializer.deserializeLine(Arrays.asList(br.readLine().split(",")));
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

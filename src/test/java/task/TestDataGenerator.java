package task;

import org.junit.jupiter.params.provider.Arguments;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class TestDataGenerator {

    public static Stream<Arguments> loadTestData() {
        List<Arguments> argumentsList = new ArrayList<>();
        String basePath = "src" + File.separator + "main" + File.separator + "java" + File.separator + "odovzdavky";

        try (Stream<Path> paths = Files.walk(Paths.get(basePath))) {
            paths.filter(Files::isRegularFile)
                    .filter(path -> path.toString().endsWith(".java"))
                    .forEach(path -> {
                        String studentPath = path.toString()
                                .replace("src" + File.separator + "main" + File.separator + "java" + File.separator, "")
                                .replace(".java", "")
                                .replace(File.separator, ".");
                        argumentsList.add(Arguments.of(studentPath));
                    });
        } catch (IOException e) {
            System.err.println("Chyba pri čítaní súborov: " + e.getMessage());
        }

        return argumentsList.stream();
    }
}

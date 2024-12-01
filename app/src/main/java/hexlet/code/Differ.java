package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.TreeMap;
import java.util.Set;
import java.util.TreeSet;
import java.util.Objects;

public class Differ {

    public static String generate(String filePathOne, String filePathTwo) throws Exception {
        String filePath1 = Differ.class.getClassLoader().getResource("file1.json").getPath();
        String filePath2 = Differ.class.getClassLoader().getResource("file2.json").getPath();
        Map<String, Object> map1 = new TreeMap<>(parseFile(filePath1));
        Map<String, Object> map2 = new TreeMap<>(parseFile(filePath2));

        // Разделяем ключи по состояниям
        Set<String> allKeys = new TreeSet<>();
        allKeys.addAll(map1.keySet());
        allKeys.addAll(map2.keySet());
        // Формируем строку с разницей между картами
        return "{\n" + allKeys.stream()
        .map(key -> {
            Object value1 = map1.get(key);
            Object value2 = map2.get(key);
            // Формируем строку для каждого ключа в зависимости от различий
            return map1.containsKey(key) && !map2.containsKey(key)
                ? "  - " + key + ": " + value1
                : !map1.containsKey(key) && map2.containsKey(key)
                ? "  + " + key + ": " + value2
                : !Objects.equals(value1, value2)
                ? "  - " + key + ": " + value1 + "\n  + " + key + ": " + value2
                : "    " + key + ": " + value1;
        })
        .collect(Collectors.joining("\n")) + "\n}";
    }

    // Метод для парсинга файла
    public static Map<String, Object> parseFile(String filePath) throws Exception {
        // Чтение содержимого файла
        try {
            String content = Files.readString(Paths.get(filePath));
            // Парсинг содержимого в Map
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(content, Map.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse file: " + filePath + ". Error: " + e.getMessage(), e);
        }
    }
}

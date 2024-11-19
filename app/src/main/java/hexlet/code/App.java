package hexlet.code;

import java.util.concurrent.Callable;
import picocli.CommandLine;
import picocli.CommandLine.Command;

@Command(
    name = "gendiff",
    mixinStandardHelpOptions = true,
    version = "gendiff 1.0",
    description = "Compares two configuration files and shows a difference.")
public class App implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        // Логика приложения
        System.out.println("This is where the application logic will go.");
        return 0; // Код успешного завершения
    }

    public static void main(String[] args) {
        // Запуск приложения с передачей аргументов
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}

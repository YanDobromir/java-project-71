package hexlet.code;
import java.util.concurrent.Callable;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

@Command(
    name = "gendiff",
    mixinStandardHelpOptions = true,
    version = "gendiff 1.0",
    usageHelpAutoWidth = true,
    description = "Compares two configuration files and shows a difference.")

public class App implements Callable<Integer> {

    @Option(
        names = {"-f", "--format"},
        description = "output format [default: stylish]",
        paramLabel = "format",
        defaultValue = "stylish")
        private String format;

    @Parameters(
        index = "0",
        description = "path to first file",
        paramLabel = "filepath1"
    )
    private String filepath1;

    @Parameters(
        index = "1",
        description = "path to second file",
        paramLabel = "filepath2"
    )
    private String filepath2;
    @Override

    public Integer call() throws Exception {
        try {
            String diff = Differ.generate(filepath1, filepath2);
            System.out.println(diff);
            return 0;
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            return 1; // Код ошибки
        }
    }

    public static void main(String[] args) {
        // Запуск приложения с передачей аргументов
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}

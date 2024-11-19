import picocli.CommandLine;
import picocli.CommandLine.Command;

@Command(
    name = "gendiff",
    mixinStandardHelpOptions = true,  // Включает флаги -h и -V по 
умолчанию
    version = "gendiff 1.0",  // Указывает версию приложения
    description = "Compares two configuration files and shows a 
difference."  // Описание приложения
)
public class App implements Runnable {

    @Override
    public void run() {
        // Логика приложения
        System.out.println("This is where the application logic will 
go.");
    }

    public static void main(String[] args) {
        // Запуск приложения с передачей аргументов
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}


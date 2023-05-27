import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //System.out.println("Случайное число от 0 до 1: " + Math.random());
        int countTruePathInFile = 0;
        while (true) {
            String path = new Scanner(System.in).nextLine();
            File file = new File(path);
            boolean fileExist = file.exists();
            boolean isDirectory = file.isDirectory();
            if (isDirectory) {
                System.out.println("Указанный путь ведёт в директорию: " + path);
                pathCount(countTruePathInFile);
                continue;
            }
            if (fileExist) {
                countTruePathInFile++;
                System.out.println("Путь указан верно");
                System.out.println("Это файл номер " + countTruePathInFile);
                pathCount(countTruePathInFile);
                continue;
            }
            pathCount(countTruePathInFile);
            System.out.print("Введён не корректный путь к файлу. Попробуйте снова: \n ");
        }
    }

    public static void pathCount(int x) {
        System.out.println("Количество верно указанных путей: " + x);
    }
}
import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
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

                int count = 0;
                int max = 0;
                int min = 0;
                try {
                    FileReader fileReader = new FileReader(path);
                    BufferedReader reader = new BufferedReader(fileReader);
                    String line;
                    max = reader.readLine().length();
                    min = reader.readLine().length();
                    while ((line = reader.readLine()) != null) {
                        int length = line.length();
                        if (length > max) {
                            max = length;
                            MyClassEx cMax = new MyClassEx();
                            try {
                                cMax.checkMax(max);
                            } catch (RuntimeException e) {
                                System.out.println("Произошла ошибка: " + e.getMessage());
                                System.exit(1);
                            }
                        }
                        if (min > length) {
                            min = length;
                        }

                        count++;
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                System.out.println("Минимальное количество символов в строке: " + min);
                System.out.println("Максимальное количество символов в строке: " + max);
                System.out.println("Количество строк в файле: " + count);

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
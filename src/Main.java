import java.io.*;
import java.text.DecimalFormat;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        //System.out.println("Случайное число от 0 до 1: " + Math.random());
        System.out.println("Введите путь к файлу:");
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

                double countYaBot = 0;
                double countGBot = 0;
                try {
                    FileReader fileReader = new FileReader(path);
                    BufferedReader reader = new BufferedReader(fileReader);
                    String line;
                    Statistics stat = new Statistics();
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

                        if (line.contains("Googlebot")) {
                            countGBot++;
                        }
                        if (line.contains("YandexBot")) {
                            countYaBot++;
                        }
                        LogEntry lg = new LogEntry(line);

                        //System.out.println("IP: " + lg.getIpAddr());
                        //System.out.println("DateTime: " + lg.getTime());
                        //System.out.println("HttpMethod: " + lg.getMethod());
                        //System.out.println("getPath: " + lg.getPath());
                        //System.out.println("responseCode: " + lg.getResponseCode());
                        //System.out.println("getResponseSize: " + lg.getResponseSize());
                        //System.out.println("getReferer: " + lg.getReferer());
                        //System.out.println("get OS type: " + lg.getUserAgent().getOsType());
                        //System.out.println("get Browser type: " + lg.getUserAgent().getNameBrowser());
                        //System.out.println("-------------------------");
                        //System.out.println("is Bot? : " + lg.getUserAgent().isBot(line));

                        stat.addEntry(lg);
                    }
                    //System.out.println("getAddressesOfExistingPages: " + stat.getAddressesOfExistingPages());
                    //System.out.println("getFrequencyOfOccurrenceOfOperatingSystems: " + stat.getFrequencyOfOccurrenceOfOperatingSystems());
                    //System.out.println("getShareOSForExistingPages: " + stat.getShareOSForExistingPages());
                    //System.out.println("getCountOfExistingPages: " + stat.getCountOfExistingPages());
                    //System.out.println("getAddressesOfNotExistingPages: " + stat.getAddressesOfNotExistingPages());
                    //System.out.println("getFrequencyOfNotOccurrenceOfOperatingSystems: " + stat.getFrequencyOfNotOccurrenceOfOperatingSystems());
                    //System.out.println("getShareOSForNotExistingPages: " + stat.getShareOSForNotExistingPages());
                    //System.out.println("getCountOfNotExistingPages: " + stat.getCountOfNotExistingPages());
                    //System.out.println("countOfVisitsPerHour: " + stat.countOfVisitsPerHour());
                    //System.out.println("countFailedRequestsPerHour: " + stat.countFailedRequestsPerHour());
                    //System.out.println("countUniqueUsers: " + stat.countUniqueUsers());
                    //System.out.println("countOfVisitsPerSecond: " + stat.countOfVisitsPerSecond(2));
                    //System.out.println("getListReferer: " + stat.getListReferer());
                    //System.out.println("getCountOfUserVisits: " + stat.getCountOfUserVisits());
                    //System.out.println("getCountOfUserVisits: " + stat.getCountOfUserVisits());
                    //System.out.println("getMaxCountOfUserVisits: " + stat.getMaxCountOfUserVisits());

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                DecimalFormat decimalFormat = new DecimalFormat("#.##");
                String roundedNumber1 = decimalFormat.format((countYaBot / count) * 100);
                String roundedNumber2 = decimalFormat.format((countGBot / count) * 100);

                System.out.println("Доля запросов YandexBot: " + roundedNumber1 + "%");
                System.out.println("Доля запросов GoogleBot: " + roundedNumber2 + "%");
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
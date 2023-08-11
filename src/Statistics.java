
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.HashSet;

public class Statistics {
    private int totalTraffic;
    private LocalDateTime minTime;
    private HashSet<String> addressesOfExistingPages;
    private HashMap<String, Integer> frequencyOfOccurrenceOfOperatingSystems;
    private LocalDateTime maxTime;
    private HashMap<String, Double> shareOSForExistingPages;
    private double countOfExistingPages;
    private HashMap<String, Integer> frequencyOfNotOccurrenceOfOperatingSystems;
    private HashSet<String> addressesOfNotExistingPages;
    private HashMap<String, Double> shareOSForNotExistingPages;
    private double countOfNotExistingPages;
    public Statistics(){
        this.minTime = LocalDateTime.of(9999,1,1,1,1,1);
        this.maxTime = LocalDateTime.of(1000,1,1,1,1,1);
        this.addressesOfExistingPages = new HashSet<>();
        this.frequencyOfOccurrenceOfOperatingSystems = new HashMap<>();
        this.countOfExistingPages = 0;
        this.frequencyOfNotOccurrenceOfOperatingSystems = new HashMap<>();
        this.shareOSForExistingPages = new HashMap<>();
        this.addressesOfNotExistingPages = new HashSet<>();
        this.countOfNotExistingPages = 0;
        this.shareOSForNotExistingPages = new HashMap<>();
    }

    public int getTotalTraffic() {
        return totalTraffic;
    }

    public void addEntry(LogEntry lg){
        this.totalTraffic+=lg.getResponseSize();

        if (lg.getTime().isBefore(minTime)){
            this.minTime = lg.getTime();
        }
        if (lg.getTime().isAfter(maxTime)){
            this.maxTime = lg.getTime();
        }
        if (saveAddressesOfExistingPage(lg)){
            saveOSOfExistingPage(lg);
        }
        if (saveAddressesOfNotExistingPage(lg)){
            saveOSOfNotExistingPage(lg);
        }
    }
    public void saveOSOfExistingPage(LogEntry lg){
        String os = lg.getUserAgent().getOsType().toString();
        if (this.frequencyOfOccurrenceOfOperatingSystems.containsKey(os)){
            int i = this.frequencyOfOccurrenceOfOperatingSystems.get(os);
            this.frequencyOfOccurrenceOfOperatingSystems.put(os,i + 1);

            this.frequencyOfOccurrenceOfOperatingSystems.forEach((key, value) -> {
                this.shareOSForExistingPages.put(key, value/ countOfExistingPages);
            });

        } else {
            this.frequencyOfOccurrenceOfOperatingSystems.put(os,1);

            this.frequencyOfOccurrenceOfOperatingSystems.forEach((key, value) -> {
                this.shareOSForExistingPages.put(key, value/ countOfExistingPages);
            });
        }
    }
    public void saveOSOfNotExistingPage(LogEntry lg){
        String os = lg.getUserAgent().getOsType().toString();
        if (this.frequencyOfNotOccurrenceOfOperatingSystems.containsKey(os)){
            int i = this.frequencyOfNotOccurrenceOfOperatingSystems.get(os);
            this.frequencyOfNotOccurrenceOfOperatingSystems.put(os,i + 1);

            this.frequencyOfNotOccurrenceOfOperatingSystems.forEach((key, value) -> {
                this.shareOSForNotExistingPages.put(key, value/ countOfNotExistingPages);
            });

        } else {
            this.frequencyOfNotOccurrenceOfOperatingSystems.put(os,1);

            this.frequencyOfNotOccurrenceOfOperatingSystems.forEach((key, value) -> {
                this.shareOSForNotExistingPages.put(key, value/ countOfNotExistingPages);
            });
        }
    }

    public Boolean saveAddressesOfExistingPage(LogEntry lg){
        if (lg.getResponseCode() == 200 ) {
            this.addressesOfExistingPages.add(lg.getPath());
            countOfExistingPages++;
            return true;
        }
        return false;
    }
    public Boolean saveAddressesOfNotExistingPage(LogEntry lg){
        if (lg.getResponseCode() == 404 ) {
            this.addressesOfNotExistingPages.add(lg.getPath());
            countOfNotExistingPages++;
            return true;
        }
        return false;
    }

    public long getTrafficRate(){
        System.out.println("this.totalTraffic: " + this.totalTraffic);
        System.out.println("this.minTime.until(this.maxTime, ChronoUnit.HOURS): " + this.minTime.until(this.maxTime, ChronoUnit.HOURS));
        System.out.println("this.minTime: " + this.minTime);
        System.out.println("this.maxTime: " + this.maxTime);
        return this.totalTraffic/this.minTime.until(this.maxTime, ChronoUnit.HOURS);
    }

    public HashSet<String> getAddressesOfExistingPages() {
        return this.addressesOfExistingPages;
    }

    public HashMap<String, Integer> getFrequencyOfOccurrenceOfOperatingSystems() {
        return this.frequencyOfOccurrenceOfOperatingSystems;
    }

    public HashMap<String, Double> getShareOSForExistingPages() {
        return this.shareOSForExistingPages;
    }

    public HashSet<String> getAddressesOfNotExistingPages() {
        return this.addressesOfNotExistingPages;
    }

    public double getCountOfExistingPages() {
        return this.countOfExistingPages;
    }

    public HashMap<String, Double> getShareOSForNotExistingPages() {
        return this.shareOSForNotExistingPages;
    }

    public double getCountOfNotExistingPages() {
        return this.countOfNotExistingPages;
    }

    public HashMap<String, Integer> getFrequencyOfNotOccurrenceOfOperatingSystems() {
        return this.frequencyOfNotOccurrenceOfOperatingSystems;
    }
}

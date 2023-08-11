
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
    private HashMap<String, Double> shareOS;
    private double count;
    public Statistics(){
        this.minTime = LocalDateTime.of(9999,1,1,1,1,1);
        this.maxTime = LocalDateTime.of(1000,1,1,1,1,1);
        this.addressesOfExistingPages = new HashSet<>();
        this.frequencyOfOccurrenceOfOperatingSystems = new HashMap<>();
        this.count = 0;
        this.shareOS = new HashMap<>();
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
        saveAddressesOfExistingPage(lg);
        saveOS(lg);
    }
    public void saveOS(LogEntry lg){
        count++;
        String os = lg.getUserAgent().getOsType().toString();
        if (this.frequencyOfOccurrenceOfOperatingSystems.containsKey(os)){
            int i = this.frequencyOfOccurrenceOfOperatingSystems.get(os);
            this.frequencyOfOccurrenceOfOperatingSystems.put(os,i + 1);

            this.frequencyOfOccurrenceOfOperatingSystems.forEach((key, value) -> {
                this.shareOS.put(key, value/count);
            });

        } else {
            this.frequencyOfOccurrenceOfOperatingSystems.put(os,1);

            this.frequencyOfOccurrenceOfOperatingSystems.forEach((key, value) -> {
                this.shareOS.put(key, value/count);
            });
        }
    }

    public void saveAddressesOfExistingPage(LogEntry lg){
        if (lg.getResponseCode() == 200 ) {
            this.addressesOfExistingPages.add(lg.getPath());
        }
    }

    public long getTrafficRate(){
        System.out.println("this.totalTraffic: " + this.totalTraffic);
        System.out.println("this.minTime.until(this.maxTime, ChronoUnit.HOURS): " + this.minTime.until(this.maxTime, ChronoUnit.HOURS));
        System.out.println("this.minTime: " + this.minTime);
        System.out.println("this.maxTime: " + this.maxTime);
        return this.totalTraffic/this.minTime.until(this.maxTime, ChronoUnit.HOURS);
    }

    public HashSet<String> getAddressesOfExistingPages() {
        return addressesOfExistingPages;
    }

    public HashMap<String, Integer> getFrequencyOfOccurrenceOfOperatingSystems() {
        return frequencyOfOccurrenceOfOperatingSystems;
    }

    public HashMap<String, Double> getShareOS() {
        return shareOS;
    }
}

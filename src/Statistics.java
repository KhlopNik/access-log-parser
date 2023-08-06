
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Statistics {
    private int totalTraffic;
    private LocalDateTime minTime;

    private LocalDateTime maxTime;
    public Statistics(){
        this.minTime = LocalDateTime.of(9999,1,1,1,1,1);
        this.maxTime = LocalDateTime.of(1000,1,1,1,1,1);
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
    }

    public long getTrafficRate(){
        System.out.println("this.totalTraffic: " + this.totalTraffic);
        System.out.println("this.minTime.until(this.maxTime, ChronoUnit.HOURS): " + this.minTime.until(this.maxTime, ChronoUnit.HOURS));
        System.out.println("this.minTime: " + this.minTime);
        System.out.println("this.maxTime: " + this.maxTime);
        return this.totalTraffic/this.minTime.until(this.maxTime, ChronoUnit.HOURS);
    }
}

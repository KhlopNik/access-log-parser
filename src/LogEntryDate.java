import java.time.LocalDateTime;

public class LogEntryDate {
    private final String rawDate;

    public LogEntryDate(String rawDate){
        this.rawDate = rawDate;
    }
    public LocalDateTime toDateTime(){
        int startIndex = this.rawDate.indexOf('[');
        int endIndex = this.rawDate.length();

        if (startIndex != -1) {
            String dateTimeString = this.rawDate.substring(startIndex + 1, endIndex).trim();
            String[] dateTimeParts = dateTimeString.split("[/:\\s]+");

            if (dateTimeParts.length != 0) {
                int day = Integer.parseInt(dateTimeParts[0]);
                int month = getMonthToInt(dateTimeParts[1]);
                int year = Integer.parseInt(dateTimeParts[2]);
                int hour = Integer.parseInt(dateTimeParts[3]);
                int minute = Integer.parseInt(dateTimeParts[4]);
                int second = Integer.parseInt(dateTimeParts[5]);

                return LocalDateTime.of(year, month, day, hour, minute, second);
            }
        }

        return null;
    }
    public static int getMonthToInt(String str){
        int month = 0;
        switch (str) {
            case "Jan":
                month = 1;
                break;
            case "Feb":
                month = 2;
                break;
            case "Mar":
                month = 3;
                break;
            case "Apr":
                month = 4;
                break;
            case "May":
                month = 5;
                break;
            case "Jun":
                month = 6;
                break;
            case "Jul":
                month = 7;
                break;
            case "Aug":
                month = 8;
                break;
            case "Sep":
                month = 9;
                break;
            case "Oct":
                month = 10;
                break;
            case "Nov":
                month = 11;
                break;
            case "Dec":
                month = 12;
                break;
        }
        return month;
    }
}

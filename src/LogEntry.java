
import java.time.LocalDateTime;

import static java.lang.Integer.parseInt;

public class LogEntry {
    private final String ipAddr;
    private final LocalDateTime time;
    private final HttpMethod method;
    private final String path;
    private final int responseCode;
    private final int responseSize;
    private final String referer;
    private final UserAgent userAgent;

    public LogEntry(String rawLodEntry) {
        String[] rawLogEntryParts = rawLodEntry.split(" ", 14);
        this.ipAddr = rawLogEntryParts[0];
        this.time = new LogEntryDate(rawLogEntryParts[3]).toDateTime();
        this.method = parseHTTPMethod(rawLogEntryParts[5]);
        this.path = rawLogEntryParts[6];
        this.responseCode = parseInt(rawLogEntryParts[8]);
        this.responseSize = parseInt(rawLogEntryParts[9]);
        this.referer = parseReferer(rawLogEntryParts[10]);
        String[] rawLogEntryPartsQuotes = rawLodEntry.split("\"", 6);
        this.userAgent = new UserAgent(rawLogEntryPartsQuotes[5]);
    }


    public String getIpAddr() {
        return ipAddr;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public HttpMethod getMethod() {
        return method;
    }

    public String getPath() {
        return path;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public int getResponseSize() {
        return responseSize;
    }

    public String getReferer() {
        return referer;
    }

    public UserAgent getUserAgent() {
        return userAgent;
    }

    public static HttpMethod parseHTTPMethod(String logEntry) {
        int startIndex = logEntry.indexOf("\"") + 1;
        int endIndex = logEntry.length();

        return HttpMethod.valueOf(logEntry.substring(startIndex, endIndex));
    }

    public static String parseReferer(String logEntry) {
        int startIndex = logEntry.indexOf("\"") + 1;
        int endIndex = logEntry.length() - 1;

        return logEntry.substring(startIndex, endIndex);
    }
}

enum HttpMethod {
    GET,
    POST,
    PUT,
    DELETE
}
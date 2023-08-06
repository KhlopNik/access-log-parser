
public class UserAgent {
    private final Browsers nameBrowser;
    private final OS osType;

    public UserAgent(String userAgent) {
        this.nameBrowser = parsBrowserType(userAgent);
        this.osType = parsOsType(userAgent);
    }

    public Browsers getNameBrowser() {
        return nameBrowser;
    }

    public OS getOsType() {
        return osType;
    }

    public OS parsOsType(String userAgent) {
        if (userAgent.contains("Linux")) {
            return OS.Linux;
        }
        if (userAgent.contains("Windows")) {
            return OS.Windows;
        }
        if (userAgent.contains("Macintosh") || userAgent.contains("Mas OS")) {
            return OS.MacOS;
        }

        return OS.Unknown;
    }

    public Browsers parsBrowserType(String userAgent) {
        if (userAgent.contains("Opera/")){
            return Browsers.Opera;
        }
        if (userAgent.contains("Edge/")){
            return Browsers.Edge;
        }
        if (userAgent.contains("Chrome/")){
            return Browsers.Chrome;
        }
        if (userAgent.contains("Firefox/")){
            return Browsers.Firefox;
        }
        return Browsers.Other;
    }
}

enum OS {
    Windows,
    MacOS,
    Linux,
    Unknown
}

enum Browsers {
    Edge,
    Firefox,
    Chrome,
    Opera,
    Other
}
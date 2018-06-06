

public class OSDetecter {
    private static String OS = System.getProperty("os.name").toLowerCase();
    public final static int UNIX = 1;
    public final static int WINDOWS = 2;
    public final static int MAC = 3;
    public final static int SOLARIS = 4;

    public static boolean isWindows() {
        return (OS.contains("win"));
    }

    public static boolean isMac() {
        return (OS.contains("mac"));
    }

    public static boolean isUnix() {
        return (OS.contains("nix") || OS.contains("nux") || OS.contains("aix"));
    }

    public static boolean isSolaris() {
        return (OS.contains("sunos"));
    }

    public static int getOS(){
        if (isWindows()) return WINDOWS;
        else if (isMac()) return MAC;
        else if (isSolaris()) return SOLARIS;
        return UNIX;
    }
}

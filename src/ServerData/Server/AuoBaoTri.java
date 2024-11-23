package ServerData.Server;

import java.io.IOException;
import java.time.LocalTime;
import java.util.logging.Logger;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils.IO;

public class AuoBaoTri extends Thread {
    public static boolean AutoBaoTri = true;
    public static final int hours = 3;
    public static final int minutes = 07;
    private static AuoBaoTri  I;
    public  static boolean isRuning = false;

    public static AuoBaoTri gI() {
        if (I == null) {
            I = new AuoBaoTri();
        }
        return I;
    }
    @Override
    public void run() {
        while (!Maintenance.isRuning && !isRuning) {
            try {
                if (AutoBaoTri) {
                    LocalTime now = LocalTime.now();
                    if (now.getHour() == hours && now.getMinute() == minutes) {
                        Logger.getLogger("Bảo trì").info("Bắt đầu bảo trì");
                        Maintenance.gI().start(30);
                        isRuning = true;    
                        AutoBaoTri = false;
                    }
                }
                Thread.sleep(1000);
            } catch (Exception e) {
            }
        }
        Maintenance.gI().start(hours * 3600 + minutes * 60);
    }
    public static void runBatchFile(String path) throws IOException {
        ProcessBuilder processBuilder = new ProcessBuilder("comd", "/c", "start");
        Process process = processBuilder.start();
        try {
            process.waitFor();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
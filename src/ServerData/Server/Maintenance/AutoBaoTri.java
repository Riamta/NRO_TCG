package ServerData.Server.Maintenance;

import java.io.IOException;
import java.time.LocalTime;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AutoBaoTri extends Thread {
    public static boolean AutoBaoTri = true;
    public static final int HOURS = 3;
    public static final int MINUTES = 16;
    private static AutoBaoTri instance;
    public static boolean isRunning = false;

    public static AutoBaoTri gI() {
        if (instance == null) {
            instance = new AutoBaoTri();
        }
        return instance;
    }

    @Override
    public void run() {
        while (!Maintenance.isRuning && !isRunning) {
            try {
                if (AutoBaoTri) {
                    LocalTime now = LocalTime.now();
                    if (now.getHour() == HOURS && now.getMinute() == MINUTES) {
                        Logger.getLogger("Bảo trì").info("Bắt đầu bảo trì");
                        Maintenance.gI().start(30); // Đảm bảo `start(int)` được định nghĩa.
                        isRunning = true;
                        AutoBaoTri = false;
                    }
                }
                Thread.sleep(1000);
            } catch (Exception e) {
                Logger.getLogger("AutoBaoTri").log(Level.SEVERE, "Lỗi trong vòng lặp bảo trì", e);
            }
        }
        try {
            Maintenance.gI().start(HOURS * 3600 + MINUTES * 60);
        } catch (Exception e) {
            Logger.getLogger("AutoBaoTri").log(Level.SEVERE, "Lỗi khi bắt đầu bảo trì", e);
        }
    }

    public static void runBatchFile(String path) throws IOException {
        ProcessBuilder processBuilder = new ProcessBuilder("cmd", "/c", "start", path);
        Process process = processBuilder.start();
        try {
            process.waitFor();
        } catch (InterruptedException e) {
            Logger.getLogger("AutoBaoTri").log(Level.SEVERE, "Lỗi khi chạy file batch", e);
            Thread.currentThread().interrupt(); // Khôi phục trạng thái ngắt của thread.
        }
    }
}

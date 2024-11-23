package ServerData.Models.Map.doanhtrai;

import ServerData.Models.Clan.Clan;
import ServerData.Models.Map.Zone;
import ServerData.Models.Map.Mob.Mob;
import ServerData.Models.Player.Player;
import ServerData.Services.ItemTimeService;
import ServerData.Services.MapService;
import ServerData.Services.Service;
import ServerData.Services.ChangeMapService;
import ServerData.Utils.Util;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Obito
 *
 * 
 */
public class DoanhTrai {

    // bang hội đủ số người mới đc mở
    public static final List<DoanhTrai> DOANH_TRAI;
    public static final int N_PLAYER_CLAN = 0;
    //số người đứng cùng khu
    public static int N_PLAYER_MAP = 0;
    public static final int AVAILABLE = 500;
    public static final int TIME_DOANH_TRAI = 1800000;

    static {
        DOANH_TRAI = new ArrayList<>();
        for (int i = 0; i < AVAILABLE; i++) {
            DOANH_TRAI.add(new DoanhTrai(i));
        }
    }

    public int id;
    public final List<Zone> zones;
    public Clan clan;
    public boolean isOpened;


    private long lastTimeOpen;
    public byte level;
    private boolean running;
    private long lastTimeUpdate;

    public DoanhTrai(int id) {
        this.id = id;
        this.zones = new ArrayList<>();
    }
    public void addZone(Zone zone) {
        this.zones.add(zone);
    }
    public Zone getMapById(int mapId) {
        for (Zone zone : this.zones) {
            if (zone.map.mapId == mapId) {
                return zone;
            }
        }
        return null;
    }

    public void opendoanhtrai(Player player) {
        this.lastTimeOpen = System.currentTimeMillis();
        this.clan = player.clan;
        player.clan.doanhTrai = this;
        player.clan.playerOpenDoanhTrai = player;
        player.clan.lastTimeOpenDoanhTrai = this.lastTimeOpen;
        player.clan.timeOpenDoanhTrai = this.lastTimeOpen;
        //Khởi tạo quái, boss
        this.init();
        //Đưa thành viên vào doanh trại

        // this.clan.timeOpenDoanhTrai = this.lastTimeOpen;
        // this.clan.playerOpenDoanhTrai = plOpen;
        // this.clan.doanhTrai = this;
        // ChangeMapService.gI().changeMapInYard(plOpen, 53, -1, 60);
        // resetDT();
        // sendTextDoanhTrai();
        for (Player pl : player.clan.membersInGame) {
            if (pl == null || pl.zone == null || !player.zone.equals(pl.zone)) {
                continue;
            }
            ChangeMapService.gI().changeMapInYard(pl, 53, -1, 60);
            ItemTimeService.gI().sendTextDoanhTrai(pl);
        }
    }
    private void init(){
        long totalDame = 0;
        long totalHp = 0;
        for (Player pl : this.clan.membersInGame) {
            totalDame += pl.nPoint.dame;
            totalHp += pl.nPoint.hpMax;
        }
        for (Zone zone : this.zones) {
            for (Mob mob : zone.mobs) {
                mob.point.dame = (int) (totalHp / 20);
                mob.point.maxHp = (int) (totalDame * 20);
                mob.hoiSinh();
            }
        }
    }
    private void sendTextDoanhTrai() {
        for (Player pl : this.clan.membersInGame) {
            ItemTimeService.gI().sendTextDoanhTrai(pl);
        }
    }
    private void kickOutOfBDKB(Player player) {
        if (MapService.gI().isMapDoanhTrai(player.zone.map.mapId)) {
            Service.getInstance().sendThongBao(player, "Doanh Trại Đã Kết Thúc Bạn Đang Được Đưa Ra Ngoài");
            ChangeMapService.gI().changeMapBySpaceShip(player, 27, -1, 1038);
            running = false;
            this.clan.BanDoKhoBau = null;
        }
    }
    public void run() {
        while (running) {
            try {
                Thread.sleep(10000);
                if (Util.canDoWithTime(lastTimeUpdate, 10000)) {
                    // update();
                    lastTimeUpdate = System.currentTimeMillis();
                }
            } catch (Exception ignored) {
            }

        }
    }


}
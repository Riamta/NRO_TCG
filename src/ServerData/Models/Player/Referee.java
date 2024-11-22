package ServerData.Models.Player;

import ServerData.Models.Map.Map;
import ServerData.Models.Map.Zone;
import ServerData.Server.Manager;
import ServerData.Services.MapService;
import ServerData.Services.Service;
import ServerData.Utils.Util;
import java.util.List;

/**
 * @author by Obito Tuấn
 */
public class Referee extends Player {

    private long lastTimeChat;
    private Player playerTarget;

    private long lastTimeTargetPlayer;
    private final long timeTargetPlayer = 5000;
    private long lastZoneSwitchTime;
    private long zoneSwitchInterval;
    private List<Zone> availableZones;

    public void initReferee() {
        init();
    }

    @Override
    public short getHead() {
        return 114;
    }

    @Override
    public short getBody() {
        return 115;
    }

    @Override
    public short getLeg() {
        return 116;
    }

    public void joinMap(Zone z, Player player) {
        MapService.gI().goToMap(player, z);
      //  z.load_Me_To_Another(player);
    }

    @Override
    public void update() {
        if (this.isDie()) {
        
        if(this.zone.map.mapId == 51){
            if (Util.canDoWithTime(lastTimeChat, 5000) ) {
                Service.getInstance().chat(this, "NRO BY OBITO");
                Service.getInstance().chat(this, "Còn chờ gì nữa mà không đăng kí tham gia để nhận nhiều phẩn quà hấp dẫn");
                lastTimeChat = System.currentTimeMillis();
            }
        }else{
            if (Util.canDoWithTime(lastTimeChat, 5000) && this.zone.map.mapId != 14) {
                Service.getInstance().chat(this, "Đại Hội Võ Thuật lần thứ 23 đã chính thức khai mạc");
                Service.getInstance().chat(this, "Còn chờ gì nữa mà không đăng kí tham gia để nhận nhiều phẩn quà hấp dẫn");
                lastTimeChat = System.currentTimeMillis();
            }
        }
    }
    }

    private void init() {
        int id = -1000000;
        for (Map m : Manager.MAPS) {
            if (m.mapId == 52) {
                for (Zone z : m.zones) {
                    Referee pl = new Referee();
                    pl.name = "Trọng Tài";
                    pl.gender = 0;
                    pl.id = id++;
                    pl.nPoint.hpMax = 500;
                    pl.nPoint.hpg = 500;
                    pl.nPoint.hp = 500;
                    pl.location.x = 387;
                    pl.location.y = 336;
                    joinMap(z, pl);
                    z.setReferee(pl);
                }
            } else if (m.mapId == 129) {
                for (Zone z : m.zones) {
                    Referee pl = new Referee();
                    pl.name = "Trọng tài";
                    pl.gender = 0;
                    pl.id = id++;
                    pl.nPoint.hpMax = 500;
                    pl.nPoint.hpg = 500;
                    pl.nPoint.hp = 500;
                    pl.location.x = 385;
                    pl.location.y = 264;
                    joinMap(z, pl);
                    z.setReferee(pl);
                 }        
            }else if (m.mapId == 51) {
                for (Zone z : m.zones) {
                    Referee pl = new Referee();
                    pl.name = "Trọng tài";
                    pl.gender = 0;
                    pl.id = id++;
                    pl.nPoint.hpMax = 500;
                    pl.nPoint.hpg = 500;
                    pl.nPoint.hp = 500;
                    pl.location.x = 384;
                    pl.location.y = 300;
                    joinMap(z, pl);
                    z.setReferee(pl);
                }
            }
        }
    }
}


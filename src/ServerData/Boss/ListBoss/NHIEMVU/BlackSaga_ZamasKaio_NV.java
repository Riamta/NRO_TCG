package ServerData.Boss.ListBoss.NHIEMVU;

import ServerData.Boss.*;
import ServerData.Models.Map.ItemMap;
import ServerData.Models.Player.Player;
import ServerData.Server.Manager;
import ServerData.Services.EffectSkillService;
import ServerData.Services.Service;
import ServerData.Utils.Util;

import java.util.Random;


public class BlackSaga_ZamasKaio_NV extends Boss {

    public BlackSaga_ZamasKaio_NV() throws Exception {
       super(BossID.ZAMASZIN, BossesData.ZAMAS);
    }

    @Override
    public void reward(Player plKill) {
        byte randomDo = (byte) new Random().nextInt(Manager.itemIds_TL.length - 1);
        byte randomNR = (byte) new Random().nextInt(Manager.itemIds_NR_SB.length);
        if (Util.isTrue(50, 100)) {
            if (Util.isTrue(20, 100)) {
                Service.gI().dropItemMap(this.zone,new ItemMap(zone, 874, 1, this.location.x, this.location.y, plKill.id));
            } else if (Util.isTrue(10, 100)){
                Service.gI().dropItemMap(this.zone, Util.ratiItem(zone, Manager.itemIds_TL[randomDo], 1, this.location.x, this.location.y, plKill.id));
            }
        }
        else {
            Service.gI().dropItemMap(this.zone, new ItemMap(zone, Manager.itemIds_NR_SB[randomNR], 1, this.location.x, this.location.y, plKill.id));
            if (Util.isTrue(20, 100)){
                ItemMap it = new ItemMap(this.zone, Util.nextInt(1600, 1602), 1, this.location.x, this.zone.map.yPhysicInTop(this.location.x,
                    this.location.y - 24), plKill.id);
                Service.getInstance().dropItemMap(this.zone, it);
            }
        }
    }

    @Override
    public void active() {
        super.active(); //To change body of generated methods, choose Tools | Templates.
    }
   
    @Override
    public void joinMap() {
        super.joinMap(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double injured(Player plAtt, double damage, boolean piercing, boolean isMobAttack) {
        if (!this.isDie()) {
            if (!piercing && Util.isTrue(this.nPoint.tlNeDon, 1000)) {
                this.chat("Xí hụt");
                return 0;
            }
            damage = this.nPoint.subDameInjureWithDeff(damage/2);
            if (!piercing && effectSkill.isShielding) {
                if (damage > nPoint.hpMax) {
                    EffectSkillService.gI().breakShield(this);
                }
                damage = damage;
            }
            this.nPoint.subHP(damage);
            if (isDie()) {
                this.setDie(plAtt);
                die(plAtt);
            }
            return damage;
        } else {
            return 0;
        }
    }

}























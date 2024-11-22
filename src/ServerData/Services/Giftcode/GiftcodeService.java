package ServerData.Services.Giftcode;

import ServerData.Services.Giftcode.Giftcode;
import ServerData.Services.Giftcode.GiftcodeManager;
import com.girlkun.database.GirlkunDB;
import ServerData.Models.Item.Item;
import ServerData.Models.Player.Player;
import ServerData.Services.InventoryServiceNew;
import ServerData.Services.Service;
import com.girlkun.result.GirlkunResultSet;

import java.sql.Timestamp;
import java.util.ArrayList;


/**
 *
 * @Stole By Arriety 💖
 *
 */
public class GiftcodeService {

    private static GiftcodeService i;
    
    private GiftcodeService(){
        
    }
    public String code;
    public int idGiftcode;
    public int gold;
    public int gem;
    public int dayexits;
    public Timestamp timecreate;
    public ArrayList<Item> listItem = new ArrayList<>();
    public static ArrayList<GiftcodeService> gifts = new ArrayList<>();
    public static GiftcodeService gI(){
        if(i == null){
            i = new GiftcodeService();
        }
        return i;
    }
   
    public void giftCode(Player player, String code) throws Exception {
        Giftcode giftcode = GiftcodeManager.gI().CheckCode((int) player.id, code);
        GirlkunResultSet rs = GirlkunDB.executeQuery(
                "SELECT * FROM giftcode_save WHERE `player_id` = " + player.id + " AND `code_da_nhap` = '"+ code + "';");
        if (rs != null && rs.first()) {
            Service.gI().sendThongBaoFromAdmin(player,"|7|VŨ TRỤ NGỌC RỒNG\n"+ "|6|Giftcode : " + code + "\nBạn đã nhập giftcode này vào lúc : " + rs.getTimestamp("tgian_nhap"));
            return;
        } else {
            rs.dispose();
            rs = GirlkunDB.executeQuery("SELECT * FROM `giftcode` WHERE `code` = '"+ code + "';");
            if (rs != null && rs.first()) {
                int count = rs.getInt("count_left");
            if (count < 1) {
                Service.gI().sendThongBaoFromAdmin(player, "|7|VŨ TRỤ NGỌC RỒNG\n"+ "|6|Giftcode : "+code+"\nĐã hết lượt nhập, vui lòng quay lại sau!");
                return;
            }}}
            if (giftcode == null) {
              Service.gI().sendThongBaoFromAdmin(player,"|7|VŨ TRỤ NGỌC RỒNG\n"+"|6|Giftcode vừa nhập không tồn tại trong hệ thống!");
            } else if (giftcode.timeCode()) {
                Service.gI().sendThongBaoFromAdmin(player,"|7|VŨ TRỤ NGỌC RỒNG\n"+ "|6|Giftcode : " + code+"\nGiftcode này đã hết hạn");
            } else if (InventoryServiceNew.gI().getCountEmptyBag(player) < giftcode.detail.size()) {
                Service.gI().sendThongBaoFromAdmin(player, "|7|VŨ TRỤ NGỌC RỒNG\n"+ "|6|Giftcode : " + code + "\nCần trống " + giftcode.detail.size() + " ô hành trang");
            } else {
                InventoryServiceNew.gI().addItemGiftCodeToPlayer(player,giftcode,code);
        } 
    }
}

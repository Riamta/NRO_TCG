package ServerData.Models.upload;

import com.girlkun.database.GirlkunDB;

public class update {
    
    public static void updateFrom(String bang, String cot, int slot, String Where, String Whe) {
        try {
            GirlkunDB.executeUpdate("UPDATE " + bang + " SET " + cot + " = " + slot + " WHERE " + Where + " = " + Whe);
        } catch (Exception e) {

        }
    }
    
    public static void Insetinto(String bang , String inset1 ,  String inset2){
        try {
            GirlkunDB.executeUpdate("INSERT INTO " + bang + inset1 + " VALUES "+ inset2);
        } catch (Exception e) {
            
        }
   }
}

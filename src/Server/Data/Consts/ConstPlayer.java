package Server.Data.Consts;

public class ConstPlayer
{
    public static final int[] HEADMONKEY = {192, 195, 196, 199, 197, 200, 198};
    
    public static final int[] HEADSSJ = {1559, 1559, 1560, 1560, 1561, 1562, 1556};
    
    // AURA BIẾN HÌNH Ở ĐÂY
    public static final byte[][] AURABIENHINH = {
        // LẦN LƯỢT TỪ LB 1-5
        {7, 7, 13, 6, 8},
        {4, 5, 13, 6, 31},
        {7, 7, 13, 6, 15}
    };
    // SỬA NGOẠI HÌNH TỪ LV 1-5 Ở ĐÂY
    public static final short[][] HEADBIENHINH = {
        {1651, 1652, 1653, 1654, 1655}, // 5 head TD 
        {1656, 1657, 1658, 1659, 1660},// 5 haed NM
         {1661, 1662, 1663, 1664, 1665}, // 5 head XD
    };
    // THÂN NGOẠI HÌNH LV 1-5
    public static final short[] BODYBIENHINH = {1606, 1554, 1557}; // TD /NM/ XD
    public static final short[] LEGBIENHINH = {1607, 1555, 1558}; // TD /NM/ XD
    
    // ICON Ở GÓC
    public static final short[][] FLAGBIENHINH = {
        {104, 104, 105, 106, 101}, // 5 flagbag TD 
        {103, 103, 105, 102, 107},// 5 flagbag NM
        {104, 104, 105, 106, 100}, // 5 flagbag XD
    };

    public static final byte TRAI_DAT = 0;
    public static final byte NAMEC = 1;
    public static final byte XAYDA = 2;

    //type pk
    public static final byte NON_PK = 0;
    public static final byte PK_PVP = 3;
    public static final byte PK_ALL = 5;

    //type fushion
    public static final byte NON_FUSION = 0;
    public static final byte LUONG_LONG_NHAT_THE = 4;
    public static final byte HOP_THE_PORATA = 6;
    public static final byte HOP_THE_PORATA2 = 8;
    public static final byte HOP_THE_PORATA3 = 10;
    public static final byte HOP_THE_PORATA4 = 12;
    public static final byte HOP_THE_PORATA5 = 14;
    public static final byte HOP_THE_GOGETA = 16;
}

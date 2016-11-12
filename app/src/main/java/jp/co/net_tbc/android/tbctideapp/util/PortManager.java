package jp.co.net_tbc.android.tbctideapp.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * Created by admin on 2016/11/12.
 */
public final class PortManager {

    private static final List<String> prefList;
    private static final Map<String,List<String>> prefPortsMap;
    private static final Map<String,Integer> portIdMap;

    static {
        prefList = new ArrayList<String>();
        prefList.add("北海道");
        prefList.add("青森");
        prefList.add("岩手");
//        prefList.add("宮城");
//        prefList.add("秋田");
//        prefList.add("山形");
//        prefList.add("福島");
//        prefList.add("茨城");
//        prefList.add("千葉");

        prefPortsMap = new HashMap<String,List<String>>();
        List<String> hokkaidoPortList = new ArrayList<String>();
        hokkaidoPortList.add("函館");//0
        hokkaidoPortList.add("福島吉岡");//1
        hokkaidoPortList.add("室蘭");
        hokkaidoPortList.add("小樽忍路");
        hokkaidoPortList.add("忍路");
        hokkaidoPortList.add("浦河");
        hokkaidoPortList.add("留萌");
        hokkaidoPortList.add("稚内");
        hokkaidoPortList.add("紋別");
        hokkaidoPortList.add("網走");
        hokkaidoPortList.add("花咲");
        hokkaidoPortList.add("苫小牧");
        hokkaidoPortList.add("釧路");//12
        prefPortsMap.put("北海道",hokkaidoPortList);
        List<String> aomoriPortList = new ArrayList<String>();
        aomoriPortList.add("大畑");//13
        aomoriPortList.add("三厩");
        aomoriPortList.add("八戸");
        aomoriPortList.add("むつ大湊");
        aomoriPortList.add("大間");
        aomoriPortList.add("小泊");
        aomoriPortList.add("小湊");
        aomoriPortList.add("尻矢");
        aomoriPortList.add("尻矢崎");
        aomoriPortList.add("岩崎");
        aomoriPortList.add("平内泊");
        aomoriPortList.add("青森浅虫");
        aomoriPortList.add("深浦");
        aomoriPortList.add("東通白糠");
        aomoriPortList.add("竜飛");
        aomoriPortList.add("龍飛崎");
        aomoriPortList.add("平内茂浦");
        aomoriPortList.add("野辺地");
        aomoriPortList.add("むつ関根浜");
        aomoriPortList.add("青森");
        aomoriPortList.add("鯵ケ沢");//33
        prefPortsMap.put("青森", aomoriPortList);
        List<String> iwatePortList = new ArrayList<String>();
        iwatePortList.add("久慈");//34
        iwatePortList.add("八木");
        iwatePortList.add("大船渡");
        iwatePortList.add("宮古");
        iwatePortList.add("山田");
        iwatePortList.add("釜石");//39
        prefPortsMap.put("岩手",iwatePortList);

        portIdMap = new HashMap<String,Integer>();
        portIdMap.put("函館",0);
        portIdMap.put("福島吉岡",1);
        portIdMap.put("室蘭",2);
        portIdMap.put("小樽忍路",3);
        portIdMap.put("忍路",4);
        portIdMap.put("浦河",5);
        portIdMap.put("留萌",6);
        portIdMap.put("稚内",7);
        portIdMap.put("紋別",8);
        portIdMap.put("網走",9);
        portIdMap.put("花咲",10);
        portIdMap.put("苫小牧",11);
        portIdMap.put("釧路",12);
        portIdMap.put("大畑",13);
        portIdMap.put("三厩",14);
        portIdMap.put("八戸",15);
        portIdMap.put("むつ大湊",16);
        portIdMap.put("大間",17);
        portIdMap.put("小泊",18);
        portIdMap.put("小湊",19);
        portIdMap.put("尻矢",20);
        portIdMap.put("尻矢崎",21);
        portIdMap.put("岩崎",22);
        portIdMap.put("平内泊",23);
        portIdMap.put("青森浅虫",24);
        portIdMap.put("深浦",25);
        portIdMap.put("東通白糠",26);
        portIdMap.put("竜飛",27);
        portIdMap.put("龍飛崎",28);
        portIdMap.put("平内茂浦",29);
        portIdMap.put("野辺地",30);
        portIdMap.put("むつ関根浜",31);
        portIdMap.put("青森",32);
        portIdMap.put("鯵ケ沢",33);
        portIdMap.put("久慈",34);
        portIdMap.put("八木",35);
        portIdMap.put("大船渡",36);
        portIdMap.put("宮古",37);
        portIdMap.put("山田",38);
        portIdMap.put("釜石",39);

//        Map<String,Integer> hokkaidoPortMap = new HashMap<String,Integer>();
//        hokkaidoPortMap.put("函館",0);
//        hokkaidoPortMap.put("福島吉岡",1);
//        hokkaidoPortMap.put("室蘭",2);
//        hokkaidoPortMap.put("小樽忍路",3);
//        hokkaidoPortMap.put("忍路",4);
//        hokkaidoPortMap.put("浦河",5);
//        hokkaidoPortMap.put("留萌",6);
//        hokkaidoPortMap.put("稚内",7);
//        hokkaidoPortMap.put("紋別",8);
//        hokkaidoPortMap.put("網走",9);
//        hokkaidoPortMap.put("花咲",10);
//        hokkaidoPortMap.put("苫小牧",11);
//        hokkaidoPortMap.put("釧路",12);
//        prefPortAllMap.put("北海道",hokkaidoPortMap);
//        Map<String,Integer> aomoriPortMap = new HashMap<String,Integer>();
//        aomoriPortMap.put("大畑",13);
//        aomoriPortMap.put("三厩",14);
//        aomoriPortMap.put("八戸",15);
//        aomoriPortMap.put("むつ大湊",16);
//        aomoriPortMap.put("大間",17);
//        aomoriPortMap.put("小泊",18);
//        aomoriPortMap.put("小湊",19);
//        aomoriPortMap.put("尻矢",20);
//        aomoriPortMap.put("尻矢崎",21);
//        aomoriPortMap.put("岩崎",22);
//        aomoriPortMap.put("平内泊",23);
//        aomoriPortMap.put("青森浅虫",24);
//        aomoriPortMap.put("深浦",25);
//        aomoriPortMap.put("東通白糠",26);
//        aomoriPortMap.put("竜飛",27);
//        aomoriPortMap.put("龍飛崎",28);
//        aomoriPortMap.put("平内茂浦",29);
//        aomoriPortMap.put("野辺地",30);
//        aomoriPortMap.put("むつ関根浜",31);
//        aomoriPortMap.put("青森",32);
//        aomoriPortMap.put("鯵ケ沢",33);
//        prefPortAllMap.put("青森",aomoriPortMap);
//        Map<String,Integer> iwatePortMap = new HashMap<String,Integer>();
//        iwatePortMap.put("久慈",34);
//        iwatePortMap.put("八木",35);
//        iwatePortMap.put("大船渡",36);
//        iwatePortMap.put("宮古",37);
//        iwatePortMap.put("山田",38);
//        iwatePortMap.put("釜石",39);
//        prefPortAllMap.put("岩手",iwatePortMap);
//        Map<String,Integer> miyagiPortMap = new HashMap<String,Integer>();
//        miyagiPortMap.put("仙台",40);
//        miyagiPortMap.put("女川",41);
//        miyagiPortMap.put("志津川",42);
//        miyagiPortMap.put("気仙沼",43);
//        miyagiPortMap.put("塩釜港橋",44);
//        miyagiPortMap.put("石巻",45);
//        miyagiPortMap.put("松島湾石浜",46);
//        miyagiPortMap.put("船越湾",47);
//        miyagiPortMap.put("花淵浜",48);
//        miyagiPortMap.put("石巻荻浜",49);
//        miyagiPortMap.put("野蒜湾",50);
//        miyagiPortMap.put("閖上",51);
//        miyagiPortMap.put("牡鹿鮎川",52);
//        prefPortAllMap.put("宮城",miyagiPortMap);
//        Map<String,Integer> akitaPortMap = new HashMap<String,Integer>();
//        akitaPortMap.put("岩舘",53);
//        akitaPortMap.put("男鹿",54);
//        akitaPortMap.put("秋田",55);
//        akitaPortMap.put("金浦",56);
//        prefPortAllMap.put("秋田",akitaPortMap);
//        Map<String,Integer> yamagataPortMap = new HashMap<String,Integer>();
//        yamagataPortMap.put("加茂",57);
//        yamagataPortMap.put("由良",58);
//        yamagataPortMap.put("酒田",59);
//        yamagataPortMap.put("温海鼠ヶ関",60);
//        prefPortAllMap.put("山形",yamagataPortMap);
//        Map<String,Integer> fukushimaPortMap = new HashMap<String,Integer>();
//        fukushimaPortMap.put("四倉",61);
//        fukushimaPortMap.put("夫沢",62);
//        fukushimaPortMap.put("福島富岡",63);
//        fukushimaPortMap.put("小名浜",64);
//        fukushimaPortMap.put("相馬松川浦",65);
//        fukushimaPortMap.put("相馬",66);
//        prefPortAllMap.put("福島",fukushimaPortMap);
//        Map<String,Integer> ibarakiPortMap = new HashMap<String,Integer>();
//        ibarakiPortMap.put("大洗",67);
//        ibarakiPortMap.put("茨城大津",68);
//        ibarakiPortMap.put("日立",69);
//        ibarakiPortMap.put("那珂湊",70);
//        ibarakiPortMap.put("鹿島",71);
//        prefPortAllMap.put("茨城",ibarakiPortMap);
//        Map<String,Integer> chibaPortMap = new HashMap<String,Integer>();
//        chibaPortMap.put("第一海堡",72);
//        chibaPortMap.put("上総勝浦",73);
//        chibaPortMap.put("千葉灯標",74);
//        chibaPortMap.put("銚子名洗",75);
//        chibaPortMap.put("君津",76);
//        chibaPortMap.put("姉崎",77);
//        chibaPortMap.put("千葉寒川",78);
//        chibaPortMap.put("岩井袋",79);
//        chibaPortMap.put("市原",80);
//        chibaPortMap.put("市川",81);
//        chibaPortMap.put("館山布良",82);
//        chibaPortMap.put("犬吠崎",83);
//        chibaPortMap.put("千葉白浜",84);
//        chibaPortMap.put("船橋",85);
//        chibaPortMap.put("銚子漁港",86);
//        chibaPortMap.put("銚子新地",87);
//        chibaPortMap.put("銚子港",88);
//        chibaPortMap.put("館山",89);
//        chibaPortMap.put("鴨川",90);
//        prefPortAllMap.put("千葉",chibaPortMap);
    }


    private PortManager(){
    }

    public static List<String> getPrefs(){
        return prefList;
    }
    public static List<String> getPorts(final String pref){
        return prefPortsMap.get(pref);
    }
    public static int getPortId(final String port){
        Integer portId = portIdMap.get(port);
        if(null==portId){
            return -1;
        }
        return portId.intValue();
    }
}

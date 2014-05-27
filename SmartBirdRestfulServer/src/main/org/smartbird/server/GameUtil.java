package org.smartbird.server;

import java.util.HashMap;
import java.util.Map;

import org.sb.mdl.enm.TileEnum;


public class GameUtil {
	protected static Map<String, TileEnum> strToHaiMap = new HashMap<String,TileEnum>();
	
    static{
        strToHaiMap.put("M1",TileEnum.CHARACTOR1);
        strToHaiMap.put("M2",TileEnum.CHARACTOR2);
        strToHaiMap.put("M3",TileEnum.CHARACTOR3);
        strToHaiMap.put("M4",TileEnum.CHARACTOR4);
        strToHaiMap.put("M5",TileEnum.CHARACTOR5);
        strToHaiMap.put("M6",TileEnum.CHARACTOR6);
        strToHaiMap.put("M7",TileEnum.CHARACTOR7);
        strToHaiMap.put("M8",TileEnum.CHARACTOR8);
        strToHaiMap.put("M9",TileEnum.CHARACTOR9);
        
        strToHaiMap.put("S1",TileEnum.BAMBOO1);
        strToHaiMap.put("S2",TileEnum.BAMBOO2);
        strToHaiMap.put("S3",TileEnum.BAMBOO3);
        strToHaiMap.put("S4",TileEnum.BAMBOO4);
        strToHaiMap.put("S5",TileEnum.BAMBOO5);
        strToHaiMap.put("S6",TileEnum.BAMBOO6);
        strToHaiMap.put("S7",TileEnum.BAMBOO7);
        strToHaiMap.put("S8",TileEnum.BAMBOO8);
        strToHaiMap.put("S9",TileEnum.BAMBOO9);

        strToHaiMap.put("P1",TileEnum.CIRCLE1);
        strToHaiMap.put("P2",TileEnum.CIRCLE2);
        strToHaiMap.put("P3",TileEnum.CIRCLE3);
        strToHaiMap.put("P4",TileEnum.CIRCLE4);
        strToHaiMap.put("P5",TileEnum.CIRCLE5);
        strToHaiMap.put("P6",TileEnum.CIRCLE6);
        strToHaiMap.put("P7",TileEnum.CIRCLE7);
        strToHaiMap.put("P8",TileEnum.CIRCLE8);
        strToHaiMap.put("P9",TileEnum.CIRCLE9);

        strToHaiMap.put("EA",TileEnum.EAST);
        strToHaiMap.put("SO",TileEnum.SOUTH);
        strToHaiMap.put("WE",TileEnum.WEST);
        strToHaiMap.put("NO",TileEnum.NORTH);
        strToHaiMap.put("D1",TileEnum.WHITE);
        strToHaiMap.put("D2",TileEnum.GREEN);
        strToHaiMap.put("D3",TileEnum.RED);     
    }
    public static TileEnum getTileEnum(String character){
    	return strToHaiMap.get(character);
    }
}

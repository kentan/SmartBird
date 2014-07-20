package org.smartbirdpj.util;

import java.util.HashMap;
import java.util.Map;

import org.smartbirdpj.mdl.enm.TileEnum;

public class SBUtil {
	static Map<String, TileEnum> strToTileMap = new HashMap<String,TileEnum>();
	
    static{
        strToTileMap.put("M1",TileEnum.CHARACTOR1);
        strToTileMap.put("M2",TileEnum.CHARACTOR2);
        strToTileMap.put("M3",TileEnum.CHARACTOR3);
        strToTileMap.put("M4",TileEnum.CHARACTOR4);
        strToTileMap.put("M5",TileEnum.CHARACTOR5);
        strToTileMap.put("M6",TileEnum.CHARACTOR6);
        strToTileMap.put("M7",TileEnum.CHARACTOR7);
        strToTileMap.put("M8",TileEnum.CHARACTOR8);
        strToTileMap.put("M9",TileEnum.CHARACTOR9);
        
        strToTileMap.put("S1",TileEnum.BAMBOO1);
        strToTileMap.put("S2",TileEnum.BAMBOO2);
        strToTileMap.put("S3",TileEnum.BAMBOO3);
        strToTileMap.put("S4",TileEnum.BAMBOO4);
        strToTileMap.put("S5",TileEnum.BAMBOO5);
        strToTileMap.put("S6",TileEnum.BAMBOO6);
        strToTileMap.put("S7",TileEnum.BAMBOO7);
        strToTileMap.put("S8",TileEnum.BAMBOO8);
        strToTileMap.put("S9",TileEnum.BAMBOO9);

        strToTileMap.put("P1",TileEnum.CIRCLE1);
        strToTileMap.put("P2",TileEnum.CIRCLE2);
        strToTileMap.put("P3",TileEnum.CIRCLE3);
        strToTileMap.put("P4",TileEnum.CIRCLE4);
        strToTileMap.put("P5",TileEnum.CIRCLE5);
        strToTileMap.put("P6",TileEnum.CIRCLE6);
        strToTileMap.put("P7",TileEnum.CIRCLE7);
        strToTileMap.put("P8",TileEnum.CIRCLE8);
        strToTileMap.put("P9",TileEnum.CIRCLE9);

        strToTileMap.put("J1",TileEnum.EAST);
        strToTileMap.put("J2",TileEnum.SOUTH);
        strToTileMap.put("J3",TileEnum.WEST);
        strToTileMap.put("J4",TileEnum.NORTH);
        strToTileMap.put("J5",TileEnum.WHITE);
        strToTileMap.put("J6",TileEnum.GREEN);
        strToTileMap.put("J7",TileEnum.RED);     

    }
    
    public static TileEnum toTileEnum(String tile){
    	return strToTileMap.get(tile);
    }
}

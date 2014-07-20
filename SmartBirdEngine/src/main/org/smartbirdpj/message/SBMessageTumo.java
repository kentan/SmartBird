package org.smartbirdpj.message;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.smartbirdpj.mdl.PaidPoint;
import org.smartbirdpj.mdl.enm.TileEnum;

public class SBMessageTumo extends SBMessage{
	final private static String OPERATION = "tumo";
	final private static String JSON_KEY_TUMO_TILE = "tumoTile";
	
	public SBMessageTumo(int playerId, TileEnum tumoTile,PaidPoint point){
		super(playerId, OPERATION);

		List<Integer> playersA = point.getPayingPlayerIdOnPoint1();
		int pointA= point.getPoint1();		
		for(int id : playersA){
			this.jsonValueMap.put("player" + String.valueOf(id) + "payment",pointA);
		}
		
		List<Integer> playersB = point.getPayingPlayerIdOnPoint2();
		int pointB = point.getPoint2();
		for(int id : playersB){
			this.jsonValueMap.put("player" + String.valueOf(id) + "payment",pointB);
		}
		
//		Map<String,Object> jsonChildMap = new HashMap<String, Object>();
		this.jsonValueMap.put(JSON_KEY_TUMO_TILE,tumoTile.toString());

	}
}

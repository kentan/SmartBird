package org.smartbirdpj.message;

import java.util.HashMap;
import java.util.Map;

import org.sb.mdl.enm.TileEnum;

public class SBMessageRon extends SBMessage {
	final private static String OPERATION = "ron";
	final private static String JSON_KEY_RON_TILE = "ronTile";
	
	public SBMessageRon(int playerId, TileEnum ronTile){
		super(playerId, OPERATION);

//		Map<String,Object> jsonChildMap = new HashMap<String, Object>();
		this.jsonValueMap.put(JSON_KEY_RON_TILE,ronTile.toString());

	}
}

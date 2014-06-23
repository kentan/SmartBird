package org.smartbirdpj.message;

import java.util.HashMap;
import java.util.Map;

import org.sb.mdl.enm.TileEnum;

public class SBMessageRichi extends SBMessage{
	final private static String OPERATION = "richi";
	final private static String JSON_KEY_RICHI_TILE = "richiTile";
	
	public SBMessageRichi(int playerId, TileEnum richiTile){
		super(playerId, OPERATION);

//		Map<String,Object> jsonChildMap = new HashMap<String, Object>();
		this.jsonValueMap.put(JSON_KEY_RICHI_TILE,richiTile.toString());

	}
}

package org.smartbirdpj.message;


import org.smartbirdpj.mdl.PaidPoint;
import org.smartbirdpj.mdl.enm.TileEnum;

public class SBMessageRon extends SBMessage {
	final private static String OPERATION = "ron";
	final private static String JSON_KEY_RON_TILE = "ronTile";
	final private static String JSON_KEY_RONNED_PLAYER = "ronnedPlayer";
	public SBMessageRon(int playerId, TileEnum ronTile,PaidPoint point){
		super(playerId, OPERATION);

//		Map<String,Object> jsonChildMap = new HashMap<String, Object>();
		this.jsonValueMap.put(JSON_KEY_RON_TILE,ronTile.toString());
		this.jsonValueMap.put(JSON_KEY_RONNED_PLAYER,ronTile.toString());
		int paying = point.getPayingPlayerIdOnPoint1().get(0);
		int p= point.getPoint1();		

		this.jsonValueMap.put("player" + String.valueOf(point.getPaidPlayerId()) + "payment",p);
		this.jsonValueMap.put(JSON_KEY_RONNED_PLAYER,paying);
	}
}

package org.smartbirdpj.message;


import org.smartbirdpj.mdl.enm.TileEnum;

public class SBMessageFinishRound extends SBMessage{
	TileEnum discardedTile;
	private final static String OPERATION = "finishRound";
	private final static String JSON_KEY_WINNER = "winner";

	public SBMessageFinishRound(int winningPlayerId){
		super(-1,OPERATION);
		
		this.jsonValueMap.put(JSON_KEY_WINNER, winningPlayerId);

	}
}

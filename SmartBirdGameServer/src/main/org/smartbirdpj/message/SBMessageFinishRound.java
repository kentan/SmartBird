package org.smartbirdpj.message;

import org.sb.mdl.enm.TileEnum;

public class SBMessageFinishRound extends SBMessage{
	TileEnum discardedTile;
	private final static String OPERATION = "finishRound";

	public SBMessageFinishRound(){
		super(-1,OPERATION);		
	}
}

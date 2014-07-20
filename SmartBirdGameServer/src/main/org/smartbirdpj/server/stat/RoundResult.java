package org.smartbirdpj.server.stat;

import java.util.List;

import org.smartbirdpj.mdl.PaidPoint;
import org.smartbirdpj.mdl.enm.TileEnum;

public class RoundResult {
	int gameNumber;
	int roundNumber;
	int parentPlayerId;
	
	int winnerPlayerId;
	List<TileEnum> winningTileSet;
	
	PaidPoint paidPoint;
}

package org.sb.server.stat;

import java.util.List;

import org.sb.mdl.PaidPoint;
import org.sb.mdl.enm.TileEnum;

public class RoundResult {
	int gameNumber;
	int roundNumber;
	int parentPlayerId;
	
	int winnerPlayerId;
	List<TileEnum> winningTileSet;
	
	PaidPoint paidPoint;
}

package org.smartbirdpj.client.shizimily7;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;


import org.smartbirdpj.server.player.AbstractGamePlayer;
import org.smartbirdpj.server.CommandEnum;
import org.smartbirdpj.server.GameServerLogger;
import org.smartbirdpj.server.InputCommand;
import org.smartbirdpj.mdl.MeldElement;
import org.smartbirdpj.mdl.enm.TileEnum;

public class ShizimilyPlayer extends AbstractGamePlayer
{


	private int searchNextHand2(List<TileEnum> tiles, List<TileEnum> liveTiles, int depth) {
		int score = 0;

		int[] shanten = PlayerUtil.calcShanten(tiles);

		for (TileEnum liveTile : liveTiles) {
			List<TileEnum> newTiles = new ArrayList<TileEnum>(tiles);
			List<TileEnum> newLiveTiles = new ArrayList<TileEnum>(liveTiles);
			newTiles.add(liveTile);
			newLiveTiles.remove(liveTile);
			int[] newShanten = PlayerUtil.calcShanten(newTiles);

			if (shanten[0] > newShanten[0]) {
				score ++;
			}
		}
		return score;
	}

	private InputCommand computeNextHand(List<TileEnum> tiles, List<TileEnum> liveTiles) {

		InputCommand command = new InputCommand();
		int[] shanten = PlayerUtil.calcShanten(tiles);

		// シャンテン数により分岐
		if (shanten[0] == -1) {
			command.setCommand(CommandEnum.TUMO);
			return command;
		}

		// ラスト
		if (liveTiles.size() == 0) {
			command.setDiscardedTile(tiles.get(0));
			command.setCommand(CommandEnum.DISCARD);
		}

		int maxScore = -1;
		TileEnum best = tiles.get(0);
		for (TileEnum tile : tiles) {
			List<TileEnum> tmpTiles = new ArrayList<TileEnum>(tiles);
			List<TileEnum> tmpLiveTiles = new ArrayList<TileEnum>(liveTiles);
			PlayerUtil.sortTiles(tmpTiles);
			tmpTiles.remove(tile);
			tmpLiveTiles.add(tile);
			int[] tmpShanten = PlayerUtil.calcShanten(tmpTiles);
			if (tmpShanten[0] == shanten[0]) {
			int score = searchNextHand2(tmpTiles, tmpLiveTiles, 0);
				//ps.println("Hai: " + tile + " Score: " + score);
				if (maxScore < score) {
					best = tile;
					maxScore = score;
				}
			}
		}
		//ps.println("Shanten: " + shanten[0]);
		command.setDiscardedTile(best);

		if (shanten[0] == 0) {
			command.setCommand(CommandEnum.RICHI);
		} else {
			command.setCommand(CommandEnum.DISCARD);
		}
		return command;

	}

	@Override
	public void notifyTurn(List<TileEnum> tiles, TileEnum tileAtThisTurn, List<MeldElement> huroMelds, List<TileEnum> discardedTiles,
			Map<Integer, List<TileEnum>> otherPlayerDiscardedTiles, Map<Integer, List<MeldElement>> otherPlayerHuroTiles) {

		TileEnum discardingTile = null;
		List<TileEnum> myTiles = new ArrayList<TileEnum>(tiles);
		Collections.sort(myTiles);

		GameServerLogger.write("Player" + _playerId + ">");
		GameServerLogger.write(myTiles);
		GameServerLogger.write(" :" + tileAtThisTurn);
		GameServerLogger.writeln(":" + huroMelds);
		List<TileEnum> liveTiles = PlayerUtil.getLiveTile(tiles, tileAtThisTurn, huroMelds, discardedTiles, otherPlayerDiscardedTiles, otherPlayerHuroTiles);
		InputCommand input = computeNextHand(tiles, liveTiles);
		switch(input.getCommand()){
			case TUMO:
				tumo(tileAtThisTurn);
				break;
			case RICHI:
				discardingTile = input.getDiscardedTile();
				richi(discardingTile);
				break;
			case DISCARD:
				discardingTile  = input.getDiscardedTile();
				discard(discardingTile);
				myTiles.remove(discardingTile);
				break;
			case KONG_BY_WALL:
				break;
		}

		GameServerLogger.write("Player" + _playerId + ">");
		GameServerLogger.writeln("Discard:" + discardingTile);
	}


	@Override
	public void notifySteal(List<TileEnum> tiles, List<MeldElement> huroMelds, List<TileEnum> discardedTiles,
			Map<Integer, List<TileEnum>> otherPlayerDiscardedTiles, Map<Integer, List<MeldElement>> otherPlayerHuroTiles,
			TileEnum currentDiscardedTile) {

		List<TileEnum> myTiles = new ArrayList<TileEnum>(tiles);
		myTiles.add(currentDiscardedTile);
		int[] shanten = PlayerUtil.calcShanten(myTiles);
		if (shanten[0] == -1) {
			ron(currentDiscardedTile);
		}
	}
}

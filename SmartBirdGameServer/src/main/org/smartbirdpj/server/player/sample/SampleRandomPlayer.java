package org.smartbirdpj.server.player.sample;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.smartbirdpj.server.GameServerLogger;
import org.smartbirdpj.server.player.AbstractGamePlayer;
import org.smartbirdpj.mdl.MeldElement;
import org.smartbirdpj.mdl.enm.TileEnum;

public class SampleRandomPlayer extends AbstractGamePlayer{
	private Random rnd = new Random();
	public TileEnum nextInput(List<TileEnum> tiles){
		int rndInt = rnd.nextInt(tiles.size() - 1);
		return tiles.get(rndInt);
	}
	@Override
	public void notifyTurn(List<TileEnum> tiles, TileEnum tileAtThisTurn, List<MeldElement> huroMelds, List<TileEnum> discardedTiles,
			Map<Integer, List<TileEnum>> otherPlayerDiscardedTiles, Map<Integer, List<MeldElement>> otherPlayerHuroTiles) {

		List<TileEnum> myTiles = new ArrayList<TileEnum>();
		for(TileEnum t : tiles){
			myTiles.add(t);
		}
		Collections.sort(myTiles);
		boolean oneMore = true;
		TileEnum discardTile = null;
		while(oneMore){
			GameServerLogger.write("Player" + _playerId  + ">");
			GameServerLogger.writeln(myTiles + " :" + tileAtThisTurn + ":" + huroMelds);
			discardTile = nextInput(myTiles);
			oneMore = !myTiles.remove(discardTile);
			
		}
		
		discard(discardTile);
		


		GameServerLogger.write("Player" + _playerId + ">");
		GameServerLogger.writeln("Discard:" + discardTile);
	}
	@Override
	public void notifySteal(List<TileEnum> tiles, List<MeldElement> huroMelds, List<TileEnum> discardedTiles,
			Map<Integer, List<TileEnum>> otherPlayerDiscardedTiles, Map<Integer, List<MeldElement>> otherPlayerHuroMelds,
			TileEnum currentDiscardedTile) {
		// TODO Auto-generated method stub
		
	}

	

}

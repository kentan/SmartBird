package org.sb.server.player.sample;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.sb.server.player.AbstractGamePlayer;
import org.sb.mdl.MeldElement;
import org.sb.mdl.enm.TileEnum;

public class SampleRandomPlayer extends AbstractGamePlayer{
	private Random rnd = new Random();
	public TileEnum nextInput(List<TileEnum> tiles){
		int rndInt = rnd.nextInt(tiles.size() - 1);
		return tiles.get(rndInt);
	}
	@Override
	public void notifyTurn(List<TileEnum> tiles, TileEnum tileAtThisTurn, List<MeldElement> huroMelds, List<TileEnum> discardeTiles,
			Map<Integer, List<TileEnum>> otherPlayerDiscardedTiles, Map<Integer, List<MeldElement>> otherPlayerHuroTiles) {

		List<TileEnum> myTiles = new ArrayList<TileEnum>();
		for(TileEnum t : tiles){
			myTiles.add(t);
		}
		Collections.sort(myTiles);
		boolean oneMore = true;
		TileEnum discardTile = null;
		while(oneMore){
//			System.out.print("Player" + _playerId  + ">");
//			System.out.println(myTiles);
			discardTile = nextInput(myTiles);
			oneMore = !myTiles.remove(discardTile);
			
		}
		
		discard(discardTile);
		


//		System.out.print("Player" + _playerId + ">");
//		System.out.println(discardTile);
	}
	@Override
	public void notifySteal() {

		return ;
	}

}

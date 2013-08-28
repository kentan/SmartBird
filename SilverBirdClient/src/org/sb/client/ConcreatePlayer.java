package org.sb.client;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.sb.server.player.AbstractGamePlayer;
import org.sb.server.CommandEnum;
import org.sb.server.InputCommand;
import org.sb.mdl.MeldElement;
import org.sb.mdl.enm.TileEnum;

public class ConcreatePlayer extends AbstractGamePlayer
{
	PrintStream ps = System.out;
	private void sortTiles(List<TileEnum> tiles){
		
		Collections.sort(tiles,new Comparator<TileEnum>(){

			@Override
			public int compare(TileEnum o1, TileEnum o2) {
				// TODO Auto-generated method stub
				return o1.getIndex() - o2.getIndex();
			}
			
		});
	}
	final static int POINT_EYES = 3;
	final static int POINT_KREW = 3;
	final static int POINT_KREW_CANDIDATE = 1;
	final static int POINT_PONG = 3;

	private InputCommand computeNextHand(List<TileEnum> tiles,TileEnum tileAtThisTurn){
		InputCommand command = new InputCommand();

		if(isWinningHandsValid(tileAtThisTurn)){
			command.setCommand(CommandEnum.TUMO);
			return command;
		}
		
		sortTiles(tiles);
		Map<TileEnum,Integer> evaluationMap = new HashMap<TileEnum, Integer>();
		for(TileEnum t : tiles){
			evaluationMap.put(t,0);
		}
		int i = 0;
		while(i < tiles.size() - 2){
			TileEnum t0 = tiles.get(i);
			TileEnum t1 = tiles.get(i + 1);
			TileEnum t2 = tiles.get(i + 2);

			if(t0 == t1){
				if(t0 == t2){
					evaluationMap.put(t0,POINT_PONG);
				}else{
					evaluationMap.put(t0,POINT_EYES);
				}
			}else if(t0.next() == t1){
				if(t1.next() == t2){
					evaluationMap.put(t0,POINT_KREW);
				}else{
					evaluationMap.put(t0,POINT_KREW_CANDIDATE);
				}
			}else if(t0.next() != null && t0.next().next() == t1){
				evaluationMap.put(t0,POINT_KREW_CANDIDATE);
			}else{
				//nop
			}
			i++;
		}

		int min = Integer.MAX_VALUE;
		TileEnum minValuedTile = null;
		for(TileEnum t : tiles){
			int current = evaluationMap.get(t);
			if(min > current){
				min = current;
				minValuedTile = t;
			}
		}
		

		command.setCommand(CommandEnum.DISCARD);
		command.setDiscardedTile(minValuedTile);
		
		return command;
	}
	
	@Override
	public void notifyTurn(List<TileEnum> tiles, TileEnum tileAtThisTurn, List<MeldElement> huroMelds, List<TileEnum> discardedTiles,
			Map<Integer, List<TileEnum>> otherPlayerDiscardedTiles, Map<Integer, List<MeldElement>> otherPlayerHuroTiles) {

		TileEnum discardingTile = null;
		List<TileEnum> myTiles = new ArrayList<TileEnum>();
		for(TileEnum tile :tiles){
			// don't need to clone because tile is enum
			myTiles.add(tile);
		}
		

		Collections.sort(myTiles);
	


		ps.print("Player" + _playerId + ">");
		ps.print(myTiles);
		ps.print(" :" + tileAtThisTurn);
		ps.println(":" + huroMelds);
		InputCommand input = computeNextHand(tiles,tileAtThisTurn);
		switch(input.getCommand()){
			case TUMO:
				tumo(tileAtThisTurn);
				break;
			case RICHI:
				TileEnum richiTile = input.getRichiTile();
				richi(richiTile);
				break;				
			case DISCARD:
				discardingTile  = input.getDiscardedTile();
				discard(discardingTile);
				myTiles.remove(discardingTile);
				break;
			case KONG_BY_WALL:
				break;
		}

		ps.print("Player" + _playerId + ">");
		ps.println("Discard:" + discardingTile);
	}

	private Map<Integer,MeldCandidate2> findMeldCandidate(List<TileEnum> tiles){
		Map<Integer,MeldCandidate2> candidatePosMap = new HashMap<Integer, MeldCandidate2>();
		for(int i = 0 ; i < tiles.size() - 2; i++){
			TileEnum t0 = tiles.get(i);
			TileEnum t1 = tiles.get(i + 1);

			List<TileEnum> candidates = new ArrayList<TileEnum>();
			MeldCandidateEnum kind = null;
			if(t0 == t1){
				// pong Candidate
				TileEnum t2 = tiles.get(i + 2);
				if(t0 != t2){
					kind = MeldCandidateEnum.PONG_CANDIDATE;
				}else{
					candidates.add(t2);
					kind = MeldCandidateEnum.KONG_CANDIDATE;
				}
			}else if(t0.next() == t1){
				// chow Candidate;
				kind = MeldCandidateEnum.CHOW_CANDIDATE_BOTH_SIDE;

			}else if(t0.next() != null && t0.next().next() == t1){

				kind = MeldCandidateEnum.CHOW_CANDIDATE_MIDDLE;
				// chow Candidate;
			}else{
				continue;				
			}
			candidates.add(t0);
			candidates.add(t1);

			MeldCandidate2 meldCandidate = new MeldCandidate2();
			meldCandidate.setMeldCandidateEnum(kind);
			meldCandidate.setTiles(candidates);

			candidatePosMap.put(i, meldCandidate);
			
		}
		return candidatePosMap;
	}	
	
	private boolean isSteal(List<TileEnum> tiles){
		
		return false;
	}

	@Override
	public void notifySteal(List<TileEnum> tiles, List<MeldElement> huroMelds, List<TileEnum> discardedTiles,
			Map<Integer, List<TileEnum>> otherPlayerDiscardedTiles, Map<Integer, List<MeldElement>> otherPlayerHuroTiles,
			TileEnum currentDiscardedTile) {
		// TODO Auto-generated method stub

		Map<Integer,MeldCandidate2> map = findMeldCandidate(tiles);
		
	}

	

	
}

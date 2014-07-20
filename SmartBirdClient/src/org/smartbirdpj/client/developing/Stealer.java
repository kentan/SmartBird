package org.smartbirdpj.client.developing;




import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.smartbirdpj.server.player.AbstractGamePlayer;
import org.smartbirdpj.server.CommandEnum;
import org.smartbirdpj.server.GameServerLogger;
import org.smartbirdpj.server.InputCommand;
import org.smartbirdpj.mdl.MeldElement;
import org.smartbirdpj.mdl.enm.TileEnum;

public class Stealer extends AbstractGamePlayer
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
	final static int POINT_TERMINAL = -10;
	final static int POINT_DRAGON = 1;
	
	final static int POINT_EYES_LESS_THAN5 = 10;
	final static int POINT_KREW_LESS_THAN5 = 10;
	final static int POINT_KREW_CANDIDATE_LESS_THAN5 = 1;
	final static int POINT_PONG_LESS_THAN5 = 10;
	final static int POINT_TERMINAL_LESS_THAN5 = -5;
	final static int POINT_DRAGON_LESS_THAN5 = -5;

	private int getPointEyes(boolean isLessThan5,boolean hasMelds){
		if(isLessThan5){
			return 10;
		}
		return 3;
	}
	private int getPointKrew(boolean isLessThan5,boolean hasMelds){
		if(isLessThan5){
			return 10;
		}
		return 3;
	}
	private int getPointKrewCandidate(boolean isLessThan5,boolean hasMelds){
		if(isLessThan5){
			return 1;
		}
		return 1;
	}
	private int getPointPong(boolean isLessThan5,boolean hasMelds){
		if(isLessThan5){
			return 10;
		}
		return 3;
	}
	private int getPointTerminal(boolean isLessThan5,boolean hasMelds){
		if(hasMelds){
			return 0;
		}
		if(isLessThan5){
			return -5;
		}
		return -10;
	}
	private int getPointDragon(boolean isLessThan5,boolean hasMelds){
		if(hasMelds){
			return 0;
		}
		if(isLessThan5){
			return -5;
		}
		return 1;
	}

	private boolean hasMelds = false;
	private InputCommand computeNextHand(List<TileEnum> tiles,TileEnum tileAtThisTurn){
		int theNumberOfTiles = tiles.size();
		boolean isLessThan5 = (theNumberOfTiles <= 5);
		InputCommand command = new InputCommand();

		if(isWinningHandsValid(tileAtThisTurn)){
			command.setCommand(CommandEnum.TUMO);
			return command;
		}
		sortTiles(tiles);

		Map<TileEnum,Integer> evaluationMap = new HashMap<TileEnum, Integer>();
		for(TileEnum t : tiles){
			if(t.isTerminal() || t == TileEnum.WEST || t == TileEnum.SOUTH || t == TileEnum.EAST || t == TileEnum.NORTH){
				evaluationMap.put(t,getPointTerminal(isLessThan5, hasMelds));
			}else if(t== TileEnum.RED || t == TileEnum.WHITE || t == TileEnum.GREEN){
				evaluationMap.put(t,getPointDragon(isLessThan5, hasMelds));
			}else{
				evaluationMap.put(t,0);
			}
		}
		int i = 0;
		while(i < tiles.size()){
			TileEnum t0 = tiles.get(i);
			TileEnum t1 = null,t2 = null;
			if(i < tiles.size() - 2){
				t1 = tiles.get(i + 1);
			}
			if(i < tiles.size() - 3){
				t2 = tiles.get(i + 2);
			}
			// discard terminal tiles for kuitan


			
			if(t0 == t1){
				if(t0 == t2){
					evaluationMap.put(t0,getPointPong(isLessThan5, hasMelds));
					evaluationMap.put(t1,getPointPong(isLessThan5, hasMelds));
					evaluationMap.put(t2,getPointPong(isLessThan5, hasMelds));					
					
				}else{
					evaluationMap.put(t0,getPointEyes(isLessThan5, hasMelds));
					evaluationMap.put(t1,getPointEyes(isLessThan5, hasMelds));
					evaluationMap.put(t2,getPointEyes(isLessThan5, hasMelds));

				}
			}else if(t0.next() == t1){
				if(t1 == null){
					// nop
				}
				else if(t1.next() != null && t1.next() == t2){
					evaluationMap.put(t0,getPointKrew(isLessThan5, hasMelds));
					evaluationMap.put(t1,getPointKrew(isLessThan5, hasMelds));
					evaluationMap.put(t2,getPointKrew(isLessThan5, hasMelds));
				}else{
					evaluationMap.put(t0,getPointKrewCandidate(isLessThan5, hasMelds));
					evaluationMap.put(t1,getPointKrewCandidate(isLessThan5, hasMelds));

				}
			}else if(t0.next() != null && t0.next().next() == t1){
				evaluationMap.put(t0,getPointKrewCandidate(isLessThan5, hasMelds));

				if(t1 != null){
					evaluationMap.put(t1,getPointKrewCandidate(isLessThan5, hasMelds));
				}
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
	


		GameServerLogger.write("Player" + _playerId + ">");
		GameServerLogger.write(myTiles);
		GameServerLogger.write(" :" + tileAtThisTurn);
		GameServerLogger.writeln(":" + huroMelds);
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

		GameServerLogger.write("Player" + _playerId + ">");
		GameServerLogger.writeln("Discard:" + discardingTile);
	}

	private boolean meldsValid = false;
	private MeldCandidate findMeldCandidate(List<TileEnum> tiles,TileEnum discardedTile){
		MeldCandidate candidate = new MeldCandidate();
		
		
		
		for(int i = 0 ; i < tiles.size() - 2; i++){
			TileEnum t0 = tiles.get(i);
			TileEnum t1 = tiles.get(i + 1);


			MeldCandidateEnum kind = null;
			if(t0 == t1 && t0 == discardedTile){
				
				if(meldsValid == false && t0.isTerminal()){
					continue;
				}
				if(t0.isWindAndDragon()){
					meldsValid = true;
				}
				TileEnum t2 = tiles.get(i + 2);
				

				if(t0 != t2){// pong Candidate
					MeldCandidateElement elem = new MeldCandidateElement();
					List<TileEnum> candidates = new ArrayList<TileEnum>();
					candidates.add(t0);
					candidates.add(t1);

					kind = MeldCandidateEnum.PONG_CANDIDATE;
					elem.setCandidate();
					elem.setMeldCandidateType(kind);
					elem.setList(candidates);

					candidate.add(elem);
	
				}else{
					MeldCandidateElement elem = new MeldCandidateElement();
					List<TileEnum> candidates = new ArrayList<TileEnum>();
					candidates.add(t0);
					candidates.add(t1);

					candidates.add(t2);
					elem.setComplete();
					elem.setList(candidates);
					elem.setPosition(i);					
					kind = MeldCandidateEnum.KONG_CANDIDATE;
					elem.setMeldCandidateType(kind);
				
					candidate.add(elem);
				}
			}else if(!t0.isWindAndDragon() && t0.next() == t1){
				
				if(discardedTile.next() == t0 || t1.next() == discardedTile){
					if(t0.isTerminal() || t1.isTerminal()|| discardedTile.isTerminal()){
						continue;
					}
					MeldCandidateElement elem = new MeldCandidateElement();
					List<TileEnum> candidates = new ArrayList<TileEnum>();
					// chow Candidate;
					kind = MeldCandidateEnum.CHOW_CANDIDATE_BOTH_SIDE;
					candidates.add(t0);
					candidates.add(t1);
	
					elem.setList(candidates);
					elem.setCandidate();
					elem.setPosition(i);
					elem.setMeldCandidateType(kind);
					candidate.add(elem);
				}
			}else if(!t0.isWindAndDragon() && t0.next() == discardedTile && discardedTile.next() == t1){
				if(t0.isTerminal() || t1.isTerminal() || discardedTile.isTerminal()){
					continue;
				}
				MeldCandidateElement elem = new MeldCandidateElement();
				List<TileEnum> candidates = new ArrayList<TileEnum>();
				kind = MeldCandidateEnum.CHOW_CANDIDATE_MIDDLE;
				// chow Candidate;
				candidates.add(t0);
				candidates.add(t1);

				elem.setList(candidates);
				elem.setCandidate();
				elem.setPosition(i);
				elem.setMeldCandidateType(kind);
				
				candidate.add(elem);
			}else{
				continue;				
			}

			
		}

		return candidate;
	}	

	private boolean canRon(List<TileEnum> tiles,TileEnum currentDiscardedTile){
		return super.isWinningHandsValid(currentDiscardedTile);
	}
	@Override
	public void notifySteal(List<TileEnum> tiles, List<MeldElement> huroMelds, List<TileEnum> discardedTiles,
			Map<Integer, List<TileEnum>> otherPlayerDiscardedTiles, Map<Integer, List<MeldElement>> otherPlayerHuroTiles,
			TileEnum currentDiscardedTile) {
				
		// TODO Auto-generated method stub
		if(canRon(discardedTiles, currentDiscardedTile)){
			super.ron(currentDiscardedTile);
		}
		MeldCandidate candidate = findMeldCandidate(tiles,currentDiscardedTile);
//		int theNumberOfTiles = tiles.size();
//		if(theNumberOfTiles <= 5){
//			return ;
//		}
//		
		for(MeldCandidateElement elem : candidate.getList()){
			GameServerLogger.writeln(elem.getPosition());
			GameServerLogger.writeln(elem.getMeldCandidateType());
			for(TileEnum t : elem.getList()){
				GameServerLogger.write(t + ":");
			}
			GameServerLogger.writeln("");


			List<TileEnum> myTiles = new ArrayList<TileEnum>();
			myTiles.addAll(tiles);
			myTiles.remove(elem.getList().get(0));
			myTiles.remove(elem.getList().get(1));
			
			InputCommand command = computeNextHand(myTiles, currentDiscardedTile);
			TileEnum discardingTile = command.getDiscardedTile();
			GameServerLogger.writeln("discarding:" + discardingTile);
			// とりあえず一個目のcandidateしか見ない
			switch(elem.getMeldCandidateType()){
				case CHOW_CANDIDATE_BOTH_SIDE:
				case CHOW_CANDIDATE_MIDDLE:
					chow(currentDiscardedTile, elem.getList().get(0), elem.getList().get(1), discardingTile);
					break;
				case PONG_CANDIDATE:
					
					if(currentDiscardedTile == TileEnum.RED || currentDiscardedTile == TileEnum.WHITE ||currentDiscardedTile == TileEnum.GREEN){
						hasMelds = true;
					}
					pong(currentDiscardedTile, elem.getList().get(0), elem.getList().get(1), discardingTile);
					break;
				case KONG_CANDIDATE:
					// do nothing for kong;
					break;
				default:
					break;
			}
		}
		GameServerLogger.writeln("--------");
		

	}

	

	
}

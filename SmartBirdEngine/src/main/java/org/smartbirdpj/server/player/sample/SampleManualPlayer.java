package org.smartbirdpj.server.player.sample;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.smartbirdpj.mdl.MeldElement;
import org.smartbirdpj.mdl.enm.TileEnum;
import org.smartbirdpj.server.CommandEnum;
import org.smartbirdpj.server.InputCommand;
import org.smartbirdpj.server.player.AbstractGamePlayer;


public class SampleManualPlayer extends AbstractGamePlayer {

	private boolean isTumoValid(){
		return true;
	}
	
	private boolean isRichiValid(){
		return false;
	}
	private InputCommand nextInputFromUserInput(List<TileEnum> tiles){
		Scanner scanner = new Scanner(System.in);
		String input = null;
		InputCommand command = new InputCommand();;
		do{
			System.out.print(">");
			input = scanner.next();
			if(input.matches("TUMO")){
			
				if(isTumoValid()){
					command.setCommand(CommandEnum.TUMO);
					break;
				}else{
					System.out.println("no");
				}
				
			}else if(input.matches("RICHI")){
				if(isRichiValid()){
					TileEnum tile =getTileEnum(input);
					
					command.setRichiTile(tile);
					command.setCommand(CommandEnum.RICHI);									
					break;
				}
			}else{
				TileEnum tile =getTileEnum(input);

				command.setDiscardedTile(tile);
				command.setCommand(CommandEnum.DISCARD);
				break;
			}

		}while(true);
		
		return command;
	}
	public InputCommand nextInput(List<TileEnum> tiles){
		return nextInputFromUserInput(tiles);
	}
	
	@Override
	public void notifyTurn(List<TileEnum> tiles, TileEnum tileAtThisTurn, List<MeldElement> huroMelds, List<TileEnum> discardeTiles,
			Map<Integer, List<TileEnum>> otherPlayerDiscardedTiles, Map<Integer, List<MeldElement>> otherPlayerHuroTiles) {
		List<TileEnum> myTiles = new ArrayList<TileEnum>();
		for(TileEnum tile :tiles){
			// don't need to clone because tile is enum
			myTiles.add(tile);
		}
		

		Collections.sort(myTiles);
	


		System.out.print("Player" + _playerId + ">");
		System.out.print(myTiles);
		System.out.print(" :" + tileAtThisTurn);
		System.out.println(":" + huroMelds);
		InputCommand input = nextInput(tiles);
		switch(input.getCommand()){
			case TUMO:
				tumo(tileAtThisTurn);

				break;
			case RICHI:
				TileEnum richiTile = input.getRichiTile();

				richi(richiTile);
				break;				
			case DISCARD:
				TileEnum discardingTile  = input.getDiscardedTile();

				discard(discardingTile);
				myTiles.remove(discardingTile);
				break;
			case CHOW:
				//ANKAN
				break;
		}

		System.out.print("Player" + _playerId + ">");
		System.out.println(myTiles);
	}



	protected void handleStdInput() {
		Scanner scanner = new Scanner(System.in);
		System.out.print(">");
		String input = scanner.next();
		if ("a".equals(input)) {
			return ;
		} else {
			String commands[] = input.split(",");
			if (commands.length <= 5) {
				return ;
			}
			String playerIdString = commands[0];
			int playerId = Integer.parseInt(playerIdString);
			String steal = commands[1];
			String discardedTileString = commands[2];
			TileEnum stolenTile = getTileEnum(discardedTileString);
			String huro1String = commands[3];
			String huro2String = commands[4];

			
			TileEnum huro1 = getTileEnum(huro1String);
			TileEnum huro2 = getTileEnum(huro2String);

			if ("C".equals(steal)) {
				
				//TODO validate user input 
				String discarding = commands[5];
				TileEnum discardingTile = getTileEnum(discarding);

				chow(stolenTile,huro1,huro2,discardingTile);


			} else if ("P".equals(steal)) {
				//TODO validate user input 
				String discarding = commands[5];
				TileEnum discardingTile = getTileEnum(discarding);
				pong(stolenTile,huro1,huro2,discardingTile);

			} else if ("K".equals(steal)) {
				//TODO validate user input 
				String huro3String = commands[5];
				TileEnum huro3 = getTileEnum(huro3String);

				String discarding = commands[6];
				TileEnum discardingTile = getTileEnum(discarding);
				kongBySteal(stolenTile,huro1,huro2,huro3,discardingTile);
			} else if ("R".equals(steal)){

				if(ron(stolenTile)){
					System.out.println("Player " + playerId + "win");
					return ;
				}

			}

		}
		return ;		
	}

	@Override
	public void notifySteal(List<TileEnum> tiles, List<MeldElement> huroMelds, List<TileEnum> discardedTiles,
			Map<Integer, List<TileEnum>> otherPlayerDiscardedTiles, Map<Integer, List<MeldElement>> otherPlayerHuroMelds,
			TileEnum currentDiscardedTile) {
		// TODO Auto-generated method stub
		
	}
	protected static Map<String, TileEnum> strToHaiMap = new HashMap<String,TileEnum>();
	
    static{
        strToHaiMap.put("M1",TileEnum.CHARACTOR1);
        strToHaiMap.put("M2",TileEnum.CHARACTOR2);
        strToHaiMap.put("M3",TileEnum.CHARACTOR3);
        strToHaiMap.put("M4",TileEnum.CHARACTOR4);
        strToHaiMap.put("M5",TileEnum.CHARACTOR5);
        strToHaiMap.put("M6",TileEnum.CHARACTOR6);
        strToHaiMap.put("M7",TileEnum.CHARACTOR7);
        strToHaiMap.put("M8",TileEnum.CHARACTOR8);
        strToHaiMap.put("M9",TileEnum.CHARACTOR9);
        
        strToHaiMap.put("S1",TileEnum.BAMBOO1);
        strToHaiMap.put("S2",TileEnum.BAMBOO2);
        strToHaiMap.put("S3",TileEnum.BAMBOO3);
        strToHaiMap.put("S4",TileEnum.BAMBOO4);
        strToHaiMap.put("S5",TileEnum.BAMBOO5);
        strToHaiMap.put("S6",TileEnum.BAMBOO6);
        strToHaiMap.put("S7",TileEnum.BAMBOO7);
        strToHaiMap.put("S8",TileEnum.BAMBOO8);
        strToHaiMap.put("S9",TileEnum.BAMBOO9);

        strToHaiMap.put("P1",TileEnum.CIRCLE1);
        strToHaiMap.put("P2",TileEnum.CIRCLE2);
        strToHaiMap.put("P3",TileEnum.CIRCLE3);
        strToHaiMap.put("P4",TileEnum.CIRCLE4);
        strToHaiMap.put("P5",TileEnum.CIRCLE5);
        strToHaiMap.put("P6",TileEnum.CIRCLE6);
        strToHaiMap.put("P7",TileEnum.CIRCLE7);
        strToHaiMap.put("P8",TileEnum.CIRCLE8);
        strToHaiMap.put("P9",TileEnum.CIRCLE9);

        strToHaiMap.put("EA",TileEnum.EAST);
        strToHaiMap.put("SO",TileEnum.SOUTH);
        strToHaiMap.put("WE",TileEnum.WEST);
        strToHaiMap.put("NO",TileEnum.NORTH);
        strToHaiMap.put("D1",TileEnum.WHITE);
        strToHaiMap.put("D2",TileEnum.GREEN);
        strToHaiMap.put("D3",TileEnum.RED);     
    }
    public static TileEnum getTileEnum(String character){
    	return strToHaiMap.get(character);
    }

}

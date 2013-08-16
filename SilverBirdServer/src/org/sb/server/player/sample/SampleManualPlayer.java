package org.sb.server.player.sample;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.sb.mdl.MeldElement;
import org.sb.mdl.enm.TileEnum;
import org.sb.server.CommandEnum;
import org.sb.server.GameUtil;
import org.sb.server.InputCommand;
import org.sb.server.player.AbstractGamePlayer;


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
					TileEnum tile =GameUtil.getTileEnum(input);
					
					command.setRichiTile(tile);
					command.setCommand(CommandEnum.RICHI);									
					break;
				}
			}else{
				TileEnum tile =GameUtil.getTileEnum(input);

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
				callFinishTumo(tileAtThisTurn);

				break;
			case RICHI:
				TileEnum richiTile = input.getRichiTile();

				callRichi(richiTile);
				break;				
			case DISCARD:
				TileEnum discardingTile  = input.getDiscardedTile();

				discardTile(discardingTile);
				myTiles.remove(discardingTile);
				break;
			case CHOW:
				//ANKAN
				break;
		}

		System.out.print("Player" + _playerId + ">");
		System.out.println(myTiles);
	}

	@Override
	public InputCommand notifySteal() {
		return handleStdInput();
	}

	protected InputCommand handleStdInput() {
		Scanner scanner = new Scanner(System.in);
		System.out.print(">");
		String input = scanner.next();
		if ("a".equals(input)) {
			return null;
		} else {
			String commands[] = input.split(",");
			if (commands.length <= 5) {
				return null;
			}
			String playerIdString = commands[0];
			int playerId = Integer.parseInt(playerIdString);
			String naki = commands[1];
			String discardedTileString = commands[2];
			TileEnum stolenTile = GameUtil.getTileEnum(discardedTileString);
			String huro1String = commands[3];
			String huro2String = commands[4];

			
			TileEnum huro1 = GameUtil.getTileEnum(huro1String);
			TileEnum huro2 = GameUtil.getTileEnum(huro2String);

			if ("C".equals(naki)) {
				//TODO validate user input 
				String discarded = commands[5];
				TileEnum discardedTile = GameUtil.getTileEnum(discarded);

				InputCommand command = new InputCommand();
				command.setCommand(CommandEnum.MELD);
				command.setDiscardedTile(discardedTile);
				command.setStolenTile(stolenTile);
				command.addHuroTile(huro1);
				command.addHuroTile(huro2);
				return command;

			} else if ("P".equals(naki)) {
				//TODO validate user input 
				String discarded = commands[5];
				TileEnum discardedTile = GameUtil.getTileEnum(discarded);


				InputCommand command = new InputCommand();
				command.setCommand(CommandEnum.PONG);
				command.setDiscardedTile(discardedTile);
				command.setStolenTile(stolenTile);
				command.addHuroTile(huro1);
				command.addHuroTile(huro2);
				return command;
			} else if ("K".equals(naki)) {
				//TODO validate user input 
				String huro3String = commands[5];
				TileEnum huro3 = GameUtil.getTileEnum(huro3String);

				String discarded = commands[6];
				TileEnum discardedTile = GameUtil.getTileEnum(discarded);

				InputCommand command = new InputCommand();
				command.setCommand(CommandEnum.CHOW);
				command.setDiscardedTile(discardedTile);
				command.setStolenTile(stolenTile);
				command.addHuroTile(huro1);
				command.addHuroTile(huro2);
				command.addHuroTile(huro3);
				return command;
			} else if ("R".equals(naki)){

				if(callFinishRon(stolenTile)){
					System.out.println("Player " + playerId + "win");
					return null;
				}

			}

		}
		return null;		
	}


}

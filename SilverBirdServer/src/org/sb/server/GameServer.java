package org.sb.server;


import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;




import org.sb.mdl.MeldElement;
import org.sb.mdl.PaidPoint;
import org.sb.mdl.cnst.GameConstants;
import org.sb.mdl.enm.MeldEnum;
import org.sb.mdl.enm.TileEnum;
import org.sb.server.player.AbstractGamePlayer;


public class GameServer {
	PrintStream ps = System.out;
//	private final boolean DEBUG  = false;
	private final boolean HARF_GAME = true;
	List<AbstractGamePlayer> players = new ArrayList<AbstractGamePlayer>();
	
	private static GameTable table;
	GameRoundStatus gameRoundStatus = new GameRoundStatus();
	GamePointHolder gamePointHolder = new GamePointHolder();
	GameValidator gameValidator = new GameValidator();
	private GameServer(){};
	private static GameServer instance = new GameServer();
	static public GameServer getInstance(){
		return instance;
	}

	Random rnd = new Random();

	public void discardTile(int playerId, TileEnum tile) {

		List<TileEnum> tiles = table.getWallTiles(playerId);
		tiles.remove(tile);
		
		table.addDiscardeTile(playerId, tile);
		
		
	}

	/**
	 * In order to avoid implementing the winning hands validation method by each AI programmer,
	 * we provide the method in GameServer. Although this is not directory related to Game Server function,
	 * it might be better to crate new jar file.(cf. Util.jar)
	 * 
	 * @param tileSet
	 * @param playerId
	 * @return
	 */
	public boolean isWinningHandsValid(int playerId,TileEnum tumo){
		table.setTumoTile(playerId, tumo);
		return table.isWinningHandsValid(playerId);
	}
	

	public boolean callRon(int playerId,TileEnum ron){

		table.setRonTile(playerId,ron,getRonedPlayerId());
		
		if(table.isWinningHandsValid(playerId)){
			gameRoundStatus.setRoundEnd();
			PaidPoint point = table.calculate(playerId);
			if(point != null){
				
				
				int p = point.getPoint1();
				List<Integer> fromList = point.getPayingPlayerIdOnPoint1();
				int to = point.getPaidPlayerId();
				for(int from : fromList){
					gamePointHolder.payPoint(gameRoundStatus.getRoundNumber(), p, from, to);				
				}
			
				ps.println(p + " to " + to);


			}
			return true;
		}
		return false;

	}
	
	public boolean callTumo(int playerId,TileEnum tumo) {
				
		table.setTumoTile(playerId, tumo);
		
		if(table.isWinningHandsValid(playerId)){
			gameRoundStatus.setRoundEnd();
			PaidPoint point = table.calculate(playerId);
			if(point != null){
				int p1=0,p2=0;
				p1 = point.getPoint1();
				int to = point.getPaidPlayerId();

				if(gameRoundStatus.getParentPlayerId() == playerId){
					List<Integer> from1List = point.getPayingPlayerIdOnPoint1();

					for(int from : from1List){
						gamePointHolder.payPoint(gameRoundStatus.getRoundNumber(), p1, from,to);
					}
				}else{
					int from1 = point.getPayingPlayerIdOnPoint1().get(0);//from parent
					gamePointHolder.payPoint(gameRoundStatus.getRoundNumber(), p1, from1,to);
					List<Integer> from2List = point.getPayingPlayerIdOnPoint2();
					
					p2 = point.getPoint2();

					for(int from : from2List){
						gamePointHolder.payPoint(gameRoundStatus.getRoundNumber(), p2, from,to);
					}
				}
				
				ps.println(p1 + "," + p2);


			}
			return true;
		}
		return false;
	}
	public void callRichi(int playerId,TileEnum tile){
		this.discardTile(playerId, tile);
		table.setRichi(playerId);
	}
	public boolean callChow(int playerId, TileEnum stolenTile, TileEnum huro1, TileEnum huro2,TileEnum discardedTile) {
		MeldElement melds = new MeldElement(MeldEnum.STEAL_CHOW, stolenTile, huro1, huro2);
		melds.setStolenTile(stolenTile);
		table.addHuro(playerId,melds);
		
		discardTile(playerId, discardedTile);

		return true;
	}

	public boolean callPong(int playerId, TileEnum stolenTile, TileEnum huro1, TileEnum huro2,TileEnum discardedTile) {
		MeldElement melds = new MeldElement(MeldEnum.STEAL_PONG, stolenTile, huro1, huro2);
		melds.setStolenTile(stolenTile);
		table.addHuro(playerId,melds);
		discardTile(playerId, discardedTile);
		return true;
	}
	
	public boolean callKongByWall(int playerId, TileEnum stolenTile, TileEnum huro1, TileEnum huro2, TileEnum huro3,TileEnum discardedTile) {
		MeldElement melds = new MeldElement(MeldEnum.WALL_KONG, stolenTile, huro1, huro2, huro3);
		melds.setStolenTile(stolenTile);
		table.addHuro(playerId,melds);
		discardTile(playerId, discardedTile);
		return true;
	}
	
	public boolean callKongBySteal(int playerId, TileEnum stolenTile, TileEnum huro1, TileEnum huro2, TileEnum huro3,TileEnum discardedTile) {
		MeldElement melds = new MeldElement(MeldEnum.STEAL_KONG, stolenTile, huro1, huro2, huro3);
		melds.setStolenTile(stolenTile);
		table.addHuro(playerId,melds);
		discardTile(playerId, discardedTile);
		return true;
	}
	private int waitForSteal(int currentPlayerId){

		return waitForCommandInput(currentPlayerId);
		
	}
	
	private int getRonedPlayerId(){
		return 0;//TODO
	}
	private int waitForCommandInput(int currentPlayerId){
		// decide notifying order,since only next player can chew.
		// next player
		int first = (currentPlayerId + 3) % GameConstants.PLAYER_NUM;
		// opposite player.
		int second = (currentPlayerId + 2) % GameConstants.PLAYER_NUM;
		// previous player.
		int third = (currentPlayerId + 1) % GameConstants.PLAYER_NUM;
		
		int order[] = {first,second,third};
		for(int playerId : order){
			AbstractGamePlayer player = players.get(playerId);
			

			player.notifySteal(
					table.getWallTiles(playerId),
					table.getHuroTiles(playerId),
					table.getDiscardedTiles(playerId),
					table.getOtherPlayersDiscardedTiles(playerId),
					table.getOtherPlayersHuroTiles(playerId),
					table.getLastDiscardedTile());
//			if(command == null){
//				continue;
//			}
//			TileEnum stolenTile = command.getStolenTile();
//			TileEnum huro1 = command.getHuroTile().get(0);
//			TileEnum huro2 = command.getHuroTile().get(1);
//			TileEnum discardedTile = command.getDiscardedTile();
//			if(CommandEnum.PONG.equals(command.getCommand())){
//
//				callPon(playerId,stolenTile,huro1,huro2,discardedTile);
//				return playerId;
//			}
//
//			else if(CommandEnum.MELD.equals(command.getCommand())){
//				callChow(playerId,stolenTile,huro1,huro2,discardedTile);
//				return playerId;
//			}
//			else if(CommandEnum.CHOW.equals(command.getCommand())){
//				TileEnum huro3 = command.getHuroTile().get(2);
//				callKong(playerId,stolenTile,huro1,huro2,huro3,discardedTile);
//				return playerId;
//			}
		}

		return -1;
	}
	@SuppressWarnings("unchecked")
	private void registerPlayers(String className[]){

		for(int i = 0; i < GameConstants.PLAYER_NUM; i++){
			Class c;
			try {
				c = Class.forName(className[i]);
				Object inst = (Object)c.newInstance();
				players.add((AbstractGamePlayer)inst);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	private void initPlayers(){
		for(int i = 0 ; i < GameConstants.PLAYER_NUM; i++){
			players.get(i).initialize(i,table.getWallTiles(i),table.getDoraTiles().get(0),table.getPrevailingWind(),table.getPlayerWind(i));
		}
	}
	private void initTable(){
		table = new GameTable(gameRoundStatus.getPrevailingWind(),gameRoundStatus.getParentPlayerId());
	}
	

	private void printResult(int playerId){
		ps.println(playerId + " won");
	}

	private void runRound(){
		int playerId = 0;
		int reminder = GameConstants.INIT_MOUNTAIN_TILE_NUM;
		while(reminder > 0){
				TileEnum tookTile = table.takeTileFromTable(playerId);
				

//				Map<Integer,List<TileEnum>> otherPlayersDiscardedTiles = new HashMap<Integer,List<TileEnum>>();
//				Map<Integer,List<MeldElement>> otherPlayersHuroTiles = new HashMap<Integer,List<MeldElement>>();
//				for(int i=0 ; i < GameConstants.PLAYER_NUM; i++){
//					if(i == playerId){
//						continue;
//					}
//
//					otherPlayersDiscardedTiles.put(i,table.getDiscardedTiles(playerId));
//					otherPlayersHuroTiles.put(i,table.getHuroTiles(playerId));
//				}
					
				players.get(playerId).notifyTurn(
						table.getWallTiles(playerId),
						tookTile,
						table.getHuroTiles(playerId),
						table.getDiscardedTiles(playerId),
						table.getOtherPlayersDiscardedTiles(playerId),
						table.getOtherPlayersHuroTiles(playerId));
				

				if(gameRoundStatus.isRoundEnd()){
					printResult(playerId);
					break ;
				}
				
				int stealingPlayerId = waitForSteal(playerId);
				if(stealingPlayerId != -1){
					playerId = (stealingPlayerId + 1) % GameConstants.PLAYER_NUM;
				}else{
					playerId = (playerId + 1) % GameConstants.PLAYER_NUM;
				}
				reminder--;
				
			if(!gameValidator.validate(table,GameConstants.INIT_MOUNTAIN_TILE_NUM - reminder)){
				break;
			}

		}
		
		if(reminder != 0){
			ps.println("no winner");
		}

	}
	
	
	public void runGame(){

		while(gameRoundStatus.isGameRoundFinish(HARF_GAME)){
			ps.println("ROUND :" + gameRoundStatus.getPrevailingWind() + " " + gameRoundStatus.getRoundNumber() + " has started");
			initTable();
			
			initPlayers();
			
			runRound();
			
			ps.println("ROUND :" + gameRoundStatus.getPrevailingWind() + " " + gameRoundStatus.getRoundNumber() + " has finished");
			gamePointHolder.showScore();
			
			gameRoundStatus.nextRound();
		}
		ps.println("Game End");

	}
	public void init(String args[]){

		registerPlayers(args);

		
		runGame();
	}
	public static void main(String args[]){

//		String debugArgs[] = {"org.sb.server.player.sample.SampleManualPlayer",
//				"org.sb.server.player.sample.SampleRandomPlayer",
//				"org.sb.server.player.sample.SampleRandomPlayer",
//				"org.sb.server.player.sample.SampleRandomPlayer"
//		};
		String playerDefs[] = {"org.sb.client.ConcreatePlayer",
				"org.sb.server.player.sample.SampleRandomPlayer",
				"org.sb.server.player.sample.SampleRandomPlayer",
				"org.sb.server.player.sample.SampleRandomPlayer"
		};
		getInstance().init(playerDefs);
		
	}
}

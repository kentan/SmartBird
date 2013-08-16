package org.sb.server;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;



import org.sb.server.InputCommand;
import org.sb.engine.controller.TileSet;
import org.sb.mdl.MeldElement;
import org.sb.mdl.bean.PointBean;
import org.sb.mdl.cnst.GameConstants;
import org.sb.mdl.enm.MeldEnum;
import org.sb.mdl.enm.TileEnum;
import org.sb.server.player.AbstractGamePlayer;
import org.sb.server.CommandEnum;


public class GameServer {
//	private final boolean DEBUG  = false;
	List<AbstractGamePlayer> players = new ArrayList<AbstractGamePlayer>();
	
	private static GameTable table = new GameTable();

	private boolean isWon = false;
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

	public TileEnum takeTile(int playerId) {
		return table.takeTileFromTable();

	}
	/**
	 * 判定メソッド提供しないとAI作るプログラマが各々実装する必要が出てくるので、ここに用意する。
	 * AI User用にUtil.jarとかつくって、そっちに移してもいいのかも。
	 * 
	 * @param tileSet
	 * @param playerId
	 * @return
	 */
	public boolean isWinningHandsValid(int playerId){
		TileSet tileSet = new TileSet(table.getPlayerWind(playerId), table.getPrevailingWind());
		tileSet.setTiles(table.getWallTiles(playerId));

		return tileSet.isWinningHandsValid();
	}
	private boolean callFinish(TileSet tileSet,int playerId){
		if(tileSet.isWinningHandsValid()){
			isWon = true;
			PointBean point =  tileSet.calculate();
			
			if(point != null){
				
				int p1=0,p2=0;
				p1 = point.getPoint1();
				if(!table.isParent(playerId)){
					p2 = point.getPoint2();
				}
				
				System.out.println(p1 + "," + p2);
			}
			return true;
		}
		return false;
	}
	public boolean callFinishRon(int playerId,TileEnum ron){
		TileSet tileSet = new TileSet(table.getPlayerWind(playerId), table.getPrevailingWind());
		tileSet.setTiles(table.getWallTiles(playerId));
		tileSet.addTile(ron);
		tileSet.addDoraTiles(table.getDoraTiles());
		tileSet.setRon();
		tileSet.setWinningTile(ron);
	
		return callFinish(tileSet, playerId);
	}
	
	public boolean callFinishTumo(int playerId,TileEnum tumo) {
		
		TileSet tileSet = new TileSet(table.getPlayerWind(playerId), table.getPrevailingWind());
		tileSet.setTiles(table.getWallTiles(playerId));
		tileSet.addDoraTiles(table.getDoraTiles());		
		tileSet.setTumo();
		tileSet.setWinningTile(tumo);
		
		return callFinish(tileSet, playerId);

	}
	public void callRichi(int playerId,TileEnum tile){
		this.discardTile(playerId, tile);
		table.setRichi(playerId);
	}
	private void callChi(int playerId, TileEnum stolenTile, TileEnum huro1, TileEnum huro2,TileEnum discardedTile) {
		MeldElement melds = new MeldElement(MeldEnum.STEAL_CHOW, stolenTile, huro1, huro2);
		table.addHuro(playerId,melds);
		
		discardTile(playerId, discardedTile);

	}

	private void callPon(int playerId, TileEnum stolenTile, TileEnum huro1, TileEnum huro2,TileEnum discardedTile) {
		MeldElement melds = new MeldElement(MeldEnum.STEAL_PONG, stolenTile, huro1, huro2);
		table.addHuro(playerId,melds);
		discardTile(playerId, discardedTile);
	}

	private void callKong(int playerId, TileEnum stolenTile, TileEnum huro1, TileEnum huro2, TileEnum huro3,TileEnum discardedTile) {
		MeldElement melds = new MeldElement(MeldEnum.STEAL_KONG, stolenTile, huro1, huro2, huro3);
		table.addHuro(playerId,melds);
		discardTile(playerId, discardedTile);
	}
	private int waitForSteal(int currentPlayerId){

		return waitForCommandInput(currentPlayerId);
		
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
			
			InputCommand command = player.notifySteal();
			if(command == null){
				continue;
			}
			TileEnum stolenTile = command.getStolenTile();
			TileEnum huro1 = command.getHuroTile().get(0);
			TileEnum huro2 = command.getHuroTile().get(1);
			TileEnum discardedTile = command.getDiscardedTile();
			if(CommandEnum.PONG.equals(command.getCommand())){

				callPon(playerId,stolenTile,huro1,huro2,discardedTile);
				return playerId;
			}

			else if(CommandEnum.MELD.equals(command.getCommand())){
				callChi(playerId,stolenTile,huro1,huro2,discardedTile);
				return playerId;
			}
			else if(CommandEnum.CHOW.equals(command.getCommand())){
				TileEnum huro3 = command.getHuroTile().get(2);
				callKong(playerId,stolenTile,huro1,huro2,huro3,discardedTile);
				return playerId;
			}
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
	
	private boolean validate(int round){
		for(int i = 0; i < GameConstants.PLAYER_NUM; i++){
			List<TileEnum> wallTiles = table.getWallTiles(i);
			List<MeldElement> huroList = table.getHuroTiles(i);
			boolean invalid = false;
			
			
			
			if(wallTiles.size() + (huroList.size() * 3) != GameConstants.WALL_TILE_MAX_NUM - 1){
				System.out.println(wallTiles);
				invalid |=  true;
			}
			if(invalid){
				System.out.println("the number of discarded tiles is invalid:");
				System.out.println("round:" + round);
				System.out.println("playerid:" + i);
				System.out.println("huro tiles:" + huroList.size() * 3);
				return false;
			}
		}
		

		return true;
	}
	private void printResult(int playerId){
		System.out.println(playerId + " won");
	}
	private void runGame(){
		int count = 0;
		int playerId = 0;
		while(count < GameConstants.TILE_SUMMARY_NUM){
				TileEnum tookTile = table.takeTileFromTable();
				table.addWallTiles(playerId, tookTile);
				

				Map<Integer,List<TileEnum>> otherPlayersDiscardedTiles = new HashMap<Integer,List<TileEnum>>();
				Map<Integer,List<MeldElement>> otherPlayersHuroTiles = new HashMap<Integer,List<MeldElement>>();
				for(int i=0 ; i < GameConstants.PLAYER_NUM; i++){
					if(i == playerId){
						continue;
					}

					otherPlayersDiscardedTiles.put(i,table.getDiscardedTiles(playerId));
					otherPlayersHuroTiles.put(i,table.getHuroTiles(playerId));
				}
					

				
				players.get(playerId).notifyTurn(
						table.getWallTiles(playerId),
						tookTile,
						table.getHuroTiles(playerId),
						table.getDiscardedTiles(playerId),
						otherPlayersDiscardedTiles,
						otherPlayersHuroTiles);
				

				if(isWon){
					printResult(playerId);
					break ;
				}
				
				int stealingPlayerId = waitForSteal(playerId);
				if(stealingPlayerId != -1){
					playerId = (stealingPlayerId + 1) % GameConstants.PLAYER_NUM;
				}else{
					playerId = (playerId + 1) % GameConstants.PLAYER_NUM;
				}
				count++;
				
			int round = (count - 1)/4;
			if(!validate(round)){
				System.out.println("validation error");
				break;
			}

		}
		if(count == GameConstants.TILE_SUMMARY_NUM){
			System.out.println("no winner");
		}
		System.out.println("Game End");
	}
	
	public void init(String args[]){

		registerPlayers(args);
		initPlayers();
		
		runGame();
	}
	public static void main(String args[]){

//		String debugArgs[] = {"org.sb.server.player.sample.SampleManualPlayer",
//				"org.sb.server.player.sample.SampleRandomPlayer",
//				"org.sb.server.player.sample.SampleRandomPlayer",
//				"org.sb.server.player.sample.SampleRandomPlayer"
//		};
		String debugArgs[] = {"org.sb.client.ConcreatePlayer",
				"org.sb.server.player.sample.SampleRandomPlayer",
				"org.sb.server.player.sample.SampleRandomPlayer",
				"org.sb.server.player.sample.SampleRandomPlayer"
		};
		getInstance().init(debugArgs);
		
	}
}

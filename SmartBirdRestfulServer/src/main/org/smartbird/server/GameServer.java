package org.smartbird.server;


import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;





import org.sb.mdl.MeldElement;
import org.sb.mdl.PaidPoint;
import org.sb.mdl.cnst.GameConstants;
import org.sb.mdl.enm.MeldEnum;
import org.sb.mdl.enm.TileEnum;
import org.smartbird.dao.SBMessageDaoFactory;
import org.smartbird.exception.SBException;
import org.smartbird.message.SBMessage;
import org.smartbird.message.SBMessageAddTile;
import org.smartbird.message.SBMessageDiscardTile;
import org.smartbird.message.SBMessageFinishRound;
import org.smartbird.message.SBMessageInitTile;
import org.smartbird.server.player.AbstractGamePlayer;
import org.smartbird.server.stat.GameStatisticAnalyzer;


public class GameServer extends Thread{
	PrintStream ps = System.out;

	
//	private final boolean DEBUG  = false;
	private final boolean HARF_GAME = true;
	private final static String PLAYER_DEF_FILES = "PlayerDefs.conf";
	private static List<AbstractGamePlayer> players = new ArrayList<AbstractGamePlayer>();
	
	private static GameTable table;
	private int _lastPlayerId = 0;
	GameRoundStatus gameRoundStatus = new GameRoundStatus();
	GamePointHolder gamePointHolder = new GamePointHolder();
	GameValidator gameValidator = new GameValidator();

	private static GameStatisticAnalyzer _gameStaticAnalyzer = new GameStatisticAnalyzer();
	private int _gameNumber = 0;
	private int _roundNumber = 0;
	private String clientId = "";




	public void discardTile(int playerId, TileEnum tile) {

		List<TileEnum> tiles = table.getWallTiles(playerId);
		tiles.remove(tile);
		
		table.addDiscardeTile(playerId, tile);
		writeMessage(new SBMessageDiscardTile(playerId,tile));
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

		table.setRonTile(playerId,ron,_lastPlayerId);
		
		if(table.isWinningHandsValid(playerId)){
			gameRoundStatus.setRoundEnd();
			PaidPoint point = table.calculate(playerId);
			if(point != null){
				
				
				int p = point.getPoint1();
				List<Integer> fromList = point.getPayingPlayerIdOnPoint1();
				int to = point.getPaidPlayerId();
//				stat.put(to, stat.get(to) == null ? 1 :stat.get(to) + 1 );
				_gameStaticAnalyzer.incrementWinnerCount(to);
				_gameStaticAnalyzer.recodeRoundResult(_gameNumber, _roundNumber, point);
				for(int from : fromList){
					gamePointHolder.payPoint(gameRoundStatus.getRoundNumber(), p, from, to);
					GameServerLogger.write(p + " to " + to + " from " + from);
				}
			



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
//				stat.put(to, stat.get(to) == null ? 1 :stat.get(to) + 1 );
				_gameStaticAnalyzer.incrementWinnerCount(to);
				_gameStaticAnalyzer.recodeRoundResult(_gameNumber, _roundNumber, point);
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
				
				GameServerLogger.write(p1 + "," + p2);


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
		}

		return -1;
	}
	@SuppressWarnings("unchecked")
	public void registerPlayers(){
		String playerDefs[] = loadPlayerDefs();
		for(int i = 0; i < GameConstants.PLAYER_NUM; i++){
			Class c;
			try {
				c = Class.forName(playerDefs[i]);
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
			players.get(i).initialize(i,table.getWallTiles(i),table.getDoraTiles().get(0),table.getPrevailingWind(),table.getPlayerWind(i),this);
			writeMessage(new SBMessageInitTile(i,table.getWallTiles(i)));
		}
	}
	private void initTable(){
		table = new GameTable(gameRoundStatus.getPrevailingWind(),gameRoundStatus.getParentPlayerId());
	}
	

	private void printResult(int playerId){
		GameServerLogger.write(playerId + " won");
	}

	private boolean isTumo(){
		return gameRoundStatus.isRoundEnd();
	}
	private boolean isRon(){
		return gameRoundStatus.isRoundEnd();
	}
	private int getNextPlayerOnSteal(int currentPlayerId,int stealingPlayerId){
		int playerId;
		if(stealingPlayerId != -1){
			playerId = (stealingPlayerId + 1) % GameConstants.PLAYER_NUM;
		}else{
			playerId = (currentPlayerId + 1) % GameConstants.PLAYER_NUM;
		}
		return playerId;

	}
	private void runRound(){
		int playerId = 0;
		int reminder = GameConstants.INIT_MOUNTAIN_TILE_NUM;
		while(reminder > 0){
				TileEnum tookTile = table.takeTileFromTable(playerId);
				
				AbstractGamePlayer player =players.get(playerId);
	
				writeMessage(new SBMessageAddTile(playerId,table.getWallTiles(playerId),tookTile));
//				SBMessageDaoFactory.getInstance().createDao("").writeMessage(new SBMessageAddTile(playerId,table.getWallTiles(playerId),tookTile));
				player.notifyTurn(
						table.getWallTiles(playerId),
						tookTile,
						table.getHuroTiles(playerId),
						table.getDiscardedTiles(playerId),
						table.getOtherPlayersDiscardedTiles(playerId),
						table.getOtherPlayersHuroTiles(playerId));
				
				_lastPlayerId = playerId;
				if(isTumo()){
					printResult(playerId);
					break ;
				}
				
				int stealingPlayerId = waitForSteal(playerId);
				playerId = getNextPlayerOnSteal(playerId, stealingPlayerId);

				if(isRon()){
					printResult(_lastPlayerId);
					break ;
				}
				reminder--;
				
			if(!gameValidator.validate(table,GameConstants.INIT_MOUNTAIN_TILE_NUM - reminder)){
				break;
			}
			
		}
		
		if(reminder == 0){
			GameServerLogger.writeln("no winner");
		}
		writeMessage(new SBMessageFinishRound());
		//SBMessageDaoFactory.getInstance().createDao("").writeMessage(new SBMessageFinishRound());
	}
	
	
	public void runGame(){

		while(gameRoundStatus.isGameRoundFinish(HARF_GAME)){
			GameServerLogger.writeln("ROUND :" + gameRoundStatus.getPrevailingWind() + " " + gameRoundStatus.getRoundNumber() + " has started");
			initTable();
			
			initPlayers();
			
			runRound();
			
			GameServerLogger.writeln("ROUND :" + gameRoundStatus.getPrevailingWind() + " " + gameRoundStatus.getRoundNumber() + " has finished");
			gamePointHolder.showScore();
			
			gameRoundStatus.nextRound();
			_roundNumber = gameRoundStatus.getRoundNumber();
			
			return;
		}
		GameServerLogger.writeln("Game End");

		_gameStaticAnalyzer.recodeGameResult(_gameNumber, gamePointHolder.getScore());

	}
	private String[] loadPlayerDefs(){
        InputStream inStream = null;
        Properties prop = null;
//        try {
//            inStream = new BufferedInputStream(new FileInputStream(PLAYER_DEF_FILES));
//    		prop = new Properties();
//    		prop.load(inStream);
//        }catch(IOException e){
//        	e.printStackTrace();
//        }finally{
//        	try{
//        		inStream.close();
//        	}catch(IOException e){
//        		
//        		e.printStackTrace();
//        	}
//        }
        String playerDefs[] = new String[4];
        playerDefs[0] = "org.smartbird.server.player.sample.SampleRandomPlayer";//prop.getProperty("player0");
        playerDefs[1] = "org.smartbird.server.player.sample.SampleRandomPlayer";//prop.getProperty("player1");
        playerDefs[2] = "org.smartbird.server.player.sample.SampleRandomPlayer";//prop.getProperty("player2");
        playerDefs[3] = "org.smartbird.server.player.sample.SampleRandomPlayer";//prop.getProperty("player3");
        
        return playerDefs;
	}
	
	public void init(int gameNumber,String clientId){

		gameRoundStatus = new GameRoundStatus();
		gamePointHolder = new GamePointHolder();
		gameValidator = new GameValidator();
		_gameNumber = gameNumber;
		this.clientId = clientId;
		
	}
	public void showResult(){
		_gameStaticAnalyzer.showRecodeResult();
	}
	private void writeMessage(SBMessage message){

		SBMessageDaoFactory.getInstance().createDao("").writeMessage(clientId,message);
	}
	@Override
	public void run(){

		registerPlayers();

		runGame();

		showResult();		
	}
}

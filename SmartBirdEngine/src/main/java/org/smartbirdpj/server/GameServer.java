package org.smartbirdpj.server;


import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;





import org.smartbirdpj.log.LoggerFactory;
import org.smartbirdpj.mdl.MeldElement;
import org.smartbirdpj.mdl.PaidPoint;
import org.smartbirdpj.mdl.cnst.GameConstants;
import org.smartbirdpj.mdl.enm.MeldEnum;
import org.smartbirdpj.mdl.enm.TileEnum;
import org.smartbirdpj.cnst.SBConst;
import org.smartbirdpj.dao.SBMessageDaoFactory;
import org.smartbirdpj.message.SBMessage;
import org.smartbirdpj.message.SBMessageAddTile;
import org.smartbirdpj.message.SBMessageChow;
import org.smartbirdpj.message.SBMessageDiscardTile;
import org.smartbirdpj.message.SBMessageFinishRound;
import org.smartbirdpj.message.SBMessageInitTile;
import org.smartbirdpj.message.SBMessagePong;
import org.smartbirdpj.message.SBMessageRichi;
import org.smartbirdpj.message.SBMessageRon;
import org.smartbirdpj.message.SBMessageStartRound;
import org.smartbirdpj.message.SBMessageStealKong;
import org.smartbirdpj.message.SBMessageTumo;
import org.smartbirdpj.message.SBMessageWallKong;
import org.smartbirdpj.server.player.AbstractGamePlayer;
import org.smartbirdpj.server.stat.GameStatisticAnalyzer;
import org.smartbirdpj.util.SBUtil;


public class GameServer extends Thread{

	
	private final boolean DEBUG  = false;
	private final boolean HARF_GAME = true;
	private final static int PLAYER_NUMBER = 4;
	private static List<AbstractGamePlayer> players = new ArrayList<AbstractGamePlayer>();
	
	private static GameTable table;
	private int _lastPlayerId = 0;
	GameRoundStatus gameRoundStatus = new GameRoundStatus();
	GamePointHolder gamePointHolder = new GamePointHolder();
	GameValidator gameValidator = new GameValidator();
	Logger LOGGER = LoggerFactory.getLogger();

	private static GameStatisticAnalyzer _gameStaticAnalyzer = new GameStatisticAnalyzer();
	private int _gameNumber = 0;
	private int _roundNumber = 0;
	private String clientId = "";
	private String gameId = "";
	private StealStatus _stealStatus;
	private final int THE_ILLEGAL_CHOW_MAX = 2;
	private boolean _isViolation = false;


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
				_gameStaticAnalyzer.incrementWinnerCount(to);
				_gameStaticAnalyzer.recodeRoundResult(_gameNumber, _roundNumber, point);
				for(int from : fromList){
					gamePointHolder.payPoint(gameRoundStatus.getRoundNumber(), p, from, to);
					LOGGER.info(p + " to " + to + " from " + from);
				}
			}
			_stealStatus.finishTransition(playerId);
			gamePointHolder.calcPooledPoint(playerId,gameRoundStatus.getPooledThousandBarNumber(),gameRoundStatus.getExtendedRoundNumber());
			writeMessage(new SBMessageRon(playerId, ron,point));
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
				
				LOGGER.info(p1 + "," + p2);

			}
			gamePointHolder.calcPooledPoint(playerId,gameRoundStatus.getPooledThousandBarNumber(),gameRoundStatus.getExtendedRoundNumber());
			writeMessage(new SBMessageTumo(playerId, tumo,point));
			return true;
		}
		return false;
	}
	public void callRichi(int playerId,TileEnum tile){
		this.discardTile(playerId, tile);
		table.setRichi(playerId);
		gamePointHolder.reduceThousandPoint(playerId);
		writeMessage(new SBMessageRichi(playerId, tile));
	}
	public boolean callChow(int playerId, TileEnum stolenTile, TileEnum huro1, TileEnum huro2,TileEnum discardedTile) {
		if(!_stealStatus.isAbleToChow()){
			_stealStatus.setIllegalChow();
			return false;
		}
		MeldElement melds = new MeldElement(MeldEnum.STEAL_CHOW, stolenTile, huro1, huro2);
		melds.setStolenTile(stolenTile);
		table.addHuro(playerId,melds);
		
		discardTile(playerId, discardedTile);
		_stealStatus.setChiDisable();
		writeMessage(new SBMessageChow(playerId, -1,stolenTile, huro1, huro2, discardedTile,table.getWallTiles(playerId)));
		return true;
	}

	public boolean callPong(int playerId, TileEnum stolenTile, TileEnum huro1, TileEnum huro2,TileEnum discardedTile) {
		MeldElement melds = new MeldElement(MeldEnum.STEAL_PONG, stolenTile, huro1, huro2);
		melds.setStolenTile(stolenTile);
		table.addHuro(playerId,melds);
		discardTile(playerId, discardedTile);
		_stealStatus.finishTransition(playerId);
		// TODO Bug exists.
		// It dosen't have information about who the tile is stolen by.
		writeMessage(new SBMessagePong(playerId, -1, stolenTile, huro1, huro2, discardedTile,table.getWallTiles(playerId)));
		return true;
	}
	
	public boolean callKongByWall(int playerId, TileEnum stolenTile, TileEnum huro1, TileEnum huro2, TileEnum huro3,TileEnum discardedTile) {
		MeldElement melds = new MeldElement(MeldEnum.WALL_KONG, stolenTile, huro1, huro2, huro3);
		melds.setStolenTile(stolenTile);
		table.addHuro(playerId,melds);
		discardTile(playerId, discardedTile);
		// TODO Bug exists.
		// It dosen't have information about who the tile is stolen by.

		writeMessage(new SBMessageWallKong(playerId, stolenTile, huro1, huro2, huro3, discardedTile,table.getWallTiles(playerId)));
		return true;
	}
	
	public boolean callKongBySteal(int playerId, TileEnum stolenTile, TileEnum huro1, TileEnum huro2, TileEnum huro3,TileEnum discardedTile) {
		MeldElement melds = new MeldElement(MeldEnum.STEAL_KONG, stolenTile, huro1, huro2, huro3);
		melds.setStolenTile(stolenTile);
		table.addHuro(playerId,melds);
		discardTile(playerId, discardedTile);
		// TODO Bug exists.
		// It dosen't have information about who the tile is stolen by.
		_stealStatus.finishTransition(playerId);
		writeMessage(new SBMessageStealKong(playerId, -1,stolenTile, huro1, huro2, huro3, discardedTile,table.getWallTiles(playerId)));
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
		_stealStatus = new StealStatus();
		int count = 0;
		int playerId = order[count];

		while(count < 3){
			playerId = order[count];
			AbstractGamePlayer player = players.get(playerId);
			
			player.notifySteal(
					table.getWallTiles(playerId),
					table.getHuroTiles(playerId),
					table.getDiscardedTiles(playerId),
					table.getOtherPlayersDiscardedTiles(playerId),
					table.getOtherPlayersHuroTiles(playerId),
					table.getLastDiscardedTile());
			
			if(!_stealStatus.doTransit()){
				break;
			}
			if(_stealStatus.getTheNumberOfIllegatChowCalled() == THE_ILLEGAL_CHOW_MAX){
				_isViolation = true;
				break;
			}
			if(!_stealStatus.isChowIllegal()){
				count++;
			}
			
		}

		return _stealStatus.getStealingPlayerId();
	}
	@SuppressWarnings("rawtypes")
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
				SBUtil.logThrowable(LOGGER, e);
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				SBUtil.logThrowable(LOGGER, e);
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				SBUtil.logThrowable(LOGGER, e);
			}
		}
	}
	private void initPlayers(){
		for(int i = 0 ; i < GameConstants.PLAYER_NUM; i++){
			players.get(i).initialize(i,table.getWallTiles(i),table.getDoraTiles().get(0),table.getPrevailingWind(),gameRoundStatus.getPlayerWind(i),this);
			writeMessage(new SBMessageInitTile(i,table.getWallTiles(i),gameRoundStatus.getPlayerWind(i),gamePointHolder.getPlayerPoint(i)));
		}
	}
	private void initTable(){
		table = new GameTable(gameRoundStatus.getPrevailingWind(),gameRoundStatus);
		
		SBMessage message = new SBMessageStartRound(gameRoundStatus.getPrevailingWind(), 
				table.getDisplayDoraTiles().get(0),
				gameRoundStatus.getParentPlayerId(),
				gameRoundStatus.getRoundNumber(),
				gameRoundStatus.getExtendedRoundNumber(),
				gameRoundStatus.getPooledThousandBarNumber());
		writeMessage(message);
	}
	

	private void printResult(int playerId){
		LOGGER.info(playerId + " won");
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
		int winner = -1;
		
		while(reminder > 0){
				TileEnum tookTile = table.takeTileFromTable(playerId);
				
				AbstractGamePlayer player =players.get(playerId);
	
				writeMessage(new SBMessageAddTile(playerId,table.getWallTiles(playerId),tookTile));
				player.notifyTurn(
						table.getWallTiles(playerId),
						tookTile,
						table.getHuroTiles(playerId),
						table.getDiscardedTiles(playerId),
						table.getOtherPlayersDiscardedTiles(playerId),
						table.getOtherPlayersHuroTiles(playerId));
				
				_lastPlayerId = playerId;
				if(isTumo()){
					winner = playerId;
					printResult(playerId);
					break ;
				}
				
				int stealingPlayerId = waitForSteal(playerId);
				playerId = getNextPlayerOnSteal(playerId, stealingPlayerId);

				if(_isViolation){
					LOGGER.severe("player " + reminder + "called chow " + THE_ILLEGAL_CHOW_MAX + " times");
					break;
				}
				if(isRon()){
					winner = stealingPlayerId;
					printResult(playerId);
					break ;
				}
				reminder--;
				
			if(!gameValidator.validate(table,GameConstants.INIT_MOUNTAIN_TILE_NUM - reminder)){
				LOGGER.severe("validation error :" + reminder);
				break;
			}
			
		}
		
		if(reminder == 0){
			LOGGER.info("no winner");
		}else if(gameRoundStatus.getParentPlayerId() == winner ){
			gameRoundStatus.setParentWon();
		}		
		writeMessage(new SBMessageFinishRound(winner));
	}
	
	public void runGame(){

		while(gameRoundStatus.isGameRoundFinish(HARF_GAME)){
			LOGGER.info("ROUND :" + gameRoundStatus.getPrevailingWind() + " " + gameRoundStatus.getRoundNumber() + " has started");
			initTable();
			
			initPlayers();
			
			runRound();
			
			LOGGER.info("ROUND :" + gameRoundStatus.getPrevailingWind() + " " + gameRoundStatus.getRoundNumber() + " has finished");
			gamePointHolder.showScore();
			
			gameRoundStatus.nextRound();
			_roundNumber = gameRoundStatus.getRoundNumber();
			
//			return;// For Debug
		}
		LOGGER.info("Game End");

		_gameStaticAnalyzer.recodeGameResult(_gameNumber, gamePointHolder.getScore());

	}
	private String[] loadPlayerDefs(){
        String playerDefs[] = new String[PLAYER_NUMBER];
        if(DEBUG){
            playerDefs[0] = "org.smartbirdpj.client.whitebird.Player";
            playerDefs[1] = "org.smartbirdpj.client.shizimily7.ShizimilyPlayer";
            playerDefs[2] = "org.smartbirdpj.server.player.sample.SampleRandomPlayer";
            playerDefs[3] = "org.smartbirdpj.server.player.sample.SampleRandomPlayer";

        }else{
	        InputStream inStream = null;
	        Properties prop = null;
	        try {
	            inStream = GameServer.class.getClassLoader().getResourceAsStream(SBConst.PLAYER_DEF_FILES);
	    		prop = new Properties();
	    		prop.load(inStream);
	            playerDefs[0] = prop.getProperty("player0");
	            playerDefs[1] = prop.getProperty("player1");
	            playerDefs[2] = prop.getProperty("player2");
	            playerDefs[3] = prop.getProperty("player3");
	        }catch(IOException e){
        		SBUtil.logThrowable(LOGGER, e);
	        }finally{
	        	try{
	        		inStream.close();
	        	}catch(IOException e){	
	        		SBUtil.logThrowable(LOGGER, e);
	        	}
	        }
        }
        
        
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
		_gameStaticAnalyzer.backupMessageAsText(message,gameId);
		SBMessageDaoFactory.getInstance().createDao("").writeMessage(clientId,message);
	}
	private void makeGameId(){
		gameId = (new Date()).toString();
		gameId = gameId.replace(" ", "_");
		gameId = gameId.replace(":", "-");
	}
	@Override
	public void run(){
		try{
			makeGameId();
			
			registerPlayers();
	
			runGame();
	
			showResult();		
		}catch(Throwable t){
			SBUtil.logThrowable(LOGGER, t);
		}

	}
	public static void main(String args[]){
		GameServer gs = new GameServer();
		gs.init(0, "/tmp/");
		gs.run();
		
	}
}

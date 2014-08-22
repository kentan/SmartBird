package org.smartbirdpj.server.stat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.smartbirdpj.log.LoggerFactory;
import org.smartbirdpj.mdl.PaidPoint;

public class GameStatisticAnalyzer {
	

	Map<Integer,GameResult> gameResults = new HashMap<Integer,GameResult>();
	Map<Integer,Integer> totalPoint = new HashMap<Integer,Integer>();
	Map<Integer,Integer> roundWinnerCount = new HashMap<Integer,Integer>();
	Map<Integer,Integer> gameWinnerCount = new HashMap<Integer,Integer>();
	Logger LOGGER = LoggerFactory.getLogger();
	private void incrHashMap(Map<Integer,Integer> map,Integer key){
		Integer v = map.get(key);
		if(v == null){
			map.put(key,1);
		}else{
			map.put(key,v + 1);
		}
	}
	public void incrementWinnerCount(int playerId){
		incrHashMap(roundWinnerCount,playerId);
	}
	public void recodeGameResult(int gameNumber,Map<Integer, Integer> point){
		GameResult gameResult = gameResults.get(gameNumber);
		if(gameResult != null){

			int max = 0;
			int winner = 0;
			for(Map.Entry<Integer, Integer> e: point.entrySet()){
				int playerId = e.getKey();
				int playerPoint = e.getValue();
				if(playerPoint > max){
					max = playerPoint;
					winner = playerId;
				}
				Integer v = totalPoint.get(playerId);
				if(v == null){
					totalPoint.put(playerId,playerPoint);
				}else{
					totalPoint.put(playerId,v + playerPoint);
				}
				incrHashMap(totalPoint,playerId);

			}
			gameResult.winnerPlayerId = winner;
			gameResult.point = point;
			
			incrHashMap(gameWinnerCount,winner);
		}

	}
	public void recodeRoundResult(int gameNumber,int roundNumber,PaidPoint paidPoint){
		RoundResult roundResult = new RoundResult();
		roundResult.gameNumber = gameNumber;
		roundResult.roundNumber = roundNumber;
		roundResult.paidPoint = paidPoint;
		
		GameResult gameResult = gameResults.get(gameNumber);
		if(gameResult == null){
			gameResult = new GameResult();
			gameResult.gameNumber = gameNumber;
			gameResults.put(gameNumber,gameResult);
		}
		gameResult.addRoundResult(roundResult);
//		roundResults.add(roundResult);
	}
	
	public void showRecodeResult(){
		StringBuffer message = new StringBuffer();
		for(Map.Entry<Integer, GameResult> entry: gameResults.entrySet()){
			message.append("==== GAME: " + entry.getKey() + "====" + "\n");
			message.append("winner:" + entry.getValue().winnerPlayerId + "\n");
			Map<Integer,Integer> finalPoint = entry.getValue().point;
			
			for(Map.Entry<Integer, Integer> entry2: finalPoint.entrySet()){
				message.append(entry2.getKey() + ":" + entry2.getValue() + "\n");
			}
			
			List<RoundResult> roundResults= entry.getValue().getRoundResult();
			for(RoundResult r :roundResults){
				PaidPoint point = r.paidPoint;

				message.append("--- ROUND :" + r.roundNumber + "---" + "\n");
				message.append("plyaer "+point.getPaidPlayerId() + " won" + "\n");
				message.append(point.getPoint1() + " from " + point.getPayingPlayerIdOnPoint1() + "\n");
				message.append(point.getPoint2() + " from " + point.getPayingPlayerIdOnPoint2() + "\n");

			}
			
		}
		
		// total point,sum total point of whole games.
		for(Map.Entry<Integer, Integer> entry: totalPoint.entrySet()){
			message.append(entry.getKey() + ":" + entry.getValue() + "\n");
		}
		
		// game winner count
		message.append(gameWinnerCount.toString() + "\n");
		// round winner count 
		message.append(roundWinnerCount.toString() + "\n");
		
		LOGGER.info(message.toString());
	}
}

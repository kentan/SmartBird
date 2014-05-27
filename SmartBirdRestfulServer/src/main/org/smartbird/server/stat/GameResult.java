package org.smartbird.server.stat;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class GameResult {
	int gameNumber;

	int winnerPlayerId = 0;
	
	private List<RoundResult> _roundResults = new ArrayList<RoundResult>();
	
	Map<Integer, Integer> point = new HashMap<Integer, Integer>();
	
	public void addRoundResult(RoundResult roundResult){
		_roundResults.add(roundResult);
	}
	public List<RoundResult> getRoundResult(){
		return _roundResults;
	}
}

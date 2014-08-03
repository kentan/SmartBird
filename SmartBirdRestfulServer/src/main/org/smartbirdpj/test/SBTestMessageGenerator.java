package org.smartbirdpj.test;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.smartbirdpj.mdl.PaidPoint;
import org.smartbirdpj.mdl.enm.TileEnum;

import org.smartbirdpj.dao.SBMessageDaoFactory;
import org.smartbirdpj.message.SBMessage;
import org.smartbirdpj.message.SBMessageChow;
import org.smartbirdpj.message.SBMessageInitTile;
import org.smartbirdpj.message.SBMessagePong;
import org.smartbirdpj.message.SBMessageRichi;
import org.smartbirdpj.message.SBMessageStartRound;
import org.smartbirdpj.message.SBMessageStealKong;
import org.smartbirdpj.message.SBMessageTumo;
import org.smartbirdpj.message.SBMessageWallKong;

public class SBTestMessageGenerator {
	final private static String CLIENT_ID = "debugMessage";
	@Before
	public void before(){
		SBMessageDaoFactory.getInstance().createDao("").init(CLIENT_ID);
	}
	private void writeMessage(SBMessage message){

		SBMessageDaoFactory.getInstance().createDao("").writeMessage(CLIENT_ID,message);
	}
	@Test
	public void generateCase1(){
		SBMessageStartRound start = new SBMessageStartRound(TileEnum.EAST, TileEnum.EAST, 0, 0,1,5);
		writeMessage(start);
		
		List<TileEnum> tiles = new ArrayList<TileEnum>();
			 
		tiles.add(TileEnum.BAMBOO5);
		tiles.add(TileEnum.CIRCLE3);
		tiles.add(TileEnum.CIRCLE1);
		tiles.add(TileEnum.CIRCLE2);
		tiles.add(TileEnum.CIRCLE3);
		tiles.add(TileEnum.CIRCLE4);
		tiles.add(TileEnum.CIRCLE5);
		tiles.add(TileEnum.CIRCLE6);
		tiles.add(TileEnum.CIRCLE7);
		tiles.add(TileEnum.CIRCLE8);
		tiles.add(TileEnum.CIRCLE9);
		tiles.add(TileEnum.CHARACTOR9);
		tiles.add(TileEnum.EAST);
		tiles.add(TileEnum.WEST);
			
		SBMessageInitTile initP0 = new SBMessageInitTile(0, tiles,TileEnum.EAST,25000);
		writeMessage(initP0);
		SBMessageInitTile initP1 = new SBMessageInitTile(1, tiles,TileEnum.NORTH,25000);
		writeMessage(initP1);
		SBMessageInitTile initP2 = new SBMessageInitTile(2, tiles,TileEnum.WEST,25000);
		writeMessage(initP2);
		SBMessageInitTile initP3 = new SBMessageInitTile(3, tiles,TileEnum.SOUTH,25000);
		writeMessage(initP3);
		
		for(int playerId = 0; playerId < 4; playerId++){
			List<TileEnum> tmpTiles = new ArrayList<TileEnum>(tiles);
			tmpTiles.remove(TileEnum.BAMBOO1);
			SBMessageRichi richi = new SBMessageRichi(playerId, TileEnum.BAMBOO1);
			writeMessage(richi);

 			tmpTiles.remove(TileEnum.CHARACTOR2);
			tmpTiles.remove(TileEnum.CHARACTOR3);
			tmpTiles.remove(TileEnum.CHARACTOR9);
			SBMessageChow chow = new SBMessageChow(playerId, (playerId + 2) % 4,TileEnum.CHARACTOR1,  TileEnum.CHARACTOR2,  TileEnum.CHARACTOR3, TileEnum.CHARACTOR9,tmpTiles);
			writeMessage(chow);
	
			tmpTiles.remove(TileEnum.BAMBOO1);
			tmpTiles.remove(TileEnum.BAMBOO1);
			tmpTiles.remove(TileEnum.EAST);
			SBMessagePong pong = new SBMessagePong(playerId, (playerId + 3) % 4, TileEnum.BAMBOO1,  TileEnum.BAMBOO1,  TileEnum.BAMBOO1,  TileEnum.EAST,tmpTiles);
			writeMessage(pong);
	
			tmpTiles.remove(TileEnum.CIRCLE1);
			tmpTiles.remove(TileEnum.CIRCLE1);
			tmpTiles.remove(TileEnum.CIRCLE1);
			tmpTiles.remove(TileEnum.CIRCLE9);
			
			SBMessageStealKong stealKong = new SBMessageStealKong(playerId, (playerId + 1 ) %4,TileEnum.CIRCLE1,  TileEnum.CIRCLE1,  TileEnum.CIRCLE1, TileEnum.CIRCLE1,  TileEnum.CIRCLE9,tmpTiles);
			writeMessage(stealKong);
			
			tmpTiles.remove(TileEnum.CIRCLE3);
			tmpTiles.remove(TileEnum.CIRCLE3);
			tmpTiles.remove(TileEnum.CIRCLE3);
			tmpTiles.remove(TileEnum.WEST);		
			SBMessageWallKong wallKong = new SBMessageWallKong(playerId,TileEnum.CIRCLE3,  TileEnum.CIRCLE3,  TileEnum.CIRCLE3, TileEnum.CIRCLE3,  TileEnum.WEST,tmpTiles);
			writeMessage(wallKong);
			
			PaidPoint point = new PaidPoint(1000, 2000);
			point.setPaidPlayerId(0);
			point.setPayingPlayerIdOnPoint1(1);
			List<Integer> l = new ArrayList<Integer>();
			l.add(2);
			l.add(3);
			point.setPayingPlayerIdOnPoint2(l);
			
			
			SBMessageTumo tumo = new SBMessageTumo(0, TileEnum.CHARACTOR5, point);
			writeMessage(tumo);
		}
	}
	
}

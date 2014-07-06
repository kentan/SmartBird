package org.smartbirdpj.test;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;
import org.sb.mdl.enm.TileEnum;

import org.smartbirdpj.dao.SBMessageFileDao;
import org.smartbirdpj.message.SBMessage;
import org.smartbirdpj.message.SBMessageAddTile;

public class SBMessageFileDaoTest {
	@Test
	public void testWriteAndRead(){
		String id = "junit1";
		SBMessageFileDao dao = new SBMessageFileDao();
		dao.init(id);
		List<TileEnum> tileEnums = new ArrayList<TileEnum>();
		tileEnums.add(TileEnum.BAMBOO1);
		tileEnums.add(TileEnum.BAMBOO2);
		tileEnums.add(TileEnum.BAMBOO3);
		SBMessage message = new SBMessageAddTile(0,tileEnums,TileEnum.BAMBOO9);
		try{
			dao.writeMessage(id,message);
		}catch(Exception e){
			e.printStackTrace();
		}
		SBMessageAddTile loadedMessage = (SBMessageAddTile)dao.loadNextMessage(id);
	
		Assert.assertEquals(loadedMessage.getPlayerId(), 0);
		Assert.assertEquals(loadedMessage.getWall().get(0), TileEnum.BAMBOO1);
		Assert.assertEquals(loadedMessage.getWall().get(1), TileEnum.BAMBOO2);
		Assert.assertEquals(loadedMessage.getWall().get(2), TileEnum.BAMBOO3);
		Assert.assertEquals(loadedMessage.getAddedTile(), TileEnum.BAMBOO9);
	}
	
	@Test
	public void testWriteAndRead2(){
		String id = "junit2";
		SBMessageFileDao dao = new SBMessageFileDao();
		dao.init(id);
		List<TileEnum> tileEnums = new ArrayList<TileEnum>();
		tileEnums.add(TileEnum.BAMBOO1);
		tileEnums.add(TileEnum.BAMBOO2);
		tileEnums.add(TileEnum.BAMBOO3);
		SBMessage message = new SBMessageAddTile(0,tileEnums,TileEnum.BAMBOO9);
		try{
			dao.writeMessage(id,message);
		}catch(Exception e){
			e.printStackTrace();
		}

		List<TileEnum> tileEnums2 = new ArrayList<TileEnum>();
		tileEnums2.add(TileEnum.CHARACTOR1);
		tileEnums2.add(TileEnum.CHARACTOR2);
		tileEnums2.add(TileEnum.CHARACTOR3);
		SBMessage message2 = new SBMessageAddTile(1,tileEnums2,TileEnum.CHARACTOR9);
		try{
			dao.writeMessage(id,message2);
		}catch(Exception e){
			e.printStackTrace();
		}

		
		
		SBMessageAddTile loadedMessage = (SBMessageAddTile)dao.loadNextMessage(id);
		Assert.assertEquals(0,loadedMessage.getPlayerId());
		Assert.assertEquals(TileEnum.BAMBOO1,loadedMessage.getWall().get(0));
		Assert.assertEquals(TileEnum.BAMBOO2,loadedMessage.getWall().get(1));
		Assert.assertEquals(TileEnum.BAMBOO3,loadedMessage.getWall().get(2));
		Assert.assertEquals(TileEnum.BAMBOO9,loadedMessage.getAddedTile());
		
		SBMessageAddTile loadedMessage2 = (SBMessageAddTile)dao.loadNextMessage(id);
		Assert.assertEquals(1,loadedMessage2.getPlayerId());
		Assert.assertEquals(TileEnum.CHARACTOR1,loadedMessage2.getWall().get(0));
		Assert.assertEquals(TileEnum.CHARACTOR2,loadedMessage2.getWall().get(1));
		Assert.assertEquals(TileEnum.CHARACTOR3,loadedMessage2.getWall().get(2));
		Assert.assertEquals(TileEnum.CHARACTOR9,loadedMessage2.getAddedTile());
	}
}

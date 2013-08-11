package org.sb.engine.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import org.sb.engine.controller.PointCalculator;
import org.sb.mdl.MeldElement;
import org.sb.engine.controller.MeldNode;
import org.sb.engine.controller.TileSet;
import org.sb.engine.controller.WinningHands;
import org.sb.engine.controller.WinningHandsBasic;
import org.sb.engine.controller.WinningHandsList;
import org.sb.mdl.enm.TileEnum;
import org.sb.mdl.enm.MeldEnum;

public class UnitTestMain {
	@Test
	public void testHaiSameInOtherColor(){
		TileEnum hai1 = TileEnum.CHARACTOR1;
		Assert.assertEquals(hai1.getSameInCharactor(), TileEnum.CHARACTOR1);
		Assert.assertEquals(hai1.getSameInBamboo(), TileEnum.BAMBOO1);
		Assert.assertEquals(hai1.getSameInCircle(), TileEnum.CIRCLE1);
		
		TileEnum hai2 = TileEnum.CIRCLE5;
		Assert.assertEquals(hai2.getSameInCharactor(), TileEnum.CHARACTOR5);
		Assert.assertEquals(hai2.getSameInBamboo(), TileEnum.BAMBOO5);
		Assert.assertEquals(hai2.getSameInCircle(), TileEnum.CIRCLE5);

		TileEnum hai3 = TileEnum.BAMBOO9;
		Assert.assertEquals(hai3.getSameInCharactor(), TileEnum.CHARACTOR9);
		Assert.assertEquals(hai3.getSameInBamboo(), TileEnum.BAMBOO9);
		Assert.assertEquals(hai3.getSameInCircle(), TileEnum.CIRCLE9);

		TileEnum hai4 = TileEnum.EAST;
		Assert.assertEquals(hai4.getSameInCharactor(), null);
		Assert.assertEquals(hai4.getSameInBamboo(), null);
		Assert.assertEquals(hai4.getSameInCircle(), null);
	}

	@Test
	public void testTransformGraphToList(){
		List<MeldElement> yakus = new ArrayList<MeldElement>();

		
		MeldNode agari1 = new MeldNode();
		MeldElement atama1 = new MeldElement(MeldEnum.EYES,TileEnum.CHARACTOR1,TileEnum.CHARACTOR1);
		agari1.setMeld(atama1);
				
		MeldNode agari2 = new MeldNode();
		MeldElement yaku2 = new MeldElement(MeldEnum.WALL_CHOW,TileEnum.CHARACTOR2,TileEnum.CHARACTOR3,TileEnum.CHARACTOR4);
		agari2.setMeld(yaku2);
		
		MeldNode agari3 = new MeldNode();
		MeldElement yaku3 = new MeldElement(MeldEnum.WALL_CHOW,TileEnum.CHARACTOR2,TileEnum.CHARACTOR3,TileEnum.CHARACTOR4);
		agari3.setMeld(yaku3);

		MeldNode agari4 = new MeldNode();
		MeldElement yaku4 = new MeldElement(MeldEnum.WALL_CHOW,TileEnum.CHARACTOR2,TileEnum.CHARACTOR3,TileEnum.CHARACTOR4);
		agari4.setMeld(yaku4);

		MeldNode agari5 = new MeldNode();
		MeldElement yaku5 = new MeldElement(MeldEnum.WALL_CHOW,TileEnum.CHARACTOR5,TileEnum.CHARACTOR6,TileEnum.CHARACTOR7);
		agari5.setMeld(yaku5);
		agari5.setEnd(true);
		
		agari1.addChild(agari2);
		agari2.addChild(agari3);
		agari3.addChild(agari4);
		agari4.addChild(agari5);
		
		MeldNode agari6 = new MeldNode();
		MeldElement yaku6 = new MeldElement(MeldEnum.WALL_PONG,TileEnum.CHARACTOR2,TileEnum.CHARACTOR2,TileEnum.CHARACTOR2);
		agari6.setMeld(yaku6);
		
		MeldNode agari7 = new MeldNode();
		MeldElement yaku7 = new MeldElement(MeldEnum.WALL_PONG,TileEnum.CHARACTOR3,TileEnum.CHARACTOR3,TileEnum.CHARACTOR3);
		agari7.setMeld(yaku7);

		MeldNode agari8 = new MeldNode();
		MeldElement yaku8 = new MeldElement(MeldEnum.WALL_PONG,TileEnum.CHARACTOR4,TileEnum.CHARACTOR4,TileEnum.CHARACTOR4);
		agari8.setMeld(yaku8);

		MeldNode agari9 = new MeldNode();
		MeldElement yaku9 = new MeldElement(MeldEnum.WALL_PONG,TileEnum.CHARACTOR5,TileEnum.CHARACTOR6,TileEnum.CHARACTOR7);
		agari9.setMeld(yaku9);
		agari9.setEnd(true);

		agari1.addChild(agari6);
		agari6.addChild(agari7);
		agari7.addChild(agari8);
		agari8.addChild(agari9);
		
		TileSet haiSet = new TileSet(null, null);
		List<WinningHands> yakuss = agari1.transformGraphToList(true,true);
		
		for(WinningHands agari : yakuss){
			for(MeldElement yaku : ((WinningHandsBasic)agari).getList()){
				for(TileEnum hai : yaku.getList()){
					System.out.print(hai);
				}
				System.out.print(" ");
			}
			System.out.println();
		}
	}
	@Test
	public void t(){
		List<Integer> l = new ArrayList<Integer>(){{ add(1);add(2);add(1);add(3);}};
		l.remove(new Integer(1));
		l.remove(new Integer(1));
		System.out.println(l);
	}
	

}

package org.sb.engine.test;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.Assert;

import org.junit.Test;


import org.sb.mdl.MeldElement;
import org.sb.mdl.PaidPoint;
import org.sb.engine.controller.TileSet;
import org.sb.mdl.enm.TileEnum;
import org.sb.mdl.enm.MeldEnum;

public class UnitTestCalculatePoint {
	static Map<String, TileEnum> strToTileMap = new HashMap<String,TileEnum>();
	
    static{
        strToTileMap.put("M1",TileEnum.CHARACTOR1);
        strToTileMap.put("M2",TileEnum.CHARACTOR2);
        strToTileMap.put("M3",TileEnum.CHARACTOR3);
        strToTileMap.put("M4",TileEnum.CHARACTOR4);
        strToTileMap.put("M5",TileEnum.CHARACTOR5);
        strToTileMap.put("M6",TileEnum.CHARACTOR6);
        strToTileMap.put("M7",TileEnum.CHARACTOR7);
        strToTileMap.put("M8",TileEnum.CHARACTOR8);
        strToTileMap.put("M9",TileEnum.CHARACTOR9);
        
        strToTileMap.put("S1",TileEnum.BAMBOO1);
        strToTileMap.put("S2",TileEnum.BAMBOO2);
        strToTileMap.put("S3",TileEnum.BAMBOO3);
        strToTileMap.put("S4",TileEnum.BAMBOO4);
        strToTileMap.put("S5",TileEnum.BAMBOO5);
        strToTileMap.put("S6",TileEnum.BAMBOO6);
        strToTileMap.put("S7",TileEnum.BAMBOO7);
        strToTileMap.put("S8",TileEnum.BAMBOO8);
        strToTileMap.put("S9",TileEnum.BAMBOO9);

        strToTileMap.put("P1",TileEnum.CIRCLE1);
        strToTileMap.put("P2",TileEnum.CIRCLE2);
        strToTileMap.put("P3",TileEnum.CIRCLE3);
        strToTileMap.put("P4",TileEnum.CIRCLE4);
        strToTileMap.put("P5",TileEnum.CIRCLE5);
        strToTileMap.put("P6",TileEnum.CIRCLE6);
        strToTileMap.put("P7",TileEnum.CIRCLE7);
        strToTileMap.put("P8",TileEnum.CIRCLE8);
        strToTileMap.put("P9",TileEnum.CIRCLE9);

        strToTileMap.put("J1",TileEnum.EAST);
        strToTileMap.put("J2",TileEnum.SOUTH);
        strToTileMap.put("J3",TileEnum.WEST);
        strToTileMap.put("J4",TileEnum.NORTH);
        strToTileMap.put("J5",TileEnum.WHITE);
        strToTileMap.put("J6",TileEnum.GREEN);
        strToTileMap.put("J7",TileEnum.RED);     

    }
	private List<MeldElement> parseHuro(String elem[]){
		List<MeldElement> huros = new ArrayList<MeldElement>();
		for(int i = 2; i <= 5; i++){
			if(elem[i].length() == 0){
				break;
			}
			String prefix = elem[i].substring(0,2);

			TileEnum huroHai1= strToTileMap.get(elem[i].substring(2, 4));

			if(MINKAN.equals(prefix)){
				huros.add(new MeldElement(MeldEnum.STEAL_KONG, huroHai1,huroHai1,huroHai1,huroHai1));

			}else if(ANKAN.equals(prefix)){
				huros.add(new MeldElement(MeldEnum.WALL_KONG, huroHai1,huroHai1,huroHai1,huroHai1));

			}else if(MINTSU.equals(prefix)){
				TileEnum huroHai2 = strToTileMap.get(elem[i].substring(4, 6));
				TileEnum huroHai3 = strToTileMap.get(elem[i].substring(6, 8));
				if(huroHai1.equals(huroHai2)){
					huros.add(new MeldElement(MeldEnum.STEAL_PONG, huroHai1,huroHai1,huroHai1));
				}else{
					huros.add(new MeldElement(MeldEnum.STEAL_CHOW, huroHai1,huroHai2,huroHai3));
				}
			}else if(ANTSU.equals(prefix)){
				TileEnum huroHai2 = strToTileMap.get(elem[i].substring(4, 6));
				TileEnum huroHai3 = strToTileMap.get(elem[i].substring(6, 8));
				if(huroHai1.equals(huroHai2)){
					huros.add(new MeldElement(MeldEnum.WALL_PONG, huroHai1,huroHai1,huroHai1));
				}else{
					huros.add(new MeldElement(MeldEnum.WALL_CHOW, huroHai1,huroHai2,huroHai3));
				}

			}
			
		}
		return huros;
	}
	private final static String MINKAN = "bb";
	private final static String ANKAN = "##";
	private final static String MINTSU = "--";
	private final static String ANTSU= "++";
	
	@Test 
	public void testOnDemand(){
		TileEnum playerWind = TileEnum.EAST;
		TileEnum prevailingWind = TileEnum.EAST;
		TileEnum winningTile = TileEnum.CHARACTOR5;
		String wallTilesInString = "M1, M1, M2, M2, M2, M3, M3, M3, M4, M4, M4, M5, M5, M5";
		String[] wallTilesInArr = wallTilesInString.split(", ");
		
		TileSet tileSet = new TileSet(playerWind, prevailingWind);
		List<TileEnum> tiles = new ArrayList<TileEnum>();
		for(String wallTileInString :wallTilesInArr){
			TileEnum tileEnum = strToTileMap.get(wallTileInString);
			tiles.add(tileEnum);
			
		}
		tileSet.setTiles(tiles);
		tileSet.setWinningTile(winningTile);
		tileSet.calculate();
		
	}
	@Test
	public void testFromDataSheet(){

		try{

			File file = new File("./test/testdata.tsv");
			BufferedReader br = new BufferedReader(new FileReader(file));
			String str = br.readLine();
			

			while(str != null){
				String elem[] = str.split("\t");
				if(elem.length == 0 || elem[0].equals("#")) {
					str = br.readLine();
					continue;
				}
				
				System.out.println("■ Test Case ID:" + elem[0]);
				if("19".equals(elem[0])){
					System.out.println("");
				}
				if(elem[2].length() == 2){
					
					str = br.readLine();
					continue;
				}

				List<MeldElement> huros = parseHuro(elem);
				
				String yakuString = elem[2] + elem[3] + elem[4] + elem[5] + elem[6] + elem[7];
				
				TileEnum baKaze = strToTileMap.get(elem[8]);		
				TileEnum jiKaze = strToTileMap.get(elem[9]);
				boolean isParent = (TileEnum.EAST.equals(jiKaze));

				TileEnum dora = strToTileMap.get(elem[10]);
//				boolean isNaki = Boolean.valueOf(elem[11]);
				boolean isTumo = Boolean.valueOf(elem[12]);
				int point1 = Integer.valueOf(elem[13]);
				
				int point2 = 0;
				try{
					point2 = Integer.valueOf(elem[14]);
				}catch(NumberFormatException e){
					
				}
				
				List<TileEnum> hais = new ArrayList<TileEnum>();
				System.out.println("-test hai:" + yakuString);

				for(int i = 0; i < yakuString.length()/2; i++){
					String s1 = yakuString.substring(i * 2, i * 2 + 2);
					
					TileEnum haiEnum = strToTileMap.get(s1);
					if(haiEnum == null){
						if(!s1.equals(MINKAN) && !s1.equals(ANKAN) && !s1.equals(MINTSU) && !s1.equals(ANTSU)){
							Assert.fail("invalid hai definition:" + s1);
						}
					}else{
						
						hais.add(haiEnum);
							
					}
				}

				TileSet haiSet = new TileSet(jiKaze, baKaze);
				haiSet.setTiles(hais);
				
				haiSet.setWinningTile(hais.get(hais.size() - 1));
				haiSet.addDoraTile(dora);
				
				for(MeldElement m : huros){

					haiSet.addHuro(m);
					if(m.getMeldEnum().equals(MeldEnum.STEAL_CHOW) ||
							m.getMeldEnum().equals(MeldEnum.STEAL_KONG) ||
							m.getMeldEnum().equals(MeldEnum.STEAL_PONG)){
						haiSet.setStolen();
						
					}
				}

				if(isTumo){
					haiSet.setTumo();
				}else{
					haiSet.setRon();
				}
				PaidPoint point = haiSet.calculate();
				if(point == null){
					System.out.println("not winning form");
				}else{
					if(isParent){
							Assert.assertEquals(point1,point.getPoint1());
							Assert.assertEquals(0,point.getPoint2());
					}else{
						Assert.assertEquals(point1,point.getPoint1());					
						if(isTumo){
							Assert.assertEquals(point2,point.getPoint2());
						}else{
							Assert.assertEquals(0,point.getPoint2());
						}
					}
				}
				

//				System.out.println(str);
				str = br.readLine();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
}

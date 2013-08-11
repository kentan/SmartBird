package org.sb.mdl.enm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("serial")
public enum TileEnum {
	CHARACTOR1("M1"),
	CHARACTOR2("M2"),
	CHARACTOR3("M3"),
	CHARACTOR4("M4"),
	CHARACTOR5("M5"),
	CHARACTOR6("M6"),
	CHARACTOR7("M7"),
	CHARACTOR8("M8"),
	CHARACTOR9("M9"),
	
	BAMBOO1("S1"),
	BAMBOO2("S2"),
	BAMBOO3("S3"),
	BAMBOO4("S4"),
	BAMBOO5("S5"),
	BAMBOO6("S6"),
	BAMBOO7("S7"),
	BAMBOO8("S8"),
	BAMBOO9("S9"),
	

	CIRCLE1("P1"),
	CIRCLE2("P2"),
	CIRCLE3("P3"),
	CIRCLE4("P4"),
	CIRCLE5("P5"),
	CIRCLE6("P6"),
	CIRCLE7("P7"),
	CIRCLE8("P8"),
	CIRCLE9("P9"),

	EAST("EA"),
	SOUTH("SO"),
	WEST("WE"),
	NORTH("NO"),
	WHITE("D1"),
	GREEN("D2"),
	RED("D3");

	private String _printString;
	private TileEnum(String printString){
		_printString = printString;
	}
	private static Map<TileEnum, Integer> ENUM_TO_INDEX_MAP = new HashMap<TileEnum, Integer>(){
		{
			put(TileEnum.CHARACTOR1,0);
			put(TileEnum.CHARACTOR2,1);
			put(TileEnum.CHARACTOR3,2);
			put(TileEnum.CHARACTOR4,3);
			put(TileEnum.CHARACTOR5,4);
			put(TileEnum.CHARACTOR6,5);
			put(TileEnum.CHARACTOR7,6);
			put(TileEnum.CHARACTOR8,7);
			put(TileEnum.CHARACTOR9,8);
			
			put(TileEnum.BAMBOO1,10);
			put(TileEnum.BAMBOO2,11);
			put(TileEnum.BAMBOO3,12);
			put(TileEnum.BAMBOO4,13);
			put(TileEnum.BAMBOO5,14);
			put(TileEnum.BAMBOO6,15);
			put(TileEnum.BAMBOO7,16);
			put(TileEnum.BAMBOO8,17);
			put(TileEnum.BAMBOO9,18);
			
			put(TileEnum.CIRCLE1,20);
			put(TileEnum.CIRCLE2,21);
			put(TileEnum.CIRCLE3,22);
			put(TileEnum.CIRCLE4,23);
			put(TileEnum.CIRCLE5,24);
			put(TileEnum.CIRCLE6,25);
			put(TileEnum.CIRCLE7,26);
			put(TileEnum.CIRCLE8,27);
			put(TileEnum.CIRCLE9,28);

			put(TileEnum.EAST,30);
			put(TileEnum.SOUTH,31);
			put(TileEnum.WEST,32);
			put(TileEnum.NORTH,33);
			put(TileEnum.WHITE,34);
			put(TileEnum.GREEN,35);
			put(TileEnum.RED,36);
		}
	};
	private static Map<Integer,TileEnum> INDEX_TO_ENUM_MAP = new HashMap<Integer,TileEnum>(){
		{
			put(0,TileEnum.CHARACTOR1);
			put(1,TileEnum.CHARACTOR2);
			put(2,TileEnum.CHARACTOR3);
			put(3,TileEnum.CHARACTOR4);
			put(4,TileEnum.CHARACTOR5);
			put(5,TileEnum.CHARACTOR6);
			put(6,TileEnum.CHARACTOR7);
			put(7,TileEnum.CHARACTOR8);
			put(8,TileEnum.CHARACTOR9);
			
			put(10,TileEnum.BAMBOO1);
			put(11,TileEnum.BAMBOO2);
			put(12,TileEnum.BAMBOO3);

			put(13,TileEnum.BAMBOO4);
			put(14,TileEnum.BAMBOO5);
			put(15,TileEnum.BAMBOO6);
			put(16,TileEnum.BAMBOO7);
			put(17,TileEnum.BAMBOO8);
			put(18,TileEnum.BAMBOO9);
			
			put(20,TileEnum.CIRCLE1);
			put(21,TileEnum.CIRCLE2);
			put(22,TileEnum.CIRCLE3);
			put(23,TileEnum.CIRCLE4);
			put(24,TileEnum.CIRCLE5);
			put(25,TileEnum.CIRCLE6);
			put(26,TileEnum.CIRCLE7);
			put(27,TileEnum.CIRCLE8);
			put(28,TileEnum.CIRCLE9);

			put(30,TileEnum.EAST);
			put(31,TileEnum.SOUTH);
			put(32,TileEnum.WEST);
			put(33,TileEnum.NORTH);
			put(34,TileEnum.WHITE);
			put(35,TileEnum.GREEN);
			put(36,TileEnum.RED);
		}
	};

	public int getIndex(){
		return ENUM_TO_INDEX_MAP.get(this);
	}
	static public TileEnum getEnum(int index){
		return INDEX_TO_ENUM_MAP.get(index);
	}
	
	public TileEnum prev(){
	
		if(this.getIndex() >= TileEnum.CHARACTOR2.getIndex() && this.getIndex() <= TileEnum.CHARACTOR9.getIndex() || 
				this.getIndex() >=TileEnum.BAMBOO2.getIndex() && this.getIndex() <= TileEnum.BAMBOO9.getIndex()||
				this.getIndex() >=TileEnum.CIRCLE2.getIndex() && this.getIndex() <= TileEnum.CIRCLE9.getIndex()){
			return INDEX_TO_ENUM_MAP.get(this.getIndex() -1);
		}else{
			return null;
		}
		
	}
	public TileEnum next(){
		if(this.getIndex() >=TileEnum.CHARACTOR1.getIndex() && this.getIndex() <= TileEnum.CHARACTOR8.getIndex() || 
				this.getIndex() >=TileEnum.BAMBOO1.getIndex() && this.getIndex() <= TileEnum.BAMBOO8.getIndex() ||
				this.getIndex() >=TileEnum.CIRCLE1.getIndex() && this.getIndex() <= TileEnum.CIRCLE8.getIndex()){
			return INDEX_TO_ENUM_MAP.get(this.getIndex() + 1);
		}else{
			return null;
		}

	}
	public boolean isCharactor(){
		return (this.getIndex() >= TileEnum.CHARACTOR1.getIndex() && this.getIndex() <= TileEnum.CHARACTOR9.getIndex());
	}
	public boolean isBamboo(){
		return (this.getIndex() >= TileEnum.BAMBOO1.getIndex() && this.getIndex() <= TileEnum.BAMBOO9.getIndex());
	}
	public boolean isCircle(){
		return (this.getIndex() >= TileEnum.CIRCLE1.getIndex() && this.getIndex() <= TileEnum.CIRCLE9.getIndex());
	}
	public boolean isWindAndDragon(){
		return (this.getIndex() >= TileEnum.EAST.getIndex() && this.getIndex() <= TileEnum.RED.getIndex());
	}
	final private static List<TileEnum> teminalTiles = new ArrayList<TileEnum>(){{
		add(TileEnum.CHARACTOR1);
		add(TileEnum.CHARACTOR9);
		add(TileEnum.BAMBOO1);
		add(TileEnum.BAMBOO9);
		add(TileEnum.CIRCLE1);
		add(TileEnum.CIRCLE9);

	}};
	public boolean isTerminal(){
		return teminalTiles.contains(this);
	}
	public boolean isTanyao(){
		if (this.equals(TileEnum.CHARACTOR1) || this.equals(TileEnum.CHARACTOR9) || this.equals(TileEnum.BAMBOO1) || this.equals(TileEnum.BAMBOO9)
				|| this.equals(TileEnum.CIRCLE1) || this.equals(TileEnum.CIRCLE9) || this.equals(TileEnum.EAST) || this.equals(TileEnum.SOUTH)
				|| this.equals(TileEnum.WEST) || this.equals(TileEnum.NORTH) || this.equals(TileEnum.WHITE) || this.equals(TileEnum.GREEN)
				|| this.equals(TileEnum.RED)) {
			return false;
		}
		return true;

	}
	public TileEnum getSameInCharactor(){
		return getSameInOtherColor(0,-10,-20);
	}
	public TileEnum getSameInBamboo(){
		
		return getSameInOtherColor(10,0,-10);
	}
	public TileEnum getSameInCircle(){
		return getSameInOtherColor(20,10,0);
	}
	private  TileEnum getSameInOtherColor(int CharactorOffset,int Bambooffset,int CircleOffset){
		TileEnum tile = null;
		if(this.getIndex() >= TileEnum.CHARACTOR1.getIndex() && this.getIndex() <= TileEnum.CHARACTOR9.getIndex()){
			 tile = INDEX_TO_ENUM_MAP.get(this.getIndex() + CharactorOffset);
		}else if(this.getIndex() >= TileEnum.BAMBOO1.getIndex() && this.getIndex() <= TileEnum.BAMBOO9.getIndex()){
			 tile = INDEX_TO_ENUM_MAP.get(this.getIndex() + Bambooffset);
		}else if(this.getIndex() >= TileEnum.CIRCLE1.getIndex() && this.getIndex() <= TileEnum.CIRCLE9.getIndex()){
			 tile = INDEX_TO_ENUM_MAP.get(this.getIndex() + CircleOffset);
		}else{
			tile = null;
		}
		return tile;	
	}
	@Override
	public String toString(){
		return _printString;
	}
}

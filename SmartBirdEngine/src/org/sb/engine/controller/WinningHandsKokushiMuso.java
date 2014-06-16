package org.sb.engine.controller;

import java.util.ArrayList;
import java.util.List;

import org.sb.mdl.enm.TileEnum;


public class WinningHandsKokushiMuso extends WinningHands {
	private List<TileEnum> _tiles = new ArrayList<TileEnum>();
    public WinningHandsKokushiMuso(boolean isStolen,boolean isTumo,boolean isRichi){
    	super(isStolen,isTumo,isRichi);                
    }
    
    public void addTile(TileEnum tile){
    	_tiles.add(tile);
    }
    
}

package org.sb.engine.controller;

import java.util.ArrayList;
import java.util.List;

import org.sb.mdl.enm.TileEnum;


public class WinningHandsKokushiMuso extends WinningHands {
	private List<TileEnum> _tiles = new ArrayList<TileEnum>();
    public WinningHandsKokushiMuso(boolean isStolen,boolean isTumo){
    	super(isStolen,isTumo);                
    }
    
    public void addTile(TileEnum tile){
    	_tiles.add(tile);
    }
    
}

package org.smartbird.server;

import java.util.ArrayList;
import java.util.List;

import org.sb.mdl.enm.TileEnum;


public class InputCommand {

	private TileEnum _stolenTile;
	private TileEnum _discardedTile;
	private TileEnum _richiTile;
	private List<TileEnum> huroTiles = new ArrayList<TileEnum>();
	private CommandEnum _command;
	
	public void setStolenTile(TileEnum tile){
		_stolenTile = tile;
	}
	
	public TileEnum getStolenTile(){
		return _stolenTile;
	}

	public void setRichiTile(TileEnum tile){
		_richiTile = tile;
	}
	
	public TileEnum getRichiTile(){
		return _richiTile;
	}
	public void setDiscardedTile(TileEnum tile){
		_discardedTile = tile;
	}
	
	public TileEnum getDiscardedTile(){
		return _discardedTile;
	}
	public void setCommand(CommandEnum input){
		_command = input;
	}
	public CommandEnum getCommand(){
		return _command;
	}
	public void addHuroTile(TileEnum tile){
		huroTiles.add(tile);
	}
	public List<TileEnum> getHuroTile(){
		return huroTiles;
	}
}

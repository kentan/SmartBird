package org.smartbirdpj.engine.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

import org.smartbirdpj.mdl.MeldElement;
import org.smartbirdpj.mdl.Point;
import org.smartbirdpj.mdl.enm.TileEnum;


public class TileSet {
	private final static Logger LOGGER = Logger.getLogger(TileSet.class.getName());
	
	private List<TileEnum> _tiles = new ArrayList<TileEnum>();
	private TileEnum _playerWind;
	private TileEnum _prevailingWind;
	private TileEnum _winningTile;
	private List<TileEnum> _doraList;// Notice: This contains "dora",not displayed tile;
	private boolean _isStolen;
	private boolean _isTumo;
	private boolean _isRichi;
    private String yaku = new String();

	private List<MeldElement> _huroList = new ArrayList<MeldElement>();


	public TileSet(TileEnum playerWind, TileEnum prevailingWind) {
		List<TileEnum> list = new ArrayList<TileEnum>();
		init(list, playerWind, prevailingWind);

	}

	public void setTiles(List<TileEnum> list){
		_tiles = list;
	}
	private void init(List<TileEnum> list, TileEnum playerWind, TileEnum prevailingWind) {
		_tiles.addAll(list);
		_playerWind = playerWind;
		_prevailingWind = prevailingWind;
		_doraList = new ArrayList<TileEnum>();
	}

	public void addHuro(MeldElement meld) {
		_huroList.add(meld);
		boolean done = false;
		for(TileEnum tile : meld.getList()){
			if(!done && tile.equals(meld.getStolenTile())){
				done = true;
				continue;
			}
			_tiles.remove(tile);
		}
	}
	public List<MeldElement> getHuros(){
		return _huroList;
	}
	
	public void setRichi(){
		_isRichi = true;
	}
	public boolean isRichi(){
		return _isRichi;
	}

	public void setStolen() {
		_isStolen = true;
	}
	
	public void setTumo() {
		_isTumo = true;
	}

	public void setRon() {
		_isTumo = false;
	}
	public boolean isStolen(){
		return _isStolen;
	}
	public boolean isTumo(){
		return _isTumo;
	}
	public boolean isRon(){
		return !_isTumo;
	}
	public void setWinningTile(TileEnum tile) {
		_winningTile = tile;
	}

	public void addDoraTiles(List<TileEnum> tiles) {
		_doraList.addAll(tiles);
	}
	public void addDoraTile(TileEnum tile) {
		_doraList.add(tile);
	}

	public boolean isWinningTile(TileEnum tile) {
		return _winningTile == tile;
	}

	private boolean validateWinningHandsList(WinningHandsList winningHandsList) {
		if (winningHandsList.getList().size() == 0) {
			LOGGER.severe("error : TileSet#validateWinningHandsList");
			return false;
		}
		for (WinningHands winningHands : winningHandsList.getList()) {
			if (winningHands instanceof WinningHandsBasic) {
				WinningHandsBasic winningHandsOtherThanKokushi = (WinningHandsBasic) winningHands;
				if (winningHandsOtherThanKokushi.getWinningFormEnum() == null) {
					LOGGER.severe("assertion error: TileSet#validateWinningHandsList == null");
					for (MeldElement m : winningHandsOtherThanKokushi.getList()) {
						LOGGER.info(m.toString());
					}
					return false;
				}
			}
		}
		return true;
	}
	private WinningHandsList winningHandsListCache = null;
	public boolean isWinningHandsValid(){
		winningHandsListCache = null;
		HandsParser parser = new HandsParser();
		WinningHandsList winningHandsList = parser.parse(this);

		if(winningHandsList.getList().size() >= 1){
			winningHandsListCache = winningHandsList;
			return true;
		}
		return false;
	}
	public Point calculate() {

		if(winningHandsListCache == null){
			if(!isWinningHandsValid()){
				return null;
			}
		}


		if(validateWinningHandsList(winningHandsListCache) == false){
			return null;
		}

		PointCalculator calculator = new PointCalculator(_playerWind, _prevailingWind, _doraList);
		Point p = calculator.calculate(winningHandsListCache);
		this.yaku = calculator.getValidatedYaku();
		return p;

	}

    public String getValidYaku(){
        return this.yaku;
    }


	public void sort() {
		Collections.sort(_tiles);
	}

	public void addTile(TileEnum tile){
		_tiles.add(tile);
	}
	public void discardTile(TileEnum tile){
		_tiles.remove(tile);
	}
	
	public List<TileEnum> getTiles(){
		// TODO deep copy for preventing from exposure
		return _tiles;
	}
	@Override
	public String toString(){
		StringBuffer bf = new StringBuffer();
		for(TileEnum enm : _tiles){
			bf.append(enm.toString());
		}
		return bf.toString();
	}

}

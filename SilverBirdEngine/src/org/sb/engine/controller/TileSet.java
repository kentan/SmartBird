package org.sb.engine.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

import org.sb.mdl.MeldElement;
import org.sb.mdl.PaidPoint;
import org.sb.mdl.enm.MeldEnum;
import org.sb.mdl.enm.TileEnum;
import org.sb.mdl.enm.WinningFormEnum;


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

	private boolean isWinningTile(TileEnum tile) {
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
		WinningHandsList winningHandsList = parse();

		if(winningHandsList.getList().size() >= 1){
			winningHandsListCache = winningHandsList;
			return true;
		}
		return false;
	}
	public PaidPoint calculate() {

		if(winningHandsListCache == null){
			if(!isWinningHandsValid()){
				return null;
			}
		}


		if(validateWinningHandsList(winningHandsListCache) == false){
			return null;
		}

		PointCalculator calculator = new PointCalculator(_playerWind, _prevailingWind, _doraList);
		return calculator.calculate(winningHandsListCache);

	}

	private List<Integer> searchHeadPos() {
		List<Integer> heads = new ArrayList<Integer>();
		for (int i = 0; i < _tiles.size() - 1; i++) {
			TileEnum h1 = _tiles.get(i);
			TileEnum h2 = _tiles.get(i + 1);

			if (h1.equals(h2)) {
				heads.add(i);
			}
		}
		return heads;
	}

	private MeldNode makeMeldTree(int headPos) {
		List<TileEnum> list = new ArrayList<TileEnum>(_tiles);
		MeldNode mTree = new MeldNode();
		makeMeldHead(list, mTree, headPos);

		boolean rv = makeMeldOtherThanHead(list, mTree);

		return rv ? mTree : null;
	}

	private void makeMeldHead(List<TileEnum> list, MeldNode mtree,
			int headPos) {
		TileEnum a1 = list.remove(headPos + 1);
		TileEnum a2 = list.remove(headPos);

		if (isWinningTile(a1) || isWinningTile(a1)) {
			mtree.setWinningFormEnum(WinningFormEnum.TANKI, _isTumo);

		}
		MeldElement yakuHead = new MeldElement(MeldEnum.EYES, a1,
				a2);
		mtree.setMeld(yakuHead);

	}

	private boolean makeMeldFromHuro(MeldNode mTree, List<MeldElement> huroList) {
		if (huroList.size() == 0) {
			return true;
		}
		MeldElement meld = huroList.remove(0);
		MeldNode child = new MeldNode();
		child.setMeld(meld);

		mTree.addChild(child);

		return makeMeldFromHuro(child, huroList);
	}

	private boolean makeMeldOtherThanHead(List<TileEnum> list, MeldNode parent) {

		if (list.size() == 0) {
			List<MeldElement> huroList = new ArrayList<MeldElement>(_huroList);
 			return makeMeldFromHuro(parent, huroList);
		}
		boolean rv = false;
		TileEnum h1 = list.remove(0);

		// 刻子
		if (list.contains(h1)) {
			int indexOfH1 = list.indexOf(h1);
			TileEnum h2 = list.remove(indexOfH1);
			if (list.contains(h1)) {
				TileEnum h3 = list.remove(list.indexOf(h1));
				// Pong
				MeldElement r = null;
				r = new MeldElement(MeldEnum.WALL_PONG, h1, h2, h3);

				MeldNode child = new MeldNode();
				child.setMeld(r);

				if (isWinningTile(h1) || isWinningTile(h2) || isWinningTile(h3)) {

					child.setWinningFormEnum(WinningFormEnum.SYANPON, _isTumo);
				}

				rv = makeMeldOtherThanHead(list, child);
				if (rv) {
					parent.addChild(child);
				}

			} else {
				list.add(indexOfH1, h2);
			}

		}

		// CHOW
		TileEnum h2 = h1.next();
		TileEnum h3 = h2 == null ? null : h2.next();
		
		if (!h1.isWindAndDragon() && list.contains(h2) && list.contains(h3)) {
			// CHOW
			h2 = list.remove(list.indexOf(h2));
			h3 = list.remove(list.indexOf(h3));

			MeldElement r = null;
			r = new MeldElement(MeldEnum.WALL_CHOW, h1, h2, h3);

			MeldNode child = new MeldNode();
			child.setMeld(r);


			if (isWinningTile(h1)) {
				if (h1.equals(TileEnum.CHARACTOR7) || h1.equals(TileEnum.BAMBOO7)
						|| h1.equals(TileEnum.CIRCLE7)) {
					child.setWinningFormEnum(WinningFormEnum.PENTYAN, _isTumo);
				} else {
					child.setWinningFormEnum(WinningFormEnum.RYANMEN, _isTumo);
				}
			}
			if (isWinningTile(h2)) {

				child.setWinningFormEnum(WinningFormEnum.KANTYAN, _isTumo);
			}
			if (isWinningTile(h3)) {

				if (h1.equals(TileEnum.CHARACTOR3) || h1.equals(TileEnum.BAMBOO3)
						|| h1.equals(TileEnum.CIRCLE3)) {
					child.setWinningFormEnum(WinningFormEnum.PENTYAN, _isTumo);
				} else {
					child.setWinningFormEnum(WinningFormEnum.RYANMEN, _isTumo);
				}
			}
			rv = makeMeldOtherThanHead(list, child);
			if (rv) {
				parent.addChild(child);
			}

		}

		return rv;
	}

	private WinningHandsList parse7Toitsu() {
		WinningHandsList winningHandsList = new WinningHandsList();

		if(_tiles.size() != 14){
			return winningHandsList;
		}
		WinningHandsBasic winningHandsBasic = new WinningHandsBasic(_isStolen,_isTumo,_isRichi);
		for (int i = 0; i < _tiles.size(); i = i + 2) {
			TileEnum h1 = _tiles.get(i);
			TileEnum h2 = _tiles.get(i + 1);
			if (h1 != h2) {
				return winningHandsList;
			}
			MeldElement m = new MeldElement(MeldEnum.EYES, h1, h2);
			winningHandsBasic.add(m);
		}
		winningHandsBasic.set7Toitsu();
		winningHandsList.add(winningHandsBasic);
		return winningHandsList;
	}

	private WinningHandsList parseKokushiMusou() {
		WinningHandsList winningHandsList = new WinningHandsList();
		WinningHandsKokushiMuso winningHands = new WinningHandsKokushiMuso(_isStolen, _isTumo,_isRichi);
		TileEnum[] kokushiTileCandidates = { TileEnum.CHARACTOR1, TileEnum.CHARACTOR9,
				TileEnum.BAMBOO1, TileEnum.BAMBOO9, TileEnum.CIRCLE1, TileEnum.CIRCLE9,
				TileEnum.EAST, TileEnum.SOUTH, TileEnum.WEST, TileEnum.NORTH,
				TileEnum.WHITE, TileEnum.GREEN, TileEnum.RED };

		for (TileEnum h : kokushiTileCandidates) {
			if (_tiles.contains(h)) {
				winningHands.addTile(h);
			}else{
				return winningHandsList;
			}
		}

		winningHandsList.add(winningHands);
		return winningHandsList;
	}
	private WinningHandsList parseBasic(){
		List<Integer> headPoses = searchHeadPos();
		WinningHandsList winningHandsList = new WinningHandsList();
		for (Integer headPos : headPoses) {
			MeldNode meldTree = makeMeldTree(headPos);

			if (meldTree != null) {
				LOGGER.info("-Meld Tree:" + meldTree);
				// to List<Yaku>
				winningHandsList.addAll(meldTree.transformGraphToList(_isStolen, _isTumo,_isRichi));
			}
		}

		return winningHandsList;
	}
	private WinningHandsList parse() {
//		// 副路は上がり前に宣言され確定しているため別扱い。
//		deleteHuro();
		sort();
		WinningHandsList winningHandsList= new WinningHandsList();
		// 国士無双
		winningHandsList.addAll(parseKokushiMusou().getList());
		if (winningHandsList.getList().size() != 0) {
			return winningHandsList;
		}
		// 七対子
		winningHandsList.addAll(parse7Toitsu().getList());
		winningHandsList.addAll(parseBasic().getList());
		return winningHandsList;

	}

	private void sort() {
		Collections.sort(_tiles);
	}

	private void deleteHuro() {

		for (MeldElement elem : _huroList) {
			TileEnum tile = elem.getList().get(0);

			while (_tiles.contains(tile)) {
				_tiles.remove(tile);
			}
		}
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
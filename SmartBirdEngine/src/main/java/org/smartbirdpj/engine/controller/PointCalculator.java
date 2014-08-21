package org.smartbirdpj.engine.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;


import org.smartbirdpj.engine.validator.ChantaValidator;
import org.smartbirdpj.engine.validator.ChiToitsuValidator;
import org.smartbirdpj.engine.validator.ChinitsuValidator;
import org.smartbirdpj.engine.validator.HonitsuValidator;
import org.smartbirdpj.engine.validator.HonrotoValidator;
import org.smartbirdpj.engine.validator.IPekoValidator;
import org.smartbirdpj.engine.validator.IkkiTsukanValidator;
import org.smartbirdpj.engine.validator.JunchanValidator;
import org.smartbirdpj.engine.validator.PinhuValidator;
import org.smartbirdpj.engine.validator.RyanPekoValidator;
import org.smartbirdpj.engine.validator.SanAnkoValidator;
import org.smartbirdpj.engine.validator.SanKantsuValidator;
import org.smartbirdpj.engine.validator.SanSyokuDojunValidator;
import org.smartbirdpj.engine.validator.SanSyokuDokokuValidator;
import org.smartbirdpj.engine.validator.SyoSangenValidator;
import org.smartbirdpj.engine.validator.TanyaoValidator;
import org.smartbirdpj.engine.validator.ToiToiValidator;
import org.smartbirdpj.engine.validator.TumoValidator;
import org.smartbirdpj.engine.validator.WinningHandsStatus;
import org.smartbirdpj.engine.validator.WinningHandsValidator;
import org.smartbirdpj.engine.validator.YakuhaiValidator;
import org.smartbirdpj.engine.validator.yakuman.AllGreensValidator;
import org.smartbirdpj.engine.validator.yakuman.TsuIsoValidator;
import org.smartbirdpj.engine.validator.yakuman.ChinrotoValidator;
import org.smartbirdpj.engine.validator.yakuman.DaiSangenValidator;
import org.smartbirdpj.engine.validator.yakuman.SuKantsuValidator;
import org.smartbirdpj.engine.validator.yakuman.SuAnkoValidator;
import org.smartbirdpj.engine.validator.yakuman.SuShihoValidator;
import org.smartbirdpj.engine.validator.yakuman.TyurenpotoValidator;
import org.smartbirdpj.engine.validator.yakuman.KokushiMusoValidator;
import org.smartbirdpj.mdl.MeldElement;
import org.smartbirdpj.mdl.Point;
import org.smartbirdpj.mdl.enm.MeldEnum;
import org.smartbirdpj.mdl.enm.TileEnum;
import org.smartbirdpj.mdl.enm.WinningHandsEnum;


@SuppressWarnings("serial")
public class PointCalculator {
	private final static Logger LOGGER = Logger.getLogger(PointCalculator.class.getName());
	
	private TileEnum _playerWind;
	private TileEnum _prevailingWind;
	private List<TileEnum> _doraList;

	public PointCalculator(TileEnum playerWind, TileEnum prevailingWind, List<TileEnum> doraList) {
		_playerWind = playerWind;
		_prevailingWind = prevailingWind;
		_doraList = doraList;
	}

	private boolean isParent() {
		return TileEnum.EAST.equals(_playerWind);
	}

	private int countDoraTile(WinningHandsBasic winningHands) {
		int numOfDora = 0;
		for (MeldElement m : winningHands.getList()) {
			for (TileEnum h : m.getList()) {
				if (_doraList.contains(h)) {
					numOfDora++;
				}
			}
		}
		
		return numOfDora;
	} 
	/** 
	 * Some winning hands can't valid at the same time, like Ipeko(一盃口) and Raynpeko(二盃口).
	 * In this case, only higher winning hands is accepted. 
	 * Considering this rules, validators  must be called in specified order.
	 */

	private Map<WinningHandsEnum,WinningHandsValidator> orderedWinningHandsMap = new LinkedHashMap<WinningHandsEnum, WinningHandsValidator>()
	{{
		put(WinningHandsEnum.ALL_GREENS, new AllGreensValidator());
		put(WinningHandsEnum.ALL_HONORS, new TsuIsoValidator());
		put(WinningHandsEnum.ALL_TERMINALS,new ChinrotoValidator());
		put(WinningHandsEnum.BIG_DORAGONS,new DaiSangenValidator());
		put(WinningHandsEnum.FOR_PONGS,new SuAnkoValidator());
		put(WinningHandsEnum.FOR_KONGS,new SuKantsuValidator());
		put(WinningHandsEnum.FOR_WINDS,new SuShihoValidator());
		put(WinningHandsEnum.NINE_TRESURES,new TyurenpotoValidator());
		put(WinningHandsEnum.THIRTEEN_ORPHANS,new KokushiMusoValidator());
		
		put(WinningHandsEnum.CHINITSU,new ChinitsuValidator());

		put(WinningHandsEnum.HONROUTOU,new HonrotoValidator());
		put(WinningHandsEnum.SYOSANGEN,new SyoSangenValidator());

		put(WinningHandsEnum.HONITSU,new HonitsuValidator());
		put(WinningHandsEnum.JUNCHAN,new JunchanValidator());
		put(WinningHandsEnum.RYAN_PEKO,new RyanPekoValidator());
		
		put(WinningHandsEnum.SAN_ANKO,new SanAnkoValidator());
		put(WinningHandsEnum.SAN_KANTSU,new SanKantsuValidator());
		put(WinningHandsEnum.TOITOI,new ToiToiValidator());
		put(WinningHandsEnum.SANSYOKU_DOJUN,new SanSyokuDojunValidator());
		put(WinningHandsEnum.SANSYOKU_DOKOKU,new SanSyokuDokokuValidator());
		put(WinningHandsEnum.IKKI_TSUKAN,new IkkiTsukanValidator());
		put(WinningHandsEnum.CHANTA,new ChantaValidator());
		put(WinningHandsEnum.CHI_TOITSU,new ChiToitsuValidator());

		put(WinningHandsEnum.TANYAO,new TanyaoValidator());
		put(WinningHandsEnum.I_PEKO,new IPekoValidator());
		put(WinningHandsEnum.YAKUHAI,new YakuhaiValidator());
		put(WinningHandsEnum.PINHU,new PinhuValidator());
		
		put(WinningHandsEnum.TUMO,new TumoValidator());
		
	}};
	private int calculateHan(WinningHands winningHands){
		int han = 0;
		if(winningHands.isRichi()){
			han += 1;
		}
		WinningHandsStatus status = new WinningHandsStatus(_playerWind,_prevailingWind);
		for(Map.Entry<WinningHandsEnum,WinningHandsValidator> entry: orderedWinningHandsMap.entrySet()){
			
			if(WinningHandsEnum.YAKUHAI.equals(entry.getKey())){
				YakuhaiValidator yakuhaiValidator = (YakuhaiValidator)entry.getValue();
				for(MeldElement elem : ((WinningHandsBasic)winningHands).getList()){
					if(yakuhaiValidator.validateEachPlayerWind(elem,status)){
						han += entry.getKey().getHan(winningHands.isStolen());
					}
					if(yakuhaiValidator.validateEachPrevailingWind(elem,status)){
						han += entry.getKey().getHan(winningHands.isStolen());
					}
					if(yakuhaiValidator.validateEachWhite(elem,status)){
						han += entry.getKey().getHan(winningHands.isStolen());
					}
					if(yakuhaiValidator.validateEachRed(elem,status)){
						han += entry.getKey().getHan(winningHands.isStolen());
					}
					if(yakuhaiValidator.validateEachGreen(elem,status)){
						han += entry.getKey().getHan(winningHands.isStolen());
					}
				}
			}
			else if(WinningHandsEnum.SANSYOKU_DOJUN.equals(entry.getKey()) ||
					WinningHandsEnum.SANSYOKU_DOKOKU.equals(entry.getKey())){
				if(entry.getValue().validate(winningHands,status)){

					han += entry.getKey().getHan(status.is3SyokuStolen);
				}
			}
			else if(WinningHandsEnum.SAN_ANKO.equals(entry.getKey()) ||
					WinningHandsEnum.SAN_KANTSU.equals(entry.getKey())){
				if(entry.getValue().validate(winningHands,status)){

					han += entry.getKey().getHan(false);
				}
			}
			else {
				if(entry.getValue().validate(winningHands,status)){

					han += entry.getKey().getHan(winningHands.isStolen());
				}
			}
		}
		han += countDoraTile((WinningHandsBasic)winningHands);
		return han;
	}

	// 20 ~ 29 => 30;
	// 30 ~ 39 => 40;
	private int ceil(int value) {

		// for Pinhu- Tumo
		if (value == 22) {
			return 20;
		}
		if (value % 10 == 0) {
			return value;
		} else {
			return (value / 10 + 1) * 10;
		}
	}

	private int calculateHu(WinningHandsBasic winningHand) {
		if (winningHand.is7Toitsu()) {
			return 25;
		}
		int hu = 20;

		if (winningHand.isMenzen() && !winningHand.isTumo()) {
			hu += 10;
		}
		try {
			hu += winningHand.getWinningFormEnum().getHu();
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		for (MeldElement meld : winningHand.getList()) {
			MeldEnum meldEnum = meld.getMeldEnum();
			if (MeldEnum.EYES.equals(meldEnum)) {
				TileEnum tile = meld.getList().get(0);
				if (tile.equals(_playerWind) || tile.equals(_prevailingWind) || tile.equals(TileEnum.WHITE) || tile.equals(TileEnum.GREEN)
						|| tile.equals(TileEnum.RED)) {
					hu += 2;
				}

			}
			if (MeldEnum.WALL_PONG.equals(meldEnum)) {
				TileEnum tile = meld.getList().get(0);
				if (tile.isTanyao()) {
					hu += 4;
				} else {
					hu += 8;
				}
			}
			if (MeldEnum.STEAL_PONG.equals(meldEnum)) {
				TileEnum tile = meld.getList().get(0);
				if (tile.isTanyao()) {
					hu += 2;
				} else {
					hu += 4;
				}
			}
			if (MeldEnum.WALL_KONG.equals(meldEnum)) {
				TileEnum tile = meld.getList().get(0);
				if (tile.isTanyao()) {
					hu += 16;
				} else {
					hu += 32;
				}
			}
			if (MeldEnum.STEAL_KONG.equals(meldEnum)) {
				TileEnum tile = meld.getList().get(0);
				if (tile.isTanyao()) {
					hu += 8;
				} else {
					hu += 16;
				}
			}
		}
		return ceil(hu);
	}

	public Point calculate(WinningHandsList winningHandsList) {
		int maxHan = 0;
		int maxHu = 0;

		for (WinningHands winningHand : winningHandsList.getList()) {

			if (winningHand instanceof WinningHandsKokushiMuso) {
				maxHan = 13;
				maxHu = 40;
				break;
			} else {

				int hu = calculateHu((WinningHandsBasic) winningHand);

				int han = calculateHan((WinningHandsBasic) winningHand);

				if (han > maxHan) {
					maxHan = han;
				}
				if (hu > maxHu) {
					maxHu = hu;
				}
			}

		}
		LOGGER.finest("-Han:" + maxHan);
		LOGGER.finest("-Hu:" + maxHu);
		boolean isTumo = winningHandsList.get(0).isTumo();
		PointHolder pointCalculator = new PointHolder();
		Point point = null;
		if (isTumo) {
			if (isParent()) {

				point = pointCalculator.getPointOnTumoParent(maxHan, maxHu);

			} else {

				point = pointCalculator.getPointOnTumoChild(maxHan, maxHu);

			}

		} else {
			if (isParent()) {

				point = pointCalculator.getPointOnRonParent(maxHan, maxHu);

			} else {

				point = pointCalculator.getPointOnRonChild(maxHan, maxHu);
			}
		}
		return point;
	}


}

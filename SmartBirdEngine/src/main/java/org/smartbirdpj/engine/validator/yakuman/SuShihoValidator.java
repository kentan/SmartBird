package org.smartbirdpj.engine.validator.yakuman;

import java.util.ArrayList;
import java.util.List;

import org.smartbirdpj.engine.controller.WinningHands;
import org.smartbirdpj.engine.controller.WinningHandsBasic;
import org.smartbirdpj.engine.validator.WinningHandsStatus;
import org.smartbirdpj.engine.validator.WinningHandsValidator;
import org.smartbirdpj.mdl.MeldElement;
import org.smartbirdpj.mdl.enm.MeldEnum;
import org.smartbirdpj.mdl.enm.TileEnum;


@SuppressWarnings("serial")
public class SuShihoValidator extends WinningHandsValidator {

	private static List<MeldEnum> allowedMelds = new ArrayList<MeldEnum>(){{
		add(MeldEnum.STEAL_KONG);
		add(MeldEnum.STEAL_PONG);
		add(MeldEnum.WALL_KONG);
		add(MeldEnum.WALL_PONG);
	}};
	@Override
	public boolean validate(WinningHands winningHands,WinningHandsStatus status){
		WinningHandsBasic hands = (WinningHandsBasic)winningHands;
		List<MeldElement> melds = hands.getList();
		boolean east =false, south = false, west = false,north = false;

		for(MeldElement meld : melds){
			if(allowedMelds.contains(meld.getMeldEnum())){
				if(TileEnum.EAST.equals(meld.getList().get(0))){
					east = true;
				}				
				if(TileEnum.SOUTH.equals(meld.getList().get(0))){
					south = true;
				}
				if(TileEnum.WEST.equals(meld.getList().get(0))){
					west = true;
				
				}
				if(TileEnum.NORTH.equals(meld.getList().get(0))){
					north = true;
				}
				
			}
			
		}
		if((east && west && south && north)){
			status.isYakuman = true;
			return true;
		}
		return false;
	}
}

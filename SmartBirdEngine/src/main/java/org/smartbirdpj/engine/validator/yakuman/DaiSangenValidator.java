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
public class DaiSangenValidator extends WinningHandsValidator {
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
		boolean haku = false;
		boolean hatsu = false;
		boolean tyun = false;
		for(MeldElement meld : melds){
			if(allowedMelds.contains(meld.getMeldEnum())){
				if(TileEnum.WHITE.equals(meld.getList().get(0))){
					haku = true;
				}				
				if(TileEnum.GREEN.equals(meld.getList().get(0))){
					hatsu = true;
				}
				if(TileEnum.RED.equals(meld.getList().get(0))){
					tyun = true;
				}
				
			}
			
		}
		return (haku && hatsu && tyun);
	}
}

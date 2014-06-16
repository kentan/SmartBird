package org.sb.engine.validator.yakuman;

import java.util.ArrayList;
import java.util.List;


import org.sb.engine.controller.WinningHands;
import org.sb.engine.controller.WinningHandsBasic;
import org.sb.engine.validator.WinningHandsStatus;
import org.sb.engine.validator.WinningHandsValidator;
import org.sb.mdl.MeldElement;
import org.sb.mdl.enm.TileEnum;



@SuppressWarnings("serial")
public class AllGreensValidator extends WinningHandsValidator {

	private static List<TileEnum> allowedTiles = new ArrayList<TileEnum>()
	{{
		add(TileEnum.BAMBOO2);
		add(TileEnum.BAMBOO3);
		add(TileEnum.BAMBOO4);
		add(TileEnum.BAMBOO6);
		add(TileEnum.BAMBOO8);
		add(TileEnum.GREEN);
	}};
	@Override
	public boolean validate(WinningHands winningHands,WinningHandsStatus status){
		WinningHandsBasic hands = (WinningHandsBasic)winningHands;
		List<MeldElement> melds = hands.getList();
		
		for(MeldElement meld : melds){
			for(TileEnum tile : meld.getList()){
				if(allowedTiles.contains(tile)){
					continue;
				}
				return false;
			}
		}
		status.isYakuman = true;
		return true;
	}

}

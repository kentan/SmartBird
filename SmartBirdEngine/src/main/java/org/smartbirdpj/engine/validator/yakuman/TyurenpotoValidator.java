package org.smartbirdpj.engine.validator.yakuman;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.smartbirdpj.engine.controller.WinningHands;
import org.smartbirdpj.engine.controller.WinningHandsBasic;
import org.smartbirdpj.engine.validator.WinningHandsStatus;
import org.smartbirdpj.engine.validator.WinningHandsValidator;
import org.smartbirdpj.mdl.MeldElement;
import org.smartbirdpj.mdl.enm.TileEnum;


public class TyurenpotoValidator extends WinningHandsValidator {
	
	private List<Map<TileEnum, Integer>> getHandsDefs(){
		
		Map<TileEnum, Integer> mapCharacters = new HashMap<TileEnum, Integer>();
		mapCharacters.put(TileEnum.CHARACTOR1, 3);
		mapCharacters.put(TileEnum.CHARACTOR2, 1);
		mapCharacters.put(TileEnum.CHARACTOR3, 1);
		mapCharacters.put(TileEnum.CHARACTOR4, 1);
		mapCharacters.put(TileEnum.CHARACTOR5, 1);
		mapCharacters.put(TileEnum.CHARACTOR6, 1);
		mapCharacters.put(TileEnum.CHARACTOR7, 1);
		mapCharacters.put(TileEnum.CHARACTOR8, 1);
		mapCharacters.put(TileEnum.CHARACTOR9, 3);
		
		Map<TileEnum, Integer> mapBamboos = new HashMap<TileEnum, Integer>();
		mapBamboos.put(TileEnum.BAMBOO1, 3);
		mapBamboos.put(TileEnum.BAMBOO2, 1);
		mapBamboos.put(TileEnum.BAMBOO3, 1);
		mapBamboos.put(TileEnum.BAMBOO4, 1);
		mapBamboos.put(TileEnum.BAMBOO5, 1);
		mapBamboos.put(TileEnum.BAMBOO6, 1);
		mapBamboos.put(TileEnum.BAMBOO7, 1);
		mapBamboos.put(TileEnum.BAMBOO8, 1);
		mapBamboos.put(TileEnum.BAMBOO9, 3);

		Map<TileEnum, Integer> mapCircles = new HashMap<TileEnum, Integer>();
		mapCircles.put(TileEnum.CIRCLE1, 3);
		mapCircles.put(TileEnum.CIRCLE2, 1);
		mapCircles.put(TileEnum.CIRCLE3, 1);
		mapCircles.put(TileEnum.CIRCLE4, 1);
		mapCircles.put(TileEnum.CIRCLE5, 1);
		mapCircles.put(TileEnum.CIRCLE6, 1);
		mapCircles.put(TileEnum.CIRCLE7, 1);
		mapCircles.put(TileEnum.CIRCLE8, 1);
		mapCircles.put(TileEnum.CIRCLE9, 3);
		
		List<Map<TileEnum,Integer>> maps = new ArrayList<Map<TileEnum,Integer>>();
		maps.add(mapCharacters);
		maps.add(mapBamboos);
		maps.add(mapCircles);
		return maps;
		
	}
	
	private boolean validateEachColor(List<MeldElement> melds,Map<TileEnum, Integer> map){
		for(MeldElement meld : melds){
			for(TileEnum tile : meld.getList()){
				Integer i = map.get(tile);
				if(i == null){
					continue;
				}
				map.put(tile,map.get(tile) - 1);
			}
		}

		boolean isNegativeOneAlreadyFound = false;
		for(Map.Entry<TileEnum, Integer> entry : map.entrySet()){
			
			switch(entry.getValue()){
				case 0:
					break;
				case -1:
					if(isNegativeOneAlreadyFound){
						return false;
					}
					isNegativeOneAlreadyFound = true;
					break;
				default :
					return false;
			}
		}
		return true;
	}
	@Override
	public boolean validate(WinningHands winningHands,WinningHandsStatus status){
		if(winningHands.isStolen()){
			return false;
		}
		WinningHandsBasic hands = (WinningHandsBasic)winningHands;
		List<MeldElement> melds = hands.getList();
		
		
		List<Map<TileEnum,Integer>> maps = getHandsDefs();
		for(Map<TileEnum, Integer> map : maps){
			boolean valid = validateEachColor(melds, map);
			if(valid){
				status.isYakuman = true;
				return true;
			}
		}
		

		return false;
	}
}

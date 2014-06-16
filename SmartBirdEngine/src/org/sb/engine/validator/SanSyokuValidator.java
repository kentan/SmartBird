package org.sb.engine.validator;

import java.util.ArrayList;
import java.util.List;

import org.sb.engine.controller.WinningHands;
import org.sb.engine.controller.WinningHandsBasic;
import org.sb.mdl.MeldElement;
import org.sb.mdl.enm.MeldEnum;
import org.sb.mdl.enm.TileEnum;


abstract public class SanSyokuValidator extends WinningHandsValidator {

	abstract public boolean validate(WinningHands winningHands, WinningHandsStatus status);
	private boolean containsSameMeldsKind(MeldEnum target, List<MeldEnum> list) {
		for (MeldEnum meldsEnum : list) {
			if (meldsEnum.equals(target)) {
				return true;
			}
		}
		return false;

	}
	
	protected boolean is3syoku(List<MeldEnum> meldsEnums,WinningHands winningHands, boolean isDokoku) {

		List<MeldElement> meldList = ((WinningHandsBasic)winningHands).getList();
		boolean isOkCharactor = false;
		boolean isOkBamboo = false;
		boolean isOkCircle = false;

		for (int i = 0; i < meldList.size(); i++) {
			isOkCharactor = isOkBamboo = isOkCircle = false;

			if (containsSameMeldsKind(meldList.get(i).getMeldEnum(), meldsEnums)) {
				TileEnum tile = meldList.get(i).getList().get(0);
				if (tile.isCharactor()) {
					isOkCharactor = true;
					for (int j = i + 1; j < meldList.size(); j++) {
						TileEnum tile2 = meldList.get(j).getList().get(0);
						if (containsSameMeldsKind(meldList.get(j).getMeldEnum(), meldsEnums)) {
							if (tile2.equals(tile.getSameInCircle())) {
								isOkCircle = true;
							} else if (tile2.equals(tile.getSameInBamboo())) {
								isOkBamboo = true;
							}

						}
					}
				} else if (tile.isBamboo()) {
					isOkBamboo = true;
					for (int j = i + 1; j < meldList.size(); j++) {
						TileEnum tile2 = meldList.get(j).getList().get(0);

						if (containsSameMeldsKind(meldList.get(j).getMeldEnum(), meldsEnums)) {
							if (tile2.equals(tile.getSameInCircle())) {
								isOkCircle = true;
							} else if (tile2.equals(tile.getSameInCharactor())) {
								isOkCharactor = true;
							}

						}
					}

				} else if (tile.isCircle()) {
					isOkCircle = true;
					for (int j = i + 1; j < meldList.size(); j++) {
						TileEnum tile2 = meldList.get(j).getList().get(0);
						if (containsSameMeldsKind(meldList.get(j).getMeldEnum(), meldsEnums)) {

							if (tile2.equals(tile.getSameInCharactor())) {
								isOkCharactor = true;
							} else if (tile2.equals(tile.getSameInBamboo())) {
								isOkBamboo = true;
							}

						}
					}
				} else {
					continue;// 字牌
				}
				if (isOkCharactor && isOkCircle && isOkBamboo) {
					return true;
				}
			}

		}
		return false;

	};
	protected boolean is3syokuStolen(WinningHands winningHands, boolean isDokoku) {
		List<MeldEnum> meldsEnums= new ArrayList<MeldEnum>();
		if (isDokoku) {
			meldsEnums.add(MeldEnum.STEAL_KONG);
			meldsEnums.add(MeldEnum.STEAL_PONG);
		} else {
			meldsEnums.add(MeldEnum.STEAL_CHOW);
		}

		return is3syoku(meldsEnums, winningHands, isDokoku);
	};
	protected boolean is3syokuWall(WinningHands winningHands, boolean isDokoku) {
		List<MeldEnum> meldsEnums= new ArrayList<MeldEnum>();
		if (isDokoku) {
			meldsEnums.add(MeldEnum.WALL_KONG);
			meldsEnums.add(MeldEnum.WALL_PONG);
		} else {
			meldsEnums.add(MeldEnum.WALL_CHOW);
		}

		return is3syoku(meldsEnums, winningHands, isDokoku);

	};
}

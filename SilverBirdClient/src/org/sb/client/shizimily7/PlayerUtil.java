package org.sb.client.shizimily7;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.sb.mdl.MeldElement;
import org.sb.mdl.enm.TileEnum;

public class PlayerUtil {
	final static TileEnum[] TILE_LIST = {
		TileEnum.CHARACTOR1,
		TileEnum.CHARACTOR2,
		TileEnum.CHARACTOR3,
		TileEnum.CHARACTOR4,
		TileEnum.CHARACTOR5,
		TileEnum.CHARACTOR6,
		TileEnum.CHARACTOR7,
		TileEnum.CHARACTOR8,
		TileEnum.CHARACTOR9,

		TileEnum.BAMBOO1,
		TileEnum.BAMBOO2,
		TileEnum.BAMBOO3,
		TileEnum.BAMBOO4,
		TileEnum.BAMBOO5,
		TileEnum.BAMBOO6,
		TileEnum.BAMBOO7,
		TileEnum.BAMBOO8,
		TileEnum.BAMBOO9,

		TileEnum.CIRCLE1,
		TileEnum.CIRCLE2,
		TileEnum.CIRCLE3,
		TileEnum.CIRCLE4,
		TileEnum.CIRCLE5,
		TileEnum.CIRCLE6,
		TileEnum.CIRCLE7,
		TileEnum.CIRCLE8,
		TileEnum.CIRCLE9,

		TileEnum.EAST,
		TileEnum.SOUTH,
		TileEnum.WEST,
		TileEnum.NORTH,
		TileEnum.WHITE,
		TileEnum.GREEN,
		TileEnum.RED
	};

	private static final Map<Integer, Integer> shantenTable  = new HashMap<Integer, Integer>();

	static {
		// シャンテン数計算用テーブル読み込み
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(PlayerUtil.class.getResourceAsStream("ShantenTable.txt")));
			String line = br.readLine();

			while (true) {
				line = br.readLine();
				if (line == null) {
					break;
				}
				String[] elem = line.split("\t");
				shantenTable.put(Integer.parseInt(elem[0]), Integer.parseInt(elem[1]));
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static List<TileEnum> getLiveTile(List<TileEnum> tiles, TileEnum tileAtThisTurn, List<MeldElement> huroMelds, List<TileEnum> discardedTiles,
			Map<Integer, List<TileEnum>> otherPlayerDiscardedTiles, Map<Integer, List<MeldElement>> otherPlayerHuroTiles) {

		List<TileEnum> list = new ArrayList<TileEnum>();
		for (TileEnum tile : TILE_LIST) {
			for (int i = 0; i < 4; i ++) {
				list.add(tile);
			}
		}

		list.removeAll(tiles);
		list.remove(tileAtThisTurn);
		for (MeldElement meld : huroMelds) {
			list.removeAll(meld.getList());
		}
		list.removeAll(discardedTiles);
		for (List<TileEnum> otherPlayerTiles : otherPlayerDiscardedTiles.values()) {
			list.removeAll(otherPlayerTiles);
		}
		for (List<MeldElement> otherPlayerMelds : otherPlayerHuroTiles.values()) {
			for (MeldElement meld : otherPlayerMelds) {
				list.removeAll(meld.getList());
			}
		}
		return list;
	}

	public static int[] calcShanten(List<TileEnum> tiles) {
		tiles = new ArrayList<TileEnum>(tiles);
		sortTiles(tiles);

		Set<TileEnum> pairs = new HashSet<TileEnum>();
		Set<TileEnum> yaochuSet = new HashSet<TileEnum>();
		Set<TileEnum> tileSet = new HashSet<TileEnum>();
		TileEnum lastTile = null;

		// ペアの処理 雀頭候補、七対子ペアを洗い出す
		for (TileEnum tile : tiles) {
			tileSet.add(tile);
			if (!tile.isTanyao())
				yaochuSet.add(tile);
			if (tile == lastTile) {
				pairs.add(tile);
				lastTile = null;
			} else {
				lastTile = tile;
			}
		}

		int shanten = 14;

		//普通のシャンテン数
		int normalShanten = calcScore(tiles);
		for (TileEnum pair : pairs) {
			List<TileEnum> tmpTiles = new ArrayList<TileEnum>(tiles);
			// 頭を抜く
			tmpTiles.remove(pair);
			tmpTiles.remove(pair);
			normalShanten = Math.min(normalShanten, calcScore(tmpTiles) - 1);
		}
		shanten = Math.min(shanten, normalShanten);

		//国士シャンテン数
		int kokushiShanten = 13 - yaochuSet.size();
		for (TileEnum tile : yaochuSet) {
			if (pairs.contains(tile)) {
				kokushiShanten --;
				break;
			}
		}
		shanten = Math.min(shanten, kokushiShanten);

		//七対子シャンテン数
		int chitoiShanten = 6 - pairs.size() + (7 - Math.min(7, tileSet.size()));
		shanten = Math.min(shanten, chitoiShanten);

		return new int[] {shanten, normalShanten, kokushiShanten, chitoiShanten};
	}


	public static void sortTiles(List<TileEnum> tiles){
		Collections.sort(tiles,new Comparator<TileEnum>(){
			@Override
			public int compare(TileEnum o1, TileEnum o2) {
				return o1.getIndex() - o2.getIndex();
			}
		});
	}

	private static int calcScore(List<TileEnum> tiles) {
		LinkedList<TileEnum> tmpTiles = new LinkedList<TileEnum>();
		int table = 0;
		for (TileEnum tile : tiles) {
			if (tmpTiles.size() > 0 &&
					(tile.isWindAndDragon() && tile.getIndex() != tmpTiles.getLast().getIndex()
					|| tile.getIndex() - tmpTiles.getLast().getIndex() > 2
					|| !isSameType(tile, tmpTiles.getLast()))) {
				table += getTable(tmpTiles);
				tmpTiles.clear();
			}
			tmpTiles.add(tile);
		}
		table += getTable(tmpTiles);

		int a1 = table / 1000;
		int a2 = (table % 1000) / 100;
		int b1 = (table % 100) / 10;
		int b2 = table % 10;

		if (a1 + a2 > 4)
			a2 = 4 - a1;
		if (b1 + b2 > 4)
			b2 = 4 - b1;

		int a = 8 - a1 * 2 - a2;
		int b = 8 - b1 * 2 - b2;
		return Math.min(a, b);
	}

	private static boolean isSameType(TileEnum a, TileEnum b) {
		return  a.isCharactor() && b.isCharactor()
				|| a.isBamboo() && b.isBamboo()
				|| a.isCircle() && b.isCircle()
				|| a.isWindAndDragon() && b.isWindAndDragon();
	}

	private static int getTable(List<TileEnum> tiles) {
		int x = 0;
		int last = 1000;
		for (TileEnum tile : tiles) {
			int diff = tile.getIndex() - last;
			if (diff > 1) {
				x *= 100;
			} else if (diff == 1) {
				x *= 10;
			}
			x ++;
			last = tile.getIndex();
		}
		Integer i = shantenTable.get(x);
		return i == null ? 0 : i;
	}
}

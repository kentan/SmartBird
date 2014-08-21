package org.smartbirdpj.mdl;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.smartbirdpj.mdl.enm.MeldEnum;
import org.smartbirdpj.mdl.enm.TileEnum;
import org.smartbirdpj.mdl.enm.TileSuiteEnum;




public class MeldElement{
        private MeldEnum _meldEnum;
        private TileEnum _stolenTile;
        private List<TileEnum> _list = new ArrayList<TileEnum>();
        private TileSuiteEnum _tileSuite;
        
        public MeldElement(){
        	
        }
        public MeldElement(MeldEnum meld,TileEnum... tile){
                _meldEnum = meld;
                if(tile[0].isCharactor()){
                        _tileSuite = TileSuiteEnum.CHARACTORS;
                }else if(tile[0].isBamboo()){
                        _tileSuite = TileSuiteEnum.BAMBOOS;
                }else if(tile[0].isCircle()){
                        _tileSuite = TileSuiteEnum.CIRCLES;
                }else{
                        _tileSuite = TileSuiteEnum.WINDS_AND_DRAGONS;
                }
 //               _stolenTile = hai[0];
                _list.addAll(Arrays.asList(tile));
        }
        public TileSuiteEnum getTileSuite(){
                return _tileSuite;
        }
        public void setTileSuite(TileSuiteEnum tileSuite){
        	_tileSuite = tileSuite;
        }
        
        public MeldEnum getMeldEnum(){
                return _meldEnum;
        }
        public void setMeldEnum(MeldEnum meldEnum){
            _meldEnum = meldEnum;
        }
        public List<TileEnum> getList(){
                return _list;
        }
        public void setList(List<TileEnum> tiles){
        	_list = tiles;
        }
        public TileEnum getStolenTile(){
        	return _stolenTile;
        }
        public void setStolenTile(TileEnum stolenTile){
        	_stolenTile = stolenTile;
        }
        @Override
        public String toString(){
                StringBuffer sb = new StringBuffer();
                sb.append("[");
                for(TileEnum h :_list){
                        sb.append(h + ",");
                        
                }
                sb.append("]");
                return sb.toString();
        }
}

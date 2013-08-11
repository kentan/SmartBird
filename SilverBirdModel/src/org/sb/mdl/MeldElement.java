package org.sb.mdl;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.sb.mdl.enm.MeldEnum;
import org.sb.mdl.enm.TileEnum;
import org.sb.mdl.enm.TileSuiteEnum;




public class MeldElement{
        private MeldEnum _meldEnum;
        private TileEnum _stolenTile;
        private List<TileEnum> _list = new ArrayList<TileEnum>();
        private TileSuiteEnum _tileSuite;
        
        public MeldElement(MeldEnum meld,TileEnum... hai){
                _meldEnum = meld;
                if(hai[0].isCharactor()){
                        _tileSuite = TileSuiteEnum.CHARACTORS;
                }else if(hai[0].isBamboo()){
                        _tileSuite = TileSuiteEnum.BAMBOOS;
                }else if(hai[0].isCircle()){
                        _tileSuite = TileSuiteEnum.CIRCLES;
                }else{
                        _tileSuite = TileSuiteEnum.WINDS_AND_DRAGONS;
                }
                _stolenTile = hai[0];
                _list.addAll(Arrays.asList(hai));
        }
        public TileSuiteEnum getTileSuite(){
                return _tileSuite;
        }
        public MeldEnum getMeldEnum(){
                return _meldEnum;
        }
        public List<TileEnum> getList(){
                return _list;
        }
        public TileEnum getStolenTile(){
        	return _stolenTile;
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
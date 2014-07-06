package org.sb.engine.controller;

import java.util.ArrayList;
import java.util.List;

import org.sb.mdl.MeldElement;
import org.sb.mdl.enm.WinningFormEnum;


public class WinningHandsBasic extends WinningHands {
        private boolean _isStolen = false;
        private WinningFormEnum _winningHandsFormEnum;
        private boolean _is7Toitsu = false;

        public WinningHandsBasic(){}
        public WinningHandsBasic(boolean isNaki,boolean isTumo,boolean isRichi){
        	super(isNaki,isTumo,isRichi);                

        }
        List<MeldElement> _meldList = new ArrayList<MeldElement>();


        public boolean isMenzen(){
                return !_isStolen;
        }
        
        public void add(MeldElement yaku){
                _meldList.add(yaku);
        }
        public void addAll(List<MeldElement> yakus){
                _meldList.addAll(yakus);
        }
        public MeldElement get(int index){
                return _meldList.get(index);
        }
        
        public List<MeldElement> getList(){
                return _meldList;
        }
        public void setWinningForm(WinningFormEnum winningHandsFormEnum){
                _winningHandsFormEnum = winningHandsFormEnum;
        }
        public WinningFormEnum getWinningFormEnum(){
                return _winningHandsFormEnum;
        }
        public boolean is7Toitsu(){
        	return _is7Toitsu;
        }
        public void set7Toitsu(){
        	_is7Toitsu = true;
        	setWinningForm(WinningFormEnum.TANKI);
        }
        
}
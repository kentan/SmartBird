package org.smartbirdpj.engine.controller;

import java.util.ArrayList;
import java.util.List;

public class WinningHandsList {
        List<WinningHands> _list = new ArrayList<WinningHands>();


        
        public void add(WinningHands winningHands){
                _list.add(winningHands);
        }
        
        public void addAll(List<WinningHands> winningHandsList){
                _list.addAll(winningHandsList);
        }
        public WinningHands get(int index){
                return _list.get(index);
        }
        public List<WinningHands> getList(){
                return _list;
        }       

}

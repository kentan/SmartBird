package org.smartbirdpj.engine.controller;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;


import org.smartbirdpj.mdl.MeldElement;
import org.smartbirdpj.mdl.enm.WinningFormEnum;



public class MeldNode{
	private final static Logger LOGGER = Logger.getLogger(MeldNode.class.getName());
	
	private List<MeldNode> children;
	private MeldElement meld;
	private boolean isEnd = false;
	private WinningFormEnum _winningFormEnum;


	public MeldNode(){
		children = new ArrayList<MeldNode>();
	}
	public void setWinningFormEnum(WinningFormEnum winningFormEnum,boolean isTumo){
		_winningFormEnum = winningFormEnum;
		if(isTumo){
			_winningFormEnum.setTumo();
		}else{
			_winningFormEnum.setRon();
		}
	}
	public WinningFormEnum getWinningFormEnum(){
		if(_winningFormEnum != null){
			return _winningFormEnum;
		}
		for(MeldNode child : children){
			WinningFormEnum form =  child.getWinningFormEnum();
			if(form != null){
				return form;
			}
		}
		
		LOGGER.severe("assertion error in MeldNode#getWinningFormEnum");

		return null;// Never reach at this line. 
	}


	public void addChild(MeldNode meldNode){
		this.children.add(meldNode);
	}
	public void setMeld(MeldElement meld){
		this.meld = meld;
	}

	public void setEnd(Boolean isEnd){
		this.isEnd = isEnd;
	}
	public boolean isEnd(){
		return isEnd;
	}
	public List<MeldNode> getChildren(){
		return this.children;
	}
	public MeldElement getMeld(){
		return meld;
	}
	
	/*for unit test*/	
	public List<WinningHands> transformGraphToList(){
		List<WinningHands> winningHandsList = new ArrayList<WinningHands>();

		WinningHandsBasic winnningHands = new WinningHandsBasic();
		winnningHands.setWinningForm(getWinningFormEnum());

		this.transformGraphToList(winningHandsList,winnningHands);
		return winningHandsList;
	}


	private  void transformGraphToList(List<WinningHands> winningHandsList,WinningHandsBasic winningHands){

		winningHands.add(this.getMeld());
		if(this.isEnd()){
			winningHandsList.add(winningHands);
			return ;
		}
		List<MeldNode> children = this.getChildren();
		if(children.size() == 0){
			winningHandsList.add(winningHands);
			return ;			
		}
		for(MeldNode child: children){

			WinningHandsBasic newWinningHands = new WinningHandsBasic(winningHands.isStolen(),winningHands.isTumo(),winningHands.isRichi());
			newWinningHands.setWinningForm(winningHands.getWinningFormEnum());
			newWinningHands.addAll(winningHands.getList()); //TODO deep copy
			child.transformGraphToList(winningHandsList,newWinningHands);

		}
	}
	
	public void print(){
		print(" ");
	}
	
	private void print(String indent){
		LOGGER.info(indent + this.meld);

		for(MeldNode a : this.children){
			a.print(indent + " ");
		}
		
	}
	// for debugs
	@Override
	public String toString(){
		StringBuffer sb = new StringBuffer();
		sb.append(this.meld);
		for(MeldNode a : this.children){
			sb.append(a);
		}
		return sb.toString();
	}
}

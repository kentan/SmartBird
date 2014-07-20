package org.smartbirdpj.mdl;

import java.util.ArrayList;
import java.util.List;

public class PaidPoint extends Point{
	private int _point1;
	private int _point2;
	private List<Integer> _from1 = new ArrayList<Integer>();
	private List<Integer> _from2 = new ArrayList<Integer>();
	private int _to;
	
	public PaidPoint (int point1){
		super(point1);
		_point1 = point1;
		_point2 = 0;
	}
	public PaidPoint (int point1, int point2){
		super(point1,point2);
		_point1 = point1;
		_point2 = point2;
	}
	public int getPoint1(){
		return _point1;
	}
	public int getPoint2(){
		return _point2;
	}
	
	public void setPayingPlayerIdOnPoint1(int playerId){
		List<Integer> l = getPayingPlayerIdOnPoint1();
		l.add(playerId);
	}
	
	public void setPayingPlayerIdOnPoint1(List<Integer> playerIds){
		_from1 = playerIds;
	}

	public void setPayingPlayerIdOnPoint2(int playerId){
		List<Integer> l = getPayingPlayerIdOnPoint2();
		l.add(playerId);
	}
	
	public void setPayingPlayerIdOnPoint2(List<Integer> playerIds){
		_from2 = playerIds;
	}
	public void setPaidPlayerId(int playerId){
		_to = playerId;
	}
	public List<Integer> getPayingPlayerIdOnPoint1(){
		return _from1;
	}
	public List<Integer> getPayingPlayerIdOnPoint2(){
		return _from2;
	}
	public int getPaidPlayerId(){
		return _to;
	}
}

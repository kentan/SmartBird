package org.smartbirdpj.mdl;

public class Point {
	private int _point1;
	private int _point2;
	
	public Point (int point1){
		_point1 = point1;
		_point2 = 0;
	}
	public Point (int point1, int point2){
		_point1 = point1;
		_point2 = point2;
	}
	public int getPoint1(){
		return _point1;
	}
	public int getPoint2(){
		return _point2;
	}
}

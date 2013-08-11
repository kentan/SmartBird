package org.sb.mdl.bean;

public class PointBean
{
	private int _point1;
	private int _point2;
	
	public PointBean (int point1){
		_point1 = point1;
		_point2 = 0;
	}
	public PointBean (int point1, int point2){
		_point1 = point1;//from Parent
		_point2 = point2;//from Child
	}
	public int getPoint1(){
		return _point1;
	}
	public int getPoint2(){
		return _point2;
	}
}


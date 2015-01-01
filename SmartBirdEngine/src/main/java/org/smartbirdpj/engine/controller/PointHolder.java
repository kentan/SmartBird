package org.smartbirdpj.engine.controller;

import java.util.HashMap;
import java.util.Map;


import org.smartbirdpj.mdl.Point;





public class PointHolder {


	
	static Map<Integer,Map<Integer,Point>> _pointMapTumoChild = new HashMap<Integer, Map<Integer,Point>>();
	static Map<Integer,Map<Integer,Point>> _pointMapTumoParent = new HashMap<Integer, Map<Integer,Point>>();
	static Map<Integer,Map<Integer,Point>> _pointMapRonChild = new HashMap<Integer, Map<Integer,Point>>();
	static Map<Integer,Map<Integer,Point>> _pointMapRonParent = new HashMap<Integer, Map<Integer,Point>>();
	static{
		Map<Integer,Point> _pointMapTumoChild1 = new HashMap<Integer,Point>();
		Map<Integer,Point> _pointMapTumoChild2 = new HashMap<Integer,Point>();
		Map<Integer,Point> _pointMapTumoChild3 = new HashMap<Integer,Point>();		
		Map<Integer,Point> _pointMapTumoChild4 = new HashMap<Integer,Point>();		
		Map<Integer,Point> _pointMapTumoChild5 = new HashMap<Integer,Point>();		
		Map<Integer,Point> _pointMapTumoChild67 = new HashMap<Integer,Point>();
		Map<Integer,Point> _pointMapTumoChild8910 = new HashMap<Integer,Point>();
		Map<Integer,Point> _pointMapTumoChild1112 = new HashMap<Integer,Point>();
		Map<Integer,Point> _pointMapTumoChild13 = new HashMap<Integer,Point>();
		
		_pointMapTumoChild1.put(20, new Point(0,0));
		_pointMapTumoChild1.put(30, new Point(500,300));
		_pointMapTumoChild1.put(40, new Point(700,400));
		_pointMapTumoChild1.put(50, new Point(800,400));
		_pointMapTumoChild1.put(60, new Point(1000,500));
		_pointMapTumoChild1.put(70, new Point(1200,600));
		_pointMapTumoChild1.put(80, new Point(1300,700));
		_pointMapTumoChild1.put(90, new Point(1500,700));
		_pointMapTumoChild1.put(100, new Point(1600,800));

		_pointMapTumoChild2.put(20, new Point(700,400));
		_pointMapTumoChild2.put(25, new Point(800,400));
		_pointMapTumoChild2.put(30, new Point(1000,500));
		_pointMapTumoChild2.put(40, new Point(1300,700));
		_pointMapTumoChild2.put(50, new Point(1600,800));
		_pointMapTumoChild2.put(60, new Point(2000,1000));
		_pointMapTumoChild2.put(70, new Point(2300,1200));
		_pointMapTumoChild2.put(80, new Point(2600,1300));
		_pointMapTumoChild2.put(90, new Point(2900,1500));
		_pointMapTumoChild2.put(100, new Point(3200,1600));		

		_pointMapTumoChild3.put(20, new Point(1300,700));
		_pointMapTumoChild3.put(25, new Point(1600,800));
		_pointMapTumoChild3.put(30, new Point(2000,1000));
		_pointMapTumoChild3.put(40, new Point(2600,1300));
		_pointMapTumoChild3.put(50, new Point(3200,1600));
		_pointMapTumoChild3.put(60, new Point(3900,2000));
		_pointMapTumoChild3.put(70, new Point(4000,2000));
		_pointMapTumoChild3.put(80, new Point(4000,2000));
		_pointMapTumoChild3.put(90, new Point(4000,2000));		

		_pointMapTumoChild4.put(20, new Point(2600,1300));
		_pointMapTumoChild4.put(25, new Point(3200,1600));
		_pointMapTumoChild4.put(30, new Point(3900,2000));
		_pointMapTumoChild4.put(40, new Point(4000,2000));
		_pointMapTumoChild4.put(50, new Point(4000,2000));
		_pointMapTumoChild4.put(60, new Point(4000,2000));
		_pointMapTumoChild4.put(70, new Point(4000,2000));
		_pointMapTumoChild4.put(80, new Point(4000,2000));
		_pointMapTumoChild4.put(90, new Point(4000,2000));
		_pointMapTumoChild4.put(100, new Point(4000,2000));		


		_pointMapTumoChild5.put(20, new Point(4000,2000));
		_pointMapTumoChild5.put(25, new Point(4000,2000));
		_pointMapTumoChild5.put(30, new Point(4000,2000));
		_pointMapTumoChild5.put(40, new Point(4000,2000));
		_pointMapTumoChild5.put(50, new Point(4000,2000));
		_pointMapTumoChild5.put(60, new Point(4000,2000));
		_pointMapTumoChild5.put(70, new Point(4000,2000));
		_pointMapTumoChild5.put(80, new Point(4000,2000));
		_pointMapTumoChild5.put(90, new Point(4000,2000));
		_pointMapTumoChild5.put(100, new Point(4000,2000));		

		_pointMapTumoChild67.put(20, new Point(6000,3000));
		_pointMapTumoChild67.put(25, new Point(6000,3000));
		_pointMapTumoChild67.put(30, new Point(6000,3000));
		_pointMapTumoChild67.put(40, new Point(6000,3000));
		_pointMapTumoChild67.put(50, new Point(6000,3000));
		_pointMapTumoChild67.put(60, new Point(6000,3000));
		_pointMapTumoChild67.put(70, new Point(6000,3000));
		_pointMapTumoChild67.put(80, new Point(6000,3000));
		_pointMapTumoChild67.put(90, new Point(6000,3000));
		_pointMapTumoChild67.put(100, new Point(6000,3000));		

		_pointMapTumoChild8910.put(20, new Point(8000,4000));
		_pointMapTumoChild8910.put(25, new Point(8000,4000));
		_pointMapTumoChild8910.put(30, new Point(8000,4000));
		_pointMapTumoChild8910.put(40, new Point(8000,4000));
		_pointMapTumoChild8910.put(50, new Point(8000,4000));
		_pointMapTumoChild8910.put(60, new Point(8000,4000));
		_pointMapTumoChild8910.put(70, new Point(8000,4000));
		_pointMapTumoChild8910.put(80, new Point(8000,4000));
		_pointMapTumoChild8910.put(90, new Point(8000,4000));
		_pointMapTumoChild8910.put(100, new Point(8000,4000));		


		_pointMapTumoChild1112.put(20, new Point(12000,6000));
		_pointMapTumoChild1112.put(25, new Point(12000,6000));
		_pointMapTumoChild1112.put(30, new Point(12000,6000));
		_pointMapTumoChild1112.put(40, new Point(12000,6000));
		_pointMapTumoChild1112.put(50, new Point(12000,6000));
		_pointMapTumoChild1112.put(60, new Point(12000,6000));
		_pointMapTumoChild1112.put(70, new Point(12000,6000));
		_pointMapTumoChild1112.put(80, new Point(12000,6000));
		_pointMapTumoChild1112.put(90, new Point(12000,6000));
		_pointMapTumoChild1112.put(100, new Point(12000,6000));	

		_pointMapTumoChild13.put(20, new Point(16000,8000));
		_pointMapTumoChild13.put(25, new Point(16000,8000));
		_pointMapTumoChild13.put(30, new Point(16000,8000));
		_pointMapTumoChild13.put(40, new Point(16000,8000));
		_pointMapTumoChild13.put(50, new Point(16000,8000));
		_pointMapTumoChild13.put(60, new Point(16000,8000));
		_pointMapTumoChild13.put(70, new Point(16000,8000));
		_pointMapTumoChild13.put(80, new Point(16000,8000));
		_pointMapTumoChild13.put(90, new Point(16000,8000));
		_pointMapTumoChild13.put(100, new Point(16000,8000));	
		
		_pointMapTumoChild.put(1,_pointMapTumoChild1);
		_pointMapTumoChild.put(2,_pointMapTumoChild2);
		_pointMapTumoChild.put(3,_pointMapTumoChild3);
		_pointMapTumoChild.put(4,_pointMapTumoChild4);
		_pointMapTumoChild.put(5,_pointMapTumoChild5);
		_pointMapTumoChild.put(6,_pointMapTumoChild67);
		_pointMapTumoChild.put(7,_pointMapTumoChild67);
		_pointMapTumoChild.put(8,_pointMapTumoChild8910);
		_pointMapTumoChild.put(9,_pointMapTumoChild8910);
		_pointMapTumoChild.put(10,_pointMapTumoChild8910);
		_pointMapTumoChild.put(11,_pointMapTumoChild1112);
		_pointMapTumoChild.put(12,_pointMapTumoChild1112);
		_pointMapTumoChild.put(13,_pointMapTumoChild13);

	    
	}
	static{
		Map<Integer,Point> _pointMapTumoParent1 = new HashMap<Integer,Point>();
		Map<Integer,Point> _pointMapTumoParent2 = new HashMap<Integer,Point>();
		Map<Integer,Point> _pointMapTumoParent3 = new HashMap<Integer,Point>();		
		Map<Integer,Point> _pointMapTumoParent4 = new HashMap<Integer,Point>();		
		Map<Integer,Point> _pointMapTumoParent5 = new HashMap<Integer,Point>();		
		Map<Integer,Point> _pointMapTumoParent67 = new HashMap<Integer,Point>();
		Map<Integer,Point> _pointMapTumoParent8910 = new HashMap<Integer,Point>();
		Map<Integer,Point> _pointMapTumoParent1112 = new HashMap<Integer,Point>();
		Map<Integer,Point> _pointMapTumoParent13 = new HashMap<Integer,Point>();
		
		_pointMapTumoParent1.put(20, new Point(0));
		_pointMapTumoParent1.put(30, new Point(500));
		_pointMapTumoParent1.put(40, new Point(700));
		_pointMapTumoParent1.put(50, new Point(800));
		_pointMapTumoParent1.put(60, new Point(1000));
		_pointMapTumoParent1.put(70, new Point(1200));
		_pointMapTumoParent1.put(80, new Point(1300));
		_pointMapTumoParent1.put(90, new Point(1500));
		_pointMapTumoParent1.put(100, new Point(1600));

		_pointMapTumoParent2.put(20, new Point(700));
		_pointMapTumoParent2.put(25, new Point(800));
		_pointMapTumoParent2.put(30, new Point(1000));
		_pointMapTumoParent2.put(40, new Point(1300));
		_pointMapTumoParent2.put(50, new Point(1600));
		_pointMapTumoParent2.put(60, new Point(2000));
		_pointMapTumoParent2.put(70, new Point(2300));
		_pointMapTumoParent2.put(80, new Point(2600));
		_pointMapTumoParent2.put(90, new Point(2900));		
		_pointMapTumoParent2.put(100, new Point(3200));		
		
		_pointMapTumoParent3.put(20, new Point(1300));
		_pointMapTumoParent3.put(25, new Point(1600));
		_pointMapTumoParent3.put(30, new Point(2000));
		_pointMapTumoParent3.put(40, new Point(2600));
		_pointMapTumoParent3.put(50, new Point(3200));
		_pointMapTumoParent3.put(60, new Point(3900));
		_pointMapTumoParent3.put(70, new Point(4000));
		_pointMapTumoParent3.put(80, new Point(4000));
		_pointMapTumoParent3.put(90, new Point(4000));		
		_pointMapTumoParent3.put(100, new Point(4000));		
		
		_pointMapTumoParent4.put(20, new Point(2600));
		_pointMapTumoParent4.put(25, new Point(3200));
		_pointMapTumoParent4.put(30, new Point(3900));
		_pointMapTumoParent4.put(40, new Point(4000));
		_pointMapTumoParent4.put(50, new Point(4000));
		_pointMapTumoParent4.put(60, new Point(4000));
		_pointMapTumoParent4.put(70, new Point(4000));
		_pointMapTumoParent4.put(80, new Point(4000));
		_pointMapTumoParent4.put(90, new Point(4000));
		_pointMapTumoParent4.put(100, new Point(4000));		


		_pointMapTumoParent5.put(20, new Point(4000));
		_pointMapTumoParent5.put(25, new Point(4000));
		_pointMapTumoParent5.put(30, new Point(4000));
		_pointMapTumoParent5.put(40, new Point(4000));
		_pointMapTumoParent5.put(50, new Point(4000));
		_pointMapTumoParent5.put(60, new Point(4000));
		_pointMapTumoParent5.put(70, new Point(4000));
		_pointMapTumoParent5.put(80, new Point(4000));
		_pointMapTumoParent5.put(90, new Point(4000));
		_pointMapTumoParent5.put(100, new Point(4000));		

		_pointMapTumoParent67.put(20, new Point(6000));
		_pointMapTumoParent67.put(25, new Point(6000));
		_pointMapTumoParent67.put(30, new Point(6000));
		_pointMapTumoParent67.put(40, new Point(6000));
		_pointMapTumoParent67.put(50, new Point(6000));
		_pointMapTumoParent67.put(60, new Point(6000));
		_pointMapTumoParent67.put(70, new Point(6000));
		_pointMapTumoParent67.put(80, new Point(6000));
		_pointMapTumoParent67.put(90, new Point(6000));
		_pointMapTumoParent67.put(100, new Point(6000));		

		_pointMapTumoParent8910.put(20, new Point(8000));
		_pointMapTumoParent8910.put(25, new Point(8000));
		_pointMapTumoParent8910.put(30, new Point(8000));
		_pointMapTumoParent8910.put(40, new Point(8000));
		_pointMapTumoParent8910.put(50, new Point(8000));
		_pointMapTumoParent8910.put(60, new Point(8000));
		_pointMapTumoParent8910.put(70, new Point(8000));
		_pointMapTumoParent8910.put(80, new Point(8000));
		_pointMapTumoParent8910.put(90, new Point(8000));
		_pointMapTumoParent8910.put(100, new Point(8000));		


		_pointMapTumoParent1112.put(20, new Point(12000));
		_pointMapTumoParent1112.put(25, new Point(12000));
		_pointMapTumoParent1112.put(30, new Point(12000));
		_pointMapTumoParent1112.put(40, new Point(12000));
		_pointMapTumoParent1112.put(50, new Point(12000));
		_pointMapTumoParent1112.put(60, new Point(12000));
		_pointMapTumoParent1112.put(70, new Point(12000));
		_pointMapTumoParent1112.put(80, new Point(12000));
		_pointMapTumoParent1112.put(90, new Point(12000));
		_pointMapTumoParent1112.put(100, new Point(12000));	

		_pointMapTumoParent13.put(20, new Point(16000));
		_pointMapTumoParent13.put(25, new Point(16000));
		_pointMapTumoParent13.put(30, new Point(16000));
		_pointMapTumoParent13.put(40, new Point(16000));
		_pointMapTumoParent13.put(50, new Point(16000));
		_pointMapTumoParent13.put(60, new Point(16000));
		_pointMapTumoParent13.put(70, new Point(16000));
		_pointMapTumoParent13.put(80, new Point(16000));
		_pointMapTumoParent13.put(90, new Point(16000));
		_pointMapTumoParent13.put(100, new Point(16000));	

		
		_pointMapTumoParent.put(1,_pointMapTumoParent1);
		_pointMapTumoParent.put(2,_pointMapTumoParent2);
		_pointMapTumoParent.put(3,_pointMapTumoParent3);
		_pointMapTumoParent.put(4,_pointMapTumoParent4);
		_pointMapTumoParent.put(5,_pointMapTumoParent5);
		_pointMapTumoParent.put(6,_pointMapTumoParent67);
		_pointMapTumoParent.put(7,_pointMapTumoParent67);
		_pointMapTumoParent.put(8,_pointMapTumoParent8910);
		_pointMapTumoParent.put(9,_pointMapTumoParent8910);
		_pointMapTumoParent.put(10,_pointMapTumoParent8910);
		_pointMapTumoParent.put(11,_pointMapTumoParent1112);
		_pointMapTumoParent.put(12,_pointMapTumoParent1112);
		_pointMapTumoParent.put(13,_pointMapTumoParent13);
	}
	
	static{
		Map<Integer,Point> _pointMapRonChild1 = new HashMap<Integer,Point>();
		Map<Integer,Point> _pointMapRonChild2 = new HashMap<Integer,Point>();
		Map<Integer,Point> _pointMapRonChild3 = new HashMap<Integer,Point>();		
		Map<Integer,Point> _pointMapRonChild4 = new HashMap<Integer,Point>();		
		Map<Integer,Point> _pointMapRonChild5 = new HashMap<Integer,Point>();		
		Map<Integer,Point> _pointMapRonChild67 = new HashMap<Integer,Point>();
		Map<Integer,Point> _pointMapRonChild8910 = new HashMap<Integer,Point>();
		Map<Integer,Point> _pointMapRonChild1112 = new HashMap<Integer,Point>();
		Map<Integer,Point> _pointMapRonChild13 = new HashMap<Integer,Point>();
		
		_pointMapRonChild1.put(20, new Point(0));
		_pointMapRonChild1.put(30, new Point(1000));
		_pointMapRonChild1.put(40, new Point(1300));
		_pointMapRonChild1.put(50, new Point(1600));
		_pointMapRonChild1.put(60, new Point(2000));
		_pointMapRonChild1.put(70, new Point(2300));
		_pointMapRonChild1.put(80, new Point(2600));
		_pointMapRonChild1.put(90, new Point(2900));
		_pointMapRonChild1.put(100, new Point(3200));

		_pointMapRonChild2.put(20, new Point(1300));
		_pointMapRonChild2.put(25, new Point(1600));
		_pointMapRonChild2.put(30, new Point(2000));
		_pointMapRonChild2.put(40, new Point(2600));
		_pointMapRonChild2.put(50, new Point(3200));
		_pointMapRonChild2.put(60, new Point(3900));
		_pointMapRonChild2.put(70, new Point(4500));
		_pointMapRonChild2.put(80, new Point(5200));
		_pointMapRonChild2.put(90, new Point(5800));		
		_pointMapRonChild2.put(100, new Point(6400));		
		
		_pointMapRonChild3.put(20, new Point(2600));
		_pointMapRonChild3.put(25, new Point(3200));
		_pointMapRonChild3.put(30, new Point(3900));
		_pointMapRonChild3.put(40, new Point(5200));
		_pointMapRonChild3.put(50, new Point(6400));
		_pointMapRonChild3.put(60, new Point(7700));
		_pointMapRonChild3.put(70, new Point(8000));
		_pointMapRonChild3.put(80, new Point(8000));
		_pointMapRonChild3.put(90, new Point(8000));		
		_pointMapRonChild3.put(100, new Point(8000));		
		
		_pointMapRonChild4.put(20, new Point(5200));
		_pointMapRonChild4.put(25, new Point(6400));
		_pointMapRonChild4.put(30, new Point(7700));
		_pointMapRonChild4.put(40, new Point(8000));
		_pointMapRonChild4.put(50, new Point(8000));
		_pointMapRonChild4.put(60, new Point(8000));
		_pointMapRonChild4.put(70, new Point(8000));
		_pointMapRonChild4.put(80, new Point(8000));
		_pointMapRonChild4.put(90, new Point(8000));
		_pointMapRonChild4.put(100, new Point(8000));		


		_pointMapRonChild5.put(20, new Point(8000));
		_pointMapRonChild5.put(25, new Point(8000));
		_pointMapRonChild5.put(30, new Point(8000));
		_pointMapRonChild5.put(40, new Point(8000));
		_pointMapRonChild5.put(50, new Point(8000));
		_pointMapRonChild5.put(60, new Point(8000));
		_pointMapRonChild5.put(70, new Point(8000));
		_pointMapRonChild5.put(80, new Point(8000));
		_pointMapRonChild5.put(90, new Point(8000));
		_pointMapRonChild5.put(100, new Point(8000));		

		_pointMapRonChild67.put(20, new Point(12000));
		_pointMapRonChild67.put(25, new Point(12000));
		_pointMapRonChild67.put(30, new Point(12000));
		_pointMapRonChild67.put(40, new Point(12000));
		_pointMapRonChild67.put(50, new Point(12000));
		_pointMapRonChild67.put(60, new Point(12000));
		_pointMapRonChild67.put(70, new Point(12000));
		_pointMapRonChild67.put(80, new Point(12000));
		_pointMapRonChild67.put(90, new Point(12000));
		_pointMapRonChild67.put(100, new Point(12000));		

		_pointMapRonChild8910.put(20, new Point(16000));
		_pointMapRonChild8910.put(25, new Point(16000));
		_pointMapRonChild8910.put(30, new Point(16000));
		_pointMapRonChild8910.put(40, new Point(16000));
		_pointMapRonChild8910.put(50, new Point(16000));
		_pointMapRonChild8910.put(60, new Point(16000));
		_pointMapRonChild8910.put(70, new Point(16000));
		_pointMapRonChild8910.put(80, new Point(16000));
		_pointMapRonChild8910.put(90, new Point(16000));
		_pointMapRonChild8910.put(100, new Point(16000));		


		_pointMapRonChild1112.put(20, new Point(24000));
		_pointMapRonChild1112.put(25, new Point(24000));
		_pointMapRonChild1112.put(30, new Point(24000));
		_pointMapRonChild1112.put(40, new Point(24000));
		_pointMapRonChild1112.put(50, new Point(24000));
		_pointMapRonChild1112.put(60, new Point(24000));
		_pointMapRonChild1112.put(70, new Point(24000));
		_pointMapRonChild1112.put(80, new Point(24000));
		_pointMapRonChild1112.put(90, new Point(24000));
		_pointMapRonChild1112.put(100, new Point(24000));	

		_pointMapRonChild13.put(20, new Point(32000));
		_pointMapRonChild13.put(25, new Point(32000));
		_pointMapRonChild13.put(30, new Point(32000));
		_pointMapRonChild13.put(40, new Point(32000));
		_pointMapRonChild13.put(50, new Point(32000));
		_pointMapRonChild13.put(60, new Point(32000));
		_pointMapRonChild13.put(70, new Point(32000));
		_pointMapRonChild13.put(80, new Point(32000));
		_pointMapRonChild13.put(90, new Point(32000));
		_pointMapRonChild13.put(100, new Point(32000));	
		
		_pointMapRonChild.put(1,_pointMapRonChild1);
		_pointMapRonChild.put(2,_pointMapRonChild2);
		_pointMapRonChild.put(3,_pointMapRonChild3);
		_pointMapRonChild.put(4,_pointMapRonChild4);
		_pointMapRonChild.put(5,_pointMapRonChild5);
		_pointMapRonChild.put(6,_pointMapRonChild67);
		_pointMapRonChild.put(7,_pointMapRonChild67);
		_pointMapRonChild.put(8,_pointMapRonChild8910);
		_pointMapRonChild.put(9,_pointMapRonChild8910);
		_pointMapRonChild.put(10,_pointMapRonChild8910);
		_pointMapRonChild.put(11,_pointMapRonChild1112);
		_pointMapRonChild.put(12,_pointMapRonChild1112);
		_pointMapRonChild.put(13,_pointMapRonChild13);
	}

	static{
		Map<Integer,Point> _pointMapRonParent1 = new HashMap<Integer,Point>();
		Map<Integer,Point> _pointMapRonParent2 = new HashMap<Integer,Point>();
		Map<Integer,Point> _pointMapRonParent3 = new HashMap<Integer,Point>();		
		Map<Integer,Point> _pointMapRonParent4 = new HashMap<Integer,Point>();		
		Map<Integer,Point> _pointMapRonParent5 = new HashMap<Integer,Point>();		
		Map<Integer,Point> _pointMapRonParent67 = new HashMap<Integer,Point>();
		Map<Integer,Point> _pointMapRonParent8910 = new HashMap<Integer,Point>();
		Map<Integer,Point> _pointMapRonParent1112 = new HashMap<Integer,Point>();
		Map<Integer,Point> _pointMapRonParent13 = new HashMap<Integer,Point>();
		
		_pointMapRonParent1.put(20, new Point(0));
		_pointMapRonParent1.put(30, new Point(1500));
		_pointMapRonParent1.put(40, new Point(2000));
		_pointMapRonParent1.put(50, new Point(2400));
		_pointMapRonParent1.put(60, new Point(2900));
		_pointMapRonParent1.put(70, new Point(3400));
		_pointMapRonParent1.put(80, new Point(3900));
		_pointMapRonParent1.put(90, new Point(4400));
		_pointMapRonParent1.put(100, new Point(4800));

		_pointMapRonParent2.put(20, new Point(2000));
		_pointMapRonParent2.put(25, new Point(2400));
		_pointMapRonParent2.put(30, new Point(2900));
		_pointMapRonParent2.put(40, new Point(3900));
		_pointMapRonParent2.put(50, new Point(4800));
		_pointMapRonParent2.put(60, new Point(5800));
		_pointMapRonParent2.put(70, new Point(6800));
		_pointMapRonParent2.put(80, new Point(7700));
		_pointMapRonParent2.put(90, new Point(7600));		
		_pointMapRonParent2.put(100, new Point(9600));		
		
		_pointMapRonParent3.put(20, new Point(3900));
		_pointMapRonParent3.put(25, new Point(4800));
		_pointMapRonParent3.put(30, new Point(5800));
		_pointMapRonParent3.put(40, new Point(7700));
		_pointMapRonParent3.put(50, new Point(9600));
		_pointMapRonParent3.put(60, new Point(11600));
		_pointMapRonParent3.put(70, new Point(12000));
		_pointMapRonParent3.put(80, new Point(12000));
		_pointMapRonParent3.put(90, new Point(12000));		
		_pointMapRonParent3.put(100, new Point(12000));		
		
		_pointMapRonParent4.put(20, new Point(7700));
		_pointMapRonParent4.put(25, new Point(9600));
		_pointMapRonParent4.put(30, new Point(11600));
		_pointMapRonParent4.put(40, new Point(12000));
		_pointMapRonParent4.put(50, new Point(12000));
		_pointMapRonParent4.put(60, new Point(12000));
		_pointMapRonParent4.put(70, new Point(12000));
		_pointMapRonParent4.put(80, new Point(12000));
		_pointMapRonParent4.put(90, new Point(12000));
		_pointMapRonParent4.put(100, new Point(12000));		


		_pointMapRonParent5.put(20, new Point(12000));
		_pointMapRonParent5.put(25, new Point(12000));
		_pointMapRonParent5.put(30, new Point(12000));
		_pointMapRonParent5.put(40, new Point(12000));
		_pointMapRonParent5.put(50, new Point(12000));
		_pointMapRonParent5.put(60, new Point(12000));
		_pointMapRonParent5.put(70, new Point(12000));
		_pointMapRonParent5.put(80, new Point(12000));
		_pointMapRonParent5.put(90, new Point(12000));
		_pointMapRonParent5.put(100, new Point(12000));		

		_pointMapRonParent67.put(20, new Point(18000));
		_pointMapRonParent67.put(25, new Point(18000));
		_pointMapRonParent67.put(30, new Point(18000));
		_pointMapRonParent67.put(40, new Point(18000));
		_pointMapRonParent67.put(50, new Point(18000));
		_pointMapRonParent67.put(60, new Point(18000));
		_pointMapRonParent67.put(70, new Point(18000));
		_pointMapRonParent67.put(80, new Point(18000));
		_pointMapRonParent67.put(90, new Point(18000));
		_pointMapRonParent67.put(100, new Point(18000));		

		_pointMapRonParent8910.put(20, new Point(24000));
		_pointMapRonParent8910.put(25, new Point(24000));
		_pointMapRonParent8910.put(30, new Point(24000));
		_pointMapRonParent8910.put(40, new Point(24000));
		_pointMapRonParent8910.put(50, new Point(24000));
		_pointMapRonParent8910.put(60, new Point(24000));
		_pointMapRonParent8910.put(70, new Point(24000));
		_pointMapRonParent8910.put(80, new Point(24000));
		_pointMapRonParent8910.put(90, new Point(24000));
		_pointMapRonParent8910.put(100, new Point(24000));		


		_pointMapRonParent1112.put(20, new Point(32000));
		_pointMapRonParent1112.put(25, new Point(32000));
		_pointMapRonParent1112.put(30, new Point(32000));
		_pointMapRonParent1112.put(40, new Point(32000));
		_pointMapRonParent1112.put(50, new Point(32000));
		_pointMapRonParent1112.put(60, new Point(32000));
		_pointMapRonParent1112.put(70, new Point(32000));
		_pointMapRonParent1112.put(80, new Point(32000));
		_pointMapRonParent1112.put(90, new Point(32000));
		_pointMapRonParent1112.put(100, new Point(32000));	

		_pointMapRonParent13.put(20, new Point(48000));
		_pointMapRonParent13.put(25, new Point(48000));
		_pointMapRonParent13.put(30, new Point(48000));
		_pointMapRonParent13.put(40, new Point(48000));
		_pointMapRonParent13.put(50, new Point(48000));
		_pointMapRonParent13.put(60, new Point(48000));
		_pointMapRonParent13.put(70, new Point(48000));
		_pointMapRonParent13.put(80, new Point(48000));
		_pointMapRonParent13.put(90, new Point(48000));
		_pointMapRonParent13.put(100, new Point(48000));	
		
		_pointMapRonParent.put(1,_pointMapRonParent1);
		_pointMapRonParent.put(2,_pointMapRonParent2);
		_pointMapRonParent.put(3,_pointMapRonParent3);
		_pointMapRonParent.put(4,_pointMapRonParent4);
		_pointMapRonParent.put(5,_pointMapRonParent5);
		_pointMapRonParent.put(6,_pointMapRonParent67);
		_pointMapRonParent.put(7,_pointMapRonParent67);
		_pointMapRonParent.put(8,_pointMapRonParent8910);
		_pointMapRonParent.put(9,_pointMapRonParent8910);
		_pointMapRonParent.put(10,_pointMapRonParent8910);
		_pointMapRonParent.put(11,_pointMapRonParent1112);
		_pointMapRonParent.put(12,_pointMapRonParent1112);
		_pointMapRonParent.put(13,_pointMapRonParent13);
	}

	private final static int HU_MAX= 100;
	private final static int HAN_MAX= 13;

	public Point getPointOnTumoParent(int han,int hu){
		return getPoint(_pointMapTumoParent,han,hu);

	}

	public Point getPointOnTumoChild(int han,int hu){
		return getPoint(_pointMapTumoChild,han,hu);

	}

	public Point getPointOnRonParent(int han,int hu){
		return getPoint(_pointMapRonParent,han,hu);

	}

	public Point getPointOnRonChild(int han,int hu){
		return getPoint(_pointMapRonChild,han,hu);

	}

	public Point getPoint(Map<Integer,Map<Integer,Point>> map,int han,int hu){
		Point rv = null;
		if(han >= HAN_MAX) {
			han = HAN_MAX;
			hu = HU_MAX;
		}

		try{
			rv = map.get(han).get(hu);
		}catch(NullPointerException e){
			rv = null;
		}
		rv.setHan(han);
		rv.setHu(hu);
		return rv;
	}
}


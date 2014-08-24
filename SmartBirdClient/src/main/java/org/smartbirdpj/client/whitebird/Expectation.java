package org.smartbirdpj.client.whitebird;

import org.smartbirdpj.mdl.Point;
import org.smartbirdpj.mdl.enm.TileEnum;

public class Expectation {

	private int distance;
	private TileEnum candidateToDiscard;
	private Point point;
	
	public void setDistance(int distance){
		this.distance = distance;
	}
	
	public void setPoint(Point point){
		this.point = point;
	}
	public void setCandidateToDiscard(TileEnum candidate){
		this.candidateToDiscard = candidate;
	}
	
	public int getDistance(){
		return this.distance;
	}
	public TileEnum getCandidateToDiscard(){
		return this.candidateToDiscard;
	}
	public Point getPoint(){
		return this.point;
	}
	
}

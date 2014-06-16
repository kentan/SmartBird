package org.sb.client.developing;



import org.sb.mdl.MeldElement;

public class MeldCandidateElement extends MeldElement {
	private MeldCandidateEnum _meldCandidateEnum;
	private int _pos = 0;
	private boolean _isCandidate;
	public void setMeldCandidateType(MeldCandidateEnum meldCandidateEnum){
		_meldCandidateEnum = meldCandidateEnum;
	}
	public MeldCandidateEnum getMeldCandidateType(){
		return _meldCandidateEnum;
	}
	public int getPosition(){
		return _pos;
	}
	public void setPosition(int pos){
		_pos = pos;
	}
	public boolean isCandidate(){
		return _isCandidate;
	}
	public void setCandidate(){
		_isCandidate = true;
	}
	public void setComplete(){
		_isCandidate = false;
	}
}

package org.smartbirdpj.client.developing;

import java.util.ArrayList;
import java.util.List;

public class MeldCandidate{
	private List<MeldCandidateElement> _meldCandidates;
	
	public MeldCandidate(){
		_meldCandidates = new ArrayList<MeldCandidateElement>();
	}
	public void add(MeldCandidateElement elem){
		_meldCandidates.add(elem);
	}
	public List<MeldCandidateElement> getList(){
		return _meldCandidates;
	}
}


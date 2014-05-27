package org.smartbird.dao;

import org.smartbird.exception.SBException;
import org.smartbird.message.SBMessage;

public abstract class SBMessageDao {

	abstract  public boolean isEmpty(String id);
	public  void init(String id){};
	abstract public void writeMessage(String id,SBMessage message);
	abstract public SBMessage loadNextMessage(String id);
}

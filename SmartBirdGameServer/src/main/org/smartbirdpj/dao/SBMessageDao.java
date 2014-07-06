package org.smartbirdpj.dao;

import org.smartbirdpj.exception.SBException;
import org.smartbirdpj.message.SBMessage;

public abstract class SBMessageDao {

	abstract  public boolean isEmpty(String id);
	public  void init(String id){};
	abstract public void writeMessage(String id,SBMessage message);
	abstract public SBMessage loadNextMessage(String id);
}

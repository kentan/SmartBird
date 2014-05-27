package org.smartbird.dao;

public class SBMessageDaoFactory {

	private SBMessageDaoFactory(){};
	private static SBMessageDaoFactory _instance;
	public static SBMessageDaoFactory getInstance(){
		if(_instance == null){
			_instance = new SBMessageDaoFactory();
		}
		return _instance;
	}
	public SBMessageDao createDao(String daoClassName){
		// Switch the return instance based on daoClassName
		return new SBMessageFileDao();
	}
}

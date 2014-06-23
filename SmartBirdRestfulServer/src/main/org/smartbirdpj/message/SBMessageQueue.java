package org.smartbirdpj.message;
//package org.smartbird.message;
//
//
//import org.smartbird.dao.SBMessageDao;
//import org.smartbird.dao.SBMessageDaoFactory;
//
//public class SBMessageQueue {
//	public static void putMessage(SBMessage message){
//
//		SBMessageDao dao = SBMessageDaoFactory.getInstance().createDao("");
//		dao.writeMessage(message);
//
//	}
//	
//	public static SBMessage getMessage(){
//
//		SBMessageDao dao = SBMessageDaoFactory.getInstance().createDao("");
//		SBMessage message = dao.loadNextMessage();
//		
//		return message;
//	}
//}

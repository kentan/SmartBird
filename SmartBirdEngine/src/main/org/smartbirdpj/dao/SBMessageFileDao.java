package org.smartbirdpj.dao;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


import org.smartbirdpj.message.SBMessage;


public class SBMessageFileDao extends SBMessageDao {
	private static final String FILE_NAME_PREFIX = "sbmessage";
	private static final String FILE_DIRECTORY = "message";
	private static Map<String,Long> tailNumber = new ConcurrentHashMap<String, Long>();
	private static Map<String,Long> headNumber = new ConcurrentHashMap<String, Long>();


	public void init(String id){
		File dir = new File(FILE_DIRECTORY + "/" + id);
		if(dir.exists()){
			File[] files=dir.listFiles();
			for(int i=0; i<files.length; i++){
				files[i].delete();
			}
			dir.delete();		
		}
		dir.mkdirs();
		tailNumber.put(id, 0l);
		headNumber.put(id, 0l);
	}
//	public SBMessageFileDao(String id) {
//		this.id = id;		
//	}

	@Override
	public void writeMessage(String id,SBMessage message) {

		// TODO Auto-generated method stub
		FileOutputStream outFile = null;
		ObjectOutputStream outObject = null;
		// TOOD error handling
		if(tailNumber.get(id) == null){
			tailNumber.put(id, 0l);
		}
		
		String fileName = FILE_DIRECTORY + "/" + id + "/" + FILE_NAME_PREFIX + "_" + tailNumber.get(id);
		try {
			outFile = new FileOutputStream(fileName);
			outObject = new ObjectOutputStream(outFile);
			outObject.writeObject(message);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				outObject.close();
				outFile.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		incrTailNumber(id);

	}

	@Override
	public SBMessage loadNextMessage(String id) {
		System.out.println("start:" + headNumber);
		// TODO Auto-generated method stub
		FileInputStream inFile = null;
		ObjectInputStream inObject = null;
		SBMessage message = new SBMessage();
		String fileName = FILE_DIRECTORY + "/" + id + "/" + FILE_NAME_PREFIX + "_" + headNumber.get(id);
		try {
			inFile = new FileInputStream(fileName);

			inObject = new ObjectInputStream(inFile);

			message = (SBMessage) inObject.readObject();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(inObject != null){
					inObject.close();
				}
				if(inFile != null){
					inFile.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		try{
			File file = new File(fileName);
			file.delete();
			incrHeadNumber(id);
		}catch(Exception e){
			e.printStackTrace();
		}
		return message;
	}
	@Override
	public boolean isEmpty(String id) {
		// TODO Auto-generated method stub
		File dir = new File(id);
		String[] list = dir.list();
		if(list.length == 0 ) return true;
		
		return false;
	}
	
	private static void incrHeadNumber(String id){
		headNumber.put(id, headNumber.get(id) + 1);
	}
	private static void incrTailNumber(String id){
		tailNumber.put(id, tailNumber.get(id) + 1);
	}


}

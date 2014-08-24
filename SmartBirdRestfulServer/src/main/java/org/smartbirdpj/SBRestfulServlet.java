package org.smartbirdpj;


import java.io.File;
import java.util.logging.Logger;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


import org.smartbirdpj.test.SBTestMessageGenerator;
import org.smartbirdpj.util.SBUtil;
import org.smartbirdpj.dao.SBMessageDaoFactory;
import org.smartbirdpj.log.LoggerFactory;
import org.smartbirdpj.message.SBMessage;
import org.smartbirdpj.server.GameServer;

@Path("endpoint")
public class SBRestfulServlet {
	private GameServer _gameServer = null;
	private final static Logger LOGGER = LoggerFactory.getLogger();
    private final static String CLASS_NAME = SBRestfulServlet.class.getCanonicalName();
    private final static String CLIENT_ID_DEBUG = "debugMessage";
	@GET
	@Path("start/{clientId}")
	@Produces(MediaType.TEXT_PLAIN)
	public String start(@PathParam("clientId") String clientId){
		final String METHOD_NAME = "start";
		try{
			LOGGER.info("SBRestfulServert#start has started: clientId = " + clientId);

	        SBMessageDaoFactory.getInstance().createDao("").init(clientId);
	        if(CLIENT_ID_DEBUG.equals(clientId)){
	        	SBTestMessageGenerator gen = new SBTestMessageGenerator();
	        	gen.before();
	        	gen.generateCase1();
	        }else{
				_gameServer = new GameServer();
		        _gameServer.init(0,clientId);
		
		        _gameServer.start();		
	        }
		}catch(Throwable t){
			SBUtil.logThrowable(LOGGER,t);
		}finally{
			LOGGER.exiting(CLASS_NAME, METHOD_NAME);
		}

		return (new File("")).getAbsolutePath();
	}
	
	
	
	@GET
	@Path("next/{clientId}")
	@Produces(MediaType.TEXT_PLAIN)
	public String next(@PathParam("clientId") String clientId){
		final String METHOD_NAME = "next";
		SBMessage message = null;
		try{
			LOGGER.info("SBRestfulServert#start has started: clientId = " + clientId);

			message = SBMessageDaoFactory.getInstance().createDao("").loadNextMessage(clientId);
		}catch(Throwable t){
			SBUtil.logThrowable(LOGGER,t);
		}finally{
			LOGGER.exiting(CLASS_NAME, METHOD_NAME);
		}
		return message.toJson();
	}

}


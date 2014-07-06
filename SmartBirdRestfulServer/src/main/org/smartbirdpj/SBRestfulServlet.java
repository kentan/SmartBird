package org.smartbirdpj;

import java.io.File;

import javax.swing.table.TableColumn;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


import org.smartbirdpj.test.SBTestMessageGenerator;

import org.smartbirdpj.dao.SBMessageDaoFactory;
import org.smartbirdpj.message.SBMessage;
import org.smartbirdpj.server.GameServer;

@Path("endpoint")
public class SBRestfulServlet {
	private GameServer _gameServer = null;
	
    
	@GET
	@Path("start/{clientId}")
	@Produces(MediaType.TEXT_PLAIN)
	public String start(@PathParam("clientId") String clientId){
		
        SBMessageDaoFactory.getInstance().createDao("").init(clientId);
        if("debugMessage".equals(clientId)){
        	SBTestMessageGenerator gen = new SBTestMessageGenerator();
        	gen.before();
        	gen.generateCase1();
        }else{
			_gameServer = new GameServer();
	        _gameServer.init(0,clientId);
	
	        _gameServer.start();		
        }
        return (new File("test").getAbsolutePath());
	}
	
	
	
	@GET
	@Path("next/{clientId}")
	@Produces(MediaType.TEXT_PLAIN)
	public String next(@PathParam("clientId") String clientId){
		SBMessage message = SBMessageDaoFactory.getInstance().createDao("").loadNextMessage(clientId);

		return message.toJson();
	}

}

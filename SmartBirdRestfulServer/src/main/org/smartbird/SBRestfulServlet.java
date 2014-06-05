package org.smartbird;

import java.io.File;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.smartbird.dao.SBMessageDaoFactory;
import org.smartbird.message.SBMessage;

import org.smartbird.server.GameServer;

@Path("endpoint")
public class SBRestfulServlet {
	private GameServer _gameServer = null;
	
    
	@GET
	@Path("start")
	@Produces(MediaType.TEXT_PLAIN)
	public String start(){
        SBMessageDaoFactory.getInstance().createDao("").init("testMessage");
		_gameServer = new GameServer();
        _gameServer.init(0,"testMessage");
        _gameServer.start();

        return (new File("test").getAbsolutePath());
	}
	
	
	
	@GET
	@Path("next")
	@Produces(MediaType.TEXT_PLAIN)
	public String next(){
		SBMessage message = SBMessageDaoFactory.getInstance().createDao("").loadNextMessage("testMessage");

		return message.toJson();
	}

}

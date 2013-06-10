package pup.thesis.controller;

import java.sql.SQLException;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;

import pup.thesis.logging.App;
import pup.thesis.util.ClientData;
import pup.thesis.util.mysql.DBQuerier;

public class DietfixServletListener implements ServletRequestListener {

	@Override
	public void requestInitialized(ServletRequestEvent arg0) {
		
		
		ClientData cdata = new ClientData();
		
		arg0.getServletRequest().setAttribute("clientdata", cdata);
		App.log("Request Object created At:" + new java.util.Date());
	}

	@Override
	public void requestDestroyed(ServletRequestEvent arg0) {
		ClientData clientdata = (ClientData) arg0.getServletRequest()
				.getAttribute("clientdata");
		try {
			int i = clientdata.getQuerier().closeAllConnection();
			App.log("DietfixServletListener", i + " connections closed");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			App.log("Failed to close connections:", e.getMessage());
		}
				App.log("The Request object destroyed at  :" + new java.util.Date());
	}

}

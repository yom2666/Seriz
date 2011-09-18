package controllers;

import java.util.List;

import models.Network;
import models.Show;
import play.data.validation.Required;
import play.mvc.Controller;

public class Networks extends Controller{

	public static void add() {
		String what = "Network";
		renderTemplate("ShowCtrl/add.html", what);
//		render();
	}
	
	public static void doAdd(@Required String name)
	{
		if(validation.hasErrors()) {
	        flash.error("Oops, please enter a network name!");
	        add();
	    }
		new Network(name).insert();
		all();
	}
	
	public static void all()
	{
		List<Network> networks = Network.all().order("name").fetch();
		String page = "network";
		render(networks, page);
	}
	
	public static void details(long id)
	{
		Network network = new Network();
		network.id = id;
		network.get();
		List<Show> shows = Show.all().filter("network", network).order("name").fetch();
		render(network, shows);
	}
	
	public static void delete(long id)
	{
		Network network = new Network();
		network.id = id;
		network.delete();
		all();
	}
}

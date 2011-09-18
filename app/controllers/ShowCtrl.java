package controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import models.Network;
import models.Person;
import models.Show;
import models.ShowStatus;
import models.Staff;
import play.data.validation.Required;
import play.mvc.Controller;
import siena.Query;

public class ShowCtrl extends Controller{
	
	public static void add() {
		String what = "Show";
		render(what);
	}
	
	public static void doAdd(@Required String name)
	{
		if(validation.hasErrors()) {
	        flash.error("Oops, please enter a show name!");
	        add();
	    }
		Show sh = new Show(name);
		sh.insert();
		edit(sh.id);
	}
	
	public static void index()
	{
		Query<Show> all = query(false);
		List<Show> shows = all.order("-weight").order("editedAt").fetch();
		String page = "show";
		renderTemplate("@list", shows, page);
	}
	
	public static void alpha()
	{
		Query<Show> all = query(false);
		List<Show> shows = all.order("name").fetch();
		boolean alpha = true;
		renderTemplate("@list", shows, alpha);
	}
	
	private static Query<Show> query(boolean showAll)
	{
		Query<Show> all = Show.all();
		if(!showAll)
		{
			all.filter("listed", true);
		}
		return all;
	}
	
	public static void edit(long id)
	{
		Show sh = new Show();
		sh.id = id;
		sh.get();
		ShowStatus[] status = ShowStatus.values();
		List<Network> networks = Network.all().order("name").fetch();
		networks.add(0, null);
		List<Staff> staffs = Staff.all().filter("showId", sh.id).fetch();
		List<Person> persons = new ArrayList<Person>();
		for(Staff staff : staffs)
		{
			persons.add(Person.all().filter("id", staff.personId).get());
		}
        render(sh, status, networks, staffs, persons);
	}
	
	public static void delete(long id)
	{
		Show sh = new Show();
		sh.id = id;
//		sh.delete();
		sh.get();
		sh.listed = false;
		sh.update();
		index();
	}
	
	public static void weight(long id, int n)
	{
		Show sh = new Show();
		sh.id = id;
		sh.get();
		sh.doWeight(n);
		sh.update();
		index();
	}
	
	public static void showEdit(@Required long id, @Required String showName, @Required String status, Date outDate, boolean betaSeries, long networkId, Integer alloId)
	{
//		showParams();
		ShowStatus realStatus = ShowStatus.valueOf(status);
		Network network = null;
		if(networkId > 0)
		{
			network = new Network();
			network.id = networkId;
			network.get();
		}
		Show sh = new Show();
		sh.id = id;
		sh.get();
		sh.name = showName;
		sh.status = realStatus;
		sh.outDate = outDate;
		sh.betaSeries = betaSeries;
		sh.network = network;
		if(alloId > 0)
		{
			sh.alloId = alloId;
		}
		sh.update();
		index();
	}
	
	@SuppressWarnings("unused")
	private static void showParams()
	{
		for(String key : params.allSimple().keySet())
		{
			System.out.println(key + " : " + params.allSimple().get(key));
		}
	}

}

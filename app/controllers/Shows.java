package controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import models.Network;
import models.Person;
import models.Show;
import models.ShowStatus;
import models.Staff;
import play.mvc.Controller;
import siena.Query;

public class Shows extends Controller
{

	public static void all()
	{
		Query<Show> all = Show.all();
		List<Show> shows = all.order("name").fetch();
		List<Network> networks = Network.all().fetch();
		renderTemplate("@list", shows, networks);
	}
	
	public static void details(long id)
	{
		Show sh = new Show();
		sh.id = id;
		sh.get();
		Network network = sh.network;
		if(network != null)
		{
			network.get();
		}
		List<Staff> staffs = Staff.all().filter("showId", sh.id).fetch();
		List<Person> persons = new ArrayList<Person>();
		for(Staff staff : staffs)
		{
			persons.add(Person.all().filter("id", staff.personId).get());
		}
        render(sh, staffs, persons, network);
	}
	
	public static void delete(long id)
	{
		Show sh = new Show();
		sh.id = id;
		sh.delete();
		all();
	}
	
	public static void toggle(long id)
	{
		Show sh = new Show();
		sh.id = id;
		sh.get();
		System.out.println("listed = "+sh.getListed());
		sh.listed = !sh.getListed();
		sh.update();
		details(id);
	}
}

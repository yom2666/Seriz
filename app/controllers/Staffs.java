package controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import models.Person;
import models.Show;
import models.Staff;
import play.data.validation.Error;
import play.data.validation.Required;
import play.mvc.Controller;
import play.mvc.Router;

public class Staffs extends Controller{

	public static void add()
	{
		addAll(-1, -1, Router.reverse("ShowCtrl.index").url);
	}
	
	public static void addFromP(long id)
	{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		addAll(id, -1, Router.reverse("Persons.details", map).url);
	}
	
	public static void addFromS(long id)
	{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		addAll(-1, id, Router.reverse("Shows.details", map).url);
	}
	
	private static void addAll(long pId, long shId, String urlBack)
	{
		List<Person> persons = Person.all().order("lastName").order("firstName").fetch();
		List<Show> shows = Show.all().order("name").fetch();
		renderTemplate("@add", persons, shows, pId, shId, urlBack);
	}
	
	public static void doAdd(@Required long personId, @Required long showId, @Required String role, String urlBack)
	{
		if(validation.hasErrors()) {
	        flash.error("Oops, please fix the following errors : ");
	        for(Error err : validation.errors())
	        {
	        	flash.error(err.message());
	        }
	        add();
	    }
		Person p = Person.all().filter("id", personId).get();
		System.out.println(p.firstName + " " + p.lastName + "(" + p.id + ")");
		Show sh = Show.all().filter("id", showId).get();
		System.out.println(sh.name + " " + sh.network + "(" + sh.id + ")");
		Staff stf = new Staff(sh.id, p.id, role);
		stf.insert();
		redirect(urlBack);
	}
	
	public static void delete(long id)
	{
		doDelete(id);
		ShowCtrl.index();
	}
	
	public static void delete(long id, long showId)
	{
		doDelete(id);
		Shows.details(showId);
	}
	
	private static void doDelete(long id)
	{
		Staff staff = new Staff();
		staff.id = id;
		staff.delete();
	}
}

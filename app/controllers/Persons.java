package controllers;

import java.util.ArrayList;
import java.util.List;

import models.Network;
import models.Person;
import models.Show;
import models.Staff;
import play.data.validation.Required;
import play.mvc.Controller;

public class Persons extends Controller{

	public static void add() {
		String what = "Person";
		render(what);
	}
	
	public static void doAdd(@Required String firstName, @Required String lastName)
	{
		if(validation.hasErrors()) {
	        flash.error("Oops, please enter a person name!");
	        add();
	    }
		Person p = new Person(firstName, lastName);
		p.insert();
		details(p.id);
	}
	
	public static void all()
	{
		List<Person> persons = Person.all().order("lastName").order("firstName").fetch();
		String page = "person";
		render(persons, page);
	}
	
	public static void details(long id)
	{
		Person person = new Person();
		person.id = id;
		person.get();
		List<Staff> staffs = Staff.all().filter("personId", id).fetch();
		List<Show> shows = new ArrayList<Show>();
		for(Staff staff : staffs)
		{
			shows.add(Show.all().filter("id", staff.showId).get());
		}
		render(person, staffs, shows);
	}
	
	public static void delete(long id)
	{
		Person person = new Person();
		person.id = id;
		person.delete();
		all();
	}
}

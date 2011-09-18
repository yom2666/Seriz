package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.OneToMany;

import play.data.validation.Required;
import siena.Id;
import siena.Model;
import siena.Query;

public class Person extends Model{
	
	@Id
	public long id;

	@Required
	public String 		firstName;
	@Required
	public String 		lastName;
//	@OneToMany
//	public List<Staff>	staffs;
	
	public Person(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
//		staffs = new ArrayList<Staff>();
	}
	
	public Person() {
		this("","");
	}
	
	public static Query<Person> all()
	{
		return all(Person.class);
	}
	
	@Override
	public String toString() {
		return firstName + " " + lastName;
	}
	
//	public List<Staff> getStaffs()
//	{
//		if(staffs == null)
//		{
//			staffs = new ArrayList<Staff>();
//		}
//		return staffs;
//	}

}

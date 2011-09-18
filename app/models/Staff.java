package models;

import play.data.validation.Required;
import siena.Id;
import siena.Model;
import siena.Query;

public class Staff extends Model
{

	@Id
	public long id;
	@Required
	public long showId;
	@Required
	public long personId;
	@Required
	public String role;
	
	public Staff(Show s, Person p, String r)
	{
		this(s.id, p.id, r);
	}
	
	public Staff(long sId, long pId, String r)
	{
		showId = sId;
		personId = pId;
		role = r;
	}
	
	public Staff() {
		this(-1, -1, "");
	}
	
	public static Query<Staff> all()
	{
		return all(Staff.class);
	}
}

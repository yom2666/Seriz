package models;

import play.data.validation.Required;
import siena.Id;
import siena.Model;
import siena.Query;

public class Network extends Model{
	
	@Id
	public long id;

	@Required
	public String 		name;
	
	public Network(String name) {
		this.name = name;
	}
	
	public Network() {
		this("");
	}
	
	public static Query<Network> all()
	{
		return all(Network.class);
	}
	
	@Override
	public String toString() {
		return name;
	}

}

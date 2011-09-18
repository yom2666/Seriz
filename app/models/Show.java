package models;

import java.util.Date;

import org.hibernate.annotations.Cascade;

import play.data.validation.Required;
import siena.Column;
import siena.Id;
import siena.Model;
import siena.Query;
import siena.embed.Embedded;

public class Show extends Model{
	
	@Id
	public long id;

	@Required
	public String 		name;
	@Required
	public Date   		editedAt = new Date();
	@Required @Embedded
	public ShowStatus	status;
	public Date			outDate;
	public boolean		betaSeries;
	public int 			weight = 0;
	public Network		network;
	public Integer		alloId = -1;
	public Boolean		listed = true;
	
	public Show(String name) {
		this.name = name;
		status = ShowStatus.Project;
		weight = 0;
		editedAt = new Date();
	}
	
	public Show()
	{
		this("");
	}
	
	public int getAlloId()
	{
		if(alloId == null)
		{
			alloId = -1;
		}
		return alloId;
	}
	
	public void setStatus(ShowStatus status) {
		this.status = status;
		doUpdate();
	}
	
	public void setOutDate(Date outDate) {
		this.outDate = outDate;
		doUpdate();
	}
	
	public void setBetaSeries(boolean betaSeries) {
		this.betaSeries = betaSeries;
		doUpdate();
	}
	
	public void doWeight(int n)
	{
		if(n > 0) weight++;
		else if (n < 0) weight--;
		doUpdate();
	}
	
	public void doUpdate()
	{
		editedAt = new Date();
	}
	
	public static Query<Show> all() {
		return Model.all(Show.class);
	}
	
	public boolean hasAlloUrl()
	{
		return getAlloId() > 0;
	}
	
	public String getAlloUrl()
	{
		if(getAlloId() > 0)
		{
			return "http://www.allocine.fr/series/ficheserie_gen_cserie="+alloId+".html";
		}
		else
		{
			return "http://www.allocine.fr/recherche/?q="+name;
		}
	}
	
	public Boolean getListed()
	{
		if(listed == null)
		{
			listed = true;
		}
		return listed;
	}
    
}

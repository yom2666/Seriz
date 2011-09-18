package controllers;

import models.Show;
import play.mvc.Controller;

public class Updater extends Controller{
	
	public static void update()
	{
		System.out.println("*** Bootsrap ***");
		for(Show sh : Show.all().fetch())
		{
			if(sh.listed == null)
			{
				sh.listed = true;
				sh.update();
			}
		}
	}

}

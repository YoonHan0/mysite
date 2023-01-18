package com.douzone.mysite.web.mvc.guest;


import com.douzone.web.mvc.Action;
import com.douzone.web.mvc.ActionFactory;

public class GuestActionFactory extends ActionFactory {

	@Override
	public Action getAction(String actionName) {
		Action action = null;
		
		if("list".equals(actionName)) {
			action = new GuestFormAction();
		}
		
		return action;
	}
}

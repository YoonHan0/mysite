package com.douzone.mysite.web.mvc.guest;


import com.douzone.mysite.web.mvc.user.JoinAction;
import com.douzone.web.mvc.Action;
import com.douzone.web.mvc.ActionFactory;

public class GuestActionFactory extends ActionFactory {

	@Override
	public Action getAction(String actionName) {
		Action action = null;
		
		if("list".equals(actionName)) {
			action = new GuestFormAction();
		} else if("guestCreate".equals(actionName)) {
			action = new AddGuestAction();
		} else if("deleteform".equals(actionName)) {
			action = new GuestDeleteFormAction();
		} else if("delete".equals(actionName)) {
			action = new GuestDeleteAction();
		} else {
			action = new GuestFormAction();		// a=list랑 같은 액션
		}
		
		return action;
	}
}

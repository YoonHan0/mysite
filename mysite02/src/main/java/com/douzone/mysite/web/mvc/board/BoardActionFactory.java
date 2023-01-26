package com.douzone.mysite.web.mvc.board;

import com.douzone.web.mvc.Action;
import com.douzone.web.mvc.ActionFactory;

public class BoardActionFactory extends ActionFactory {

	@Override
	public Action getAction(String actionName) {
		Action action = null;
		
		if("write".equals(actionName)) {
			action = new BoardFormAction();
		} else if("AddBoardAction".equals(actionName)) {
			action = new AddBoardAction();
		} else if("viewpage".equals(actionName)) {
			action = new BoardPageViewAction();
		} else if("UpdateForm".equals(actionName)) {
			action = new UpdateForm();
		} else if("update".equals(actionName)) {
			action = new UpdateAction();
		}
		else {
			action = new ListAction();
		}
		
		return action;
	}

}
package vn.com.ecotechgroup.erp.service;

import org.springframework.ui.Model;

import vn.com.ecotechgroup.erp.entity.User;

public interface UiService {
	
	void getInformation(Model model);
	
	void getInformationForUser(Model model, User user);
}


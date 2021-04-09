package com.SpeedHome.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SpeedHome.model.ActiveUserData;
import com.google.gson.Gson;

/**
 * 
 * @author Fhadk
 *
 */

@Service
public class SpeedHomeService {
	private Logger logger = Logger.getLogger(SpeedHomeService.class);

	private Map<String, String> propertyManagment = new HashMap<String, String>();

	@Autowired
	ActiveUserData activeUserData;

	/**
	 * @return List<String>
	 */
	public List<String> getActiveUser() {
		return activeUserData.getUsers();
	}

	/**
	 * @return JSON Object
	 */
	public String createProperty(String type, String address) {
		try {
			propertyManagment.put(address, type);

		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return returnJson(propertyManagment);
	}

	/**
	 * @return JSON Object
	 */
	public String updateProperty(String type, String address) {
		try {
			if (propertyManagment.isEmpty())
				return "HashMap Empty !";
			
			propertyManagment.computeIfPresent(address, (k, v) -> v = type);

		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return returnJson(propertyManagment);
	}

	/**
	 * @return JSON Object
	 */
	public String aprroveProperty(String address) {
		String flag = "";
		try {
			if (propertyManagment.isEmpty())
				return "HashMap Empty !";
			
			if(address.contains("USA")) {
				flag = "Approved !!";
			}else {
				flag = "Failed to approve !!";
			}
			
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * @return JSON Object
	 */
	public String searchProperty(String address) {
		String str = "";
		try {
			
			if (propertyManagment.isEmpty())
				return "HashMap Empty !";
			
			str = propertyManagment.entrySet().stream().filter(e -> e.getKey().equals(address)).map(Map.Entry::getKey).findFirst()
					.orElse(null);

		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return "Found: " + str;
	}

	private String returnJson(Map<String, String> data) {
		return new Gson().toJson(data);
	}

}

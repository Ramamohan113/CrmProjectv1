package com.crmproject.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ReadPropertyData {

	Properties pro;

	public ReadPropertyData()  {
		File f = new File("C:\\Users\\Ramu\\OneDrive\\Desktop\\crm.properties");

		
			try {
				FileInputStream file = new FileInputStream(f);
				pro = new Properties();
				pro.load(file);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		

	}

	public String getUrl() {
		String baseUrl = pro.getProperty("url");
		return baseUrl;
	}

	public String getUaserName() {
		String userName = pro.getProperty("Userid");
		return userName;
	}

	public String getPassWord() {
		String passWord = pro.getProperty("Password");
		return passWord;
	}

	public String getChromePath() {
		String chromePath = pro.getProperty("chromepath");
		return chromePath;
	}

	public String getFirefoxPath() {
		String fireFoxPath = pro.getProperty("firefoxpath");
		return fireFoxPath;
	}
}

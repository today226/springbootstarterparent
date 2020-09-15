package com.config.impl.db;

import java.io.File;

import com.config.Configuration;



public class DBConfig extends Configuration {
	protected File getConfigFile(){
		//return IOUtilities.getFileFromClassPath("db/db.properties");
		return null;
	}
}

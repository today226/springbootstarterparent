package com.util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.config.Configuration;

public class FileUtilities {
	static Configuration config = new Configuration();
	public static String getFileName() {
		StringBuffer sb = new StringBuffer();
		
		Date dt = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		sb.append(config.get("file.name.pre"));
		sb.append(config.get("file.name.sep"));
		sb.append(sdf.format(dt));
		sb.append(config.get("file.name.suf"));
		
		return sb.toString();
	}
	
	public static PrintWriter getPrintWriter(String fileName) throws RuntimeException {
		String filePath = new Configuration().get("file.path")+ "/" + fileName;
		try {
			return new PrintWriter(new FileWriter(filePath, true));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	} 
	
}

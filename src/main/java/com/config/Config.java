package com.config;


public interface Config {

	/**
	 * @return java.lang.String
	 * @param key java.lang.String
	 */
	 public String get(String key);

	/**
	 * @return boolean
	 * @param key java.lang.String
	 */
	public boolean getBoolean(String key);

	/**
	 * @return int
	 * @param key java.lang.String
	 */
	public int getInt(String key);

	/**
	 * @return java.util.Properties
	 */
	public java.util.Properties getProperties()	;

	/**
	 * @return java.lang.String
	 * @param key java.lang.String
	 */
	public String getString(String key);

	/**
	 * 
	 * @return last modification time.
	 */
	long lastModified();
}
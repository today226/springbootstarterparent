package com.config;

import java.io.UnsupportedEncodingException;


public abstract class GeneralConfiguration implements Config
{
	protected static Object lock = new Object();
	protected static java.util.Properties props = null;
	protected static long lastModified = 0;

	/**
	 *
	 */
	public GeneralConfiguration() {
		super();
	}

	/**
	 * @return java.lang.String
	 * @param key java.lang.String
	 */
	public String get(String key) {
		return getString(key);
	}
	/**
	 * 
	 * @return boolean
	 * @param key java.lang.String
	 */
	public boolean getBoolean(String key) {
		boolean value = false;
		try {
			value = (new Boolean(props.getProperty(key))).booleanValue();
		}
		catch(Exception e){
			throw new IllegalArgumentException("Illegal Boolean Key : " + key);
		}
		return value;
	}

	/**
	 * @return int
	 * @param key java.lang.String
	 */
	public int getInt(String key) {
		int value = -1;
		try {
			value = Integer.parseInt(props.getProperty(key));
		}
		catch(Exception e){
			throw new IllegalArgumentException("Illegal Integer Key : " + key);
		}
		return value;
	}

	/**
	 * @return java.util.Properties
	 */
	public java.util.Properties getProperties() {
		return props;
	}

	/**
	 * @return java.lang.String
	 * @param key java.lang.String
	 */
	public String getString(String key) {
		String value = null;
		try {
			String tmp = props.getProperty(key);
			if ( tmp == null ) throw new Exception("value of key(" +key+") is null" );
			value = E2K(tmp);
		}
		catch(Exception e){
			
			throw new IllegalArgumentException("Illegal String Key : " + key);
		}
		return value;
	}
	
	
	
	/**
	 * 
	 * @return last modification time.
	 */
	public long lastModified() {
		return lastModified;
	}
	String E2K( String english ) {
		String korean = null;
		
		if (english == null ) return null;
		//if (english == null ) return "";
		
		try { 
			korean = new String(new String(english.getBytes("8859_1"), "KSC5601"));
		}
		catch( UnsupportedEncodingException e ){
			korean = new String(english);
		}
		return korean;
	}
}
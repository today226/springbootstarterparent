package com.config;

/**
 * @(#) Configuration.java
 * Copyright 1999-2000 by  LG-EDS Systems, Inc.,
 * Information Technology Group, Application Architecture Team, 
 * Application Intrastructure Part.
 * 236-1, Hyosung-2dong, Kyeyang-gu, Inchun, 407-042, KOREA.
 * All rights reserved.
 * 
 * NOTICE !      You can copy or redistribute this code freely, 
 * but you should not remove the information about the copyright notice 
 * and the author.
 * 
 * @author  WonYoung Lee, wyounglee@via.lg.co.kr.
 */
import java.io.File;
import java.io.FileInputStream;



public class Configuration extends GeneralConfiguration
{
	private static long srvenv_last_modified = 0;
	private static long pbf_last_modified = 0;
	private File default_file= null;

	
	

	/**
	 *
	 */
	public Configuration() throws RuntimeException {
		super();
		default_file=getConfigFile();
		initialize();
	}

	/**
	 * 
	 */
	protected void initialize() throws RuntimeException {
		synchronized(lock){	
			try{
				boolean needUpdate = false;

			// Java Development Framework Configuration File
				
				if ( ! default_file.canRead() ) 
					throw new RuntimeException( this.getClass().getName() + " - Can't open srvenv configuration file: " + default_file.getAbsolutePath() );
				
				if ( (srvenv_last_modified != default_file.lastModified() ) || props == null ) {
					needUpdate = true;
				}
				else {
					String pbf_file_name = props.getProperty("com.via.srvenv.config.pbf.file");
					if ( pbf_file_name != null ) {
						File pbf_file = new File(pbf_file_name);
						if ( ! pbf_file.canRead() ) 
							throw new RuntimeException( this.getClass().getName() + " - Can't open pbf configuration file: " + default_file.getAbsolutePath() );
						if ( pbf_last_modified != pbf_file.lastModified() ) {
							needUpdate = true;
						}
					}
				}// end if

				if ( needUpdate ) {
					props = new java.util.Properties();
					FileInputStream srvenv_fin = new FileInputStream(default_file.getAbsolutePath());
					props.load(new java.io.BufferedInputStream(srvenv_fin));
					srvenv_fin.close();
					srvenv_last_modified = default_file.lastModified();

					String pbf_file_name = props.getProperty("com.via.srvenv.config.pbf.file");
					if ( pbf_file_name != null ) {
						
						if ( ! default_file.canRead() ) 
							throw new RuntimeException( this.getClass().getName() + " - Can't open pbf configuration file: " + default_file.getAbsolutePath() );
						FileInputStream pbf_fin = new FileInputStream(default_file);
						props.load(new java.io.BufferedInputStream(pbf_fin));
						pbf_fin.close();
						pbf_last_modified = default_file.lastModified();
					}
					super.lastModified = System.currentTimeMillis();
					
				} // end if
			}
			catch(RuntimeException e) {
				super.lastModified = 0;
				srvenv_last_modified = 0;
				pbf_last_modified = 0;
				throw e;
			}
			catch(Exception e){
				super.lastModified = 0;
				srvenv_last_modified = 0;
				pbf_last_modified = 0;
				throw new RuntimeException( this.getClass().getName() + " - Can't load configuration file: " + e.getMessage());
			}
		} // end of sunchronized(lock);
	}
	protected File getConfigFile(){
//		return IOUtilities.getFileFromClassPath("srvenv.properties");
		
		return new File(
				getClass().getClassLoader().getResource("srvenv.properties").getFile()
			);
	}
}
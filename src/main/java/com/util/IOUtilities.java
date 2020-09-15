package com.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;

public class IOUtilities {
	@SuppressWarnings("resource")
	public static String getStringFromFile(String path) throws IOException {
		StringBuffer sb = new StringBuffer();
		BufferedReader reader = null;
		reader = new BufferedReader(new FileReader((new File(path))));

		String line = null;
		while ((line = reader.readLine()) != null) {
			sb.append(line);
		}

		return sb.toString();
	}

	public static String getStringFromFile(File file) throws IOException {
		StringBuffer sb = new StringBuffer();
		
		InputStream is = null;
		InputStreamReader ir = null;
		BufferedReader reader = null;
		try {
		is = new FileInputStream(file);
		ir= new InputStreamReader(is);
		reader = new BufferedReader(ir);
		String line = null;
		while ((line = reader.readLine()) != null) {
			sb.append(line);
		}
		}catch(IOException io) {
			throw io;
		}finally {
			if(reader != null) reader.close();
			if(ir != null) ir.close();
			if(is != null) is.close();
		}
		return sb.toString();
	}

	public static File getFileFromClassPath(String fileName) {
		ClassPathResource res = new ClassPathResource(fileName);
		File file;
		try {
			file = res.getFile();
			if(file == null)
			throw new RuntimeException("Not found ["+fileName+"]");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return file;
	}

	/*
	 * convert to string from exception stack trace.
	 */
	public static String getStackTrace(Throwable e) {
		java.io.ByteArrayOutputStream bos = new java.io.ByteArrayOutputStream();
		java.io.PrintWriter writer = new java.io.PrintWriter(bos);
		e.printStackTrace(writer);
		writer.flush();
		return bos.toString();
	}

	public static void copy(InputStream src, OutputStream dest)
			throws IOException {
		IOUtils.copy(src, dest);
	}

	public static void mkDir(String dirPath) throws IOException {
		Path logDir = Paths.get(dirPath);
		if (Files.notExists(logDir)) {
			Files.createDirectories(logDir);
		}
	}

	public static void mkFile(String filePath) throws IOException {
		Path path = Paths.get(filePath);
		if (Files.notExists(path.getParent())) {
			Files.createDirectories(path.getParent());
		}

		if (Files.notExists(path)) {
			Files.createFile(path);
		}
	}

	public static File mkFile(String pkg, String filePath) throws IOException {
		File dir = getFileFromClassPath(pkg).getParentFile();
		File newFile = new File(dir.getAbsoluteFile() + File.separator
				+ filePath);

		if (!newFile.exists())
			mkFile(newFile.getAbsolutePath());
		return newFile;
	}

	public static File getFileFromClassPath(String pkg, String filePath) {
		File dir = getFileFromClassPath(pkg).getParentFile();
		return new File(dir.getAbsoluteFile() + File.separator + filePath);
	}

	public static File getFileFromResoureses(String pkg, String filePath) {

		return new File(getFileFromClassPath(pkg).getParentFile(), filePath);
	}

	public static File mkFileFromResoureses(String pkg, String filePath)
			throws IOException {
		File file = getFileFromResoureses(pkg, filePath);
		return mkFile(pkg, file.getAbsolutePath());
	}

	public static String getDateRollingPath(String base, String code, String ext) {
		StringBuffer tmp = new StringBuffer().append(base)
				.append(File.separator).append(code).append(File.separator)
				.append(DateTime.getToday("yyyyMMdd")).append(".").append(ext);

		return tmp.toString();
	}

	public static String getDateRollingFilePath(String base, String code,
			String fileName) {
		StringBuffer tmp = new StringBuffer().append(base)
				.append(File.separator).append(code).append(File.separator)
				.append(DateTime.getToday("yyyyMMdd")).append(File.separator)
				.append(fileName);

		return tmp.toString();
	}
	
}

package com.util;

import java.io.*;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class GzipUtil {

	public static boolean compress(String sourceFilePath, String compressFilePath) {

		byte[] buffer = new byte[1024];

		try {
			GZIPOutputStream gzos = new GZIPOutputStream(new FileOutputStream(compressFilePath));
			FileInputStream in = new FileInputStream(sourceFilePath);
			int len;
			while ((len = in.read(buffer)) > 0) {
				gzos.write(buffer, 0, len);
			}
			in.close();
			gzos.finish();
			gzos.close();
		} catch (IOException ex) {
			ex.printStackTrace();
			return false;
		}
		return true;
	}

	public static boolean deCompress(String sourceFilePath, String storeFilePath) {
		byte[] buffer = new byte[1024];

		try {
			GZIPInputStream gzis = new GZIPInputStream(new FileInputStream(sourceFilePath));
			FileOutputStream out = new FileOutputStream(storeFilePath);
			int len;
			while ((len = gzis.read(buffer)) > 0) {
				out.write(buffer, 0, len);
			}
			gzis.close();
			out.close();
		} catch (IOException ex) {
			ex.printStackTrace();
			return false;
		}
		return true;
	}

	public static byte[] deCompressByte(byte[] data) {
		byte[] buffer = new byte[1024];
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			ByteArrayInputStream bin = new ByteArrayInputStream(data);
			GZIPInputStream gzis = new GZIPInputStream(bin);
			int len;
			while ((len = gzis.read(buffer)) > 0) {
				out.write(buffer, 0, len);
			}
			gzis.close();
			out.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return out.toByteArray();
	}
}

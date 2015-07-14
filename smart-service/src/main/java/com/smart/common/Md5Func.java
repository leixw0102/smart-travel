package com.smart.common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Func {

	static public  String byte2hex(byte[] b) {
		String hs = "";
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			stmp = Integer.toHexString(b[n] & 0XFF);
			if (stmp.length() == 1) {
				hs = hs + "0" + stmp;
			} else {
				hs = hs + stmp;
			}
		}
		return hs.toUpperCase();
	}
	static private String digest(byte[] buf, String al) {
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance(al);
		} catch (NoSuchAlgorithmException e) {
			return "";
		}
		return byte2hex(md.digest(buf));
	}
	static public  String md5(byte[] buf) {
		return digest(buf, "MD5");
	}	
	static public  String sha1(byte[] buf) {
		return digest(buf, "SHA-1");
	}
	static public  String myUuid(byte[] buf) {		 
		StringBuffer strSqlBuffer = new StringBuffer();
		strSqlBuffer.append(digest(buf, "MD5")) ;
		strSqlBuffer.insert(8, '-');
		strSqlBuffer.insert(13, '-');
		strSqlBuffer.insert(18, '-');
		strSqlBuffer.insert(23, '-');
		return strSqlBuffer.toString();
	}
	
	public static void main(String[] args){
		 
		String   result ="123";
		String   result2=Md5Func.myUuid(result.getBytes());	
		String   result22=Md5Func.md5(result.getBytes());		
		String   result233=Md5Func.sha1(result.getBytes());		
		
	}
}

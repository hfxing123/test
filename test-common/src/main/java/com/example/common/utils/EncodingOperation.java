package com.example.common.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncodingOperation {

	public static void main(String[] args) {
		//System.out.println(MD5("123456"));
		System.out.println(MD5("e10adc3949ba59abbe56e057f20f883e8d6a59"));
	}
	
	private static final String strDigits[] = { "0", "1", "2", "3", "4", "5",
			"6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

	public static String toUtf8(String s) {
		try {
			s = new String(s.getBytes(checkEncoding(s)), "utf-8");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return s;
	}

	/**
	 * 检查该字符使用什么编码,只检测ISO-8859-1,GBK,gb2312,utf-8 需要的话在sc这个数组里面添加所需检测的语言
	 * 
	 * @param s
	 * @return
	 */
	public static String checkEncoding(String s) {
		String s1 = "utf-8";
		String[] sc = { "ISO-8859-1", "GBK", "gb2312", "utf-8" };
		for (String sct : sc) {
			try {
				if (s.equals(new String(s.getBytes(sct), sct))) {
					return sct;
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return s1;
	}

	private static String byteToArrayString(byte bByte) {
		int iRet = bByte;
		if (iRet < 0)
			iRet += 256;
		int iD1 = iRet / 16;
		int iD2 = iRet % 16;
		return (new StringBuilder(String.valueOf(strDigits[iD1]))).append(
				strDigits[iD2]).toString();
	}

	private static String byteToNum(byte bByte) {
		int iRet = bByte;
		System.out.println((new StringBuilder("iRet1=")).append(iRet)
				.toString());
		if (iRet < 0)
			iRet += 256;
		return String.valueOf(iRet);
	}

	private static String byteToString(byte bByte[]) {
		StringBuffer sBuffer = new StringBuffer();
		for (int i = 0; i < bByte.length; i++)
			sBuffer.append(byteToArrayString(bByte[i]));

		return sBuffer.toString();
	}

	private static String GetMD5Code(String strObj) {
		String resultString = null;
		try {
			resultString = new String(strObj);
			MessageDigest md = MessageDigest.getInstance("MD5");
			resultString = byteToString(md.digest(strObj.getBytes()));
		} catch (NoSuchAlgorithmException ex) {
			ex.printStackTrace();
		}
		return resultString;
	}

	public static String MD5(String s) {
		return GetMD5Code(s);
	}

}

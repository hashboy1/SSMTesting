package com.oti.util;

import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;


/**
 * DES解密
 */
public class Encryption {


	private static final String SECRET_KEY = "OTInvocie";


	public static String Encrypt(String Content) {
		try {
			SecureRandom random = new SecureRandom();
			DESKeySpec desKey = new DESKeySpec(SECRET_KEY.getBytes());
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			SecretKey securekey = keyFactory.generateSecret(desKey);
			// Cipher对象实际完成加密操作
			Cipher cipher = Cipher.getInstance("DES");
			BASE64Encoder base64encoder = new BASE64Encoder();
			cipher.init(Cipher.ENCRYPT_MODE, securekey, random);
			return base64encoder.encode(cipher.doFinal(Content.getBytes("UTF-8")));
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return "";
	}

	
	public static String Decrypt(String Content) {
		try {
			SecureRandom random = new SecureRandom();
			DESKeySpec desKey = new DESKeySpec(SECRET_KEY.getBytes());
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			SecretKey securekey = keyFactory.generateSecret(desKey);
			// Cipher对象实际完成加密操作
			Cipher cipher = Cipher.getInstance("DES");
			BASE64Decoder base64decoder = new BASE64Decoder();
			byte[] encodeByte = base64decoder.decodeBuffer(Content);
			cipher.init(Cipher.DECRYPT_MODE, securekey, random);
			byte[] decoder = cipher.doFinal(encodeByte);
			return new String(decoder, "UTF-8");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return "";
	}

	// 测试
	public static void main(String args[]) {
		// 待加密内容
		String str = "1234";
		System.out.println("加密后：" + Encryption.Encrypt(str));
		System.out.println("解密后：" + Encryption.Decrypt(Encrypt(str)));

	}
}

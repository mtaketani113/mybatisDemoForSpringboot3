package com.example.demo.common;

import javax.crypto.Cipher;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.apache.tomcat.util.codec.binary.Base64;

public class Crypt {

	private static final String ENCRYPT_KEY =
		System.getenv("crypt.aes.key") != null ? System.getenv("crypt.aes.key") : "SPRING_3_MYBATIS";
	private static final String ENCRYPT_IV =
		System.getenv("crypt.aes.iv") != null ? System.getenv("crypt.aes.iv") : "demo_application";

    /**
	 * 暗号化メソッド
	 *
	 * @param text 暗号化する文字列
	 * @return 暗号化文字列
	 */
	public static String encrypt(String text) {
		// 変数初期化
		String strResult = null;

		try {
			// 文字列をバイト配列へ変換
			byte[] byteText = text.getBytes("UTF-8");

			// 暗号化キーと初期化ベクトルをバイト配列へ変換
			byte[] byteKey = ENCRYPT_KEY.getBytes("UTF-8");
			byte[] byteIv = ENCRYPT_IV.getBytes("UTF-8");

			// 暗号化キーと初期化ベクトルのオブジェクト生成
			SecretKeySpec key = new SecretKeySpec(byteKey, "AES");
			GCMParameterSpec iv = new GCMParameterSpec(128, byteIv);

			// Cipherオブジェクト生成
			Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");

			// Cipherオブジェクトの初期化
			cipher.init(Cipher.ENCRYPT_MODE, key, iv);

			// 暗号化の結果格納
			byte[] byteResult = cipher.doFinal(byteText);

			// Base64へエンコード
			strResult = Base64.encodeBase64String(byteResult);

		} catch (Exception e) {
			e.printStackTrace();
		}

		// 暗号化文字列を返却
		return strResult;
	}

    /**
	 * 復号化メソッド
	 *
	 * @param text 復号化する文字列
	 * @return 復号化文字列
	 */
	public static String decrypt(String text) {
		// 変数初期化
		String strResult = null;

		try {
			// Base64をデコード
			byte[] byteText = Base64.decodeBase64(text);

			// 暗号化キーと初期化ベクトルをバイト配列へ変換
			byte[] byteKey = ENCRYPT_KEY.getBytes("UTF-8");
			byte[] byteIv = ENCRYPT_IV.getBytes("UTF-8");

			// 復号化キーと初期化ベクトルのオブジェクト生成
			SecretKeySpec key = new SecretKeySpec(byteKey, "AES");
			GCMParameterSpec iv = new GCMParameterSpec(128, byteIv);

			// Cipherオブジェクト生成
			Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");

			// Cipherオブジェクトの初期化
			cipher.init(Cipher.DECRYPT_MODE, key, iv);

			// 復号化の結果格納
			byte[] byteResult = cipher.doFinal(byteText);

			// バイト配列を文字列へ変換
			strResult = new String(byteResult, "UTF-8");

		} catch (Exception e) {
			e.printStackTrace();
		}

		// 復号化文字列を返却
		return strResult;
	}
}

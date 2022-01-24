package com.project.memo.common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptUtils {

	// static 객체 생성 없이 바로 사용 가능
	// 암호화 메소드
	public static String md5(String message) {
		String encData = "";	// 반복문을 통한 결과를 문자열로 저장
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");	// 내부에서 객체 관리
			
			// ex.asdf -> byte[] = [00000000, 00000000, 00000000, 00000000]
			byte[] bytes = message.getBytes();	// 암호화 하려는 대상 -> byte 형태의 배열
			md.update(bytes);
			
			byte[] digest = md.digest();		// digest()를 통해 암호화된 message를 배열에 저장
			
			// 숫자 -> 문자열
			for(int i = 0; i < digest.length; i++) {
				encData += Integer.toHexString(digest[i] & 0xff); // 반복하면서 문자열에 하나씩 저장
				//  00101000
				//& 00110100
				//  00100000 -> mask
			}
			
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;						// 문제가 생길 경우 null을 보고 문제가 있음을 확인
		}
		return encData;
	}
}

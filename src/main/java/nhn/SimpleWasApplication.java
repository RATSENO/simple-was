package nhn;

import nhn.was.NhnWebApplicationServer;

/**
 * 
 * nhn
 * ===>SimpleWasApplication.java.java
 *
 * @Class   : SimpleWasApplication.java
 * @Since   : 2021. 5. 12.
 * @Desc	: 메인 클래스
 */
public class SimpleWasApplication {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Thread server = new Thread(new NhnWebApplicationServer());
			server.start();
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
	}
}

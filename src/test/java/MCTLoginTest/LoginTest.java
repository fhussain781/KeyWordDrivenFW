package MCTLoginTest;

import org.testng.annotations.Test;

import KeyWordEngine.KWEngine;

public class LoginTest {

	public KWEngine KWEngine;
	@Test
	public void LoginTestSc() {
		KWEngine = new KWEngine();
		KWEngine.startExecution("Login");
		
		
	}
	
	
	
	
}

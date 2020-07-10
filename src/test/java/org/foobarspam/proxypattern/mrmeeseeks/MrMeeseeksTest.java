package org.foobarspam.proxypattern.mrmeeseeks;

import static org.junit.Assert.*;
import org.junit.Test;

public class MrMeeseeksTest {
	
	@Test
	public void formulateRequestTest() {
		
		// el problema es que este caso test se ejecuta antes que 
		// el anterior y ahora tengo 1011 => no pasan los casos test
		// https://stackoverflow.com/questions/3655944/is-it-really-necessary-to-nullify-objects-in-junit-teardown-methods
		
		MrMeeseeks mrMe = new MrMeeseeks();
		mrMe.formulateRequest("Be ab normal");
		
		assertEquals("Be ab normal", mrMe.getRequest());
	}

}

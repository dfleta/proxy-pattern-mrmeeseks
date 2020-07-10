package org.foobarspam.proxypattern.mrmeeseeks;

import java.util.Collection;

/*
 *  Singleton
 *  Ensure a class has only one instance, and provide a global point of access to it.
 */


public class BoxProxyMrMeeseeks {
	
	// final reference variable
	private static final BoxProxyMrMeeseeks instanceBox = new BoxProxyMrMeeseeks();
	
	private BoxProxyMrMeeseeks() {};
	
	// lazy instantiation
	public static BoxProxyMrMeeseeks getInstanceBox() {
		return instanceBox;
	}
	
	private MrMeeseeks createMrMeeseeks() {
		return new MrMeeseeks();
	} 
	
	// inyeccion de dependencia
	public void pushButton(Collection<MrMeeseeks> setMrMe) {
		setMrMe.add(createMrMeeseeks());
	}
	
}

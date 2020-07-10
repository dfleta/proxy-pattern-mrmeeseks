package org.foobarspam.proxypattern.mrmeeseeks;

import static org.junit.Assert.*;

import java.util.Comparator;
import java.util.NavigableSet;
import java.util.TreeSet;

import org.junit.Test;

public class BoxProxyMrMeeseeksTest {

	@Test
	public void getInstanceBoxTest() {
		
		BoxProxyMrMeeseeks firstInstanceBox = BoxProxyMrMeeseeks.getInstanceBox();
		assertTrue(firstInstanceBox instanceof BoxProxyMrMeeseeks);
		
		BoxProxyMrMeeseeks secondInstanceBox = BoxProxyMrMeeseeks.getInstanceBox();
		assertTrue(secondInstanceBox instanceof BoxProxyMrMeeseeks);
		
		assertSame("las dos referencias apuntan al mismo objeto", firstInstanceBox, secondInstanceBox);	
	}
	
	@Test
	public void pushButtonTest() {
		BoxProxyMrMeeseeks box = BoxProxyMrMeeseeks.getInstanceBox();
		NavigableSet<MrMeeseeks> setMrMe = new TreeSet<MrMeeseeks>(Comparator.comparing(MrMeeseeks::getId));
		box.pushButton(setMrMe);		
		assertEquals("Mr Meeseeks añadido al conjunto", 1, setMrMe.size());		
	}	

}

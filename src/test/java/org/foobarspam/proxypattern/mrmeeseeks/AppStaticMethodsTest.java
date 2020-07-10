package org.foobarspam.proxypattern.mrmeeseeks;

import static org.junit.Assert.*;

import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeSet;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class AppStaticMethodsTest {
	
	static NavigableSet<MrMeeseeks> setMrMe = null;
	static Set<Integer> ids = null;
	static BoxProxyMrMeeseeks box = null;
	
	@BeforeClass
	public static void setup() {
		setMrMe = new TreeSet<MrMeeseeks>(Comparator.comparing(MrMeeseeks::getId));
		ids = new LinkedHashSet<Integer>();
		box = BoxProxyMrMeeseeks.getInstanceBox();
		
	}

	@Before
	public void populateSets() {
		
		int pushedButtonOperations = 10;
		for(int i=1; i<=pushedButtonOperations; i++) {
			box.pushButton(setMrMe);
			ids.add(setMrMe.last().getId());
		}
		
		assertEquals(setMrMe.size(), ids.size());
	}
	
	@After
	public void clearSets() {
		setMrMe.clear();
		ids.clear();
		assertEquals(0, setMrMe.size());
		assertEquals(0, ids.size());
	}
	
	@Test
	public void explodeNonJerryMrMeeseeksTest() {
		
		for(int id : ids) {
			App.explodeMrMeeseeks(setMrMe, id);
		}
		
		assertEquals(0, setMrMe.size());
		
	}
	
	@Test
	public void explodeNonJerryMrMeeseeksNotInIdsTest() {
		int id = 10000;
		App.explodeMrMeeseeks(setMrMe, id);
		assertEquals(10, setMrMe.size());
	}
	
	@Test
	public void explodeNonJerryMrMeeseeksEmptyTest() {
		setMrMe.clear();
		int id = 1001;		
		App.explodeMrMeeseeks(setMrMe, id);
		assertEquals(0, setMrMe.size());
	}
	
	@Test 
	public void explodeJerryMrMeeseeksTest() {
		App.explodeMrMeeseeks(setMrMe);
		assertEquals(0, setMrMe.size());
	}

}

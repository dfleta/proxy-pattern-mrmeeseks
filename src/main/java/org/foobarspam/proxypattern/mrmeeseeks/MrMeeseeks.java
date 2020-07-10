package org.foobarspam.proxypattern.mrmeeseeks;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

public class MrMeeseeks implements Doable {	

	private static AtomicInteger ID_GENERATOR = new AtomicInteger(1000);
	private Integer id;
	private String[] messageOnRequest = {"Oooh yeah! Can do!", "Yes sireee!" , "Oh, yeah!, Yes, ma'am!"};
	private String messageOnDone = "All done!";
	private String messageOnExplode = "Pooooof";
	private String[] request = new String[2];
	private List<String> requestAsList = new LinkedList<String>();
				
	public MrMeeseeks() {
		this.id = ID_GENERATOR.incrementAndGet();
		sayMessageOnCreate(); // mejor en un metodo onCreate()
	}
	
	public Integer getId() {
		return this.id;
	}
	
	private String getMessageOnRequest(int Index) {
		return this.messageOnRequest[Index];
	}
	
	private void setRequest(String request) {
		int objectPosition = request.indexOf(" ");
		this.requestAsList.add(request.substring(0, objectPosition));
		this.requestAsList.add(request.substring(objectPosition));
	}
	
	private String getRequest(int index) {
		return this.request[index];
	}
	
	// public testing
	public String getRequest() {
		return this.requestAsList.stream()
								 .reduce("", (part1, part2) -> part1 + part2);	
	}
	
	@Override
	public String toString() {
		return getId().toString();
	}
	
	private int lenghtMessageOnRequest() {
		return this.messageOnRequest.length;
	}
			
	public void sayMessageOnCreate() {
		System.out.println("I'm Mr Meeseeks " + this.getId()  + ". Look at meeee!"); 
	}
	
	public void sayMessageOnRequest() {		
		System.out.println(generateMessageOnRequest());
	}
	
	private String generateMessageOnRequest() {
		int randomNum = ThreadLocalRandom.current().nextInt(0, lenghtMessageOnRequest());
		return getMessageOnRequest(randomNum);
	}
	
	public void sayMessageOnDone() {
		System.out.println(this.messageOnDone); 
	}

	public void sayMessageOnExplode() {
		System.out.println(this.messageOnExplode + " " + this.getId().toString());
	}
	
	public void formulateRequest(String request) {
		sayMessageOnRequest();
		setRequest(request);
	}
	
	public void fulfillRequest() {
		doRequest(getRequest(0), getRequest(1));
		stopExisting();
	}

	@Override
	public void doRequest(String accion, String objeto) {
		
		// usar reflection para recorrer los argumentos del metodo doRequest
		
		System.out.println(getRequest() + "\n" + this.messageOnDone);
	}
	
	private void stopExisting() {
		sayMessageOnExplode();		
	}
	
}

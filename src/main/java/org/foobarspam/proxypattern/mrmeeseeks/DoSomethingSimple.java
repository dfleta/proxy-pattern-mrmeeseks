package org.foobarspam.proxypattern.mrmeeseeks;

// T y R son tipos
public interface DoSomethingSimple<T, R> {
	
	void doRequest(T accion, R objeto);

}

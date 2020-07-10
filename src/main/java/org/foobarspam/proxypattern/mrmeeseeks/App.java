package org.foobarspam.proxypattern.mrmeeseeks;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.NavigableSet;
import java.util.TreeSet;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Mr Meeseeks
 */
public class App {
	
    public static void main( String[] args ){

        // Crea una estructura de datos llamada setMrMe donde almacenar los Mr Meeseeks creados
		// Crea una estructura de datos llamada ids donde almacenar los ids de los Mr Meeseeks creados
        
    	/**
    	 *  NavigableSet es un conjunto ordenado de 
    	 *  Mr Meeseeks en funcion de su id.
    	 *  Esta interfaz permite operaciones como acceder al ultimo,
    	 *  al primero, a un subconjunto o hacer pop() entre otras.
    	 */
        NavigableSet<MrMeeseeks> setMrMe = new TreeSet<MrMeeseeks>(Comparator.comparing(MrMeeseeks::getId));
        
        /**
         *  Lista desordenada con las ids de los Mr Meeseeks
         *  Utilizo un ArrayList y no un conjunto para acceder
         *  por posicion y simular que los Mr Meeseeks terminan su tarea
         *  en distinto orden al que son creados.
         */
        ArrayList<Integer> ids = new ArrayList<Integer>();
        
        BoxProxyMrMeeseeks box = BoxProxyMrMeeseeks.getInstanceBox();
               
        box.pushButton(setMrMe);
        System.out.println("Rick: Open Jerry's stupid mayonnaise jar");

        // NavigableSet me permite acceder al ultimo Mr Meeseeks creado
        setMrMe.last().formulateRequest("Open stupid jar");
        System.out.println();
        
        box.pushButton(setMrMe);    
        System.out.println("Summer: I wanna be popular at school!");
        setMrMe.last().formulateRequest("Be popular at school");
        System.out.println();
        
        box.pushButton(setMrMe);
        System.out.println("Beth: I wanna be a more complete woman!");
        setMrMe.last().formulateRequest("Be a more complete woman");
        System.out.println();
        
        /**
         * Escribe la rutina collectIds(setMrMe, ids)
		 * que almacene en un array los ids de los Meeseeks creados. 
         * 
         * Jerry genera un tropel de MrMeeseeks.
         * Apunto cual es el ultimo que no es suyo.
         */
        
        System.out.println("****    Vienen los de Jerry!    **** \n");
        
        MrMeeseeks lastNonJerrysMrMeeseeks = setMrMe.last();
       
        for(int i=1; i<=10; i++) {
        	box.pushButton(setMrMe);
        	System.out.println("Jerry: I would like to take two strokes off my golf game");
        	setMrMe.last().formulateRequest("Take two strokes off my golf game");
        }
        System.out.println("\n **************** \n");
        
        System.out.println("Estos son los Mr Meeseeks creados:");

        /**
         * Escribe la rutina listMrMeeseeks(setMrMe)
		 * que liste los ids de todos los Meeseeks creado.
         * 
         * listMrMeeseeks(setMrMe) haciendo uso de toString()
         * es equivalente a forEach()
         * pero debes sobreescribir el metodo toString()
         * en la clase Mr Meeseeks.
         */
        
        // method reference
        setMrMe.forEach(System.out::println);
        System.out.println();
        
        
        /** 
         * Escribe la rutina explodeMrMeeseeks(setMrMe, id)
		 * elimina del conjunto de Meeseeks creados 
         * el que ya ha completado su cometido.
         * 
         * Generamos al azar el orden en el que los Mr Meeseeks
         * cumplen su solicitud entre los que han generado
         * Beth, Summer y Rick.
         * Los de Jerry explotan desde el ultimo al primero.
         */
        
        System.out.println("Mr Meeseeks cumplen su solicitud"); 
        
        // Subconjunto de MrMeeseeks que no son de Jerry
        
        NavigableSet<MrMeeseeks> subSetMrMe = setMrMe.headSet(lastNonJerrysMrMeeseeks, true);
        collectIds(subSetMrMe, ids);         
        
        while(!subSetMrMe.isEmpty()) {
        	// Explotan aleatoriamente => selecciono un id al azar
        	int randomIndex = ThreadLocalRandom.current().nextInt(0, ids.size());        	 
        	explodeMrMeeseeks(subSetMrMe, ids.get(randomIndex));
        }   
        
        // Subconjunto de MrMeeseeks que SI son de Jerry => explotan en orden
        
        subSetMrMe = (NavigableSet<MrMeeseeks>) setMrMe.tailSet(lastNonJerrysMrMeeseeks);
        explodeMrMeeseeks(subSetMrMe);
              
    }
    
    public static void listMrMeeseeks(Collection<MrMeeseeks> setMrMe) {
    	for(MrMeeseeks mrMe : setMrMe){
    		System.out.println(mrMe.toString());
    	}
    }
    
    public static void explodeMrMeeseeks(NavigableSet<MrMeeseeks> setMrMe, Integer id) {
    	if(setMrMe.isEmpty()) {
    		return;
    	}
    	MrMeeseeks meeseeksToExplode = null;
    	for(MrMeeseeks mrMe : setMrMe) {
    		if(mrMe.getId().equals(id)) {
    			meeseeksToExplode = mrMe;
    			break;
    		}
    	    return;
    	}
    	meeseeksToExplode.sayMessageOnExplode();
    	setMrMe.remove(meeseeksToExplode);
    }
    
    public static void explodeMrMeeseeks(NavigableSet<MrMeeseeks> setMrMe) {
    	while(!setMrMe.isEmpty()) {
    		// pollLast devuelve y elimina el ultimo elemento
    		setMrMe.pollLast().sayMessageOnExplode();
    	}
    }
    
    public static void collectIds(Collection<MrMeeseeks> setMrMe, Collection<Integer> ids) {
    	for(MrMeeseeks mrMe : setMrMe){
    		ids.add(mrMe.getId());
    	}
    }
    
}

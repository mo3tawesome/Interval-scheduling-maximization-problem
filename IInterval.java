/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intervalschedulingmaximization;

/**
 *
 * @author owner
 */
public interface IInterval <E extends Comparable<E>> {
	/**
	 * Returns the begin of the interval.
	 *
	 * @return The begin of the interval
	 */
	public E getBegin();
	
	/**
	 * Returns the end of the interval.
	 * 
	 * @return The end of the interval
	 */
	public E getEnd();
}

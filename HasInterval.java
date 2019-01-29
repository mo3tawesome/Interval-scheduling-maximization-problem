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
public interface HasInterval <E extends Comparable<E>> {
	/**
	 * Returns the interval of the entry.
	 * 
	 * @return The interval of the entry.
	 */
	public IInterval<E> getInterval();
}
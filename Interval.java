/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intervalschedulingmaximization;
public class Interval implements IInterval<Integer> {

	private int start;
	private int end;
	
	public Interval(int start, int end) {
		this.start = start;
		this.end = end;
	}
	
	@Override
	public Integer getBegin() {
		return this.start;
	}

	@Override
	public Integer getEnd() {
		return this.end;
	}

}
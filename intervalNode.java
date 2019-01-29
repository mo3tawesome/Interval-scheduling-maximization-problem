/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intervalschedulingmaximization;

import java.util.ArrayList; 

public class intervalNode implements HasInterval<Integer> {
	
	int min;
	int max;
	int priority;
        int numberOfOverlaps=0;
        int id;
        static int IDs=0;
         ArrayList<intervalNode> overlaps = new ArrayList<intervalNode>();
        
	public intervalNode(int start, int end, int priority) {
		this.min = start;
		this.max = end;
		this.priority = priority;
                this.id=IDs++;
                
	}
	
	@Override
	public IInterval<Integer> getInterval() {
		return new Interval(this.min, this.max);
	}

        public int getMin() {
            return max;
        }

	
	@Override
	public String toString() {
		return "[" + min + ", " + max + "]";
	}
	
	public int getPriority() {
		return this.priority;
	}
        
}
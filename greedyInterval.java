package intervalschedulingmaximization;

import java.util.*;
 
public class greedyInterval implements Comparable<greedyInterval>{
	public int start;
	public int finish;
	public String name;

	public greedyInterval(int start, int finish, String name){
		this.start=start;
		this.finish=finish;
		this.name=name;
	}

	//Compare jobs by finish time
	@Override
	public int compareTo(greedyInterval job) {
		return this.finish - job.finish;
	}
	
	@Override
	public String toString(){
		return "[" + name + ": (" + start + ", " + finish + ")]";
	}

	public static int findOptimalJobScheule(greedyInterval[] jobs){
		//System.out.println("Input Jobs: \t" + Arrays.toString(jobs));
		Arrays.sort(jobs);		//Sort jobs by finish time

		LinkedList<greedyInterval> jobsSelected = new LinkedList<greedyInterval>();
		jobsSelected.add(jobs[0]);		//add 1st job
		greedyInterval lastJobAdded = jobs[0];

		for(int i=1; i<jobs.length; i++){
			if(jobs[i].start >= lastJobAdded.finish){		//check if job is compatible (starts after or at the time time as the last job finishes)
				jobsSelected.add(jobs[i]);
				lastJobAdded = jobs[i];		//update for the job that was just added
			}
		}

		System.out.println("\nGreedy algorithm selected: " + jobsSelected.size() );
		for(greedyInterval job : jobsSelected){
			System.out.println(job);
		}
                return jobsSelected.size();
	}

}

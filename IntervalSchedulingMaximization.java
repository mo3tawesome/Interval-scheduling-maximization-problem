package intervalschedulingmaximization;
 
import java.util.Comparator;
import java.util.LinkedList; 

public class IntervalSchedulingMaximization {
    
    static IntervalTree<Integer, intervalNode> Tree = new IntervalTree<Integer, intervalNode>();
    static int intervalID=0; 
    static LinkedList<intervalNode> input = new LinkedList<intervalNode>(); 
    static LinkedList<intervalNode> inputCopy = new LinkedList<intervalNode>(); 
    static LinkedList<intervalNode> result = new LinkedList<intervalNode>(); 
     

    static private intervalNode getBest(intervalNode n){
        int maxInt = 2147483647;
        intervalNode best = new intervalNode(0,0,0);
        best.numberOfOverlaps=maxInt;
        n.overlaps=Tree.findOverlapping(n.getInterval());
        n.overlaps.sort(Comparator.comparing(intervalNode::getMin));
        for(intervalNode j : n.overlaps){
            j.overlaps=Tree.findOverlapping(j.getInterval());
            j.numberOfOverlaps = j.overlaps.size();
            if(j.numberOfOverlaps<best.numberOfOverlaps){best=j;}
        }
        return best;
    } 
    static private void select (intervalNode n){
        System.out.print("  added:  "+n);
        result.add(n);
        Tree.delete(n);
        input.remove(n); 
        
        System.out.print("  deleted:");
        for(intervalNode j : n.overlaps){
            System.out.print("    "+j);
            Tree.delete(j);
            input.remove(j); 
        }
    }
    //function to check if two intervals are chossing eachother endlessly in a loop
    //i.e the maximum number chosen will remain the same if either was deleted
    static intervalNode x,y; 
    static boolean toggle=true;
    static boolean isAsGood(intervalNode n,intervalNode j){ 
        if(toggle){
            x=n;
            toggle = false;
            if(j==y){
                return true;
            }
            return false;
        }
        else{
            toggle=true;
            y=n;
            if(j==x){
                return true;
            }
            return false;
        }
        
    }
    
    static private intervalNode getHigherPriority(intervalNode n,intervalNode j){
        if(n.priority<j.priority){return j;}
        else{return n;}
    }
    static private intervalNode getLowerPriority(intervalNode n,intervalNode j){
        if(n.priority>=j.priority){return j;}
        else{return n;}
    }
    
    static private void chooseInterval(intervalNode n){ 

        n.overlaps =  Tree.findOverlapping(n.getInterval());
        
        if(!n.overlaps.isEmpty()){
            
            n.numberOfOverlaps = n.overlaps.size();
            System.out.println("\nn   "+n+"   overlaps:   "+n.overlaps.size()); 
            intervalNode j = getBest(n);
            System.out.println("  j   "+j+"   overlaps:   "+j.overlaps.size());

           if( (j.numberOfOverlaps <= n.numberOfOverlaps) ){
               intervalNode best = j;
               if (isAsGood(n,j)){
                   best = getHigherPriority(n,j);
                   intervalNode worst = getLowerPriority(n,j); 
                   Tree.delete(worst);
                   input.remove(worst);
                   System.out.print("       "+worst+" is deleted");
               }
               System.out.print("      "+best+" is better");
               chooseInterval(best);
               
           }
           else{
               select(n);
               if(!input.isEmpty()){
                   chooseInterval(input.get(0));
               }

           }
        }  
        else{
            select(n);
               if(!input.isEmpty()){
                   chooseInterval(input.get(0));
               }
        }
    }
    
    
    static int Greedy(int N){
    greedyInterval jobs[] =  new greedyInterval[N]; 
    for (int j = 0; j < N; j++) {
                int s =inputCopy.get(j).min;
                int f =inputCopy.get(j).max;
                jobs[j] = new greedyInterval (s,f,String.valueOf(j));
            }
		return greedyInterval.findOptimalJobScheule(jobs);
    }  
    
    public static void testExample(){
            input.add(new intervalNode(0,3,1));
            input.add(new intervalNode(1,4,1));
            input.add(new intervalNode(2,5,1));
            input.add(new intervalNode(3,6,1));
            input.add(new intervalNode(4,7,10));
            input.add(new intervalNode(5,8,1));
            input.add(new intervalNode(6,9,1));
            input.add(new intervalNode(7,10,10));
            input.add(new intervalNode(8,11,1));
            for(intervalNode n : input){
                Tree.insert(n);
            }
    }
    
    public static void generateRandomExample(int N, int maxHour){
            for (int i = 0; i < N; i++) { 
                int finish = (int )(Math.random() * maxHour + 1);
                int start = (int )(Math.random() * finish);
                int priority = (int) (Math.random() * 10 + 1);
                intervalNode interval = new intervalNode(start,finish,priority);
                if(!Tree.lookup(interval.getInterval()).isEmpty()){i--;} //to avoid repeating an interval
                else{
                    Tree.insert(interval);
                    input.add(interval);
                }
            }
    }
    
    public static void main(String[] args) {
        int N = 9;
        int maxHour = 125;
        
        int mine=0;//my result
        int greedy=0;// greedy result
        
        while(greedy==mine){
            result.clear();
            input.clear();
            Tree = new IntervalTree<Integer, intervalNode>(); 
            inputCopy.clear();
            //testExample();
            generateRandomExample(N,maxHour);
            // sort input based on start time:
            input.sort(Comparator.comparing(intervalNode::getMin));
            System.out.println("\n\ninput:  "+input);
            for(intervalNode i : input){ // make a copy of the input for the greedy algorithm to work with
                inputCopy.add(i);
            } 
            
            chooseInterval(input.get(0));
            System.out.println("\n\nMy algorithm selected: "+result.size());
            System.out.println(result);
            mine = result.size();
            
            greedy = Greedy(N);
        }
        

    }
    
    
}

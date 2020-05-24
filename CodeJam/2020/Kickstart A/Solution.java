//make sure to make new file!
import java.io.*;
import java.util.*;
//prob 3
public class Solution{
   
    public static void main(String[] args)throws IOException{
        BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        
        int t = Integer.parseInt(f.readLine());
        
        for(int q = 1; q <= t; q++){
        
            StringTokenizer st = new StringTokenizer(f.readLine());
            
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            
            int[] array = new int[n];
            st = new StringTokenizer(f.readLine());
            for(int k = 0; k < n; k++){
                array[k] = Integer.parseInt(st.nextToken());
            }
            PriorityQueue<State> pq = new PriorityQueue<State>(n-1,Collections.reverseOrder());
            for(int k = 1; k < n; k++){
                pq.add(new State(array[k]-array[k-1],0));
            }
            
            for(int k = 0; k < m; k++){
                State s = pq.poll();
                pq.add(new State(s.i,s.n+1));
            }
            
            int answer = Math.max(1,pq.peek().calc());
            out.println("Case #" + q + ": " + answer);
            
        
        }
        
        
        out.close();
    }
    
    public static class State implements Comparable<State>{
        int i;  //interval length
        int n;  //number of splits
        public State(int a, int b){
            i = a;
            n = b;
        }
        public int calc(){
            return (int)Math.ceil((double)(i)/(double)(n+1));
        }
        public int compareTo(State s){
            return calc()-s.calc();
            
        }   
    }

      
}
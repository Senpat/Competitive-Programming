//make sure to make new file!
import java.io.*;
import java.util.*;

public class C781{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         int n = Integer.parseInt(f.readLine());
      
         StringTokenizer st = new StringTokenizer(f.readLine());
         int[] numchild = new int[n+1];
         for(int k = 0; k < n-1; k++){
            int i = Integer.parseInt(st.nextToken());
            numchild[i]++;
         }
         
         ArrayList<Integer> alist = new ArrayList<Integer>();
         alist.add(1);
         for(int k = 1; k <= n; k++){
            if(numchild[k] > 0) alist.add(numchild[k]);
         }
         
         Collections.sort(alist);
         
         //stores the time that that node will finish
         PriorityQueue<Integer> pq = new PriorityQueue<Integer>(10,Collections.reverseOrder());
         
         int timestep = 1;
         for(int k = alist.size()-1; k >= 0; k--){
            //infect that node
            pq.add(alist.get(k)+timestep-1);
            timestep++;
         }
         
         while(!pq.isEmpty()){
            int i = pq.poll();
            
            if(timestep > i){
               //already finished
               continue;
            }
            
            pq.add(i-1);
            timestep++;
         }
         
         out.println(timestep-1);
         
      

      }
      
      
      
      
      out.close();
   }
   
}
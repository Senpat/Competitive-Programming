//make sure to make new file!
import java.io.*;
import java.util.*;

public class BG2b{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      int[] array = new int[n];
      
      st = new StringTokenizer(f.readLine());
      for(int k = 0; k< n; k++){
         array[k] = Integer.parseInt(st.nextToken());
      }
      
      
      
      
      for(int a = 1; a <= n; a++){
         PriorityQueue<Integer> pq = new PriorityQueue<Integer>(a,Collections.reverseOrder());
         for(int k = 0; k < a; k++){
            pq.add(array[k]);
         }
         
         //HashSet<Integer> shelves = new HashSet<Integer>();
         HashSet<Integer> otherused = new HashSet<Integer>();
         
         ArrayList<Integer> shelf = new ArrayList<Integer>();
         shelf.add(0);
         
         int lastshelf = 0;
         while(!pq.isEmpty()){
            int p = pq.poll();
            if(m-lastshelf >= p){
               if(!pq.isEmpty()) pq.poll();
               lastshelf+=p;
            } else {
               out.println(a-1);
               out.close();
               System.exit(0);
               
            }
         }
         
      }
      out.println(n);
         
         
         

      
      
      
      
      out.close();
   }
   
      
}
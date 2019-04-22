//make sure to make new file!
import java.io.*;
import java.util.*;

public class BG2{
   
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
         
         while(!pq.isEmpty()){
            int p = pq.poll();
            
            //check if can place on shelf
            if(m-shelf.get(shelf.size()-1) >= p){
               //place shelf at p
               shelf.add(shelf.get(shelf.size()-1)+p);
            } else {
               if(shelf.get(shelf.size()-1) != m){
                  shelf.add(m);
               }
               //check if you can place on defined shelf
               boolean added = false;
               for(int i = 1; i < shelf.size(); i++){
                  if(otherused.contains(i)) continue;
                  if(shelf.get(i) - shelf.get(i-1) >= p){
                     added = true;
                     otherused.add(i);
                     break;
                  }
               }
               
               if(!added){
                  out.println(a-1);
                  out.close();
                  System.exit(0);
               }
            }
         }
      }
      out.println(n);
         
         
         

      
      
      
      
      out.close();
   }
   
      
}
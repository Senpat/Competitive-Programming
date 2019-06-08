//make sure to make new file!
import java.io.*;
import java.util.*;

public class B560{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      for(int k = 0; k < n; k++){
         pq.add(Integer.parseInt(st.nextToken()));
      }
      
      int k = 1;
      while(!pq.isEmpty()){
         int i = pq.poll();
         if(i >= k){
            k++;
         }
      }
      k--;
      
      out.println(k);
      
      

      
      
      
      
      out.close();
   }
   
      
}
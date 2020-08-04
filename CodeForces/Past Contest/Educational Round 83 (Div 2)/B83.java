//make sure to make new file!
import java.io.*;
import java.util.*;

public class B83{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         int n = Integer.parseInt(f.readLine());
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         PriorityQueue<Integer> pq = new PriorityQueue<Integer>(n,Collections.reverseOrder());
         
         for(int k = 0; k < n; k++){
            pq.add(Integer.parseInt(st.nextToken()));
         }
         
         StringJoiner sj = new StringJoiner(" ");
         while(!pq.isEmpty()){
            sj.add("" + pq.poll());
         }
         
         out.println(sj.toString());

      }
      
      
      
      
      out.close();
   }
   
      
}
//make sure to make new file!
import java.io.*;
import java.util.*;

public class C69{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      PriorityQueue<Integer> pq = new PriorityQueue<Integer>(10,Collections.reverseOrder());
      
      int sumdiffs = 0;
      int[] array = new int[n];
      st = new StringTokenizer(f.readLine());
      for(int k = 0; k < n; k++){
         array[k] = Integer.parseInt(st.nextToken());
         if(k > 0){
            int dif = array[k]-array[k-1];
            pq.add(dif);
            sumdiffs+=dif;
         }
      }
      
      for(int k = 0; k < m-1; k++){
         sumdiffs-=pq.remove();
      }
      
      out.println(sumdiffs);

      
      
      
      
      out.close();
   }
   
      
}
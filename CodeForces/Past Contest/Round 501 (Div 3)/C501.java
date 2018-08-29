//Songs Compression
import java.io.*;
import java.util.*;

public class C501{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      PriorityQueue<Integer> pq = new PriorityQueue<Integer>(n,Collections.reverseOrder());
      
      long sum = 0L;
      for(int k = 0; k < n; k++){
         st = new StringTokenizer(f.readLine());
         int q = Integer.parseInt(st.nextToken());
         int w = Integer.parseInt(st.nextToken());
         pq.add(q-w);
         sum+=q;
      }
      
      int count = 0;
      while(sum > m && !pq.isEmpty()){
         sum-=pq.poll();
         count++;
      }
      
      if(sum<=m){
         System.out.println(count);
      } else {
         System.out.println(-1);
      }
   }
}
      
      
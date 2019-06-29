//make sure to make new file!
import java.io.*;
import java.util.*;
//semi-t
public class D560b{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      
      
      for(int q = 0; q < t; q++){
         
         int n = Integer.parseInt(f.readLine());
         
         StringTokenizer st = new StringTokenizer(f.readLine());
         long[] array = new long[n];
         
         for(int k = 0; k < n; k++){
            array[k] = Long.parseLong(st.nextToken());
         }
         
         Arrays.sort(array);
         
         
         long N = array[0] * array[n-1];
         
         PriorityQueue<Long> pq = new PriorityQueue<Long>();
         for(long k = 2; k*k <= N; k++){
            if(k*k==N) pq.add(k);
            else {
               if(N%k == 0){
                  pq.add(k);
                  pq.add(N/k);
               }
            }
         }
         
         if(checkequal(array,pq)){
            out.println(N);
         } else {
            out.println(-1);
         }
         
      
         
      
      }
      
      
      
      
      out.close();
   }
   
   public static boolean checkequal(long[] array, PriorityQueue<Long> pq){
      if(array.length != pq.size()) return false;
      for(int k = 0; k < array.length; k++){
         if(array[k] != pq.poll()) return false;
      }
      return true;
   }

      
}
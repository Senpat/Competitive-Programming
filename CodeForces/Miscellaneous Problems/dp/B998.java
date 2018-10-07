//Cutting
import java.io.*;
import java.util.*;

public class B998{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
      
      st = new StringTokenizer(f.readLine());
      
      int[] array = new int[n];
      
      for(int k = 0; k < n; k++){
         array[k] = Integer.parseInt(st.nextToken());
      }
      
      int o = array[0]%2==0 ? 0 : 1;
      int e = o==0 ? 1 : 0;
      
      for(int k = 1; k < n-1; k++){
         if(array[k]%2==0) e++;
         else o++;
         
         if(o==e){
            pq.add(Math.abs(array[k]-array[k+1]));
         }
      }
      
      if(pq.isEmpty()){
         out.println(0);
         out.close();
         System.exit(0);
      }
      int count = 0;
      int cur = pq.remove();
      while(m-cur>=0 && !pq.isEmpty()){
         m-=cur;
         cur = pq.remove();
         count++;
      }
      if(m-cur>=0) count++;
      
      out.println(count);
      
      
      
      out.close();
   }
   
}
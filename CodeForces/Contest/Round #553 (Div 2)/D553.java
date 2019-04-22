//make sure to make new file!
import java.io.*;
import java.util.*;

public class D553{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      long suma = 0L;
      long sumb = 0L;
      PriorityQueue<Long> pq = new PriorityQueue<Long>();
      for(int k = 0; k < n; k++){
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         long a = Long.parseLong(st.nextToken());
         long b = Long.parseLong(st.nextToken());
         
         suma += a;
         sumb += b;
         pq.add(a-b);
      }
      
      long sumc = 0;
      for(long k = n; k > 0 && !pq.isEmpty(); k--){
         long i = pq.poll();
         sumc += k * i;
      }
      
      long answer = sumc + sumb * n - suma;
      out.println(answer);
      
      
      
      
      out.close();
   }
   
      
}
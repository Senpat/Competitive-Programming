//Count Pairs
import java.io.*;
import java.util.*;
//semi-t
public class B1188{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      long p = Long.parseLong(st.nextToken());
      long m = Long.parseLong(st.nextToken());
      
      HashMap<Long,Long> hmap = new HashMap<Long,Long>();
      
      long answer = 0L;
      
      long[] a = new long[n];
      st = new StringTokenizer(f.readLine());
      for(int k = 0; k < n; k++){
         a[k] = Long.parseLong(st.nextToken());
      }
      
      for(int k = 0; k < n; k++){
         long i = (a[k]*a[k]+p)%p;
         i = (i*i+p)%p;
         i += p - (m*a[k]+p)%p;
         i = (i+p)%p;
         if(hmap.containsKey(i)){
            answer+=hmap.get(i);
            hmap.put(i,hmap.get(i)+1);
         } else {
            hmap.put(i,1L);
         }
      }
      
      
      out.println(answer);
      
      
      
      
      out.close();
   }
   
      
}
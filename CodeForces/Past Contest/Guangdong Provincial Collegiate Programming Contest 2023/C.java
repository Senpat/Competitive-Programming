//make sure to make new file!
import java.io.*;
import java.util.*;

public class C{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int qq = 1; qq <= t; qq++){

         int n = Integer.parseInt(f.readLine());
         
         Store[] stores = new Store[n];
         
         long total = 0L;
         for(int k = 0; k < n; k++){
            StringTokenizer st = new StringTokenizer(f.readLine());
            
            long p = Long.parseLong(st.nextToken());
            long q = Long.parseLong(st.nextToken());
      
            total += q;
         
            stores[k] = new Store(p,q);
         }
         
         Arrays.sort(stores);
         
         long thresh = total/2;
         long seen = 0L;
         
         long answer = 0L;
         for(int k = 0; k < n; k++){
            if(seen + stores[k].q <= thresh) answer -= stores[k].p * stores[k].q;
            else{
               long q = thresh-seen;
               answer -= stores[k].p * q;
               break;
            }
            seen += stores[k].q;
         }
         
         seen = 0;
         for(int k = n-1; k >= 0; k--){
            if(seen + stores[k].q <= thresh) answer += stores[k].p * stores[k].q;
            else{
               long q = thresh-seen;
               answer += stores[k].p * q;
               break;
            }
            seen += stores[k].q;
         }
         
         out.println(answer);
         
         
         
         
         
         
      }
      
      
      
      
      out.close();
   }
   
   
   public static class Store implements Comparable<Store>{
      long p;
      long q;
      public Store(long a, long b){
         p = a;
         q = b;
      }
      
      public int compareTo(Store s){
         if(p < s.p) return -1;
         if(p > s.p) return 1;
         return 0;
      }
   }
      
}
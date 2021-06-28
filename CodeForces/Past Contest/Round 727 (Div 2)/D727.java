//make sure to make new file!
import java.io.*;
import java.util.*;

public class D727{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      Prod[] prods = new Prod[n];
      for(int k = 0; k < n; k++){
         StringTokenizer st = new StringTokenizer(f.readLine());
         
         long a = Long.parseLong(st.nextToken());
         long b = Long.parseLong(st.nextToken());
         
         prods[k] = new Prod(a,b);
      }
      
      Arrays.sort(prods);
      
      int l = 0;
      int r = n-1;
      
      long answer = 0L;
      long used = 0L;
      
      while(l <= r){
         //if can't get discount on l, use r until you can
         if(used < prods[l].b){
            if(prods[r].a < prods[l].b-used){
               answer += prods[r].a*2L;
               used += prods[r].a;
               prods[r].a = 0;
               r--;
            } else {
               answer += (prods[l].b-used)*2L;
               prods[r].a -= prods[l].b-used;
               used = prods[l].b;
            }
         } else {
            //use all of l
            answer += prods[l].a;
            used += prods[l].a;
            prods[l].a = 0;
            l++;
         }
      }
      
      out.println(answer);
      
      
      
      
      
      
      
      
      
      out.close();
   }
   
   public static class Prod implements Comparable<Prod>{
      long a;
      long b;
      public Prod(long c, long d){
         a = c;
         b = d;
      }
      
      //sort by b
      public int compareTo(Prod p){
         if(b < p.b) return -1;
         if(b > p.b) return 1;
         return 0;
      }
   }
   
      
}
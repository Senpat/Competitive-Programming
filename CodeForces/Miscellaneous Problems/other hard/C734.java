//make sure to make new file!
import java.io.*;
import java.util.*;

public class C734{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      long n = Long.parseLong(st.nextToken());
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());
      
      st = new StringTokenizer(f.readLine());
      
      long x = Long.parseLong(st.nextToken());
      long s = Long.parseLong(st.nextToken());
      
      A[] as = new A[a];
      
      long[] b1 = new long[b];                    //potions immediately created
      long[] b2 = new long[b];                    //cost
      
      st = new StringTokenizer(f.readLine());
      StringTokenizer st2 = new StringTokenizer(f.readLine());
   
      
      for(int k = 0; k < a; k++){
         as[k] = new A(Long.parseLong(st.nextToken()),Long.parseLong(st2.nextToken()));
      }
      
      Arrays.sort(as);
           
      st = new StringTokenizer(f.readLine());
      
      for(int k = 0; k < b; k++){
         b1[k] = Long.parseLong(st.nextToken());
      }
      
      st = new StringTokenizer(f.readLine());
      
      for(int k = 0; k < b; k++){
         b2[k] = Long.parseLong(st.nextToken());
      }
      
      
      
      int bp = b-1;
      
      long answer = n*x;
      
      for(int k = 0; k < a; k++){
         
         while(bp >= 0 && as[k].cost + b2[bp] > s){
            bp--;
         }
         
         if(bp < 0) break;
         
         
         answer = Math.min(answer,(n-b1[bp])*as[k].val);
      }
      
      //check for only a or only b
      for(int k = 0; k < a; k++){
         if(as[k].cost <= s){
            answer = Math.min(answer,n*as[k].val);
         }
      }
      
      for(int k = 0; k < b; k++){
         if(b2[k] <= s){
            answer = Math.min(answer,(n-b1[k])*x);
         }
      }
      
      
      out.println(answer);
      
      
      
   
      
      
      
      
      
      out.close();
   }
   
   public static class A implements Comparable<A>{
      long val;
      long cost;
      
      public A(long a, long b){
         val = a;
         cost = b;
      }
      
      public int compareTo(A a){
         if(cost > a.cost) return 1;
         if(cost < a.cost) return -1;
         return 0;
      }
   }
   
      
}
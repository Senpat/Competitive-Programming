//make sure to make new file!
import java.io.*;
import java.util.*;

public class C85{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         int n = Integer.parseInt(f.readLine());
         
         Monster[] array = new Monster[n];
         for(int k = 0; k < n; k++){
            StringTokenizer st = new StringTokenizer(f.readLine());
         
            long a = Long.parseLong(st.nextToken());
            long b = Long.parseLong(st.nextToken());
            
            array[k] = new Monster(a,b);
         }
         
         //calc first
         long count = array[0].a;
         
         for(int k = 1; k < n; k++){
            count += Math.max(0,array[k].a-array[k-1].b);
         }
         
         long min = count;
         long prev = count;
         for(int k = 1; k < n; k++){
            long cur = prev + array[k].a - Math.max(0,array[k].a-array[k-1].b) - array[k-1].a + Math.max(0,array[k-1].a-array[(k-2+n)%n].b);
            min = Math.min(min,cur);
            prev = cur;
         }
         
         out.println(min);
         
      
      }
      
      
      
      
      out.close();
   }
   
   public static class Monster{
      long a;
      long b;
      public Monster(long c, long d){
         a = c;
         b = d;
      }
   }
   
      
}
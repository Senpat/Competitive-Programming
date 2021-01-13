//make sure to make new file!
import java.io.*;
import java.util.*;

public class C101{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         long m = Long.parseLong(st.nextToken());
         
         long[] array = new long[n];
         st = new StringTokenizer(f.readLine());
         for(int k = 0; k < n; k++){
            array[k] = Integer.parseInt(st.nextToken());
         }
         
         if(check(n,m,array)){
            out.println("YES");
         } else {
            out.println("NO");
         }

      }
      
      
      
      
      out.close();
   }
   
   public static boolean check(int n, long m, long[] array){
      long max = array[0];
      long min = array[0];
      
      for(int k = 1; k < n; k++){
         long newmax = Math.min(array[k] + m-1, max+m-1);
         long newmin = Math.max(array[k], min-(m-1));
         
         if(newmax < newmin) return false;
         
         max = newmax;
         min = newmin;
      }
      
      return array[n-1] >= min && array[n-1] <= max;
   }
      
}
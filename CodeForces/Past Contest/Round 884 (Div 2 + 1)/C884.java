//make sure to make new file!
import java.io.*;
import java.util.*;

public class C884{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         int n = Integer.parseInt(f.readLine());
      
         StringTokenizer st = new StringTokenizer(f.readLine());
         long[] array = new long[n];
         long max = Long.MIN_VALUE;
         for(int k = 0; k < n; k++){
            array[k] = Long.parseLong(st.nextToken());
            max = Math.max(max,array[k]);
         }
         
         if(max <= 0L){
            out.println(max);
            continue;
         }
         
         ArrayList<Long> odd = new ArrayList<Long>();
         ArrayList<Long> even = new ArrayList<Long>();
         
         for(int k = 0; k < n; k++){
            if(k % 2 == 0) even.add(array[k]);
            else odd.add(array[k]);
         }
         
         long sumodd = 0L;
         for(long i : odd){
            if(i > 0L) sumodd += i;
         }
         long sumeven = 0L;
         for(long i : even){
            if(i > 0L) sumeven += i;
         }
         
         long answer = Math.max(sumodd,sumeven);
         out.println(answer);
      
      

      }
      
      
      
      
      out.close();
   }
   
      
}
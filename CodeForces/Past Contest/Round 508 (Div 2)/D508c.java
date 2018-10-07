//Slime
//tutorial
import java.io.*;
import java.util.*;

public class D508c{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      long min = Long.MAX_VALUE;
      long sum = 0L;
      
      long[] array = new long[n];
      boolean pos = true;
      boolean neg = true;
      for(int k = 0; k < n; k++){
         array[k] = Long.parseLong(st.nextToken());
         if(array[k]<0) pos = false;
         if(array[k]>0) neg = false;
         min = Math.min(Math.abs(array[k]),min);
         sum+=Math.abs(array[k]);
      }
      
      if(n==1){
         out.println(array[0]);
      } else if(pos || neg){
         out.println(sum-2*min);
      } else {
         out.println(sum);
      }
         
      
      
      out.close();
   }
   
}
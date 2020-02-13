//make sure to make new file!
import java.io.*;
import java.util.*;

public class B79b{
   
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
            array[k] = Long.parseLong(st.nextToken());
         }
         
         
         
         long sum = 0L;
         long max = 0L;
         int maxindex = 0;
         
         int numsans = -1;
         int numavec = -1;
         for(int k = 0; k < n; k++){
            sum+=array[k];
            if(array[k] > max && numsans == -1){
               max = array[k];
               maxindex = k+1;
            }
            
            if(sum > m){
               if(numsans == -1){
                  numsans = k;
                  sum -= max;
               } else {
                  numavec = k-1;
                  break;
               }
            }
         }
         
         if(numsans == -1){
            numsans = n;
         } else if(numavec == -1){
            numavec = n-1;
         }
         
         
         
         if(numsans >= numavec){
            out.println(0);
         } else {
            out.println(maxindex);
         }
      }
      
      
      
      
      out.close();
   }
   
      
}
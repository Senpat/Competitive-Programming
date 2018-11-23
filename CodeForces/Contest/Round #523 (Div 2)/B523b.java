//make sure to make new file!
import java.io.*;
import java.util.*;

public class B523b{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      if(n == 1){
         out.println(0);
         out.close();
         System.exit(0);
      }
      
      st = new StringTokenizer(f.readLine());
      
      int[] array = new int[n];
      
      long sum = 0L;
      for(int k = 0; k < n; k++){
         array[k] = Integer.parseInt(st.nextToken());
         sum += array[k];
      }
      
      Arrays.sort(array);
      
      int last;
      long total = 0L;
      if(array[n-1] == array[n-2]){
         last = array[n-1];
         total += 1;
      } else {
         last = array[n-2]+1;
         total += array[n-1]-array[n-2];
      }
      
      for(int k = n-2; k > 0; k--){
         if(array[k] == 1){
            total++;
            continue;
         }
         if(last-1 <= array[k-1]){
            total += 1;
            //last = array[k-1];
            last = Math.max(0,last-1);
         } else {
            total += last-1-array[k-1];
            last = array[k-1]+1;
         }
      }
      
      total+=Math.max(1,last-1);
      
      out.println(sum-total);
            
      
      out.close();
   }
   
}
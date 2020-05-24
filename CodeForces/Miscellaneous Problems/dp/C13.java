//Sequence
import java.io.*;
import java.util.*;
//basically same as C713 Sonya and Problem Wihtout a Legend, used tutorial/comments
public class C13{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      long[] array = new long[n];
      long[] sort = new long[n];
      
      for(int k = 0; k < n; k++){
         array[k] = Long.parseLong(st.nextToken());
         sort[k] = array[k];
      }
      
      Arrays.sort(sort);
      
      
      long[] prev = new long[n];
      long[] cur = new long[n];
      Arrays.fill(prev,Long.MAX_VALUE);
      Arrays.fill(cur,Long.MAX_VALUE);
      
      prev[0] = Math.abs(array[0]-sort[0]);
      
      for(int k = 0; k < n; k++){
         for(int j = 0; j < n; j++){
            if(k < n-1){
               cur[j] = Math.min(cur[j],prev[j] + Math.abs(array[k+1]-sort[j]));
            }
            if(j < n-1){
               if(k == 0) prev[j+1] = Math.min(prev[j+1],Math.abs(array[k]-sort[j+1]));
               prev[j+1] = Math.min(prev[j+1],prev[j]);
            }
         }
         
         if(k < n-1){
            for(int j = 0; j < n; j++){
               prev[j] = cur[j];
               cur[j] = Long.MAX_VALUE;
            }
         }
      }
      
      out.println(prev[n-1]);
      
      
      
      
      
      
      
      out.close();
   }
   
      
}
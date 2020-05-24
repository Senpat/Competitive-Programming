//make sure to make new file!
import java.io.*;
import java.util.*;

public class C631{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      st = new StringTokenizer(f.readLine());
      int[] array = new int[m];
      long sum = 0L;
      for(int k = 0; k < m; k++){
         array[k] = Integer.parseInt(st.nextToken());
         sum += (long)array[k];
      }
      
      if(sum < (long)n){
         out.println("-1");
         out.close();
         return;
      }
      
      int[] answer = new int[m];
      int[] max = new int[m];          //last cell covered after nth operation
      
      for(int k = 0; k < m; k++){
         answer[k] = k+1;
         if(answer[k] + array[k]-1> n){
            out.println("-1");
            out.close();
            return;
         }
         if(k==0) max[k] = answer[k]+array[k]-1;
         else max[k] = Math.max(max[k-1],answer[k]+array[k]-1);
      }
      
      
      int end = n;
      int index = m-1;
      while(max[index] < end){
         answer[index] = end-array[index]+1;
         end=end-array[index];
         index--;
      }
      
      StringJoiner sj = new StringJoiner(" ");
      for(int k = 0; k < m; k++){
         sj.add("" + answer[k]);
      }
      
      out.println(sj.toString());
      

      
      out.close();
   }
   
      
}
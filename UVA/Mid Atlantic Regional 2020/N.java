//make sure to make new file!
import java.io.*;
import java.util.*;

public class N{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      long[] array = new long[n];
      HashSet<Long> hset = new HashSet<Long>();
      for(int k = 0; k < n; k++){
         array[k] = Long.parseLong(st.nextToken());
         hset.add(array[k]);
      }
      
      long answer = 0L;
      for(int k = 0; k < n; k++){
         if(!hset.contains(array[k]-1)) answer += array[k];
      }
      
      out.println(answer);
      
      
      
      
      
      
      
      
      out.close();
   }
   
      
}
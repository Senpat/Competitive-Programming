//make sure to make new file!
import java.io.*;
import java.util.*;

public class G{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      long[] array = new long[n];
      st = new StringTokenizer(f.readLine());
      for(int k = 0; k < n; k++){
         array[k] = Long.parseLong(st.nextToken());
      }
      
      Arrays.sort(array);
      
      long answer = 0L;
      for(int k = 0; k < m; k++){
         answer += array[n-k-1];
      }
      
      out.println(answer);
      
      
      
      
      
      
      
      
      out.close();
   }
   
      
}
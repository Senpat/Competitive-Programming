//make sure to make new file!
import java.io.*;
import java.util.*;

public class BM13{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         int m = Integer.parseInt(st.nextToken());
         
         int answer = 0;
         st = new StringTokenizer(f.readLine());
         for(int k = 0; k < m; k++){
            int i = Integer.parseInt(st.nextToken());
            if(i <= m) answer++;
         }
         
         out.println(m-answer);

      }
      
      
      
      
      out.close();
   }
   
      
}
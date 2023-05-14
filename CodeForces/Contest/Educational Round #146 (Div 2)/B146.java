//make sure to make new file!
import java.io.*;
import java.util.*;

public class B146{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
         
         int N = 50000;
         
         int answer = Integer.MAX_VALUE;
         //k is all final leg lengths
         for(int k = 1; k <= N; k++){
            int curanswer = (k-1) + (a+k-1)/k + (b+k-1)/k;
            answer = Math.min(answer,curanswer);
         }
         
         out.println(answer);

      }
      
      
      
      
      out.close();
   }
   
      
}
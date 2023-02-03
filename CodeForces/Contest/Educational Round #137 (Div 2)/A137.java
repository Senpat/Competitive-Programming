//make sure to make new file!
import java.io.*;
import java.util.*;

public class A137{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         int n = Integer.parseInt(f.readLine());
      
         StringTokenizer st = new StringTokenizer(f.readLine());
         for(int k = 0; k < n; k++){
            int i = Integer.parseInt(st.nextToken());
         }
         
         int N = 10-n;
         
         int answer = 6*N*(N-1)/2;
         
         out.println(answer);

      }
      
      
      
      
      out.close();
   }
   
      
}
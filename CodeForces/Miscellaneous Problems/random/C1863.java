//MEX Repetition
import java.io.*;
import java.util.*;

public class C1863{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         int m = Integer.parseInt(st.nextToken());
         
         st = new StringTokenizer(f.readLine());
         int[] array = new int[n+1];
         long sum = 0L;
         for(int k = 0; k < n; k++){
            array[k] = Integer.parseInt(st.nextToken());
            sum += (long)array[k];
         }
         
         long nl = (long)n;
         array[n] = (int)(nl * (nl+1) / 2L - sum);
         
         int[] answer = new int[n+1];
         //shift by m
         for(int k = 0; k <= n; k++){
            answer[(k+m)%(n+1)] = array[k];
         }
         
         StringJoiner sj = new StringJoiner(" ");
         for(int k = 0; k < n; k++){
            sj.add("" + answer[k]);
         }
         out.println(sj.toString());
      

      }
      
      
      
      
      out.close();
   }
   
      
}
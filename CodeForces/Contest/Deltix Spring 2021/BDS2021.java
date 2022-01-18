//make sure to make new file!
import java.io.*;
import java.util.*;

public class BDS2021{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         int n = Integer.parseInt(f.readLine());
      
         StringTokenizer st = new StringTokenizer(f.readLine());
         long[] array = new long[n];
         for(int k = 0; k < n; k++){
            array[k] = Long.parseLong(st.nextToken());
         }
         
         out.println(n*3);
         StringJoiner sj = new StringJoiner("\n");
         for(int k = 0; k < n; k+=2){
            String q1 = "1 " + (k+1) + " " + (k+2);
            String q2 = "2 " + (k+1) + " " + (k+2);
            sj.add(q1);
            sj.add(q1);
            sj.add(q2);
            sj.add(q1);
            sj.add(q1);
            sj.add(q2);
         }
         out.println(sj.toString());
      

      }
      
      
      
      
      out.close();
   }
   
      
}
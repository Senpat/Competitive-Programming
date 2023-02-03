//make sure to make new file!
import java.io.*;
import java.util.*;

public class D141{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      
      long[][] array = new long[300][2*300*300];
      for(int k = 0; k < 300; k++){
         for(int j = 0; j < 2*300*300; j++){
            array[k][j] = k+j+n;
         }
      }
      
      long sum = 0L;
      for(int k = 0; k < 300; k++){
         for(int j = 0; j < 2*300*300; j++){
            sum += array[k][j];
         }
      }
      
      out.println(sum);
      
      
      
      
      
      out.close();
   }
   
      
}
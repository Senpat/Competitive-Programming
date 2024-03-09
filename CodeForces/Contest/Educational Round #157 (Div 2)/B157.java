//make sure to make new file!
import java.io.*;
import java.util.*;

public class B157{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         int n = Integer.parseInt(f.readLine());
      
         StringTokenizer st = new StringTokenizer(f.readLine());
         Integer[] array = new Integer[2*n];
         for(int k = 0; k < 2*n; k++){
            array[k] = Integer.parseInt(st.nextToken());
         }
         
         Arrays.sort(array);
         int sum = 0;
         for(int k = 0; k < n-1; k++){
            sum += array[k+1] - array[k];
            sum += array[k+1+n] - array[k+n];
         }
         
         StringJoiner sj = new StringJoiner("\n");
         sj.add("" + sum);
         for(int k = 0; k < n; k++){
            sj.add("" + array[k] + " " + array[k+n]);
         }
         out.println(sj.toString());
         
      
      

      }
      
      
      
      
      out.close();
   }
   
      
}
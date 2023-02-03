//make sure to make new file!
import java.io.*;
import java.util.*;

public class BGB22{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         int m = Integer.parseInt(st.nextToken());
         
         int[] array = new int[n+1];
         
         int i = 1;
         for(int k = m; k < n; k += m){
            array[k] = i;
            i++;
         }
         
         for(int k = n; k >= 1; k--){
            if(array[k] == 0){
               array[k] = i;
               i++;
            }
         }
         
         StringJoiner sj = new StringJoiner(" ");
         for(int k = 1; k <= n; k++){
            sj.add("" + array[k]);
         }
         out.println(sj.toString());
      

      }
      
      
      
      
      out.close();
   }
   
      
}
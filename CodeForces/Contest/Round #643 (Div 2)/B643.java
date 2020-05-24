//make sure to make new file!
import java.io.*;
import java.util.*;

public class B643{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         int n = Integer.parseInt(f.readLine());
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         Integer[] array = new Integer[n];
         for(int k = 0; k < n; k++){
            array[k] = Integer.parseInt(st.nextToken());
            
         }
         
         Arrays.sort(array);
         
         int lastindex = -1;
         int answer = 0;
         
         for(int k = 0; k < n; k++){
            if(array[k] <= k-lastindex){
               answer++;
               lastindex += array[k];
            }
         }
         
         out.println(answer);
         
      

      }
      
      
      
      
      out.close();
   }
   
      
}
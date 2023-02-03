//make sure to make new file!
import java.io.*;
import java.util.*;

public class A845{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         int n = Integer.parseInt(f.readLine());
      
         StringTokenizer st = new StringTokenizer(f.readLine());
         int[] array = new int[n];
         
         for(int k = 0; k < n; k++){
            array[k] = Integer.parseInt(st.nextToken());
         }
         
         int par = array[0]%2;
         int streak = 1;
         int answer = 0;
         for(int k = 1; k < n; k++){
            int curpar = array[k]%2;
            if(curpar == par){
               streak++;
            } else {
               answer += streak-1;
               streak = 1;
               par = curpar;
            }
         }
         
         answer += streak-1;
         
         out.println(answer);

      }
      
      
      
      
      out.close();
   }
   
      
}
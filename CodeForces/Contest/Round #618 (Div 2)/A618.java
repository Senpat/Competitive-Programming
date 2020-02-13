//make sure to make new file!
import java.io.*;
import java.util.*;

public class A618{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         int n = Integer.parseInt(f.readLine());
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int[] array = new int[n];
         int num0 = 0;
         int sum = 0;
         
         for(int k = 0; k < n; k++){
            array[k] = Integer.parseInt(st.nextToken());
            if(array[k] == 0){
               num0++;
            }
            sum+=array[k];
         }
         
         if(sum == -1*num0){
            out.println(num0+1);
         } else{
            out.println(num0);
         }
      

      }
      
      
      
      
      out.close();
   }
   
      
}
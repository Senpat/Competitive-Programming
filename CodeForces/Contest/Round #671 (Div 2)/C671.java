//make sure to make new file!
import java.io.*;
import java.util.*;

public class C671{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         int m = Integer.parseInt(st.nextToken());
         
         int[] array = new int[n];
         st = new StringTokenizer(f.readLine());
         for(int k = 0; k < n; k++){
            array[k] = Integer.parseInt(st.nextToken());
         }
         
         int dif = 0;
         boolean allm = true;
         int numm = 0;
         int sum = 0;
         
         for(int k = 0; k < n; k++){
            dif += m-array[k];
            if(array[k] != m) allm = false;
            
            if(array[k] == m) numm++;
            sum += array[k];
         }
         
         if(allm){
            out.println(0);
         } else if(dif == 0 || numm >= 1){
            out.println(1);
         } else {
            out.println(2);
         }
         

      }
      
      
      
      
      out.close();
   }
   
      
}
//make sure to make new file!
import java.io.*;
import java.util.*;

public class BCTON{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         int x = Integer.parseInt(st.nextToken());
         
         int[][] array = new int[n][3];
         for(int k = 0; k < 3; k++){
            st = new StringTokenizer(f.readLine());
            for(int j = 0; j < n; j++){
               array[j][k] = Integer.parseInt(st.nextToken());
            }
         }
         
         int curor = 0;
         
         for(int k = 0; k < 3; k++){
            for(int j = 0; j < n; j++){
               if((array[j][k] | x) != x){
                  break;
               }
               curor |= array[j][k];
            }
         }
         
         if(curor == x){
            out.println("Yes");
         } else {
            out.println("No");
         }
         

      }
      
      
      
      
      out.close();
   }
   
      
}
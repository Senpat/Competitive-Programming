//make sure to make new file!
import java.io.*;
import java.util.*;

public class P2207{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      //all values > 2000 are willing states
      int N = 2000;
      
      int[] array = new int[N+1];
      array[1] = 0;
      array[2] = 0;
      for(int k = 3; k <= N; k++){
         boolean[] b = new boolean[2*N+1];
         for(int j = 1; k-j > j; j++){
            b[array[j] ^ array[k-j]] = true;
         }
         for(int j = 0; j < 2*N; j++){
            if(!b[j]){
               array[k] = j;
               break;
            }
         }
      }
      
      
      for(int q = 1; q <= t; q++){

         int n = Integer.parseInt(f.readLine());
      
         if(n <= N && array[n] == 0){
            out.println("second");
         } else {
            out.println("first");
         }

      }
      
      
      
      
      out.close();
   }
   
      
}
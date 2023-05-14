//make sure to make new file!
import java.io.*;
import java.util.*;

public class A870{
   
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
         
         int answer = -1;
         for(int k = 0; k <= n; k++){
            //see if # of liars match up
            
            int liars = 0;
            for(int j = 0; j < n; j++){
               if(array[j] > k) liars++;
            }
            
            if(liars == k){
               answer = k;
               break;
            }
         }
         
         out.println(answer);
      
      

      }
      
      
      
      
      out.close();
   }
   
      
}
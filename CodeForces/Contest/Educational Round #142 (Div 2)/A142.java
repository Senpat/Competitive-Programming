//make sure to make new file!
import java.io.*;
import java.util.*;

public class A142{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         int n = Integer.parseInt(f.readLine());
      
         StringTokenizer st = new StringTokenizer(f.readLine());
         int num1 = 0;
         int[] array = new int[n];
         for(int k = 0; k < n; k++){
            array[k] = Integer.parseInt(st.nextToken());
            if(array[k] == 1) num1++;
         }
         
         int answer = n-num1 + (num1+1)/2;
         out.println(answer);
      

      }
      
      
      
      
      out.close();
   }
   
      
}
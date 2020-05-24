//make sure to make new file!
import java.io.*;
import java.util.*;

public class A621{
   
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
         
         int answer = array[0];
         for(int k = 1; k < n; k++){
            if(array[k]*k <= m){
               answer += array[k];
               m-=array[k]*k;
            }
            else{
               answer += m/k;
               m=0;
            }
            
            if(m==0) break;
         }
         
         out.println(answer);
      

      }
      
      
      
      
      out.close();
   }
   
      
}
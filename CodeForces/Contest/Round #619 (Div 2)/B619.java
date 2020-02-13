//make sure to make new file!
import java.io.*;
import java.util.*;

public class B619{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         int n = Integer.parseInt(f.readLine());
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int[] array = new int[n];
         
         int max = -1;
         int min = Integer.MAX_VALUE;
         
         int maxdif = -1;
         for(int k = 0; k < n; k++){
            array[k] = Integer.parseInt(st.nextToken());
            
            
         }
         
         for(int k = 0; k < n; k++){
            if(k > 0 && array[k] != -1 && array[k-1] != -1){
               maxdif = Math.max(maxdif,Math.abs(array[k]-array[k-1]));
            }
            if(array[k] != -1 && ((k > 0 && array[k-1] == -1) || (k < n-1 && array[k+1] == -1))){
               max = Math.max(array[k],max);
               min = Math.min(array[k],min);
            }
         }
         int answer = 0;
         int d = 0;
         if(max == -1){
         //all -1
            d = 0;
            answer = 0;
               
         } else{
            d = (max+min)/2;
            answer = Math.max(max-d,d-min);
         }
         answer = Math.max(answer,maxdif);
         out.println(answer + " " + d);
         
         
      
      }
      
      
      
      
      out.close();
   }
   
      
}
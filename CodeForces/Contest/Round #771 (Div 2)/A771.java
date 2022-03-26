//make sure to make new file!
import java.io.*;
import java.util.*;

public class A771{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         int n = Integer.parseInt(f.readLine());
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int[] array = new int[n+1];
         
         int first = -1;
         
         for(int k = 1; k <= n; k++){
            array[k] = Integer.parseInt(st.nextToken());
            
            if(first == -1 && array[k] != k){
               first = k;
            }
         }
         
         if(first != -1){
            //swap from first to index of first
            int indexof = -1;
            for(int k = 1; k <= n; k++){
               if(array[k] == first){
                  indexof = k;
                  break;
               }
            }
            
            int l = first;
            int r = indexof;
            
            while(l < r){
               int temp = array[l];
               array[l] = array[r];
               array[r] = temp;
               l++;
               r--;
            }
         }
         
         StringJoiner sj = new StringJoiner(" ");
         for(int k = 1; k <= n; k++){
            sj.add("" + array[k]);
         }
         out.println(sj.toString());

      }
      
      
      
      
      out.close();
   }
   
      
}
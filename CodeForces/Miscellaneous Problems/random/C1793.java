//Dora and Search
import java.io.*;
import java.util.*;

public class C1793{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         int n = Integer.parseInt(f.readLine());
      
         StringTokenizer st = new StringTokenizer(f.readLine());
         int[] array = new int[n];
         
         int[] indexof = new int[n+1];
         for(int k = 0; k < n; k++){
            array[k] = Integer.parseInt(st.nextToken());
            indexof[array[k]] = 0;
         }
      
         int l = 0;
         int r = n-1;
         int lx = 1;
         int lr = n;
         
         int al = -1;
         int ar = -1;
         
         while(l < r){
            if(array[l] == lx){
               l++;
               lx++;
               continue;
            }
            if(array[l] == lr){
               l++;
               lr--;
               continue;
            }
            if(array[r] == lx){
               r--;
               lx++;
               continue;
            }
            if(array[r] == lr){
               r--;
               lr--;
               continue;
            }
            al = l+1;
            ar = r+1;
            break;
         }
         
         if(al == -1){
            out.println(-1);
         } else {
            out.println(al + " " + ar);
         }
         

      }
      
      
      
      
      out.close();
   }
   
      
}
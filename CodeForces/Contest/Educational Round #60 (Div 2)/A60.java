//make sure to make new file!
import java.io.*;
import java.util.*;

public class A60{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int[] a = new int[n];
      
      int max = -1;
      for(int k = 0; k < n; k++){
         a[k] = Integer.parseInt(st.nextToken());
         max = Math.max(max,a[k]);
      }
      
      int maxs = 1;
      int curs = 0;
      
      for(int k = 0; k < n; k++){
         if(a[k] == max){
            curs++;
            maxs = Math.max(curs,maxs);
         } else {
            curs = 0;
         }
      }
      
      out.println(maxs);
      

      
      
      
      
      out.close();
   }
   
      
}
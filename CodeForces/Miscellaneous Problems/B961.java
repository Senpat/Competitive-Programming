//Lecture Sleep
import java.io.*;
import java.util.*;

public class B961{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      int[] a = new int[n]; 
      
      int[] b = new int[n];
      
      st = new StringTokenizer(f.readLine());
      for(int k = 0; k < n; k++){
         a[k] = Integer.parseInt(st.nextToken());
      }
      
      st = new StringTokenizer(f.readLine());
      
      for(int k = 0; k < n; k++){
         b[k] = Integer.parseInt(st.nextToken());
      }
      
      int[] add = new int[n];
      
      int before = 0;
      for(int k = 0; k < n; k++){
         if(b[k]==0) add[k] = a[k];
         else{
            add[k] = 0;
            before+=a[k];
         }
      }
      
      int cur = 0;
      for(int k = 0; k < m; k++){
         cur+=add[k];
      }
         
      int max = cur;
      
      for(int k = m; k < n; k++){
         cur-=add[k-m];
         cur+=add[k];
         max = Math.max(max,cur);
      }
      
      out.println(before+max);
      
      out.close();
   }
   
}
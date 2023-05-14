//make sure to make new file!
import java.io.*;
import java.util.*;

public class A860{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         int n = Integer.parseInt(f.readLine());
      
         StringTokenizer st1 = new StringTokenizer(f.readLine());
         StringTokenizer st2 = new StringTokenizer(f.readLine());
      
         int[] a = new int[n];
         int[] b = new int[n];
         
         for(int k = 0; k < n; k++){
            a[k] = Integer.parseInt(st1.nextToken());
            b[k] = Integer.parseInt(st2.nextToken());
         }
         
         int top = Math.max(a[n-1],b[n-1]);
         int bot = Math.min(a[n-1],b[n-1]);
         
         boolean fail = false;
         for(int k = 0; k < n-1; k++){
            int max = Math.max(a[k],b[k]);
            int min = Math.min(a[k],b[k]);
            
            if(max > top){
               fail = true;
               break;
            }
            
            if(min > bot){
               fail = true;
               break;
            }
         }
         
         if(fail){
            out.println("No");
         } else {
            out.println("Yes");
         }

      }
      
      
      
      
      out.close();
   }
   
      
}
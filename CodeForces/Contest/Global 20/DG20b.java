//make sure to make new file!
import java.io.*;
import java.util.*;

public class DG20b{

   
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
         
         int[] stored = new int[n+1];
         int totalstored = 0;
         
         int i = 0;
         while(i < n){
         
            //check can place
            if(totalstored > 0 && a[i] == b[i-totalstored+1] && a[i] == b[i-totalstored] && stored[b[i-totalstored]] > 0){
               stored[b[i-totalstored]]--;
               totalstored--;
            } else if(a[i] != b[i-totalstored]){            //not equal
               stored[a[i]]++;
               totalstored++;
            }
            
            i++;
         }
         
         i = n-1;
         while(totalstored > 0 && a[i] == b[i-totalstored+1] && a[i] == b[i-totalstored] && stored[b[i-totalstored]] > 0){
            stored[b[i-totalstored]]--;
            totalstored--;
         }
         
         if(totalstored == 0){
            out.println("YES");
         } else {
            out.println("NO");
         }
                     
         
      }
      
      
      
      
      out.close();
   }
   
      
}
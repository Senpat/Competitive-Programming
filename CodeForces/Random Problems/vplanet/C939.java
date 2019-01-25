//make sure to make new file!
import java.io.*;
import java.util.*;

public class C939{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int[] array = new int[n];
      
      for(int k = 0; k < n; k++){
         array[k] = Integer.parseInt(st.nextToken());
      }
      
      st = new StringTokenizer(f.readLine());
      
      int s = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());
      
      int[] pref = new int[n+1];
      
      for(int k = 0; k < n; k++){
         pref[k+1] = array[k] + pref[k];
      }
      
      int dif = b-s;
      
      int max = 0;
      int maxind = -1;
      
      for(int k = 0; k < dif; k++){
         int cur = pref[k+1] + pref[n] - pref[n-dif+k+1];
         if(cur > max){
            max = cur;
            maxind = k;
         }
      }
      
      for(int k = dif; k < n; k++){
         int cur = pref[k+1] - pref[k+1-dif];
         if(cur > max){
            max = cur;
            maxind = k;
         }
      }
      
      out.println(maxind+1);
      
      
      
      out.close();
   }
   
}
//make sure to make new file!
import java.io.*;
import java.util.*;
//missing some observations
public class B906{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         long c = Long.parseLong(st.nextToken());
      
         long[] array = new long[n+1];
         long[] psum = new long[n+1];
         st = new StringTokenizer(f.readLine());
         for(int k = 1; k <= n; k++){
            array[k] = Long.parseLong(st.nextToken());
            psum[k] = psum[k-1] + array[k];
         }
         
         long[] atleast = new long[n+1];
         for(int k = 2; k <= n; k++){
            atleast[k] = (long)k * c - array[k];
         }
         
         long[] sufmin = new long[n+1];
         sufmin[n] = atleast[n];
         for(int k = n-1; k >= 2; k--){
            sufmin[k] = Math.min(sufmin[k+1],atleast[k]);
         }
         
         boolean pre = false;
         int i = 1;
         while(i < n){
            //see biggest node you can combine with
            long thresh = pre ? psum[i] : array[i];
            
            int l = i+1;
            int r = n;
            int ans = -1;
            while(l <= r){
               int mid = (l+r)/2;
               
               long mul = pre ? 1L : (long)i;
               if(sufmin[mid]*mul <= thresh){
                  ans = mid;
                  l = mid+1;
               } else {
                  r = mid-1;
               }
            }
            
            if(ans == -1){
               pre = false;
               i++;
            } else {
               pre = true;
               i = ans;
            }
         }
         
         if(pre){
            out.println("Yes");
         } else {
            out.println("No");
         }

      }
      
      
      
      
      out.close();
   }
   
      
}
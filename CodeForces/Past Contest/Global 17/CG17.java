//make sure to make new file!
import java.io.*;
import java.util.*;

public class CG17{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         int n = Integer.parseInt(f.readLine());
         
         int[] a = new int[n];
         int[] b = new int[n];
         
         for(int k = 0; k < n; k++){
            StringTokenizer st = new StringTokenizer(f.readLine());
         
            a[k] = Integer.parseInt(st.nextToken());
            b[k] = Integer.parseInt(st.nextToken());
         }
         
         int l = 1;
         int r = n;
         int ans = 1;
         while(l <= r){
            int mid = l + (r-l)/2;
            if(check(a,b,mid)){
               ans = mid;
               l = mid+1;
            } else {
               r = mid-1;
            }
         }
         
         out.println(ans);
      
      
      }
      
      
      
      
      out.close();
   }
   
   public static boolean check(int[] a, int[] b, int x){
      int found = 0;
      
      for(int k = 0; k < a.length; k++){
         if(found == x) break;
         if(a[k] >= x-found-1 && b[k] >= found){
            found++;
         }
      }
      
      return found == x;
   }
      
}
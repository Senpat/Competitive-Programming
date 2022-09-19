//make sure to make new file!
import java.io.*;
import java.util.*;

public class EG20{
   
   public static BufferedReader f;
   public static PrintWriter out;
   
   public static void main(String[] args)throws IOException{
      f = new BufferedReader(new InputStreamReader(System.in));
      out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      int l = 1;
      int r = 4003000;
      int ans = -1;
      while(l <= r){
         int mid = l + (r-l)/2;
         
         int response = query(mid);
         if(response != 1){
            l = mid+1;
         } else {
            r = mid-1;
            ans = mid;
         }
      }
      
      long answer = (long)ans;
      ArrayList<Integer> alist = new ArrayList<Integer>();
      for(int sub = 2; sub <= n; sub++){
         int i = ans-sub+1;
         for(int k = sub; k <= n; k++){
            if(i%k == 0){
               alist.add(i/k);
            }
         }
      }
      
      for(int i : alist){
         int res = query(i);
         
         if(res == 0) continue;
         answer = Math.min(answer,(long)i*(long)res);
      }
      
         
         
         
      out.println("! " + answer);
      
      
      
      
      
      
      
      
      
      out.close();
   }
   
   public static int query(int x) throws IOException{
      out.println("? " + x);
      out.flush();
      
      return Integer.parseInt(f.readLine());
   }
      
}
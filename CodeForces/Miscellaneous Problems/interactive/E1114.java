//make sure to make new file!
import java.io.*;
import java.util.*;

public class E1114{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      //binary search for largest number
      
      int l = 0;
      int r = 1000000000;
      int ans = -1;
      while(l <= r){
         int mid = l + (r-l)/2;
         
         out.println("> " + mid);
         out.flush();
         
         int i = Integer.parseInt(f.readLine());
         
         if(i == 1){
            l = mid+1;
         } else {
            ans = mid;
            r = mid-1;
         }
      }
      
      ArrayList<Integer> alist = new ArrayList<Integer>(n);
      for(int k = 1; k <= n; k++){
         alist.add(k);
      }
      
      Collections.shuffle(alist);
      
      int gcd = -1;
      
      for(int k = 0; k < alist.size() && k < 26; k++){
         out.println("? " + alist.get(k));
         out.flush();
         
         int i = Integer.parseInt(f.readLine());
         
         if(i == ans) continue;
         
         if(gcd == -1){
            gcd = ans-i;
         } else {
            gcd = gcd(gcd,ans-i);
         }
      }
      
      
      int answer1 = ans-(n-1)*gcd;
      out.println("! " + answer1 + " " + gcd);
      
      
      
      
      
      
      
      
      out.close();
   }
   
   public static int gcd(int a, int b){
      if(a > b){
         if(b == 0) return a;
         return gcd(a%b,b);
      } else if(a < b){
         if(a == 0) return b;
         return gcd(b%a,a);
      }
      return a;
   }
      
}
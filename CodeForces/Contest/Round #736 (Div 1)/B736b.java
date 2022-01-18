//make sure to make new file!
import java.io.*;
import java.util.*;

public class B736b{

   public static long[] t;
   public static int n;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int tt = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= tt; q++){

         n = Integer.parseInt(f.readLine());
      
         StringTokenizer st = new StringTokenizer(f.readLine());
         long[] array = new long[n];
         long[] difs = new long[n-1];
         for(int k = 0; k < n; k++){
            array[k] = Long.parseLong(st.nextToken());
            if(k > 0){
               difs[k-1] = Math.abs(array[k-1]-array[k]);
            }
         }
         
         if(n == 1){
            out.println(1);
            continue;
         }
         
         t = new long[4*n+5];
         //build(difs,1,0,n-2);
         for(int k = 0; k < n-1; k++){
            update(1,0,n-2,k,difs[k]);
         }
         
         int l = 2;
         int r = n;
         int ans = 1;
         
         while(l <= r){
            int mid = l + (r-l)/2;
            if(check(mid-1)){
               l = mid+1;
               ans = mid;
            } else {
               r = mid-1;
            }
         }
         
         
         out.println(ans);
      

      }
      
      
      
      
      out.close();
   }
   
   public static boolean check(int x){
      for(int k = 0; k < n-x+1-1; k++){
         if(gcd(1,0,n-2,k,k+x-1) > 1) return true;
      }
      return false;
   }
   
   //build currently unconfirmed working
   public static void build(long[] a, int v, int tl, int tr) {
      if (tl == tr) {
         t[v] = a[tl];
      } else {
         int tm = (tl + tr) / 2;
         build(a, v*2, tl, tm);
         build(a, v*2+1, tm+1, tr);
         t[v] = gcd(t[v*2],t[v*2+1]);
      }
   }
   
   //to call: v=1, tl = 0, tr = n-1
   public static long gcd(int v, int tl, int tr, int l, int r) {
      //System.out.println(v + " " + tl + " " + tr + " " + l + " " + r);
      if (l > r) 
         return 0;
      if (l == tl && r == tr) {
         return t[v];
      }
      int tm = (tl + tr) / 2;
      return gcd(gcd(v*2, tl, tm, l, Math.min(r, tm)),gcd(v*2+1, tm+1, tr, Math.max(l, tm+1), r));
   }
   
   //to call: v=1, tl = 0, tr = n-1
   public static void update(int v, int tl, int tr, int pos, long new_val) {
      if (tl == tr) {
         t[v] = new_val;
      } else {
         int tm = (tl + tr) / 2;
         if (pos <= tm)
            update(v*2, tl, tm, pos, new_val);
         else
            update(v*2+1, tm+1, tr, pos, new_val);
         t[v] = gcd(t[v*2],t[v*2+1]);
      }
   }
   
   
   public static long gcd(long a, long b){
      if(Math.abs(a-b) == 1) return 1;
      if(a == b) return a;
      if(a < b){
         if(a == 0) return b;
         return gcd(b%a,a);
      } else {
         if(b == 0) return a;
         return gcd(a%b,b);
      }
   }
   
      
}
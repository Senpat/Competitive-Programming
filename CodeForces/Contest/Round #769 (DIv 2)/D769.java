//make sure to make new file!
import java.io.*;
import java.util.*;

public class D769{

   public static int[] t;
   public static int MAX_N = 200005;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      int[] array = new int[n];
      for(int k = 0; k < n; k++){
         array[k] = Integer.parseInt(st.nextToken());
      }
      
      t = new int[4*MAX_N];
      build(array,1,0,n-1);      //or build
   
      
      int[] answer = new int[n];
      int last = -1;
      if(array[0] == 1){
         answer[0] = 1;
         last = 0;
      }
      else answer[0] = 0;
      
      for(int k = 1; k < n; k++){
         if(array[k] == 1){
            answer[k] = answer[k-1]+1;
            last = k;
         } else {
         
            boolean found = false;
            boolean newlast = false;
            for(int j = 2; j*j <= array[k]; j++){
               if(k-j+1 < 0) break;
               if(k-j+1 <= last) break;
               if(array[k] % j != 0) continue;
               
               //check gcd
               if(getgcd(1,0,n-1,k-j+1,k) == j){
                  found = true;
                  
                  break;
               }
               
            }
            
            //check full
            
            if(k-array[k]+1 >= 0 && k-array[k]+1 > last && getgcd(1,0,n-1,k-array[k]+1,k) == array[k]){
               found = true;
            }
            
            if(found){
               answer[k] = answer[k-1]+1;
               last = k;
            } else {
               answer[k] = answer[k-1];
            }
         }
      }
      
      
      
      StringJoiner sj = new StringJoiner(" ");
      for(int k = 0; k < n; k++){
         sj.add("" + answer[k]);
      }
      out.println(sj.toString());
      
      
      
      out.close();
   }
   
      //build currently unconfirmed working
   public static void build(int[] a, int v, int tl, int tr) {
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
   public static int getgcd(int v, int tl, int tr, int l, int r) {
      //System.out.println(v + " " + tl + " " + tr + " " + l + " " + r);
      if (l > r) 
         return 0;
      if (l == tl && r == tr) {
         return t[v];
      }
      int tm = (tl + tr) / 2;
      return gcd(getgcd(v*2, tl, tm, l, Math.min(r, tm)),getgcd(v*2+1, tm+1, tr, Math.max(l, tm+1), r));
   }
   
   //to call: v=1, tl = 0, tr = n-1
   public static void update(int v, int tl, int tr, int pos, int new_val) {
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
   
   
   public static int gcd(int a, int b){
      if(a > b){
         if(b == 0) return a;
         return gcd(a%b, b);
      } else if(a < b){
         if(a == 0) return b;
         return gcd(b%a,a);
      }
      return a;
   }
      
}
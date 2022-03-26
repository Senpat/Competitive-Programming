//make sure to make new file!
import java.io.*;
import java.util.*;

public class D769c{

   public static int N = 200005;
   public static int M = 20;
   public static int[] log;
   public static int[][] spt;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      //sparse table setup
      log = new int[N+1];
      log[1] = 0;
      for(int k = 2; k <= N; k++){
         log[k] = log[k/2]+1;
      }
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      int[] array = new int[n];
      for(int k = 0; k < n; k++){
         array[k] = Integer.parseInt(st.nextToken());
      }
      
      spt = new int[N+1][M+1];
      for(int k = 0; k < n; k++){
         spt[k][0] = array[k];
      }
      
      for(int j = 1; j <= M; j++){
         for(int k = 0; k + (1 << j) <= N; k++){
            spt[k][j] = gcd(spt[k][j-1], spt[k + (1 << (j-1))][j-1]);
         }
      }
      
   
      
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
         
            int l = 0;
            int r = k;
            int ans = -1;
            
            while(l <= r){
               int mid = l+(r-l)/2;
               
               int gcd = query(mid,k);
               
               if(gcd == k-mid+1){
                  ans = mid;
                  break;
               }
               
               if(gcd < k-mid+1){
                  l = mid+1;
               } else {
                  r = mid-1;
               }
            }
            
            if(ans == -1 || ans <= last){
               answer[k] = answer[k-1];
            } else {
               answer[k] = answer[k-1]+1;
               last = k;
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
       
   //only for operations like min, gcd, where including same number more than once doesn't matter
   public static int query(int l, int r){
      //System.out.println(l + " " + r);
      int j = log[r-l+1];
      return gcd(spt[l][j],spt[r-(1 << j) + 1][j]);
   }
   
   public static int gcd(int a, int b){
      //System.out.println(a + " " + b);
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
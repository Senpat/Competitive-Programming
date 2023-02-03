//make sure to make new file!
import java.io.*;
import java.util.*;
//semi-t
public class F961{
   
   public static int B = 3;
   public static long[] base = new long[]{29L,31L,37L};
   public static long[] ibase = new long[]{517241384L,128805723L,621621626L};
   public static long[] MOD = new long[]{1000000009L,998244353L,1000000007L};
   
   public static long[][] pow;
   public static long[][] ipow;
   public static long[][] hashes;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int N = 1000005;
      pow = new long[N][B];
      ipow = new long[N][B];
      for(int b = 0; b < B; b++){
         pow[0][b] = 1L;
         ipow[0][b] = 1L;
         for(int k = 1; k < N; k++){
            pow[k][b] = (pow[k-1][b] * base[b] + MOD[b])%MOD[b];
            ipow[k][b] = (ipow[k-1][b] * ibase[b] + MOD[b])%MOD[b];
         }
      }
      
      int n = Integer.parseInt(f.readLine());
      //1-indexed
      char[] array = (" " + f.readLine()).toCharArray();
      
      hashes = new long[n+1][B];
      for(int b = 0; b < B; b++){
         for(int k = 1; k <= n; k++){
            long prod = (ctol(array[k]) * pow[k][b] + MOD[b])%MOD[b];
            hashes[k][b] = (hashes[k-1][b] + prod + MOD[b])%MOD[b];
         }
      }
      
      int[] answer = new int[(n+1)/2+1];
      Arrays.fill(answer,-1);
      
      int L = 1;
      int R = n;
      while(L < R){
         int l = 0;
         int r = L-1;
         int ans = -1;
         
         while(l <= r){
            int mid = l + (r-l)/2;
            
            //out.println((L-mid) + " " + (L+mid) + " " + (R-mid) + " " + (R+mid) + " " + checkequal(L-mid,L+mid,R-mid,R+mid));
            if(checkequal(L-mid,L+mid,R-mid,R+mid)){
               l = mid+1;
               ans = mid;
            } else {
               r = mid-1;
            }
         }
         
         //out.println(L + " " + R + " " + ans);
         if(ans != -1){
            answer[L-ans] = Math.max(answer[L-ans],2*ans+1);
         }
         
         L++;
         R--;
      }
      
      for(int k = 2; k < answer.length; k++){
         answer[k] = Math.max(answer[k],answer[k-1]-2);
      }
      
      StringJoiner sj = new StringJoiner(" ");
      for(int k = 1; k < answer.length; k++){
         sj.add("" + answer[k]);
      }
      out.println(sj.toString());
      
      
      
      
      
      
      
      
      out.close();
   }
   
   public static boolean checkequal(int l1, int r1, int l2, int r2){
      
      for(int b = 0; b < B; b++){
         long hash1 = ((hashes[r1][b] - hashes[l1-1][b])%MOD[b] + MOD[b])%MOD[b];
         hash1 = (hash1 * ipow[l1][b] + MOD[b])%MOD[b];
         
         long hash2 = ((hashes[r2][b] - hashes[l2-1][b])%MOD[b] + MOD[b])%MOD[b];
         hash2 = (hash2 * ipow[l2][b] + MOD[b])%MOD[b];
         
         if(hash1 != hash2) return false;
      }
      
      return true;
      
   }
   
   public static long ctol(char ch){
      return (long)(ch-'a'+1);
   }
   
      
}
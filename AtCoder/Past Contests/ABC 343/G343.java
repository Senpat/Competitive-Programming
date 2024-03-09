//make sure to make new file!
import java.io.*;
import java.util.*;
//bug: in function using kmp was wrong
public class G343{

   public static long base = 29L;
   public static long MOD = 1000000007L;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int N = 200005;
      long[] pow = new long[N];
      pow[0] = 1L;
      for(int k = 1; k < N; k++){
         pow[k] = (pow[k-1] * base + MOD)%MOD;
      }
      
      int n = Integer.parseInt(f.readLine());
      
      String[] array = new String[n];
      for(int k = 0; k < n; k++){
         array[k] = f.readLine();
      }
      /*
      int[] kmp0 = getkmp(array[0].toCharArray());
      out.println(in(array[0],array[1],kmp0));
      out.flush();
      //0 0 1 2 0*/
      ArrayList<String> alistp = new ArrayList<String>();
      for(int k = 0; k < n; k++){
         int[] kmp = getkmp(array[k].toCharArray());
         
         boolean fail = false;
         for(int j = 0; j < n; j++){
            if(k == j) continue;
            if(in(array[k],array[j],kmp)){
               fail = true;
               break;
            }
         }
         
         if(!fail) alistp.add(array[k]);
      }
      
      ArrayList<String> alist = new ArrayList<String>();
      //get rid of duplicates
      Collections.sort(alistp);
      alist.add(alistp.get(0));
      for(int k = 1; k < alistp.size(); k++){
         if(!alistp.get(k).equals(alistp.get(k-1))){
            alist.add(alistp.get(k));
         }
      }
      
      
      int an = alist.size();
      //matrix[x][y] -> how many overlapping characters when alist.get(x) is right before alist.get(y)
      int[][] matrix = new int[an][an];
      
      for(int k = 0; k < an; k++){
         int kn = alist.get(k).length();
         
         for(int j = 0; j < an; j++){
            if(k == j) continue;
            int jn = alist.get(j).length();
            
            long ahash = 0L;
            long bhash = 0L;
            for(int h = 0; h < Math.min(kn,jn); h++){
               ahash = (ahash * base + (long)(alist.get(k).charAt(kn-1-h) - 'a' + 1L) + MOD)%MOD;
               bhash = (bhash + pow[h] * (long)(alist.get(j).charAt(h) - 'a' + 1L) + MOD)%MOD;
               
               if(ahash == bhash){
                  matrix[k][j] = h+1;
               }
            }     
         }
      }
         
      int pn = (1 << an);
      int[][] dp = new int[pn][an];
      for(int k = 0; k < pn; k++){
         Arrays.fill(dp[k],Integer.MAX_VALUE);
      }
      for(int k = 0; k < an; k++){
         dp[1 << k][k] = alist.get(k).length();
      }
      
      for(int mask = 1; mask < pn; mask++){
         for(int k = 0; k < an; k++){
            if(dp[mask][k] == Integer.MAX_VALUE) continue;
            
            //try adding j
            for(int j = 0; j < an; j++){
               if(((mask >> j)&1) == 0){
                  int newmask = mask ^ (1 << j);
                  dp[newmask][j] = Math.min(dp[newmask][j],dp[mask][k] + alist.get(j).length() - matrix[k][j]);
               }
            }
         }
      }
      
      int min = Integer.MAX_VALUE;
      for(int k = 0; k < an; k++){
         min = Math.min(min,dp[pn-1][k]);
      }
      
      out.println(min);
      
      
      out.close();
   }
   
   //check if a is a substring in b
   public static boolean in(String a, String b, int[] kmp){
      if(a.length() >= b.length()) return false;
      
      int ai = 0;
      for(int k = 0; k < b.length(); k++){
         //System.out.println(a + " " + b + " " + k + " " + ai);
         if(b.charAt(k) == a.charAt(ai)){
            ai++;
            if(ai == a.length()) return true;
         } else {
            if(ai == 0) continue;
            ai = Math.max(0,kmp[ai-1]);
            k--;
         }
      }
      
      return false;
      
   }
   
   
   //kmp:
   //abcabcd -> 0 0 0 1 2 3 0
   public static int[] getkmp(char[] s){
      int n = s.length;
      int[] kmp = new int[n];
      
      for(int k = 1; k < n; k++){
         int j = kmp[k-1];
         while(j > 0 && s[k] != s[j]){
            j = kmp[j-1];
         }
         if(s[k] == s[j]){
            j++;
         }
         kmp[k] = j;
      }
      
      return kmp;
   }
   
      
}
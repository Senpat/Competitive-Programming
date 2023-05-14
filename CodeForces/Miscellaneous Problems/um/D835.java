//Palindromic Characteristics
import java.io.*;
import java.util.*;
//Um episode 13
public class D835{

   public static char[] array;
   public static int n;
   
   public static boolean[][] ispalin;
   
   public static int[][] dp;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      array = f.readLine().toCharArray();
      n = array.length;
      
      ispalin = new boolean[n][n];     //ispalim[l][r] says if array[l..r] is a palindrome
      for(int k = 0; k < n; k++) ispalin[k][k] = true;
      for(int k = 0; k < n-1; k++){
         if(array[k] == array[k+1]) ispalin[k][k+1] = true;
      }
      
      for(int d = 2; d < n; d++){
         for(int l = 0; l+d < n; l++){
            int r = l+d;
            if(array[l] == array[r] && ispalin[l+1][r-1]) ispalin[l][r] = true;  
         }
      }
      
      dp = new int[n][n];     //stores palindromic characteristics
      for(int k = 0; k < n; k++){
         Arrays.fill(dp[k],-1);
         dp[k][k] = 1;
      }
      
      dothing(0,n-1);
      
      int[] answer = new int[n+1];
      for(int k = 0; k < n; k++){
         for(int j = k; j < n; j++){
            answer[dp[k][j]]++;
         }
      }
      
      for(int k = n-1; k >= 1; k--){
         answer[k] += answer[k+1];
      }
      
      StringJoiner sj = new StringJoiner(" ");
      for(int k = 1; k <= n; k++){
         sj.add("" + answer[k]);
      }
      out.println(sj.toString());
      
      
      
      
      
      
      out.close();
   }
   
   public static void dothing(int l, int r){
      if(r < l) return;
      if(dp[l][r] != -1) return;
      
      dothing(l+1,r);
      dothing(l,r-1);
      
      if(!ispalin[l][r]){
         dp[l][r] = 0;
      } else {
         dp[l][r] = dp[l][l + (r-l+1)/2-1]+1;
      }
   }
   
      
}
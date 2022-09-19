//make sure to make new file!
import java.io.*;
import java.util.*;

public class H1575{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      String as = f.readLine();
      String bs = f.readLine();
      
      int[] a = new int[n];
      for(int k = 0; k < n; k++){
         a[k] = Character.getNumericValue(as.charAt(k));
      }
      int[] b = new int[m];
      for(int k = 0; k < m; k++){
         b[k] = Character.getNumericValue(bs.charAt(k));
      }
      
      ArrayList<Integer> pos = genpos(b);
      
      int[][][] dp = new int[n][n-m+2][m];         //dp[i][j][h] means minimum # of operations to do first i characters, have j occurences of b, and m characters toward next occurence

      for(int k = 0; k < n; k++){
         for(int j = 0; j < n-m+2; j++){
            Arrays.fill(dp[k][j],Integer.MAX_VALUE);
         }
      }
      
      if(a[0] == b[0]){
         if(m == 1){
            dp[0][1][0] = 0;
            dp[0][0][0] = 1;
         } else {
            dp[0][0][1] = 0;
            dp[0][0][0] = 1;
         }
      } else {
         if(m == 1){
            dp[0][1][0] = 1;
            dp[0][0][0] = 0;
         } else {
            dp[0][0][1] = 1;
            dp[0][0][0] = 0;
         }
      }
      
      
      for(int k = 0; k < n-1; k++){
         for(int j = 0; j < n-m+2; j++){
            for(int h = 0; h < m; h++){
               if(dp[k][j][h] == Integer.MAX_VALUE) continue;
               
               int r;
               if(b[0] == a[k+1]){
                  r = 1;
               } else {
                  r = 0;
               }
                      
               if(a[k+1] == b[h]){
                  if(h == m-1){
                     //get new occurence
                     for(int i : pos){
                        dp[k+1][j+1][i] = Math.min(dp[k+1][j+1][i], dp[k][j][h]);             //don't switch
                     }
                     dp[k+1][j][1-r] = Math.min(dp[k+1][j][1-r], dp[k][j][h]+1);               //switch
                  } else {
                     //get one more character
                     dp[k+1][j][h+1] = Math.min(dp[k+1][j][h+1], dp[k][j][h]);              //don't switch
                     dp[k+1][j][1-r] = Math.min(dp[k+1][j][1-r], dp[k][j][h]+1);               //switch
                  }
               } else {
                  if(h == m-1){
                     //get new occurence
                     for(int i : pos){
                        dp[k+1][j+1][i] = Math.min(dp[k+1][j+1][i], dp[k][j][h]+1);             //switch
                     }
                     dp[k+1][j][r] = Math.min(dp[k+1][j][r], dp[k][j][h]);               //don't switch
                  } else {
                     //get one more character
                     dp[k+1][j][h+1] = Math.min(dp[k+1][j][h+1], dp[k][j][h]+1);              //switch
                     dp[k+1][j][r] = Math.min(dp[k+1][j][r], dp[k][j][h]);               //don't switch
                  }
               }
            }
         }
      }
      
      
      int[] answer = new int[n-m+2];
      for(int k = 0; k < n-m+2; k++){
         
         int min = Integer.MAX_VALUE;
         for(int j = 0; j < m; j++){
            min = Math.min(min,dp[n-1][k][j]);
         }
         
         if(min == Integer.MAX_VALUE){
            answer[k] = -1;
         } else {
            answer[k] = min;
         }
      }
      
      StringJoiner sj = new StringJoiner(" ");
      for(int k = 0; k < n-m+2; k++){
         sj.add("" + answer[k]);
      }
      out.println(sj.toString());
           
          
      
      
      
      
      
      out.close();
   }
   
   public static ArrayList<Integer> genpos(int[] b){
      int m = b.length;
      
      ArrayList<Integer> ret = new ArrayList<Integer>();
      for(int k = 1; k < m; k++){
         //check if can do k
         boolean fail = false;
         for(int j = k; j < m; j++){
            if(b[j] != b[j-k]){
               fail = true;
               break;
            }
         }
         
         if(fail) continue;
         //make sure you don't overcount
         for(int j = 1; j < k; j++){
            boolean curfail = false;
            for(int h = 0; h < m; h++){
               if(h+j < m){
                  if(b[h] != b[h+j]){
                     curfail = true;
                     break;
                  }
               } else {
                  //if(b[h] != b[h+j-(m-k)-1]){
                  if(b[h] != b[h+j-k]){
                     curfail = true;
                     break;
                  }
               }
            }
            if(curfail){
               fail = true;
               break;
            }
         }
         
         if(k == 1 || fail){
            ret.add(m-k);
         }
      }
      
      if(ret.size() == 0) ret.add(0);
      return ret;
   }
      
      
      
      
}
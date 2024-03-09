//Unlucky Numbers
import java.io.*;
import java.util.*;
//calculates the min difference but not the construction
public class C1808{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         StringTokenizer st = new StringTokenizer(f.readLine());
         char[] lin = st.nextToken().toCharArray();
         char[] rin = st.nextToken().toCharArray();
         
         int n = rin.length;
         
         int[] l = new int[n];
         int[] r = new int[n];
         
         for(int k = 0; k < n; k++){
            if(k+lin.length < n) l[k] = 0;
            else l[k] = Character.getNumericValue(lin[k - (n-lin.length)]);
            
            r[k] = Character.getNumericValue(rin[k]);
         }
         
         //dp[x][y][z][a][b] -> xth digit, min is y, max is z (10 is none), is min, is max
         boolean[][][][][] dp = new boolean[n][11][11][2][2];
         
         if(l[0] == r[0]){
            dp[0][l[0]][r[0]][1][1] = true;
         } else {
            if(l[0] == 0){
               //first digit is 0
               dp[0][10][10][1][0] = true;
            } else {
               dp[0][l[0]][l[0]][1][0] = true;
            }
            
            dp[0][r[0]][r[0]][0][1] = true;
            
            for(int k = l[0]+1; k <= r[0]-1; k++){
               dp[0][l[0]][r[0]][0][0] = true;
            }
         }
         
         for(int k = 0; k < n-1; k++){
            for(int min = 0; min <= 10; min++){
               for(int max = 0; max <= 10; max++){
                  for(int x = 0; x <= 9; x++){
                     int nmin = Math.min(x,min);
                     int nmax = Math.max(x,max);
                     if(min == 10 && x == 0) nmin = 10;
                     if(max == 10 && x == 0) nmax = 10;
                     
                     dp[k+1][nmin][nmax][0][0] |= dp[k][min][max][0][0];
                     
                     if(x == l[k+1]){
                        dp[k+1][nmin][nmax][1][0] |= dp[k][min][max][1][0];
                     } else if(x > l[k+1]){
                        dp[k+1][nmin][nmax][0][0] |= dp[k][min][max][1][0];
                     }
                     
                     if(x == r[k+1]){
                        dp[k+1][nmin][nmax][0][1] |= dp[k][min][max][0][1];
                     } else if(x < r[k+1]){
                        dp[k+1][nmin][nmax][0][0] |= dp[k][min][max][0][1];
                     }
                     
                     if(l[k+1] == r[k+1]){
                        if(x == l[k+1]){
                           dp[k+1][nmin][nmax][1][1] |= dp[k][min][max][1][1];
                        }
                     } else {
                        if(x == l[k+1]){
                           dp[k+1][nmin][nmax][1][0] |= dp[k][min][max][1][1];
                        }
                        if(x == r[k+1]){
                           dp[k+1][nmin][nmax][0][1] |= dp[k][min][max][1][1];
                        }
                        
                        if(x > l[k+1] && x < r[k+1]){
                           dp[k+1][nmin][nmax][0][0] |- dp[k][min][max][1][1];
                        }
                     }
                  }  
               }
            }
         }
         
         
         int answer = 10;
         for(int min = 0; min <= 9; min++){
            for(int max = min; max <= 9; max++){
               if(dp[n-1][min][max][0][0] || dp[n-1][min][max][0][0] || dp[n-1][min][max][0][0] || dp[n-1][min][max][0][0]){
                  answer = Math.min(answer,max-min)
               }
            }
         }
         
         out.println(answer);
      

      }
      
      
      
      
      out.close();
   }
   
      
}
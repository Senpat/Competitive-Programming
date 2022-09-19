//1000th problem solved!
//Omkar and Last Floor
import java.io.*;
import java.util.*;

public class E655{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      int[][] ids = new int[n+1][m+1];
      
      ArrayList<ArrayList<Integer>> left = new ArrayList<ArrayList<Integer>>(n+1);
      ArrayList<ArrayList<Integer>> right = new ArrayList<ArrayList<Integer>>(n+1);
      for(int k = 0; k <= n; k++){
         left.add(new ArrayList<Integer>());
         right.add(new ArrayList<Integer>());
      }
      
      for(int k = 1; k <= n; k++){
         int ni = Integer.parseInt(f.readLine());
         
         for(int j = 0; j < ni; j++){
            st = new StringTokenizer(f.readLine());
         
            int l = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            
            for(int h = l; h <= r; h++){
               ids[k][h] = j;
            }
            
            
            left.get(k).add(l);
            right.get(k).add(r);
         }
      }
      
      //dp[l][r] is the answer for the columns [l,r]
      int[][] dp = new int[m+1][m+1];
      
      for(int d = 0; d < m; d++){
         for(int k = 1; k+d<=m; k++){
            int l = k;
            int r = k+d;
            
            //pick column to fill in with 1s
            int max = 0;
            for(int col = l; col <= r; col++){
               
               int cur = 0;
               for(int row = 1; row <= n; row++){
                  if(left.get(row).get(ids[row][col]) >= l && right.get(row).get(ids[row][col]) <= r){
                     cur++;
                  }
               }
               
               cur *= cur;
               if(col > l) cur += dp[l][col-1];
               if(col < r) cur += dp[col+1][r];
               
               max = Math.max(max,cur);
            }
            
            dp[l][r] = max;
         }
      }
      
      out.println(dp[1][m]);
                  
      
      
      
      
      
      out.close();
   }
   
      
}
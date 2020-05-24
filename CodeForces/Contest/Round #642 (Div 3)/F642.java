//make sure to make new file!
import java.io.*;
import java.util.*;

public class F642{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         int m = Integer.parseInt(st.nextToken());
      
         long[][] matrix = new long[n][m];
         
         for(int k = 0; k < n; k++){
            st = new StringTokenizer(f.readLine());
            for(int j = 0; j < m; j++){
               matrix[k][j] = Long.parseLong(st.nextToken());
            }
         }
         /*
         long[][][][] dp = new long[n][m][n][m];
         
         
         for(int k = 0; k < n; k++){
            for(int j = 0; j < m; j++){
               for(int rx = 0; rx < n; rx++){
                  for(int ry = 0; ry < m; ry++){
                     dp[k][j][rx][ry] = Long.MAX_VALUE;
                  }
               }
            }
         }
         
         dp[0][0][0][0] = 0L;*/
         
         long[][][] prev = new long[m][n][m];
         long[][][] cur = new long[m][n][m];
         
         for(int j = 0; j < m; j++){
            for(int rx = 0; rx < n; rx++){
               for(int ry = 0; ry < m; ry++){
                  prev[j][rx][ry] = Long.MAX_VALUE;
                  cur[j][rx][ry] = Long.MAX_VALUE;
               }
            }
         }
         prev[0][0][0] = 0L;
         
         for(int k = 0; k < n; k++){
            for(int j = 0; j < m; j++){
               for(int rx = 0; rx < n; rx++){
                  for(int ry = 0; ry < m; ry++){
                     if(prev[j][rx][ry] == Long.MAX_VALUE) 
                        continue;
                     
                     //go to k+1
                     if(k < n-1){
                     //same reference point
                     //find what the value should be
                        long v = matrix[rx][ry]+(long)dist(k+1,j,rx,ry);
                        if(matrix[k+1][j] >= v){
                           cur[j][rx][ry] = Math.min(cur[j][rx][ry],prev[j][rx][ry] + matrix[k+1][j]-v);
                        }
                        
                        //new reference point
                        if(matrix[k+1][j] < v){
                           cur[j][k+1][j] = Math.min(cur[j][k+1][j],prev[j][rx][ry] + (v-matrix[k+1][j])*(long)(dist(0,0,k,j)+1));
                        }
                     }
                     
                     //go to j+1
                     
                     if(j < m-1){
                     //same reference point
                     //find what the value should be
                        long v = matrix[rx][ry]+(long)dist(k,j+1,rx,ry);
                        if(matrix[k][j+1] >= v){
                           prev[j+1][rx][ry] = Math.min(prev[j+1][rx][ry],prev[j][rx][ry] + matrix[k][j+1]-v);
                        }
                        
                        //new reference point
                        if(matrix[k][j+1] < v){
                           prev[j+1][k][j+1] = Math.min(prev[j+1][k][j+1],prev[j][rx][ry] + (v-matrix[k][j+1])*(long)(dist(0,0,k,j)+1));
                        }
                     }
                     
                     
                  }
               }
            }
            
            if(k < n-1){
               for(int j = 0; j < m; j++){
                  for(int rx = 0; rx < n; rx++){
                     for(int ry = 0; ry < m; ry++){
                        prev[j][rx][ry] = cur[j][rx][ry];
                        cur[j][rx][ry] = Long.MAX_VALUE;
                     }
                  }
               }
            }
            
         }
         
         /*
         for(int k = 0; k < n; k++){
            for(int j = 0; j < m; j++){
               out.print(dp[k][j][0][0] + " ");
            }
            out.println();
         }*/
         
         long min = Long.MAX_VALUE;
         
         for(int k = 0; k < n; k++){
            for(int j = 0; j < m; j++){
               min = Math.min(min,prev[m-1][k][j]);
            }
         }
         
         out.println(min);
         
         
      }
      
      
      
      
      out.close();
   }
   
   public static int dist(int a, int b, int c, int d){
      return Math.abs(a-c) + Math.abs(b-d);
   }
   
      
}
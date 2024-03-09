//make sure to make new file!
import java.io.*;
import java.util.*;
import java.math.*;

public class P1726{
   
   public static int N = 8;
   
   public static int[] dx = {0,0,1,-1};
   public static int[] dy = {1,-1,0,0};
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int X = Integer.parseInt(f.readLine());
      
      double[][] pempty = new double[N][N];
      for(int k = 0; k < N; k++){
         Arrays.fill(pempty[k],1.0);
      }
      
      for(int sx = 0; sx < N; sx++){
         for(int sy = 0; sy < N; sy++){
            double[][] cur = new double[N][N];
            cur[sx][sy] = 1.0;
            
            for(int k = 0; k < X; k++){
               double[][] next = new double[N][N];
               for(int x = 0; x < N; x++){
                  for(int y = 0; y < N; y++){
                     double num = 4.0;
                     if(x == 0 || x == N-1) num--;
                     if(y == 0 || y == N-1) num--;
                     
                     for(int d = 0; d < 4; d++){
                        int nx = x + dx[d];
                        int ny = y + dy[d];
                        if(!in(nx,ny)) continue;
                        next[nx][ny] += cur[x][y]/num;
                     }
                  }
               }
               
               cur = next;
            }
            
            for(int x = 0; x < N; x++){
               for(int y = 0; y < N; y++){
                  pempty[x][y] *= (1.0-cur[x][y]);
               }
            }
         }
      }
      
      double answer = 0.0;
      for(int k = 0; k < N; k++){
         for(int j = 0; j < N; j++){
            answer += pempty[k][j];
         }
      }
      
      out.println(new BigDecimal(answer).setScale(6,RoundingMode.HALF_EVEN));
      
      
      
      
      
      
      out.close();
   }
   
   public static boolean in(int x, int y){
      return x >= 0 && x < N && y >= 0 && y < N;
   }
   
      
}
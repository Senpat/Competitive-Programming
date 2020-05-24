//make sure to make new file!
import java.io.*;
import java.util.*;
//DOESNT WORK
public class P3{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         int n = Integer.parseInt(f.readLine());
      
         int[][] matrix = new int[n][n];
         
         int[][] freqr = new int[n][2*n+1];
         int[][] freqc = new int[n][2*n+1];
         for(int k = 0; k < n; k++){
            StringTokenizer st = new StringTokenizer(f.readLine());
            for(int j = 0; j < n; j++){
               matrix[k][j] = n+Integer.parseInt(st.nextToken());
               freqr[k][matrix[k][j]]++;
               freqc[j][matrix[k][j]]++;
            }
         }
         
         //change twos
         
         int answer = 0;
         for(int k = 0; k < n; k++){
            for(int j = 0; j < n; j++){
               if(freqr[k][matrix[k][j]] >= 2 && freqc[j][matrix[k][j]] >= 2){
                  answer++;
                  freqr[k][matrix[k][j]]--;
                  freqc[j][matrix[k][j]]--;
               }
            }
         }
         
         for(int k = 0; k < n; k++){
            for(int j = 0; j <= 2*n; j++){
               if(freqr[k][j] >= 2){
                  answer += freqr[k][j]-1;
               }
               if(freqc[k][j] >= 2){
                  answer += freqc[k][j]-1;
               }
            }
         }
         
         out.println("Case #" + q + ": " + answer);
         
         
            

      }
      
      
      
      
      out.close();
   }
   
      
}
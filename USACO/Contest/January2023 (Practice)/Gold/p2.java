//make sure to make new file!
import java.io.*;
import java.util.*;

public class p2{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int t = Integer.parseInt(st.nextToken());
      int n = Integer.parseInt(st.nextToken());
      
      int B = (1 << n);
      int M = 60;
      boolean[][] dp = new boolean[M+1][B];
      dp[0][0] = true;
      
      int[] masks = new int[n];
      
      for(int k = 0; k < M; k++){
         for(int j = 0; j < n; j++){
            int shift = (j+k)%n;
            masks[j] ^= (1 << shift);
         }
         
         for(int j = 0; j < B; j++){
            if(!dp[k][j]) continue;
            
            for(int h = 0; h < n; h++){
               dp[k+1][j^masks[h]] = true;
            }
         }
      }
      
      for(int q = 0; q < t; q++){
         st = new StringTokenizer(f.readLine());
         
         String slight = st.nextToken();
         String sswitch = st.nextToken();
         
         int lights = 0;
         int switches = 0;
         
         for(int k = 0; k < n; k++){
            if(slight.charAt(k) == '1') lights += (1 << k);
            if(sswitch.charAt(k) == '1') switches += (1 << k);
         }
         
         int mask = 0;
         
         for(int k = 0; k < M; k++){
            if(dp[k][lights^mask]){
               out.println(k);
               break;
            }
            //update mask
            mask^=switches;
            //rotate switches
            int topbit = (switches >> (n-1));
            switches -= (topbit << (n-1));
            switches <<= 1;
            switches += topbit;
         }
      }
      
      
      
      
      
      
      out.close();
   }
   
      
}
//make sure to make new file!
import java.io.*;
import java.util.*;

public class F{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      int sum = 0;
      int[] freq = new int[7];
      int[] array = new int[n];
      st = new StringTokenizer(f.readLine());
      for(int k = 0; k < n; k++){
         array[k] = Integer.parseInt(st.nextToken());
         sum += array[k];
         freq[array[k]]++;
      }
      
      if(sum == m){
         out.println("0");
         out.close();
         return;
      }
      
      long[] pow6 = new long[25];
      pow6[0] = 1;
      for(int k = 1; k < 25; k++){
         pow6[k] = pow6[k-1]*6L;
      }
      
      long[][] probs = new long[24][145];
      
      for(int k = 1; k <= 6; k++){
         probs[0][k] = 1L;
      }
      
      for(int k = 0; k < 23; k++){
         for(int j = 0; j < 145; j++){
            if(probs[k][j] == 0) continue;
            for(int h = j+1; h <= j+6; h++){
               probs[k+1][h] += probs[k][j];
            }
         }
      }
      
      for(int k = 0; k < 23; k++){
         for(int j = 0; j < 145; j++){
            probs[k][j] *= pow6[23-k];
         }
      }
      
      ArrayList<HashSet<Integer>> poss = new ArrayList<HashSet<Integer>>(25);
      for(int k = 0; k <= 24; k++) poss.add(new HashSet<Integer>());
      
      boolean[][][] calc = new boolean[7][25][145];
      
      for(int k = 1; k <= 6; k++){
         for(int g = 1; g <= freq[k]; g++){
            calc[k][g][k*g] = true;
            
            poss.get(g).add(k*g);
         }
         
         if(k == 6) break;
         for(int j = 1; j <= 24; j++){
            for(int h = 1; h <= 144; h++){
               if(!calc[k][j][h]) continue;
               for(int g = 0; g <= freq[k+1]; g++){
                  calc[k+1][j+g][h+(k+1)*g] = true;
                  
                  poss.get(j+g).add(h+(k+1)*g);
               }
            }
         }
      }
      
      
      /*
      for(int k = 0; k < 1<<24; k++){
         
         int cursum = 0;
         int i = k;
         int num1 = 0;              //number of 1s so far
         int numt = 0;              //total number of digits
         while(i > 0 && numt < n){
            if(i%2 == 1){
               cursum += array[numt];
               num1++;
            }
            numt++;
            i >>= 1;
         }
         
         poss.get(num1).add(cursum);
      }*/
      
      long maxprob = -1;
      int answer = -1;
      
      for(int k = 1; k <= 24; k++){
         for(int sump : poss.get(k)){
            int i = m-(sum-sump);
            if(i >= 0 && i <= 144){
               if(probs[k-1][i] > maxprob){
                  maxprob = probs[k-1][i];
                  answer = k;
               }
            }
         }
      }
      
      out.println(answer);
                  
      
      
      
      
      
      out.close();
   }
   
      
}
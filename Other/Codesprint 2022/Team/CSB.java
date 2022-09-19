//make sure to make new file!
import java.io.*;
import java.util.*;

public class CSB{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int x = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      int[] freqrem = new int[10];
      Arrays.fill(freqrem,4);
      int[] array = new int[14];
      for(int k = 0; k < 14; k++){
         array[k] = Integer.parseInt(st.nextToken());
         freqrem[array[k]]--;
      }
      
      double[] odds = new double[10];
      for(int k = 1; k <= 9; k++){
         odds[k] = 1.0*freqrem[k]/22.0;
      }
      
      double max = 0.0;
      for(int k = 0; k < 14; k++){
         
         double sum = 0.0;
         for(int j = 1; j <= 9; j++){
            int[] cur = new int[14];
            for(int h = 0; h < 14; h++){
               cur[h] = array[h];
            }
            cur[k] = j;
            
            Arrays.sort(cur);
            
            int score = getscore(cur,x);
            
                 
            sum += (double)score * odds[j];
            
         }
         
         max = Math.max(max,sum);
      }
      
      
      
      out.println(max);
      
      
      
      
      
      
      
      out.close();
   }
   
   public static int getscore(int[] array,int x){
      //check completed
      
      int[] freq = new int[10];
      for(int k = 0; k < 14; k++){
         freq[array[k]]++;
      }
      
      //try all values to be the pair
      boolean found = false;
      for(int k = 1; k <= 9; k++){
         if(freq[k] < 2) continue;
         int[] curfreq = new int[10];
         for(int j = 1; j <= 9; j++){
            curfreq[j] = freq[j];
         }
         curfreq[k] -= 2;
         
         if(check(curfreq)){
            found = true;
            break;
         }
      }
      
      if(!found) return 0;
      
      return 1 + freq[x];
   }
      
      
   public static boolean check(int[] freq){
      for(int k = 1; k <= 9; k++){
         if(freq[k] < 0) return false;
         while(freq[k] >= 3) freq[k] -= 3;
         if(freq[k] > 0){
            if(k+2 > 9){
               return false;
            }
            freq[k+1]-=freq[k];
            freq[k+2]-=freq[k];
         }
         freq[k] = 0;
      }
      
      return true;
   
            
   }
      
}
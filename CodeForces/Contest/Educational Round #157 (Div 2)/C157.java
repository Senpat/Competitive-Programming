//make sure to make new file!
import java.io.*;
import java.util.*;

public class C157{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      int[][] array = new int[n][5];
      int[] lens = new int[n];
      int[] sums = new int[n];
      
      long[][] lensum = new long[6][46];        //number of numbers with that len and sum
      long[][] matchto = new long[6][46];
      
      for(int k = 0; k < n; k++){
         String s = st.nextToken();
         lens[k] = s.length();
         sums[k] = 0;
         for(int j = 0; j < lens[k]; j++){
            array[k][j] = Character.getNumericValue(s.charAt(j));
            sums[k] += array[k][j];
         }
         
         lensum[lens[k]][sums[k]]++;
         
         int suffsum = 0;
         for(int j = 0; j < lens[k]; j++){
            int lenmatch = lens[k] - 2*(j+1);
            if(lenmatch <= 0 || lenmatch > 5) continue;
            suffsum += array[k][j];
            int summatch = (sums[k]-suffsum) - suffsum;
            if(summatch <= 0 || summatch > 45) continue;
            
            if(lenmatch % 2 == lens[k]%2){
               matchto[lenmatch][summatch]++;
            }
         }
         
      }
      
      long answer = 0L;
      for(int k = 0; k < n; k++){
         int presum = sums[k];
         for(int j = 0; j < lens[k]; j++){
            int lenmatch = lens[k] - 2*(j);
            if(lenmatch <= 0) continue;
            if(lenmatch%2 == lens[k]%2){
               int needsum = presum - (sums[k]-presum);
               if(needsum <= 0 || needsum > 45) continue;
               answer += lensum[lenmatch][needsum];
               //out.println(k + " " + (lens[k]-j) + " " + lensum[lens[k]-j][presum]);
            }
            presum -= array[k][lens[k]-j-1];
         }
         
         answer += matchto[lens[k]][sums[k]];
         //out.println(k + " match to: " + matchto[lens[k]][sums[k]]);
      }
      
      out.println(answer);
      
      
      
      
      
      
      
      
      
      
      out.close();
   }
   
      
}
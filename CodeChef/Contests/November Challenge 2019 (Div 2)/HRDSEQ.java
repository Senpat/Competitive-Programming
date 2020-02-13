//make sure to make new file!
import java.io.*;
import java.util.*;

class HRDSEQ{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         int n = Integer.parseInt(f.readLine());
         
         if(n==1){
            out.println(1);
            continue;
         }
         
         int[] array = new int[n];
         
         int[] last = new int[n];
         
         int[] count = new int[n];
            
         array[0] = 0;
         array[1] = 0;
         last[0] = 0;
         count[0] = 2;         
         for(int k = 1; k < n-1; k++){
            if(k>1 && last[array[k]] == 0){
               array[k+1] = 0;
            } else {
               array[k+1] = k-last[array[k]];
            }
            last[array[k]] = k;
            count[array[k+1]]++;
         }
         
         int answer = count[array[n-1]];
         out.println(answer);
      
      }
      
      
      
      
      out.close();
   }
   
      
}
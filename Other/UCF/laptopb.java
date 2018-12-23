//make sure to make new file!
import java.io.*;
import java.util.*;

public class laptopb{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n1 = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= n1; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         int r = Integer.parseInt(st.nextToken());
         
         char[] array = f.readLine().toCharArray();
         
         int[] rp = new int[n+1];
         int[] gp = new int[n+1];
         int[] bp = new int[n+1];
         
         for(int k = 0; k < n; k++){
            rp[k+1] = rp[k];
            gp[k+1] = gp[k];
            bp[k+1] = bp[k];
            
            if(array[k] == 'R') rp[k+1]++;
            if(array[k] == 'G') gp[k+1]++;
            if(array[k] == 'B') bp[k+1]++;
         }
         int R = rp[n];                               //total number of that color
         int G = gp[n];
         int B = bp[n];
         
         int[] dp = new int[n][n];                    //[num buttons pressed][position] = mindistance to that state
         
         
         
         
      
      }
      
      out.close();
   }
   
}
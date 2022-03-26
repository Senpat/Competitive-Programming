//make sure to make new file!
import java.io.*;
import java.util.*;

public class Fb{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      char[] array = f.readLine().toCharArray();
      int n = array.length;
         
      long[][] dp = new long[n][n];
      
      //start
      for(int k = 0; k < n-1; k++){
         if(array[k] == '?' && array[k+1] == '?'){
            dp[k][k+1] = 4L;
         } else if(one(array[k],array[k+1])){
            dp[k][k+1] = 1L;
         } else {
            dp[k][k+1] = 0L;
         }
      }
      
      for(int k = 4; k <= n; k+=2){
         for(int j = 0; j < n-k+1; j++){
            //calculate dp[j][j+k-1]
            long mul = 0L;
            if(array[j] == '?' && array[j+k-1] == '?'){
               mul = 4L;
            } else if(one(array[j],array[j+k-1])){
               mul = 1L;
            } else {
               mul = 0L;
            }
            long[] curdp = new long[n];
            for(int h = j+2; h <= j+k-2; h+=2){
               curdp[h] = dp[j+1][h];
               for(int g = j+3; g < h; g += 2){
                  curdp[h] += curdp[g-1] * dp[g][h];
               }
            }
            dp[j][j+k-1] = curdp[j+k-2]*mul;
         }
      }
      
      long[] curdp = new long[n];
      for(int h = 1; h <= n-1; h+=2){
         curdp[h] = dp[0][h];
         for(int g = 2; g < h; g += 2){
            curdp[h] += curdp[g-1] * dp[g][h];
         }
      }
      
      long answer = curdp[n-1];
      out.println(answer);
      
      
      out.close();
   }
   
   
   public static boolean one(char a, char b){
      if(a == '(' && (b == ')' || b == '?')) return true;
      if(a == '[' && (b == ']' || b == '?')) return true;
      if(a == '{' && (b == '}' || b == '?')) return true;
      if(a == '<' && (b == '>' || b == '?')) return true;
      if(b == ')' && (a == '(' || a == '?')) return true;
      if(b == ']' && (a == '[' || a == '?')) return true;
      if(b == '}' && (a == '{' || a == '?')) return true;
      if(b == '>' && (a == '<' || a == '?')) return true;
      return false;
   }
      
}
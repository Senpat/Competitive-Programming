//make sure to make new file!
import java.io.*;
import java.util.*;

public class G808{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      char[] s = ("0" + f.readLine()).toCharArray();
      char[] t = f.readLine().toCharArray();
      
      int sn = s.length-1;
      int tn = t.length;
      
      int[] z = getz(t);
      
      int[] dp = new int[sn+1];        //dp[x] = max answer where x is end of an instance of t
      
      int runningmax = 0;
      for(int k = 1; k <= sn; k++){
         //check if you can add a t here
         if(tn > k) continue;
         runningmax = Math.max(runningmax,dp[k-tn]);
         
         boolean fail = false;
         int max = runningmax;
         
         for(int j = 0; j < tn; j++){
            int i = k-tn+1+j;
            if(s[i] != '?' && s[i] != t[j]){
               fail = true;
               break;
            }
            
            if(tn-j-1+z[tn-j-1] == tn){
               max = Math.max(max,dp[i]);
            }
         }
         
         if(!fail){
            dp[k] = max+1;
         }
         
      }
      
      int max = 0;
      for(int k = 0; k <= sn; k++){
         max = Math.max(max,dp[k]);
      }
      out.println(max);
      
      
      
      
      
      out.close();
   }
   
   //z function:
   //aaabaab -> 0 2 1 0 2 1 0
   public static int[] getz(char[] s){
      int n = s.length;
      int[] z = new int[n];
      int l = -1;
      int r = -1;
      for(int i = 1; i < n; i++){
         z[i] = i >= r ? 0 : Math.min(r - i, z[i - l]);
         while (i + z[i] < s.length && s[i + z[i]] == s[z[i]])
            z[i]++;
         if (i + z[i] > r)
            l = i;
         r = i + z[i];
      
      }
      
      return z;
   }
   
      
}
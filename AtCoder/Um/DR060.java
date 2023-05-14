//make sure to make new file!
import java.io.*;
import java.util.*;

public class DR060{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      char[] s = f.readLine().toCharArray();
      
      int n = s.length;
      
      char[] sr = new char[n];
      for(int k = 0; k < n; k++){
         sr[k] = s[n-k-1];
      }
      
      int[] kmp = getkmp(s);
      int[] kmpr = getkmp(sr);
      
      //check if the answer is 1
      if(kmp[n-1] == n-1){
         out.println(n);
         out.println(1);
      } else if(!(kmp[n-1] >= (n+1)/2 && n % (n-kmp[n-1]) == 0)){
         out.println("1");
         out.println("1");
      } else {
         //answer must be 2 because s[0,n-2] and s[n-1] are always a valid representation
         
         int count = 0;
         for(int k = 0; k < n-1; k++){
            //if left side is bad
            boolean left = (k != 0 && (kmp[k] >= (k+1+1)/2 && (k+1) % (k+1-kmp[k]) == 0));
            //if right side is bad
            int kr = n-k-1-1;
            boolean right = (kr != 0 && (kmpr[kr] >= (kr+1+1)/2 && (kr+1) % (kr+1-kmpr[kr]) == 0));
            //out.println(k + " " + kr + " " + left + " " + right);
            //if left side is not bad and right side is not bad, count it to the answer
            if(!left && !right) count++; 
         }
         
         out.println("2");
         out.println(count);
      }
      
      
      
      
      
      
      out.close();
   }
   
   //kmp:
   //abcabcd -> 0 0 0 1 2 3 0
   public static int[] getkmp(char[] s){
      int n = s.length;
      int[] kmp = new int[n];
      
      for(int k = 1; k < n; k++){
         int j = kmp[k-1];
         while(j > 0 && s[k] != s[j]){
            j = kmp[j-1];
         }
         if(s[k] == s[j]){
            j++;
         }
         kmp[k] = j;
      }
      
      return kmp;
   }
   
   
   
   
}
//make sure to make new file!
import java.io.*;
import java.util.*;

public class P1732{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      String s = f.readLine();
      int n = s.length();
      
      int[] kmp = getkmp(s.toCharArray());
      
      int i = n-1;
      ArrayList<Integer> answer = new ArrayList<Integer>();
      
      while(kmp[i] > 0){
         answer.add(kmp[i]);
         i = kmp[i]-1;
      }
      
      Collections.sort(answer);
      
      StringJoiner sj = new StringJoiner(" ");
      for(int a : answer){
         sj.add("" + a);
      }
      out.println(sj.toString());
      
      
      
      
      
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
//make sure to make new file!
import java.io.*;
import java.util.*;

public class P1731{

   public static long MOD = 1000000007L;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      char[] s = f.readLine().toCharArray();
      int n = s.length;
      
      int m = Integer.parseInt(f.readLine());
      
      String[] words = new String[m];
      for(int k = 0; k < m; k++){
         words[k] = f.readLine();
      }
      
      Trie head = new Trie();
      
      //make trie
      for(int k = 0; k < m; k++){
         int cn = words[k].length();
         
         Trie trie = head;
         for(int j = 0; j < cn; j++){
            int letter = words[k].charAt(j)-'a';
            if(trie.children[letter] == null){
               trie.children[letter] = new Trie();
            }
            
            trie = trie.children[letter];
         }
         
         trie.ends++;
      }
      
      long[] dp = new long[n+1];
      dp[0] = 1L;
      for(int k = 0; k < n; k++){
         Trie trie = head;
         for(int j = k+1; j <= n; j++){
            int letter = s[j-1]-'a';
            if(trie.children[letter] == null){
               break;
            }
            
            trie = trie.children[letter];
            
            dp[j] = (dp[j] + dp[k]*trie.ends + MOD)%MOD;
            
         }
      }
      
      out.println(dp[n]);
      
      
      
      
      
      
      out.close();
   }
   
   public static class Trie{
      Trie[] children;
      int ends;
      
      public Trie(){
         ends = 0;
         
         children = new Trie[26];
      }
   }
   
      
}
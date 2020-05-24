//make sure to make new file!
import java.io.*;
import java.util.*;

public class R{

   public static long count;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      String s = f.readLine();
      
      Trie trie = new Trie('?');
      
      for(int k = 0; k < n; k++){
         String s1 = s.substring(k);
         add(trie,s1,0);
      }
      
      count = 0L;
      
      
      dfs(trie);
      
      out.println(count-1);
      

      
      
      
      
      
      out.close();
   }
   
   public static void dfs(Trie trie){
      count++;
      
      for(int k = 0; k < 26; k++){
         if(trie.children[k] != null){
            dfs(trie.children[k]);
         }
      }
   }
      
   
   public static void add(Trie trie, String s, int i){
      int ti = s.charAt(i)-'a';
      if(trie.children[ti] == null){
         trie.children[ti] = new Trie(s.charAt(i));
      }
      
      if(i < s.length() - 1){
         add(trie.children[ti],s,i+1);
      }
   }
  
   public static class Trie{
      char value;
      Trie[] children;
      int i;
      public Trie(char c){
         value = c;
         i = 0;
         children = new Trie[26];
      }
   }
      
}
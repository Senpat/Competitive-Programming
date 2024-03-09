//Collapsing Strings
import java.io.*;
import java.util.*;

public class E1902{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      String[] s = new String[n];
      long slensum = 0L;
      for(int k = 0; k < n; k++){
         s[k] = f.readLine();
         slensum += (long)s[k].length();
      }
      
      Trie head = new Trie();
      
      for(int k = 0; k < n; k++){
         int len = s[k].length();
         
         Trie cur = head;
         for(int j = 0; j < len; j++){
            int ci = s[k].charAt(j)-'a';
            
            if(cur.children[ci] == null) cur.children[ci] = new Trie();
            cur = cur.children[ci];
            
            cur.lensum++;
         }
      }
      
      long answer = 2L*(long)n * slensum;
      for(int k = 0; k < n; k++){
         int len = s[k].length();
         
         Trie cur = head;
         for(int j = len-1; j >= 0; j--){
            int ci = s[k].charAt(j)-'a';
            
            if(cur.children[ci] == null) break;
            cur = cur.children[ci];
            
            answer -= 2L*cur.lensum;
         }
      }
      
      out.println(answer);
      
      
      
      
      
      
      
      
      out.close();
   }
   
   public static class Trie{
      public Trie[] children;
      public long lensum;
      
      public Trie(){
         children = new Trie[26];
         lensum = 0L;
      }
   }
   
      
}
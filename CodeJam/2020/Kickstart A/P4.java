//make sure to make new file!
import java.io.*;
import java.util.*;

public class P4{

   public static int m;
   public static long answer;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         m = Integer.parseInt(st.nextToken());
         
         Trie trie = new Trie('?',1,0,null);
         
         for(int k = 0; k < n; k++){
            String s = f.readLine();
            add(trie,s,0);
         }
         
         answer = 0L;
         
         dfs(trie);
         
         out.println("Case #" + q + ": "+ answer);
      
      }
      
      
      
      
      
      
      out.close();
   }
   
   public static void dfs(Trie trie){
      
      for(int k = 0; k < 26; k++){
         if(trie.children[k] != null){
            dfs(trie.children[k]);
         }
      }
      
      if(trie.value >= m){
         answer += (long)(trie.value/m*trie.depth);
      }
      
      if(trie.parent!=null){
         trie.parent.value += trie.value%m;
      }
   }
   
   public static void add(Trie trie, String s, int i){
      int ti = s.charAt(i)-'A';
      if(trie.children[ti] == null){
         trie.children[ti] = new Trie(s.charAt(i),0,i+1,trie);
      }
      
      if(i == s.length()-1){
         trie.children[ti].value++;
      }
      
      if(i < s.length() - 1){
         add(trie.children[ti],s,i+1);
      }
   }
  
   
   public static class Trie{
      char c;
      Trie[] children;
      int value;
      Trie parent;
      int depth;
      public Trie(char a, int b, int d, Trie p){
         c = a;
         value = b;
         parent = p;
         depth = d;
         children = new Trie[26];
      }
   }
   
      
}
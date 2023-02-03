//make sure to make new file!
import java.io.*;
import java.util.*;

public class K{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      Trie trie = new Trie();
      int answer = 0;
      for(int k = 0; k < n; k++){
         char[] si = f.readLine().toCharArray();
         int ni = si.length;
         
         int[] s = new int[ni];
         for(int j = 0; j < ni; j++){
            s[j] = si[j]-'A';
         }
         
         int[] z = getz(s);
         z[0] = ni;
         Trie curtrie = trie;
         int curmax = -1;
         for(int j = 0; j < ni; j++){
            if(curtrie.children[s[j]] == null){
               curtrie.children[s[j]] = new Trie();
            }
            curtrie = curtrie.children[s[j]];
            
            if(z[ni-j-1] == j+1 && curtrie.val != -1){
               curmax = Math.max(curmax,curtrie.val);
            }
         }
         
         curtrie.val = Math.max(curtrie.val,curmax+1);
         answer = Math.max(answer,curmax+1);
      }
      
      out.println(answer+1);
      
      out.close();
   }
   
   public static int[] getz(int[] s){
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
   
   public static class Trie{
      int val;
      Trie[] children;
      public Trie(){
         children = new Trie[26];
         val = -1;
      }
   }
   
      
}
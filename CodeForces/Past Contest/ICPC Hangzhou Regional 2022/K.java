//make sure to make new file!
import java.io.*;
import java.util.*;

public class K{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int q = Integer.parseInt(st.nextToken());
      
      String[] array = new String[n];
      
      for(int k = 0; k < n; k++){
         array[k] = f.readLine();
      }
      
      int[][] orders = new int[q][26];
      for(int k = 0; k < q; k++){
         char[] input = f.readLine().toCharArray();
         for(int j = 0; j < 26; j++){
            orders[k][j] = input[j] - 'a';
         }
      }
      
      //z = pairs[x][y] means that z pairs need x before y
      long[][] pairs = new long[26][26];
      
      Trie head = new Trie();
      
      long all = 0L;
      
      for(int k = 0; k < n; k++){
         Trie cur = head;
         for(int j = 0; j < array[k].length(); j++){
            int ci = array[k].charAt(j) - 'a';
            if(cur.children[ci] == null){
               cur.children[ci] = new Trie();
            }
            
            for(int h = 0; h < 26; h++){
               if(h != ci && cur.children[h] != null){
                  pairs[h][ci] += cur.children[h].freq;
               }
            }
            
            cur = cur.children[ci];
            cur.freq++;
            
         }
         
         //add superstrings
         for(int j = 0; j < 26; j++){
            if(cur.children[j] != null){
               all += cur.children[j].freq;
            }
         }
      }
      
      StringJoiner sj = new StringJoiner("\n");
      for(int qq = 0; qq < q; qq++){
         long answer = all;
         for(int k = 0; k < 26; k++){
            for(int j = k+1; j < 26; j++){
               answer += pairs[orders[qq][j]][orders[qq][k]];  
            }
         }
         sj.add("" + answer);
      }
      
      out.println(sj.toString());
      
      
      
      
      
      
      out.close();
   }
   
   public static class Trie{
      Trie[] children;
      long freq;
      public Trie(){
         children = new Trie[26];
         freq = 0L;      
      }
   }
}
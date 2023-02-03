//make sure to make new file!
import java.io.*;
import java.util.*;

public class D142{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         int m = Integer.parseInt(st.nextToken());
      
         int[][] arrays = new int[n][m+1];
         for(int k = 0; k < n; k++){
            st = new StringTokenizer(f.readLine());
            
            for(int j = 1; j <= m; j++){
               arrays[k][j] = Integer.parseInt(st.nextToken());
            }
         }
         
         Trie trie = new Trie(m);
         //make trie with indexof
         for(int k = 0; k < n; k++){
            int[] indexof = new int[m+1];
            for(int j = 1; j <= m; j++){
               indexof[arrays[k][j]] = j;
            }
            
            Trie curtrie = trie;
            for(int j = 1; j <= m; j++){
               if(curtrie.children[indexof[j]] == null){
                  curtrie.children[indexof[j]] = new Trie(m);
               }
               curtrie = curtrie.children[indexof[j]];
            }
         }
         
         int[] answer = new int[n];
         Arrays.fill(answer,0);
         for(int k = 0; k < n; k++){
            //get max beauty
            Trie curtrie = trie;
            
            for(int j = 1; j <= m; j++){
               if(curtrie.children[arrays[k][j]] == null) break;
               answer[k]++;
               curtrie = curtrie.children[arrays[k][j]];
            }
         }
         
         StringJoiner sj = new StringJoiner(" ");
         for(int k = 0; k < n; k++){
            sj.add("" + answer[k]);
         }
         out.println(sj.toString());
      }
      
      
      
      
      out.close();
   }
   
   public static class Trie{
      Trie[] children;
      public Trie(int m){
         children = new Trie[m+1];
      }
   }
      
}
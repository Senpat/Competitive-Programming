//make sure to make new file!
import java.io.*;
import java.util.*;

public class P101{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      int[] array = new int[n];
      st = new StringTokenizer(f.readLine());
      for(int k = 0; k < n; k++){
         array[k] = Integer.parseInt(st.nextToken());
      }
      
      int[] equal = new int[n];
      int[] less = new int[n];
      Arrays.fill(equal,-1);
      Arrays.fill(less,-1);
      
      Trie head = new Trie();
      
      for(int k = 0; k < n; k++){
         Trie cur = head;
         
         for(int j = 29; j >= 0; j--){
            int mc = ((m>>j)&1);
            int ac = ((array[k]>>j)&1);
            
            int xor = mc^ac;
            if(mc == 1 && cur.children[1-xor] != null){
               less[k] = Math.max(less[k],cur.children[1-xor].max);
            }
            
            if(cur.children[xor] == null){
               break;
            }
         
            cur = cur.children[xor];
            
            if(j == 0){
               equal[k] = cur.max;
            }
         }
         
         cur = head;
         for(int j = 29; j >= 0; j--){
            int ac = ((array[k]>>j)&1);
            
            if(cur.children[ac] == null){
               cur.children[ac] = new Trie();
            }
            
            cur = cur.children[ac];
            cur.max = k;
         }
         
         
      }
      
      
      long answer = 0L;
      
      int equalmax = -1;
      int lessmax = -1;  
      for(int k = 0; k < n; k++){
         equalmax = Math.max(equalmax,equal[k]);
         lessmax = Math.max(lessmax,less[k]);
         
         if(equalmax != -1 && equalmax > lessmax){
            answer += (long)(equalmax-lessmax);
         }
      }
      
      out.println(answer);
      
      
      
      
      
      
      
      out.close();
   }
   
   
   public static class Trie{
      Trie[] children;
      int max;
      
      public Trie(){
         children = new Trie[2];
         max = -1;
      }
   }
      
}
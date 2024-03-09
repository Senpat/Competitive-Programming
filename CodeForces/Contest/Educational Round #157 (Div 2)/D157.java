//make sure to make new file!
import java.io.*;
import java.util.*;
//upsolve
public class D157{

   public static int P = 20;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      int[] array = new int[n-1];
      int[] pxor = new int[n];
      for(int k = 0; k < n-1; k++){
         array[k] = Integer.parseInt(st.nextToken());
         pxor[k+1] = pxor[k] ^ array[k];
      }
      
      int b1 = getb1(pxor);
      
      int[] answer = new int[n];
      answer[0] = b1;
      for(int k = 1; k < n; k++){
         answer[k] = answer[k-1] ^ array[k-1];
      }
      
      StringJoiner sj = new StringJoiner(" ");
      for(int k = 0; k < n; k++){
         sj.add("" + answer[k]);
      }
      out.println(sj.toString());
      
      out.close();
   }
   
   public static int getb1(int[] pxor){
      int n = pxor.length;
      
      int max = 0;
      for(int k = 1; k < n; k++){
         max = Math.max(max,pxor[k]);
      }
      
      if(max == n-1) return 0;
      
      //make a trie with all prefix xors
      Trie head = new Trie();
      for(int k = 1; k < n; k++){
         Trie cur = head;
         for(int p = P; p >= 0; p--){
            int next = (pxor[k]>>p)&1;
            if(cur.children[next] == null) cur.children[next] = new Trie();
            cur = cur.children[next];
         }
      }
      
      //find a value whose max is n-1
      for(int k = 1; k < n; k++){
         Trie cur = head;
         int curmax = 0;
         for(int p = P; p >= 0; p--){
            int bit = (pxor[k]>>p)&1;
            if(cur.children[1-bit] != null){
               curmax += (1 << p);
               cur = cur.children[1-bit];
            } else {
               cur = cur.children[bit];
            }
         }
         
         if((curmax == n-1 && pxor[k] < n-1) || (curmax == n-2 && pxor[k] == n-1)) return pxor[k];
      }
      
      return -1;
      
   }
   
   public static class Trie{
      Trie[] children;
      public Trie(){
         children = new Trie[2];
      }
   }
   
      
}
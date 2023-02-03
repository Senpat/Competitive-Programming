//make sure to make new file!
import java.io.*;
import java.util.*;
//upsolve, tutorial
public class F845{

   public static int[] array;
   public static int[] pxor;
   //for cartesian tree
   public static int[] left;
   public static int[] right;
   
   public static int answer = 0;
   
   public static int M = 30;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         int n = Integer.parseInt(f.readLine());
      
         StringTokenizer st = new StringTokenizer(f.readLine());
         array = new int[n+1];
         for(int k = 1; k <= n; k++){
            array[k] = Integer.parseInt(st.nextToken());
         }
         
         //prefix xor
         pxor = new int[n+1];
         pxor[0] = 0;
         for(int k = 1; k <= n; k++){
            pxor[k] = pxor[k-1] ^ array[k];
         }
         
         //cartesian tree
         left = new int[n+1];      //stores left child in cartesian tree
         right = new int[n+1];     //stores right child in cartesian tree
         Arrays.fill(left,-1);
         Arrays.fill(right,-1);
         
         Stack<Integer> stack = new Stack<Integer>();
         
         for(int k = 1; k <= n; k++){
            while(!stack.isEmpty() && array[stack.peek()] <= array[k]){
               int i = stack.pop();
               left[k] = i;
            }
            
            if(!stack.isEmpty()){
               right[stack.peek()] = k;
            }
            
            stack.push(k);
         }
      
         int head = stack.pop();
         while(!stack.isEmpty()){
            head = stack.pop();
         }
         
         answer = 0;
         
         dfs(head,1,n);    
         
         out.println(answer); 
         
      }
      
      
      
      
      out.close();
   }
   
   
   public static Trie dfs(int x, int l, int r){
      if(left[x] == -1 && right[x] == -1){
         Trie trie = new Trie();
         add(trie,pxor[x-1]);
         add(trie,pxor[x]);
         return trie;
      }
      
      Trie lt = null;
      Trie rt = null;
      if(left[x] != -1) lt = dfs(left[x],l,x-1);
      if(right[x] != -1) rt = dfs(right[x],x+1,r);
      
      if(left[x] == -1){
         //only rt
         //calculate the answer with array[x] and trie in rt
         answer = Math.max(answer,calc(rt,array[x],pxor[x-1]));
         add(rt,pxor[x-1]);
         return rt;
      }
      
      if(right[x] == -1){
         //only lt
         answer = Math.max(answer,calc(lt,array[x],pxor[x]));
         add(lt,pxor[x]);
         return lt;
      }
      
      //both tries are not null
      if(r-x+1 < x-l+1){
         //left is bigger (merge right to left)
         //calc answer
         answer = Math.max(answer,calc(lt,array[x],pxor[x]));
         for(int k = x; k <= r; k++){
            answer = Math.max(answer,calc(lt,array[x],pxor[k]));
         }
         //merge
         for(int k = x; k <= r; k++){
            add(lt,pxor[k]);
         }
         return lt;
      } else {
         //right is bigger (merge left to right)
         //calc answer
         answer = Math.max(answer,calc(rt,array[x],pxor[x-1]));
         for(int k = l-1; k < x; k++){
            answer = Math.max(answer,calc(rt,array[x],pxor[k]));
         }
         for(int k = l-1; k < x; k++){
            add(rt,pxor[k]);
         }
         return rt;
      }
      
      
      
   
   
   }
   
   //calculate the answer for a trie, a
   public static int calc(Trie trie, int ax, int v){
      int x = ax^v;
      
      Trie curtrie = trie;
      int answer = 0;
      for(int k = (1 << M); k >= 1; k >>= 1){
         if((x&k) == 0){
            //look in 1
            if(curtrie.children[1] != null){
               answer += k;
               curtrie = curtrie.children[1];
            } else {
               curtrie = curtrie.children[0];
            }
         } else {
            //look in 0
            if(curtrie.children[0] != null){
               answer += k;
               curtrie = curtrie.children[0];
            } else {
               curtrie = curtrie.children[1];
            }
         }
      }
      return answer;
   }
   
   //add x to trie
   public static void add(Trie trie, int x){
      Trie curtrie = trie;
      for(int i = (1 << M); i >= 1; i >>= 1){
         if((x&i) == 0){
            //go in 0 direction
            if(curtrie.children[0] == null){
               curtrie.children[0] = new Trie();
            }
            curtrie = curtrie.children[0];
         } else {
            //go in 1 direction
            if(curtrie.children[1] == null){
               curtrie.children[1] = new Trie();
            }
            curtrie = curtrie.children[1];
         }
      }
      
   }
   
   public static class Trie{
      int val;
      Trie[] children;
      public Trie(){
         children = new Trie[2];
      }
   }
      
}
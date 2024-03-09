//make sure to make new file!
import java.io.*;
import java.util.*;
//fail
public class D160{
   
   public static long MOD = 998244353L;
   
   public static int[] l;
   public static int[] r;
   public static int[] p;
   
   public static long[] leftchop;
   public static long[] rightchop;
   public static long[] full;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         int n = Integer.parseInt(f.readLine());
      
         StringTokenizer st = new StringTokenizer(f.readLine());
         int[] array = new int[n];
         for(int k = 0; k < n; k++){
            array[k] = Integer.parseInt(st.nextToken());
         }
         
         l = new int[n];
         r = new int[n];
         p = new int[n];
         Arrays.fill(l,-1);
         Arrays.fill(r,-1);
         Arrays.fill(p,-1);
         
         Stack<Integer> stack = new Stack<Integer>();    //stores increasing sequence  
         
         for(int k = 0; k < n; k++){
            int prev = -1;
            while(!stack.isEmpty() && array[stack.peek()] > array[k]){
               prev = stack.pop();
            }
            if(prev != -1){
               l[k] = prev;
               p[prev] = k;
            }
            if(!stack.isEmpty()){
               r[stack.peek()] = k;
               p[k] = stack.peek();
            }
            
            stack.push(k);
         }
         
         int root = stack.peek();
         while(!stack.isEmpty()){
            root = stack.pop();
         }
         
         /*
         for(int k = 0; k < n; k++){
            out.print(array[k] + " ");
            out.print((l[k] == -1 ? "-" : array[l[k]]) + " ");
            out.print((r[k] == -1 ? "-" : array[r[k]]) + " ");
            out.print((p[k] == -1 ? "-" : array[p[k]]) + " ");
            out.println();
         }*/
         
         leftchop = new long[n];
         rightchop = new long[n];
         full = new long[n];
         
         dfs(root);
         
         out.println(full[root]);

      }
      
      
      
      
      out.close();
   }
   
   public static void dfs(int v){
      long llchop = 0L;
      long lrchop = 0L;
      long lfull = 0L;
      if(l[v] != -1){
         dfs(l[v]);
         llchop = leftchop[l[v]]+1;
         lrchop = rightchop[l[v]]+1;
         lfull = full[l[v]];
      }
      
      long rlchop = 0L;
      long rrchop = 0L;
      long rfull = 0L;
      if(r[v] != -1){
         dfs(r[v]);
         rlchop = leftchop[r[v]]+1;
         rrchop = rightchop[r[v]]+1;
         rfull = full[r[v]];
      }
       
      leftchop[v] = (llchop + lfull*rlchop + MOD)%MOD;
      rightchop[v] = (rrchop + rfull*lrchop + MOD)%MOD;
      full[v] = ((leftchop[v]+1L) * (rightchop[v]+1L) + MOD)%MOD;
      
   }
   
   
}
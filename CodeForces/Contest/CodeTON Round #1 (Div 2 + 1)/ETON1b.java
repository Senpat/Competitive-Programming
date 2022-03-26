//make sure to make new file!
import java.io.*;
import java.util.*;
//based on ETON1.java, use tutorial to know how to set A and x1 initially
public class ETON1b{

   public static ArrayList<ArrayList<Integer>> adj;
   
   public static long[] answer;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      int N = 100000;
      
      for(int q = 1; q <= t; q++){
      
         int n = Integer.parseInt(f.readLine());
         
         adj = new ArrayList<ArrayList<Integer>>(n+1);
         for(int k = 0; k <= n; k++) adj.add(new ArrayList<Integer>());
         
         for(int k = 0; k < n-1; k++){
            StringTokenizer st = new StringTokenizer(f.readLine());
         
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            
            adj.get(a).add(b);
            adj.get(b).add(a);
         }
         
         long A = -1;
         long x1 = adj.get(1).size();
         
         answer = new long[n+1];
         dfs(1,-1,A,x1);
        
         
         StringJoiner sj = new StringJoiner(" ");
         for(int k = 1; k <= n; k++){
            sj.add("" + answer[k]);
         }
         out.println(sj.toString());
      
      }
      
      
      
      
      out.close();
   }
   
   public static void dfs(int v, int p, long A, long x1){
      
      answer[v] = x1;
      
      long n1 = adj.get(v).size()-1;
      long B = A*n1 + x1;
      
      for(int nei : adj.get(v)){
         if(nei == p) 
            continue;
            
         long n2 = adj.get(nei).size()-1;
         long x2 = A - B*n2;
         
         dfs(nei,v,B,x2);
      }
   
   }
   
      
}
//make sure to make new file!
import java.io.*;
import java.util.*;
//bad sol, try to troll wth random
public class ETON1{

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
         
         while(true){
            answer = new long[n+1];
            
            long A = (long)(Math.random()*N)-N;
            long x1 = (long)(Math.random()*N)-N;
            int start = (int)(Math.random()*n)+1;
            
            dfs(start,-1,A,x1);
            
            boolean fail = false;
            for(int k = 1; k <= n; k++){
               if(answer[k] == 0 || answer[k] > N || answer[k] < -1*N){
                  fail = true;
                  break;
               }
            }
            
            if(!fail) break;
         
         }
         
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
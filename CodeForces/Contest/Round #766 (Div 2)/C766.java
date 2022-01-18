//make sure to make new file!
import java.io.*;
import java.util.*;

public class C766{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         int n = Integer.parseInt(f.readLine());
         
         ArrayList<ArrayList<Edge>> adj = new ArrayList<ArrayList<Edge>>(n+1);
         for(int k = 0 ; k <= n; k++) adj.add(new ArrayList<Edge>());
         
         for(int k = 0; k < n-1; k++){
            StringTokenizer st = new StringTokenizer(f.readLine());
         
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            
            adj.get(a).add(new Edge(b,k));
            adj.get(b).add(new Edge(a,k));
         }
         
         int nei1 = -1;
         boolean fail = false;
         for(int k = 1; k <= n; k++){
            int size = adj.get(k).size();
            if(size == 1) nei1 = k;
            if(!(size == 1 || size == 2)) {
               fail = true;
               break;
            }
         }
         
         if(fail){
            out.println(-1);
            continue;
         }
         
         int[] answer = new int[n];
         int iters = 0;
         int i = nei1;
         int par = -1;
         while(true){
            Edge cure = new Edge(-1,-1);
            for(Edge e : adj.get(i)){
               if(e.to == par) continue;
               cure = e;
               break;
            }
            if(cure.to == -1) break;
            if(iters % 2 == 0) answer[cure.index] = 2;
            else answer[cure.index] = 3;
            
            par = i;
            i = cure.to;
            
            iters++;
         }
            
            
         StringJoiner sj = new StringJoiner(" ");
         for(int k = 0; k < n-1; k++){
            sj.add("" + answer[k]);
         }
         out.println(sj.toString());
      }
      
      
      
      
      out.close();
   }
   
   public static class Edge{
      int to;
      int index;
      
      public Edge(int a, int b){
         to = a;
         index = b;
      }
   }
      
}
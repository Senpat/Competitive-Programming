//Information Graph
import java.io.*;
import java.util.*;

public class E466{

   public static int[] bits;
   public static int N;
   
   public static ArrayList<ArrayList<Integer>> adj;
   
   public static ArrayList<Integer> euler;
   public static int[] start;
   public static int[] end;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int q = Integer.parseInt(st.nextToken());
      
      boolean[] root = new boolean[n+1];
      Arrays.fill(root,true);
      adj = new ArrayList<ArrayList<Integer>>(n+1);
      for(int k = 0; k <= n; k++){
         adj.add(new ArrayList<Integer>());
      }
      
      ArrayList<Query> queries = new ArrayList<Query>();
      int q2 = 1;
      int q3 = 0;
      
      //for every q2 query, store the q3 queries that correspond to it
      ArrayList<ArrayList<Query>> q2queries = new ArrayList<ArrayList<Query>>();
      q2queries.add(new ArrayList<Query>());       //1-indexed
      
      for(int k = 0; k < q; k++){
         st = new StringTokenizer(f.readLine());
         int t = Integer.parseInt(st.nextToken());
         int x = Integer.parseInt(st.nextToken());
         
         if(t == 1){
            int y = Integer.parseInt(st.nextToken());
            if(x == y) continue;
            queries.add(new Query(t,x,y));
            
            //y becomes the boss of x
            adj.get(y).add(x);
            root[x] = false;
         }
         if(t == 2){
            queries.add(new Query(t,x,q2));
            q2++;
            q2queries.add(new ArrayList<Query>());
         }
         if(t == 3){
            int i = Integer.parseInt(st.nextToken());
            q2queries.get(i).add(new Query(t,x,i,q3));
            q3++;
         }
      }
      
      euler = new ArrayList<Integer>();
      euler.add(0);        //1-indexed
      start = new int[n+1];
      end = new int[n+1];
      
      for(int k = 1; k <= n; k++){
         if(root[k]){
            dfs(k);
         }
      }
      
      N = euler.size()+1;
      
      bits = new int[N+1];
      
      boolean[] answer = new boolean[q3];
      for(int t = queries.size()-1; t >= 0; t--){
         //out.println(queries.get(t).t);
         if(queries.get(t).t == 1){
            int x = queries.get(t).x;
            update(start[x],1);
            update(end[x]+1,-1);
         } else if(queries.get(t).t == 2){
            for(Query query : q2queries.get(queries.get(t).i)){
               //check if query.x is an ancestor of queries.get(t).x
               //aka if queries.get(t).x is in the subtree of queryx
               int ancestor = query.x;
               int child = queries.get(t).x;
               
               //out.println(query.index + " " + ancestor + " " + child + " " + start[child] + " " + start[ancestor] + " " + end[ancestor] + " " + psum(start[child]) + " " + psum(start[ancestor]));
               
               //child is within ancestor in the euler tour, and they are on the same level
               if(start[child] >= start[ancestor] && start[child] < end[ancestor] && psum(start[child]) == psum(start[ancestor])){
                  answer[query.index] = true;
               } else {
                  answer[query.index] = false;
               }
            }
         }
      }
      
      StringJoiner sj = new StringJoiner("\n");
      for(int k = 0; k < q3; k++){
         if(answer[k]){
            sj.add("YES");
         } else {
            sj.add("NO");
         }
      }
      
      out.println(sj.toString());
      
      
      
      out.close();
   }
   
   public static void dfs(int v){
      start[v] = euler.size();
      euler.add(v);
      
      for(int nei : adj.get(v)){
         dfs(nei);
      }
      
      end[v] = euler.size();
      euler.add(v);
   }
   
   public static class Query{
      int t;
      
      int x;
      int y;
      int i;         //for query 2 it is index of packet
      int index;
      
      public Query(int qt, int qx, int qyi){
         t = qt;
         if(qt == 1){
            x = qx;
            y = qyi;
         } else if(qt == 2){
            x = qx;
            i = qyi;
         }
      }
      
      public Query(int qt, int qx, int qi, int qindex){
         t = qt;
         x = qx;
         i = qi;
         index = qindex;
      }
   }
   
   
   public static void update(int i, int x){
      for(; i <= N; i += i&-i){
         //System.out.println(i);
         bits[i]+=x;
      }
   
   }
   
   public static int psum(int i){
      int sum = 0;
      for(; i > 0; i -= i&-i){
         //System.out.println(i);
         sum += bits[i];
      }
      return sum;
   
   }

   
   
      
}
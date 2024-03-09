//make sure to make new file!
import java.io.*;
import java.util.*;

public class A{
   
   public static long[] w;
   public static long sumw;
   
   public static ArrayList<ArrayList<Edge>> adj;
   
   public static long nl;
   public static long[] subsize;       //# nodes in subtree (including itself)
   public static long[] subw;          //# sum of weights in subtree (including itself)
   
   public static long a1;
   public static long a2;
   
   public static long[] answer;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      nl = (long)n;
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      w = new long[n+1];
      sumw = 0L;
      for(int k = 1; k <= n; k++){
         w[k] = Long.parseLong(st.nextToken());
         sumw += w[k];
      }
      
      adj = new ArrayList<>(n+1);
      for(int k = 0; k <= n; k++){
         adj.add(new ArrayList<Edge>());
      }
      
      for(int k = 0; k < n-1; k++){
         st = new StringTokenizer(f.readLine());
         
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
         long c = Long.parseLong(st.nextToken());
         
         adj.get(a).add(new Edge(b,c));
         adj.get(b).add(new Edge(a,c));
      }
      
      //calc answer for 1
      subsize = new long[n+1];
      subw = new long[n+1];
      a1 = 0L;
      a2 = 0L;
      dfs1(1,-1);
      
      answer = new long[n+1];
      //answer[1] = a1*w[1] + a2;
      
      //out.println(answer[1]);
      
      dfs2(1,-1, a1, a2);
      
      StringJoiner sj = new StringJoiner("\n");
      for(int k = 1; k <= n; k++){
         sj.add("" + answer[k]);
      }
      out.println(sj.toString());
      
      
      
      
      out.close();
   }
   
   public static void dfs2(int v, int p, long pathsum, long wsum){
      //System.out.println(v + " " + pathsum + " " + wsum);
      answer[v] = pathsum * w[v] + wsum;
      
      for(Edge e : adj.get(v)){
         if(e.to == p) continue;
         
         long newpsum = pathsum - e.w*subsize[e.to] + e.w*(nl-subsize[e.to]);
         long newwsum = wsum - e.w*subw[e.to] + e.w*(sumw-subw[e.to]);
         
         dfs2(e.to,v,newpsum,newwsum);
         
      }
      
      
   }
   
   
   public static void dfs1(int v, int p){
      
      subsize[v] = 1L;
      subw[v] = w[v];
      for(Edge e : adj.get(v)){
         if(e.to == p) continue;
         dfs1(e.to,v);
         
         a1 += subsize[e.to] * e.w;
         a2 += subw[e.to] * e.w;
         subsize[v] += subsize[e.to];
         subw[v] += subw[e.to];
      }
   }
   
   
   public static class Ret{
      long sumd;
      long wd;
      
      public Ret(long a, long b){
         sumd = a;
         wd = b;
      }
   }
   
   public static class Edge{
      int to;
      long w;
      public Edge(int a, long b){
         to = a;
         w = b;
      }
   }
   
      
}
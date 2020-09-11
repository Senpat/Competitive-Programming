//make sure to make new file!
import java.io.*;
import java.util.*;
//try to fix big on F932b, uses different method of finding biggest nodes for merging
public class F932b{
   
   public static long[] a;
   public static long[] b;
   
   public static ArrayList<ArrayList<Integer>> adj;
   public static int[] subsum;
   public static int[] parents;
   public static long[] answer;
   public static boolean[] heavy;
   public static int[] heavychild;
   public static int[] heavysize;                  //stores size of top of heavy chain
   
   public static LCT[] lcts;
   
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      a = new long[n+1];
      b = new long[n+1];
      
      StringTokenizer st1 = new StringTokenizer(f.readLine());
      StringTokenizer st2 = new StringTokenizer(f.readLine());
      for(int k = 1; k <= n; k++){
         a[k] = Long.parseLong(st1.nextToken());
         b[k] = Long.parseLong(st2.nextToken());
      }
      
      adj = new ArrayList<ArrayList<Integer>>(n+1);
      
      for(int k = 0; k <= n; k++) adj.add(new ArrayList<Integer>());
      
      for(int k = 0; k < n-1; k++){
         st1 = new StringTokenizer(f.readLine());
         
         int v1 = Integer.parseInt(st1.nextToken());
         int v2 = Integer.parseInt(st1.nextToken());
         
         adj.get(v1).add(v2);
         adj.get(v2).add(v1);
      }
      //calculate subtree sizes
      subsum = new int[n+1];
      parents = new int[n+1];
      
      dfssubsum(1,-1);
      
      heavy = new boolean[n+1];
      heavy[1] = true;
      
      heavychild = new int[n+1];
      
      for(int k = 1; k <= n; k++){
         //set heavy of max child to true
         if(k != 1 && adj.get(k).size() == 1) 
            continue;
         int maxchild = -1;
         int max = -1;
         
         for(int nei : adj.get(k)){
            if(nei == parents[k]) 
               continue;
            if(max < subsum[nei]){
               maxchild = nei;
               max = subsum[nei];
            }
         }
         
         heavy[maxchild] = true;
         heavychild[k] = maxchild;
      }
      
      heavysize = new int[n+1];
      dfssize(1,-1);
      
      answer = new long[n+1];
      
      lcts = new LCT[n+1];
      dfs(1,-1);
      
      StringJoiner sj = new StringJoiner(" ");
      for(int k = 1; k <= n; k++){
         sj.add("" + answer[k]);
      }
      out.println(sj.toString());
      
      
      
      out.close();
   }
   
   public static void dfs(int v, int p){
      //check if leaf
      if(v != 1 && adj.get(v).size() == 1){
         answer[v] = 0L;
         lcts[v] = new LCT(heavysize[v]);
      } else {
         
         int maxchild = -1;
         int maxsize = -1;
         for(int nei : adj.get(v)){
            if(nei == p) 
               continue;
            dfs(nei,v);
            if(lcts[nei].added.size() > maxsize){
               maxchild = nei;
               maxsize = lcts[nei].added.size();
            }
         }
         
         lcts[v] = lcts[maxchild];
         for(int nei : adj.get(v)){
            if(nei == p || nei == maxchild) 
               continue;
            //merge
            for(Line l : lcts[nei].added){
               lcts[v].addline(l);
            }
         }
         
         answer[v] = lcts[v].get(a[v],1,0,lcts[v].MAXN);
      }
      
      //add line
      lcts[v].addline(new Line(b[v],answer[v]));
   }
   
   public static void dfssize(int v, int p){
      if(v == 1 || !heavy[v] || !heavy[p]) heavysize[v] = subsum[v];
      else heavysize[v] = heavysize[p];
      
      for(int nei : adj.get(v)){
         if(nei == p) continue;
         dfssize(nei,v);
      }
   }
   
   //subsum
   public static void dfssubsum(int v, int p){
      subsum[v] = 1;
      parents[v] = p;
      
      for(int nei : adj.get(v)){
         if(nei == p) 
            continue;
         dfssubsum(nei,v);
         subsum[v] += subsum[nei];
      }
   }
   
      //translated from cp-algorithms
   //Li-Chao Tree
   
   public static class LCT{
      
      public Line[] lines;
      public int MAXN;
      public ArrayList<Line> added;
      
      public LCT(int maxsize){
         MAXN = maxsize+2;
         lines = new Line[4*MAXN];
         Arrays.fill(lines,new Line(0L,10000000000000000L));
         
         added = new ArrayList<Line>();
      }
      
      
      public void addline(Line line){
         added.add(line);
         addline(line,1,0,MAXN);
      }
   //to call: line is the line, v = 1, l = 0, r = MAXN
      public void addline(Line line, int v, int l, int r){
         int m = (l+r)/2;
         boolean lef = line.evald(l) < lines[v].evald(l);
         boolean mid = line.evald(m) < lines[v].evald(m);
      
         if(mid){
            Line temp = line;
            line = lines[v];
            lines[v] = temp;
         }
      
         if(r-l==1) 
            return;
         else if(lef != mid){
            addline(line, 2*v, l, m);
         } else {
            addline(line,2*v+1, m, r);
         }
      }
   
   //to call: x is the x coordinate, v = 1, l = 0, r = MAXN
      public long get(long x, int v, int l, int r){
         int m = (l+r)/2;
         if(r-l==1){
            return lines[v].eval(x);
         } else if(x < m){
            return Math.min(lines[v].eval(x),get(x,2*v, l, m));
         } else {
            return Math.min(lines[v].eval(x),get(x,2*v+1, m ,r));
         }
      }
   
   
   }
   
   public static class Line{
      //y=mx+c
      long m;
      long c;
      public Line(long a, long b){
         m = a;
         c = b;
      }
      public long eval(long x){
         return m*x+c;
      }
      public double evald(long x){
         return (double)m * (double)x + (double)c;
      }
      //returns x value of intersection (doesn't handle parallel)
      public double intersectionX(Line l){
         return ((double)(l.c-c))/((double)(m-l.m));
      }
      public String toString(){
         return m + " " + c;
      }
         
   }
   
      
}
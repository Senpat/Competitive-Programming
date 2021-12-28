//make sure to make new file!
import java.io.*;
import java.util.*;
//solves D1
public class D738{
   
   public static ArrayList<ArrayList<Integer>> adj1;
   public static ArrayList<ArrayList<Integer>> adj2;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m1 = Integer.parseInt(st.nextToken());
      int m2 = Integer.parseInt(st.nextToken());
      
      adj1 = new ArrayList<ArrayList<Integer>>(n+1);
      adj2 = new ArrayList<ArrayList<Integer>>(n+1);
      
      for(int k = 0; k <= n; k++){
         adj1.add(new ArrayList<Integer>());
         adj2.add(new ArrayList<Integer>());
      }
      
      Dsu dsu1 = new Dsu(n);
      Dsu dsu2 = new Dsu(n);
      
      for(int k = 0; k < m1; k++){
         st = new StringTokenizer(f.readLine());
      
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
         
         adj1.get(a).add(b);
         adj1.get(b).add(a);
         
         dsu1.union(a,b);
      }
      
      for(int k = 0; k < m2; k++){
         st = new StringTokenizer(f.readLine());
      
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
         
         adj2.get(a).add(b);
         adj2.get(b).add(a);
         
         dsu2.union(a,b);
      }
      
      ArrayList<Pair> answer = new ArrayList<Pair>();
      
      for(int k = 1; k <= n; k++){
         for(int j = 1; j <= n; j++){
            if(k == j) continue;
            if(dsu1.find(k) != dsu1.find(j) && dsu2.find(k) != dsu2.find(j)){
               dsu1.union(k,j);
               dsu2.union(k,j);
               
               answer.add(new Pair(k,j));
            }
         }
      }
      
      StringJoiner sj = new StringJoiner("\n");
      sj.add("" + answer.size());
      for(Pair p : answer){
         sj.add(p.a + " " + p.b);
      }
      
      out.println(sj.toString());
      
      
      
      
      
      
      
      out.close();
   }
   
   public static class Dsu{
      int[] parent;
      int[] sizes;
      
      public Dsu(int n){
         parent = new int[n+1];
         sizes = new int[n+1];
         
         for(int k = 1; k <= n; k++){
            parent[k] = k;
         }
         Arrays.fill(sizes,1);
      }
      
      public void union(int u, int v){
         int findv = find(v);
         int findu = find(u);
         sizes[findv] += sizes[findu];
         parent[findu] = findv;
      }
   
      public int find(int v){
         if(parent[v] == v) 
            return v;
         else{
            parent[v] = find(parent[v]);
            return parent[v];
         //return find(parent[v]);
         }
      }
   }
   
   public static class Pair{
      int a;
      int b;
      public Pair(int c, int d){
         a = c;
         b = d;
      }
   }
   
}
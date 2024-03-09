//make sure to make new file!
import java.io.*;
import java.util.*;
//bug: when returning Op from segtree query, make sure to copy before modifying
public class P2{

   public static long MOD = 1000000007L;
   public static long[] pow10;

   public static int[] parent;
   
   //store values of kruskal's tree
   public static Node[] nodes;
   
   public static int[] start;
   public static int[] end;
   public static int time;
   
   public static Segtree segtree;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int N = 200005;
      pow10 = new long[N];
      pow10[0] = 1L;
      for(int k = 1; k < N; k++){
         pow10[k] = (pow10[k-1] * 10L + MOD)%MOD;
      }
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      parent = new int[n+1];
      int[] root = new int[n+1];            //current top of kruskal's tree
      for(int k = 1; k <= n; k++){
         parent[k] = k;
         root[k] = k;
      }
      
      /*
      kadj = new ArrayList<>(2*n);
      for(int k = 0; k < 2*n; k++){
         kadj.add(new ArrayList<Integer>());
      }*/
      
      nodes = new Node[2*n];
      for(int k = 1; k <= n; k++){
         nodes[k] = new Node((long)k);
      }
      
      int nodei = n+1;
      
      for(int k = 1; k <= m; k++){
         st = new StringTokenizer(f.readLine());
         
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
         
         if(find(a) != find(b)){
            //add edge, update kruskal's tree
            nodes[nodei] = new Node((long)k,root[find(a)],a,root[find(b)],b);
                        
            union(a,b);
            
            root[find(a)] = nodei;
            nodei++;
         }
      }
      
      time = 0;
      start = new int[2*n];
      end = new int[2*n];
      dfs1(2*n-1);
      
      segtree = new Segtree(time);
      dfs2(2*n-1);
      
      long[] answer = new long[n+1];
      for(int k = 1; k <= n; k++){
         answer[k] = segtree.query(0,0,time-1,start[k]).val;
      }
      
      StringJoiner sj = new StringJoiner("\n");
      for(int k = 1; k <= n; k++){
         sj.add("" + answer[k]);
      }
      
      out.println(sj.toString());
      
      
      out.close();
   }
   
   public static void dfs2(int v){
      //System.out.println(v + " " + nodes[v].val);
      if(nodes[v].leftnode == -1){
         //leaf
         //segtree.update(0,0,time-1,start[v],end[v],new Op(1,nodes[v].val));
      } else {
         dfs2(nodes[v].leftnode);
         dfs2(nodes[v].rightnode);
         
         Op lop = segtree.query(0,0,time-1,start[nodes[v].lefttop]);
         Op rop = segtree.query(0,0,time-1,start[nodes[v].righttop]);
         
         //System.out.println(v + " left: " + lop.val + " right: " + rop.val);
         
         //add nodes[v].val to lop and rop
         long newlval = (lop.val + nodes[v].val * pow10[lop.d] + MOD)%MOD;
         Op newlop = new Op(lop.d+1,newlval);
         
         long newrval = (rop.val + nodes[v].val * pow10[rop.d] + MOD)%MOD;
         Op newrop = new Op(rop.d+1,newrval);
         
         //System.out.println(v + " after left: " + lop.val + " after right: " + rop.val);
         
         //add to other side
         segtree.update(0,0,time-1,start[nodes[v].leftnode],end[nodes[v].leftnode],newrop);
         segtree.update(0,0,time-1,start[nodes[v].rightnode],end[nodes[v].rightnode],newlop);
      }
   }
   
   public static void dfs1(int v){
      start[v] = time;
      
      if(nodes[v].leftnode == -1){
         //leaf
         end[v] = time;
         time++;
      } else {
         dfs1(nodes[v].leftnode);
         dfs1(nodes[v].rightnode);
         
         end[v] = time-1;
      }
   }
   
   
   //lazy segtree to "insert" vals, by shifting by a power of 10 then adding
   //query an index
   public static class Segtree{
      Op[] ops;
      //long[] a;
      public Segtree(int size){
         ops = new Op[4*size];
         for(int k = 0; k < ops.length; k++){
            ops[k] = new Op();
         }
         //a = new long[4*size];
      }
      
      private Op combine(Op op1, Op op2){
         long newval = (op1.val * pow10[op2.d] + op2.val + MOD)%MOD;
         return new Op(op1.d + op2.d, newval);
      }
      
      private void propagate(int v){
         ops[2*v+1] = combine(ops[2*v+1],ops[v]);
         ops[2*v+2] = combine(ops[2*v+2],ops[v]);
         ops[v] = new Op();
      }
      
      public void update(int v, int l, int r, int ql, int qr, Op op){
         if(l >= ql && r <= qr){
            ops[v] = combine(ops[v],op);
         } else if(r < ql || l > qr){
            return;
         } else {
            int mid = (l+r)/2;
            
            propagate(v);
            
            update(2*v+1,l,mid,ql,qr,op);
            update(2*v+2,mid+1,r,ql,qr,op);
         }
      }
      
      public Op query(int v, int l, int r, int i){
         if(l == r){
            return ops[v];
         } else {
            int mid = (l+r)/2;
            
            propagate(v);
            
            if(i <= mid){
               return query(2*v+1,l,mid,i);
            } else {
               return query(2*v+2,mid+1,r,i);
            }
            
         }
      }
   }
   
   public static class Op{
      int d;                  //pow10 to shift
      long val;
      public Op(){
         this(0,0);
      }
      public Op(int a, long b){
         d = a;
         val = b;
      }
      
      public String toString(){
         return d + " " + val;
      }
   }
   
   
   public static class Node{
      long val;
      
      int leftnode;
      int lefttop;
      
      int rightnode;
      int righttop;
      
      public Node(long a){
         this(a,-1,-1,-1,-1);
      }
      public Node(long a, int b, int c, int d, int e){
         val = a;
         
         leftnode = b;
         lefttop = c;
         
         rightnode = d;
         righttop = e;
      }
   }
   
   /*
   public static class Edge{
      int a;
      int b;
      int i;
      
      public Edge(int x, int y, int z){
         a = x;
         b = y;
         i = z;
      }
   }*/
   
   public static void union(int u, int v){
      int findv = find(v);
      int findu = find(u);
      parent[findu] = findv;
   }
   
   public static int find(int v){
      if(parent[v] == v) return v;
      else{
         parent[v] = find(parent[v]);
         return parent[v];
         //return find(parent[v]);
      }
   }

      
}
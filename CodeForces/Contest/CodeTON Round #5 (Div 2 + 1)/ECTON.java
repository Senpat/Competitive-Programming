//make sure to make new file!
import java.io.*;
import java.util.*;
//upsolve, tutorial
public class ECTON{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      int A = Integer.parseInt(st.nextToken());
      
      int csum = 0;
      
      ArrayList<ArrayList<Range>> end = new ArrayList<ArrayList<Range>>(n+1);    //end.get(x) stores info about all ranges that end at x
      for(int k = 0; k <= m; k++){
         end.add(new ArrayList<Range>());
      }
      
      for(int k = 0; k < n; k++){
         st = new StringTokenizer(f.readLine());
         int x = Integer.parseInt(st.nextToken());
         int y = Integer.parseInt(st.nextToken());
         int c = Integer.parseInt(st.nextToken());
         
         int l = x;
         int r = x + (m-(x+y));
         end.get(r).add(new Range(l,c));
         
         csum += c;
      }
      
      Segtree segtree = new Segtree(m+1);
      
      int[] dp = new int[m+1];
      dp[0] = 0;
      for(int k = 1; k <= m; k++){
         //add intervals
         for(Range range : end.get(k)){
            segtree.update(0,0,m,0,range.start,range.c);
         }
         
         //subtract a
         if(k > 0){
            segtree.update(0,0,m,0,k-1,-1*A);
         }
         
         dp[k] = segtree.max(0,0,m,0,k-1);
         if(k > 0) dp[k] = Math.max(dp[k],dp[k-1]);      //do nothing
         
         //update segtree
         segtree.update(0,0,m,k,k,dp[k]);
         
         //out.println(dp[k]);         
      }
      
      int answer = csum-dp[m];
      out.println(answer);
      
      
      
      
      
      
      out.close();
   }
   
   public static class Range{
      int start;
      int c;
      public Range(int a, int b){
         start = a;
         c = b;
      }
   }
   
   
      
}
//range += update, range max query
class Segtree{
   private int[] op;
   private int[] a;
   
   public Segtree(int size){
      op = new int[4*size];
      a = new int[4*size];
   }
   
   //build: set all to 0
   
   private void propagate(int v){
      op[2*v+1] += op[v];
      a[2*v+1] += op[v];
      op[2*v+2] += op[v];
      a[2*v+2] += op[v];
      
      op[v] = 0;
   }
   
   public void update(int v, int l, int r, int ql, int qr, int d){
      if(l >= ql && r <= qr){
         op[v] += d;
         a[v] += d;
      } else if(r < ql || l > qr){
         return;
      } else {
         int mid = (l+r)/2;
         
         propagate(v);
         
         update(2*v+1,l,mid,ql,qr,d);
         update(2*v+2,mid+1,r,ql,qr,d);
         
         a[v] = Math.max(a[2*v+1],a[2*v+2]);
      }
   }
   
   public int max(int v, int l, int r, int ql, int qr){
      if(l >= ql && r <= qr){
         return a[v];
      } else if(r < ql || l > qr){
         return Integer.MIN_VALUE;
      } else {
         int mid = (l+r)/2;
         
         propagate(v);
         
         return Math.max(max(2*v+1,l,mid,ql,qr),max(2*v+2,mid+1,r,ql,qr));
      }
   }
}  
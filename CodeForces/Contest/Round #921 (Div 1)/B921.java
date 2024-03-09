//make sure to make new file!
import java.io.*;
import java.util.*;
//doesn't solve l,r queries
public class B921{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      int q = Integer.parseInt(st.nextToken());
      
      long[] harbor = new long[n];
      Arrays.fill(harbor,-1L);
      TreeSet<Integer> tset = new TreeSet<Integer>();
      st = new StringTokenizer(f.readLine());
      StringTokenizer stv = new StringTokenizer(f.readLine());
      for(int k = 0; k < m; k++){
         int i = Integer.parseInt(st.nextToken())-1;
         int v = Long.parseLong(stv.nextToken());
         harbor[i] = v;
         tset.add(i);
      }
      
      Segtree segtree = new Segtree(n);
      
      long answer = 0L;
      for(int k = 0; k < n; k++){
         if(harbor[k] == -1L) continue;       //0 anyway
         
         int l = tset.lower(k);
         int r = tset.upper(k);
         
         segtree.update(0,0,n-1,k,k,r-k);
         answer += harbor[l] * (long)(r-k);
      }
      
      for(int t = 0; t < q; t++){
         st = new StringTokenizer(f.readLine());
         
         int qt = Integer.parseInt(st.nextToken());
         
         if(qt == 1){
            int x = 
         } else if(qt == 2){
            out.println(answer);
         }
      }
      
      
      
      
      
      
      
      out.close();
   }
   
   
   
   //range update, range sum query
   //to call, v = 0, l = 0, r = size-1
   //l,r and ql,qr are inclusive
   public static class Segtree{
      
      private long[] ops;
      private long[] a;
      
      public Segtree(int size){
         ops = new long[4*size];
         a = new long[4*size];
      }
      
      private void propagate(int v, long len1, long len2){
         ops[2*v+1] += ops[v];
         a[2*v+1] += len1 * ops[v];
         ops[2*v+2] += ops[v];
         a[2*v+2] += len2 * ops[v];
         ops[v] = 0L;
      }
      
      public void update(int v, int l, int r, int ql, int qr, long x){
         if(l >= ql && r <= qr){
            ops[v] = +x;
            long len = (long)(r-l+1);
            a[v] += len * x;
         } else if(r < ql || l > qr){
            return;
         } else {
            int mid = (l+r)/2;
            
            propagate(v,(long)(mid-l+1),(long)(r-(mid+1)+1));
            
            update(2*v+1,l,mid,ql,qr,x);
            update(2*v+2,mid+1,r,ql,qr,x);
            
            a[v] = a[2*v+1] + a[2*v+2];
         }
      }
      
      public long sum(int v, int l, int r, int ql, int qr){
         if(l >= ql && r <= qr){
            return a[v];
         } else if(r < ql || l > qr){
            return 0L;
         } else {
            int mid = (l+r)/2;
            propagate(v,(long)(mid-l+1),(long)(r-(mid+1)+1));

            return sum(2*v+1,l,mid,ql,qr) + sum(2*v+2,mid+1,r,ql,qr);
            
         }
      }
      
      
   }
   

      
}
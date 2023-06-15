//make sure to make new file!
import java.io.*;
import java.util.*;

public class P4B{

   public static long MOD;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      MOD = Long.parseLong(st.nextToken());
      int n = Integer.parseInt(st.nextToken());
      int q = Integer.parseInt(st.nextToken());
      
      long[][][] matrices = new long[n][2][2];
      for(int k = 0; k < n; k++){
         st = new StringTokenizer(f.readLine());
         matrices[k][0][0] = (Long.parseLong(st.nextToken()) + MOD)%MOD;
         matrices[k][0][1] = (Long.parseLong(st.nextToken()) + MOD)%MOD;
         st = new StringTokenizer(f.readLine());
         matrices[k][1][0] = (Long.parseLong(st.nextToken()) + MOD)%MOD;
         matrices[k][1][1] = (Long.parseLong(st.nextToken()) + MOD)%MOD;
         
         f.readLine();
      }
      
      Segtree segtree = new Segtree(n);
      segtree.build(0,0,n-1,matrices);
      
      for(int t = 0; t < q; t++){
         st = new StringTokenizer(f.readLine());
         
         int l = Integer.parseInt(st.nextToken())-1;
         int r = Integer.parseInt(st.nextToken())-1;
         
         long[][] ans = segtree.prod(0,0,n-1,l,r);
         out.println(ans[0][0] + " " + ans[0][1]);
         out.println(ans[1][0] + " " + ans[1][1]);
         out.println();
      }
      
      
      
      
      
      
      out.close();
   }
   
   
   public static class Segtree{
   
      private long[][][] a;
      private long[][] id;
      
      public Segtree(int size){
         a = new long[4*size][2][2];
         
         id = new long[][]{{1,0},{0,1}};
      }
      
      public long[][] mul(long[][] m1, long[][] m2){
         long[][] ret = new long[2][2];
         
         ret[0][0] = (m1[0][0]*m2[0][0] + m1[0][1]*m2[1][0] + MOD)%MOD;
         ret[0][1] = (m1[0][0]*m2[0][1] + m1[0][1]*m2[1][1] + MOD)%MOD;
         ret[1][0] = (m1[1][0]*m2[0][0] + m1[1][1]*m2[1][0] + MOD)%MOD;
         ret[1][1] = (m1[1][0]*m2[0][1] + m1[1][1]*m2[1][1] + MOD)%MOD;
         
         return ret;
      }
      
      public void build(int v, int l, int r, long[][][] matrices){
         if(l == r){
            a[v] = matrices[l];
         } else {
            int mid = (l+r)/2;
            
            build(2*v+1,l,mid,matrices);
            build(2*v+2,mid+1,r,matrices);
            
            a[v] = mul(a[2*v+1],a[2*v+2]);
         }
      }
      
      public long[][] prod(int v, int l, int r, int ql, int qr){
         if(l >= ql && r <= qr){
            return a[v];
         } else if(r < ql || l > qr){
            return id;
         } else {
            int mid = (l+r)/2;
            
            return mul(prod(2*v+1,l,mid,ql,qr),prod(2*v+2,mid+1,r,ql,qr));
         }
      }
   
   }
   
}
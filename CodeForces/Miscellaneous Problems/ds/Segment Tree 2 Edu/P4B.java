//make sure to make new file!
import java.io.*;
import java.util.*;

public class P4B{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int q = Integer.parseInt(st.nextToken());
      
      Segtree segtree = new Segtree(n);
      
      for(int t = 0; t < q; t++){
         st = new StringTokenizer(f.readLine());
         int type = Integer.parseInt(st.nextToken());
         if(type == 1){
            int l = Integer.parseInt(st.nextToken())-1;
            int r = Integer.parseInt(st.nextToken())-1;
            long a = Long.parseLong(st.nextToken());
            long d = Long.parseLong(st.nextToken());
            
            segtree.update(0,0,n-1,l,r,new Op(a,d));
         } else {
            int i = Integer.parseInt(st.nextToken())-1;
            
            out.println(segtree.query(0,0,n-1,i));
            out.flush();
         }
      }
      
      
      
      
      out.close();
   }
   
   public static class Segtree{
   
      private Op[] ops;
      
      public Segtree(int size){
         ops = new Op[4*size];
         for(int k = 0; k < ops.length; k++) ops[k] = new Op();
      }
      
      public Op shiftOp(Op op, long shift){
         return new Op(op.a + op.d * shift,op.d);
      }
      
      public void propagate(int v, long shift){
         ops[2*v+1].combine(ops[v]);
         ops[2*v+2].combine(shiftOp(ops[v],shift));
         ops[v] = new Op();
      }
      
      public void update(int v, int l, int r, int ql, int qr, Op op){
         if(l >= ql && r <= qr){
            ops[v].combine(op);
         } else if(r < ql || l > qr){
            return;
         } else {
            int mid = (l+r)/2;
            
            long shift = (long)(mid-l+1);
            propagate(v,shift);
            
            update(2*v+1,l,mid,ql,qr,op);
            
            shift = (long)Math.max(0L,mid-Math.max(ql,l)+1);
            Op rightOp = shiftOp(op,shift);
            update(2*v+2,mid+1,r,ql,qr,rightOp);
         }
      }
      
      public long query(int v, int l, int r, int i){
         if(l == r){
            return ops[v].a;
         } else {
            int mid = (l+r)/2;
            
            long shift = (long)(mid-l+1);
            propagate(v,shift);
            
            if(i <= mid){
               return query(2*v+1,l,mid,i);
            } else {
               return query(2*v+2,mid+1,r,i);
            }
         }
      }
      
   }
   
   public static class Op{
      long a;
      long d;
      
      //default - a=0 and d=0 is same as doing nothing
      public Op(){
         this(0L,0L);
      }
      
      public Op(long b, long c){
         a = b;
         d = c;
      }
      
      public void combine(Op op){
         a += op.a;
         d += op.d;
      }
   }
   
      
}
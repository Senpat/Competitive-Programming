//make sure to make new file!
import java.io.*;
import java.util.*;

public class P4A{
   
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
         int l = Integer.parseInt(st.nextToken());
         int r = Integer.parseInt(st.nextToken())-1;
         
         if(type == 1 || type == 2){
            long x = Long.parseLong(st.nextToken());
            
            segtree.update(0,0,n-1,l,r,new Op(type,x));
         } else {
            out.println(segtree.sum(0,0,n-1,l,r));
         }
      }
         
      
      
      
      
      
      
      
      
      out.close();
   }
   
   public static class Segtree{
      
      private Op[] ops;
      private long[] a;       //stores sum on segment
      
      public Segtree(int size){
         ops = new Op[4*size];
         a = new long[4*size];  
      
         Arrays.fill(ops,new Op());
      }
      
      public Op combineOp(Op prev, Op op){
         if(op.type == 1){
            return op;
         } else {
            return new Op(prev.type,prev.value + op.value);
         }
      }
      
      public void applyOp(int v, Op op, long len){
         
         if(op.type == 1){
            a[v] = len*op.value;
         } else {
            a[v] += len*op.value;
         }
         
         ops[v] = combineOp(ops[v],op);
      }
      
      public void propagate(int v, int l, int r){
         int mid = (l+r)/2;
         long lenl = (long)(mid-l+1);
         long lenr = (long)(r-(mid+1)+1);
         
         applyOp(2*v+1,ops[v],lenl);
         applyOp(2*v+2,ops[v],lenr);
         ops[v] = new Op();
      }
      
      public void update(int v, int l, int r, int ql, int qr, Op op){
         if(l >= ql && r <= qr){
            long len = (long)(r-l+1);
            applyOp(v,op,len);
         } else if(r < ql || l > qr){
            return;
         } else {
            int mid = (l+r)/2;
            
            propagate(v,l,r);
            
            update(2*v+1,l,mid,ql,qr,op);
            update(2*v+2,mid+1,r,ql,qr,op);
            
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
            
            propagate(v,l,r);
            
            return sum(2*v+1,l,mid,ql,qr) + sum(2*v+2,mid+1,r,ql,qr);
         }
      }
      
   }
   
   public static class Op{
      int type;         //1 for assign, 2 for add
      long value;
      
      //default - add 0 is the same as doing nothing
      public Op(){
         this(2,0L);
      }
      
      public Op(int a, long b){
         type = a;
         value = b;
      }
      
      
   }
      
}
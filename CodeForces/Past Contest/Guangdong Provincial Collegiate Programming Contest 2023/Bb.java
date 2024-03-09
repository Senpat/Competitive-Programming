//make sure to make new file!
import java.io.*;
import java.util.*;
//bug: bound in segtree min
//danny
public class Bb{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         int n = Integer.parseInt(f.readLine());
      
         StringTokenizer st = new StringTokenizer(f.readLine());
         long[] array = new long[n];
         for(int k = 0; k < n; k++){
            array[k] = Long.parseLong(st.nextToken());
         }
         
         int reqn = Integer.parseInt(f.readLine());
         Req[] reqarray = new Req[reqn];
         for(int k = 0; k < reqn; k++){
            st = new StringTokenizer(f.readLine());
            
            int l = Integer.parseInt(st.nextToken())-1;
            int r = Integer.parseInt(st.nextToken())-1;
            
            reqarray[k] = new Req(l,r);
         }
         
         Arrays.sort(reqarray);
         
         ArrayList<Req> reqs = new ArrayList<Req>();
         
         int maxl = -1;
         for(int k = 0; k < reqn; k++){
            if(reqarray[k].l > maxl){
               reqs.add(reqarray[k]);
               maxl = reqarray[k].l;
               //out.println(reqarray[k].l + " " + reqarray[k].r);
            }
         }
         
         int rn = reqs.size();
         
         int[] minreq = new int[n];
         Arrays.fill(minreq,-1);
         
         for(int k = 0; k < rn; k++){
            int start = reqs.get(k).l;
            if(k > 0) start = Math.max(start,reqs.get(k-1).r+1);
            
            for(int j = start; j <= reqs.get(k).r; j++){
               minreq[j] = k;
            }
         }
         
         /*
         for(int k = 0; k < n; k++){
            out.print(minreq[k] + " ");
         }
         out.println();
         */
         
         Segtree segtree = new Segtree(n);
         
         //calculate the min # to get kth element and all ranges that come before it
         for(int k = 0; k < n; k++){
            if(minreq[k] == -1) continue;
            
            if(minreq[k] == 0){
               segtree.update(0,0,n-1,k,array[k]);
            } else {
               long pre = segtree.min(0,0,n-1,reqs.get(minreq[k]-1).l,k-1);
               
               //out.println(pre + " " + array[k]);
               segtree.update(0,0,n-1,k,pre + array[k]);
            }
         }
         
         //out.println(reqs.get(rn-1).l + " " +  reqs.get(rn-1).r);
         long answer = segtree.min(0,0,n-1,reqs.get(rn-1).l,reqs.get(rn-1).r);
         out.println(answer);
         
         
      }
      
      
      
      
      out.close();
   }
   
   public static class Req implements Comparable<Req>{
      int l;
      int r;
      public Req(int a, int b){
         l = a;
         r = b;
      }
      
      //sort by increasing r
      //tiebreak by decreasing l
      public int compareTo(Req req){
         if(r == req.r) return req.l-l;
         return r-req.r;
      }
   }
   
   //point update, range min query
   public static class Segtree{
      long[] a;
      public Segtree(int size){
         a = new long[4*size];
         Arrays.fill(a,Long.MAX_VALUE);
      }
      
      public void update(int v, int l, int r, int i, long x){
         if(l == r){
            a[v] = x;
         } else {
            int mid = (l+r)/2;
            if(i <= mid) update(2*v+1,l,mid,i,x);
            else update(2*v+2,mid+1,r,i,x);
            
            a[v] = Math.min(a[2*v+1],a[2*v+2]);
         }
      }
      
      public long min(int v, int l, int r, int ql, int qr){
         //System.out.println("min: " + ql + " " + qr);
         if(l >= ql && r <= qr){
            return a[v];
         } else if(qr < l || ql > r){
            return Long.MAX_VALUE;
         } else {
            int mid = (l+r)/2;
            
            return Math.min(min(2*v+1,l,mid,ql,qr),min(2*v+2,mid+1,r,ql,qr));
         }
      }
   }
            
        
}
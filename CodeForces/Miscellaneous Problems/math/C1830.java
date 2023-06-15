//Hyperregular Bracket Strings
import java.io.*;
import java.util.*;

public class C1830{

   public static long MOD = 998244353L;
   public static long[] fac;
   public static long[] ifac;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int N = 300010;
      int N2 = 150005;
      
      fac = new long[N];
      fac[0] = 1L;
      for(int k = 1; k < N; k++){
         fac[k] = (fac[k-1] * (long)k + MOD)%MOD;
      }
      
      ifac = new long[N];
      ifac[N-1] = modInverse(fac[N-1],MOD);
      for(int k = N-2; k >= 0; k--){
         ifac[k] = (ifac[k+1] * (long)(k+1) + MOD)%MOD;
      }
      
      //formula: c_n = 1/(n+1) * (2n choose n) = (2n choose n) - (2n choose n+1)
      long[] catalan = new long[N2];
      catalan[0] = 1L;
      for(int k = 1; k < N2; k++){
         catalan[k] = ((choose(2*k,k) - choose(2*k,k+1))%MOD + MOD)%MOD;
      }
      
      
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int x = Integer.parseInt(st.nextToken());
         int n = Integer.parseInt(st.nextToken());
         
         //one-indexed
         ArrayList<Sub> subs = new ArrayList<Sub>();
         ArrayList<ArrayList<Integer>> start = new ArrayList<>(x+1);
         ArrayList<ArrayList<Integer>> end = new ArrayList<>(x+1);
         for(int k = 0; k <= x; k++){
            start.add(new ArrayList<Integer>());
            end.add(new ArrayList<Integer>());
         }
         
         HashSet<Long> seen = new HashSet<Long>();
         int sn = 0;
         for(int k = 0; k < n; k++){
            st = new StringTokenizer(f.readLine());
            
            int l = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            
            
            Sub sub = new Sub(l,r,sn);
            
            long hash = sub.hash();
            if(!seen.contains(hash)){
               start.get(l).add(sn);
               end.get(r).add(sn);
               subs.add(sub);
               seen.add(hash);
               sn++;
            }
         }
         
         Sub bigsub = new Sub(1,x,sn);
         if(!seen.contains(bigsub.hash())){
            subs.add(bigsub);
            sn++;
            //no need to update seen or start/end
         }
         
         //barriers are numbered from 1 to x+1 inclusive
         HashSet<Integer> barrierhset = new HashSet<Integer>();
         boolean[] used = new boolean[sn];
         
         //stores all elements that are covered by a processed substring
         Segtree covered = new Segtree(x+1);
         
         FenwickTree ft = new FenwickTree(x);
         
         for(int k = 1; k <= x; k++){
            for(int i : end.get(k)){
               ft.update(subs.get(i).l,-1);
            }
            ft.update(k,start.get(k).size());
            for(int i : end.get(k)){
               int psum = ft.psum(k)-ft.psum(subs.get(i).l-1);
               if(psum > 0){
                  used[i] = true;
                  barrierhset.add(subs.get(i).l);
                  barrierhset.add(subs.get(i).r+1);
                  
                  covered.set1(0,0,x,subs.get(i).l,subs.get(i).r);
               }
               
            }
         }
         
         //do the same thing but backward
         ft = new FenwickTree(x);
         for(int k = x; k >= 1; k--){
            for(int i : start.get(k)){
               ft.update(subs.get(i).r,-1);
            }
            ft.update(k,end.get(k).size());
            for(int i : start.get(k)){
               int psum = ft.psum(subs.get(i).r)-ft.psum(k-1);
               if(psum > 0){
                  used[i] = true;
                  barrierhset.add(subs.get(i).l);
                  barrierhset.add(subs.get(i).r+1);
                  
                  covered.set1(0,0,x,subs.get(i).l,subs.get(i).r);
               }
            }
         }
         
         //stores endpoints of intersecting segments
         FenwickTree intersecting = new FenwickTree(x);
         for(int k = 0; k < sn; k++){
            if(used[k]){
               intersecting.update(subs.get(k).l,1);
               intersecting.update(subs.get(k).r,1);
            }
         }
         
         ArrayList<Integer> barriers = new ArrayList<Integer>(barrierhset);
         Collections.sort(barriers);
         boolean[] usebarrier = new boolean[Math.max(0,barriers.size()-1)];
         for(int k = 0; k < barriers.size()-1; k++){
            int len = barriers.get(k+1)-1 - barriers.get(k) +1;
            if(covered.sum(0,0,x,barriers.get(k),barriers.get(k+1)-1) == len){
               usebarrier[k] = true;
            }
         }
         
         //process non-intersecting substrings
         Collections.sort(subs);         //sort by size
         ArrayList<Integer> segments = new ArrayList<Integer>();
         
         //stores non-intersecting substrings within an intersecting substring
         Segtree within = new Segtree(x+1);
         FenwickTree nonintersecting = new FenwickTree(x);
         for(int k = 0; k < sn; k++){
            if(used[subs.get(k).i]) 
               continue;
            
            int coveredsum = covered.sum(0,0,x,subs.get(k).l,subs.get(k).r);
            if(coveredsum == subs.get(k).len){
               if(intersecting.psum(subs.get(k).r)-intersecting.psum(subs.get(k).l-1) > 0){
                  //covering a component of intersecting segments exactly
                  continue;
               }
               if(nonintersecting.psum(subs.get(k).r)-nonintersecting.psum(subs.get(k).l-1) > 0){
                  //covering a component of non-intersecting segments exactly
                  continue;
               }
               //within
               segments.add(subs.get(k).len-within.sum(0,0,x,subs.get(k).l,subs.get(k).r));
               within.set1(0,0,x,subs.get(k).l,subs.get(k).r);
            } else {
               segments.add(subs.get(k).len-coveredsum);
               covered.set1(0,0,x,subs.get(k).l,subs.get(k).r);
            }
            
            nonintersecting.update(subs.get(k).l,1);
            nonintersecting.update(subs.get(k).r,1);
         }
         
         //add intersecting segments to segments
         for(int k = 0; k < barriers.size()-1; k++){
            if(!usebarrier[k]) 
               continue;
            int len = barriers.get(k+1)-1 - barriers.get(k) +1;
            segments.add(len-within.sum(0,0,x,barriers.get(k),barriers.get(k+1)-1));
         }
         
         long answer = 1L;
         boolean fail = false;
         for(int i : segments){
            out.println(i);
            if(i % 2 == 1){
               fail = true;
               //break;
            }
            answer = (answer * catalan[i/2] + MOD)%MOD;
         }
         
            for(int i : barriers){
               out.println(i);
            }
         
         if(fail){
            out.println(0);
         } else {
            out.println(answer);
         }
      }
      
      
      
      
      out.close();
   }
   
   //range assign to 1 (from 0), range sum
   public static class Segtree{
      
      private boolean[] is1;        //stores if the entire segment is true
      private int[] a;              //stores sum on segment
      
      public Segtree(int size){
         is1 = new boolean[4*size];
         a = new int[4*size];
      }
      
      public void set1(int v, int l, int r, int ql, int qr){
         if(is1[v]){
            //already set entire segment to 1
            return;
         } else if(l >= ql && r <= qr){
            is1[v] = true;
            a[v] = r-l+1;
         } else if(r < ql || l > qr){
            return;
         } else {
            int mid = (l+r)/2;
            
            set1(2*v+1,l,mid,ql,qr);
            set1(2*v+2,mid+1,r,ql,qr);
            
            a[v] = a[2*v+1] + a[2*v+2];
            if(is1[2*v+1] && is1[2*v+2]) is1[v] = true;
         }
      }
      
      public int sum(int v, int l, int r, int ql, int qr){
         if(r < ql || l > qr){
            return 0;
         } else if(l >= ql && r <= qr){
            return a[v];
         } else if(is1[v]){
            int left = Math.max(l,ql);
            int right = Math.min(r,qr);
            return right-left+1;
         } else {
            int mid = (l+r)/2;
            
            return sum(2*v+1,l,mid,ql,qr) + sum(2*v+2,mid+1,r,ql,qr);
         }
      }
   }
   
   public static class FenwickTree{
      
      private int n;
      private int[] bits;
      
      public FenwickTree(int size){
         bits = new int[size+1];
         n = size;
      }
      
      public void update(int i, int x){
         for(; i <= n; i += i&-i){
            bits[i]+=x;
         }
      
      }
   
      public int psum(int i){
         int sum = 0;
         for(; i > 0; i -= i&-i){
            sum += bits[i];
         }
         return sum;
      
      }
   }
   
   public static class Sub implements Comparable<Sub>{
      //inclusive range of substring
      int l;
      int r;
      int i;
      int len;
      public Sub(int a, int b, int c){
         l = a;
         r = b;
         i = c;
         len = r-l+1;
      }
      
      public int compareTo(Sub s){
         if(len == s.len) 
            return l-s.l;
         return len-s.len;
      }
      
      public long hash(){
         return (long)l * 1000000L + (long)r;
      }
   }
   
   public static long choose(int n, int r){
      long prod = (fac[n] * ifac[r] + MOD)%MOD;
      return (ifac[n-r] * prod + MOD)%MOD;
   }
   
   //from geeksforgeeks
   public static long modInverse(long a, long m) 
   { 
      long m0 = m; 
      long y = 0;
      long x = 1; 
     
      if (m == 1) 
         return 0; 
     
      while (a > 1) 
      { 
           // q is quotient 
         long q = a / m; 
         long t = m; 
      
           // m is remainder now, process same as 
           // Euclid's algo 
         m = a % m;
         a = t; 
         t = y; 
      
           // Update y and x 
         y = x - q * y; 
         x = t; 
      } 
     
       // Make x positive 
      if (x < 0) 
         x += m0; 
      return x; 
   }  

   
      
}
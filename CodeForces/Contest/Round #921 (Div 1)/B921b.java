//make sure to make new file!
import java.io.*;
import java.util.*;
//bug: int n in main
public class B921b{
   
   
   public static long[] bits;
   public static int n;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      int q = Integer.parseInt(st.nextToken());
      
      long[] harbor = new long[n+1];
      Arrays.fill(harbor,-1L);
      TreeSet<Integer> tset = new TreeSet<Integer>();
      st = new StringTokenizer(f.readLine());
      StringTokenizer stv = new StringTokenizer(f.readLine());
      for(int k = 0; k < m; k++){
         int i = Integer.parseInt(st.nextToken());
         long v = Long.parseLong(stv.nextToken());
         harbor[i] = v;
         tset.add(i);
      }
      
      
      bits = new long[n+1];
      
      update(1,0L);
      for(int i : tset){
         if(i == 1) continue;
         
         int l = tset.lower(i);
         
         long diff = (long)(i-l);
         long sumd = diff * (diff-1L) / 2L * harbor[l];
         update(i,sumd);
      }
      
      for(int t = 0; t < q; t++){
         st = new StringTokenizer(f.readLine());
         
         int qt = Integer.parseInt(st.nextToken());
         
         if(qt == 1){
            int x = Integer.parseInt(st.nextToken());
            long v = Long.parseLong(st.nextToken());
            
            int l = tset.lower(x);
            int r = tset.higher(x);
            
            long tdiff = (long)(r-l);
            long tot = tdiff * (tdiff-1L) / 2L * harbor[l];
            
            long rdiff = (long)(r-x);
            long newv = rdiff * (rdiff-1L) / 2L * v;
            
            update(r,newv - tot);
            
            long ldiff = (long)(x-l);
            long curv = ldiff * (ldiff-1L) / 2L * harbor[l];
            update(x,curv);
            
            tset.add(x);
            harbor[x] = v;
            
         } else if(qt == 2){
            int l = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            
            if(l == 1) l++;
            if(r == n) r--;
            if(r < l){
               out.println(0);
               continue;
            }
            
            int ll = tset.lower(l);
            int lr = tset.ceiling(l);
            
            int rl = tset.lower(r);
            int rr = tset.ceiling(r);
            
            if(lr == rr){
               //l and r in the same segment
               long ldiff = (long)(lr-l);
               long sumd = ldiff * (ldiff+1L) / 2L * harbor[ll];
               
               long rdiff = (long)(lr-r-1);
               sumd -= rdiff * (rdiff+1L) / 2L * harbor[ll];
               
               out.println(sumd);
            } else {
               long ldiff = (long)(lr-l);
               long lsum = ldiff * (ldiff + 1L) / 2L * harbor[ll];
               
               long rdiffadd = (long)(rr-rl);
               long rsum = rdiffadd * (rdiffadd-1L) / 2L * harbor[rl];
               long rdiffsub = (long)(rr-r-1);
               rsum -= rdiffsub * (rdiffsub + 1L) /2L * harbor[rl];
               
               long msum = psum(rl) - psum(lr);
               if(lr == rl) msum = 0L;
               //out.println(rl + " " + lr + " " + psum(rl) + " " + psum(lr));
               out.println(lsum + rsum + msum);
               
            }
         }
      }
      /*
      for(int k = 1; k <= n; k++){
         out.print(psum(k) + " ");
      }
      out.println();
      */
      
      
      
      
      out.close();
   }
   
   
   
     
   public static void update(int i, long x){
      //System.out.println(i + " " + x);
      for(; i <= n; i += i&-i){
         //System.out.println(i);
         bits[i]+=x;
      }
   
   }
   
   public static long psum(int i){
      long sum = 0L;
      for(; i > 0; i -= i&-i){
         //System.out.println(i);
         sum += bits[i];
      }
      return sum;
   
   }

      

      
}
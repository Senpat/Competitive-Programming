//make sure to make new file!
import java.io.*;
import java.util.*;
import java.math.*;

public class F{

   public static BigInteger[] fac;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int N = 55;
      fac = new BigInteger[N];
      fac[0] = new BigInteger("1");
      for(int k = 1; k < N; k++){
         fac[k] = fac[k-1].multiply(new BigInteger("" + k));
      }
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      long x = Long.parseLong(st.nextToken());
      
      BigInteger bix = new BigInteger("" + x);
      
      BigInteger check = calc(n,m,n);
      if(check.compareTo(bix) < 0){
         out.println("-1");
         out.close();
         return;
      }
      
      ArrayList<Integer> answer = new ArrayList<Integer>();
      boolean[] added = new boolean[n+1];
      
      int needf = m;
      for(int k = 1; k <= n; k++){
         
         BigInteger tot = new BigInteger("0");
         //out.println(bix.longValue()); out.flush();
         //candidates
         for(int j = 1; j <= n; j++){
            if(added[j]) continue;
            //length of permutation
            int cn = n-k;
            //number of fixed points in permutation
            int cf = needf;
            if(k == j) cf--;
            
            //number of potential fixed points
            int cg = 0;
            for(int h = k+1; h <= n; h++){
               if(!added[h] && h != j) cg++;
            }
            
            //check possible
            if(cf < 0) continue;
            if(cf > cn) continue;
            
            BigInteger add = calc(cn,cf,cg);
            //out.println(k + " " + j + " (" + cn + " " + cf + " " + cg + ") " + add.longValue()); out.flush();
            //tot + add >= bix
            if(tot.add(add).compareTo(bix) >= 0){
               added[j] = true;
               answer.add(j);
               if(k == j) needf--;
               bix = bix.subtract(tot);
               break;
            } else {
               tot = tot.add(add);
            }
         }
         
      }
      
      StringJoiner sj = new StringJoiner(" ");
      for(int k = 0; k < n; k++){
         sj.add("" + answer.get(k));
      }
      out.println(sj.toString());
      
      
      
      
      
      
      out.close();
   }
   
   //length of permutation, # of fixed points, # of potential fixed points
   public static BigInteger calc(int n, int f, int g){
      BigInteger ret = new BigInteger("0");
      
      for(int k = f; k <= g; k++){
         BigInteger term = choose(g,k).multiply(fac[n-k]).multiply(choose(k,f));
         if((k-f)%2 == 0) ret = ret.add(term);
         else ret = ret.subtract(term);
      }
      
      return ret;
      
      
   }
   
   
   public static BigInteger choose(int n, int r){
      return fac[n].divide(fac[r]).divide(fac[n-r]);
   }
   
   
}
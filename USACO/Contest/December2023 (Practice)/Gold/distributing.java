//make sure to make new file!
import java.io.*;
import java.util.*;
//bug: when doing ternary search, account for equal m1 and m2 correctly
//could look like this:
/*
\._.  /
    \/
*/
//get rid of equal m1 and m2 affecting the problem by accounting for duplicates properly
public class distributing{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      ArrayList<Integer> alist = new ArrayList<Integer>();
      HashMap<Integer,Long> mul = new HashMap<Integer,Long>();
      for(int k = 0; k < n; k++){
         int i = Integer.parseInt(st.nextToken());
         if(mul.containsKey(i)){
            mul.put(i,mul.get(i)+1L);
         } else {
            mul.put(i,1L);
            alist.add(i);
         }
      }
      
      Collections.sort(alist);
      
      int an = alist.size();
      long[] suml = new long[an];
      long[] sumr = new long[an];
      
      suml[0] = 0L;
      long ln = mul.get(alist.get(0));
      for(int k = 1; k < an; k++){
         suml[k] = suml[k-1] + (long)(alist.get(k)-alist.get(k-1)) * ln;
         ln += mul.get(alist.get(k));
      }
      
      sumr[an-1] = 0L;
      long rn = mul.get(alist.get(an-1));
      for(int k = an-2; k >= 0; k--){
         sumr[k] = sumr[k+1] + (long)(alist.get(k+1)-alist.get(k)) * rn;
         rn += mul.get(alist.get(k));
      }
      
      int q = Integer.parseInt(f.readLine());
      
      for(int t = 0; t < q; t++){
         st = new StringTokenizer(f.readLine());
         long a = Long.parseLong(st.nextToken());
         long b = Long.parseLong(st.nextToken());
         
         int l = 0;
         int r = an-1;
         long ans = Long.MAX_VALUE;
         
         //roughly 30 iterations
         while(l <= r){
            int m1 = l + (r-l)/3;
            int m2 = r - (r-l)/3;
            
            long a1 = a*suml[m1] + b*sumr[m1];
            long a2 = a*suml[m2] + b*sumr[m2];
            
            if(a1 <= a2){
               ans = Math.min(ans,a1);
               r = m2-1;
            } else {
               ans = Math.min(ans,a2);
               l = m1+1;
            }
            
         }
         
         out.println(ans);
         
      }
      
      
      
      
      
      
      
      
      
      out.close();
   }
   
      
}
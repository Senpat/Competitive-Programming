//make sure to make new file!
import java.io.*;
import java.util.*;

public class G229{
   
   public static long[] triangle;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int N = 200005;
      triangle = new long[N];
      triangle[0] = 0;
      for(int k = 1; k < N; k++){
         triangle[k] = triangle[k-1] + (long)k;
      }
      
      char[] s = f.readLine().toCharArray();
      ArrayList<Long> alist = new ArrayList<Long>();
      for(int k = 0; k < s.length; k++){
         if(s[k] == 'Y'){
            alist.add((long)k);
         }
      }
      int n = alist.size();
      
      long x = Long.parseLong(f.readLine());
      
      int l = 1;
      int r = n;
      int ans = 0;
      while(l <= r){
         int mid = l + (r-l)/2;
         
         if(check(alist,x,mid)){
            ans = mid;
            l = mid+1;
         } else {
            r = mid-1;
         }
      }
      
      out.println(ans);
      
      
      
      
      
      
      
      
      out.close();
   }
   
   
   public static boolean check(ArrayList<Long> alist, long x, int w){
      //initial
      int thresh = (w-1)/2;
      long nl = 0L;
      long suml = 0L;
      for(int k = 0; k < thresh; k++){
         suml += alist.get(k);
         nl++;
      }
      long nr = 0L;
      long sumr = 0L;
      for(int k = thresh+1; k < w; k++){
         sumr += alist.get(k);
         nr++;
      }
      
      /*
      nl is # elements below thresh
      nr is # elements above thresh
      answer = nl * alist.get(thresh) - suml - triangle[thresh] + (sumr - triangle[thresh]) - nr * alist.get(thresh)
      
      when w is odd:
      answer = sumr - suml - 2*triangle[thresh]
      when w is even:
      answer = sumr - suml - 2*triangle[thresh] - alist.get(thresh) - (thresh+1)
      */
      if(w%2 == 1 && sumr - suml - 2*triangle[thresh] <= x) return true;
      if(w%2 == 0 && sumr - suml - 2*triangle[thresh] - alist.get(thresh) - (thresh+1) <= x) return true;
      
      
      int l = 0;
      int mid = thresh;
      int r = w-1;
      
      while(r+1 < alist.size()){
         suml -= alist.get(l);
         suml += alist.get(mid);
         sumr -= alist.get(mid+1);
         sumr += alist.get(r+1);
         
         l++; mid++; r++;
         
         if(w%2 == 1 && sumr - suml - 2*triangle[thresh] <= x) return true;
         if(w%2 == 0 && sumr - suml - 2*triangle[thresh] - alist.get(mid) - (thresh+1) <= x) return true;
      }
      
      return false;
      
   }
   
      
}
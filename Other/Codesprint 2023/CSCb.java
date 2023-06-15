//make sure to make new file!
import java.io.*;
import java.util.*;

public class CSCb{
   
   public static long MAX = 1000000000000000000L-1;
   
   public static BufferedReader f;
   public static PrintWriter out;
   
   public static int queriesleft;
   
   public static void main(String[] args)throws IOException{
      f = new BufferedReader(new InputStreamReader(System.in));
      out = new PrintWriter(System.out);
      
      
      //binary search for highest number where they are not equal
      long l = 0L;
      long r = MAX;
      long ans = -1;
      String answer = "";
      
      queriesleft = 191;
      while(l <= r){
         long mid = l + (r-l)/2;
         
         if(r-l+1 <= queriesleft){
            //bf
            String sbf = bf(l,r);
            if(!sbf.equals("EQUAL")) answer = sbf;
            break;
         }
         
         if(check(mid)){
            ans = mid;
            l = mid+1;
         } else {
            r = mid-1;
         }
      }
      
      if(answer.length() == 0){
         answer = query2(ans);
      }
      out.println("! " + answer);
      
      //out.println(queriesleft);
      
      
      
      
      
      out.close();
   }
   
   public static String bf(long l, long r) throws IOException{
      for(long k = r; k >= l; k--){
         String q = query2(k);
         if(!q.equals("EQUAL")) return q;
      }
      
      return "EQUAL";
   }
   
   public static boolean check(long x) throws IOException{
      int NUM = 3;
      
      int tr = 0;
      int fa = 0;
      
      while(Math.max(tr,fa) < NUM){
         boolean q = query1(x);
         if(q) tr++;
         else fa++;
      }
      
      return tr > fa;
   }
   
   public static boolean query1(long x) throws IOException{
      queriesleft--;
      out.println("1 " + x + " " + MAX);
      out.flush();
      
      String s = f.readLine();
      return s.equals("NO");
   }
   
   public static String query2(long x) throws IOException{
      queriesleft--;
      out.println("2 " + x);
      out.flush();
      
      return f.readLine();
   }
      
}
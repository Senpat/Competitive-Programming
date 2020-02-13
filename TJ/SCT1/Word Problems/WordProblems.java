//make sure to make new file!
import java.io.*;
import java.util.*;

public class WordProblems{
   
   public static TreeSet<Long> tset;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      String s = st.nextToken();
      
      long n = Long.parseLong(st.nextToken());
      
      long N = (long)s.length();

      tset = new TreeSet<Long>();
      
      long ind = N;
      while(ind < n){
         tset.add(ind);
         ind*=2;
      }
      tset.add(ind);


      int i = (int)dothing(n,N);
      
      
      
      out.println(s.charAt(i-1));
      
      out.close();
   }
   
   public static long calc(long n){
      return tset.floor(n);
   }
   
   public static long dothing(long n,long N){
      //System.out.println(n);
      if(n <= N){
         return n;
      }
      
      return dothing(n-calc(n)+1,N);
   }
   
      
}
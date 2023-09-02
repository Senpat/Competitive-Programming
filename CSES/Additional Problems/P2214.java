//make sure to make new file!
import java.io.*;
import java.util.*;

public class P2214{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      long n = Long.parseLong(st.nextToken());
      long m = Long.parseLong(st.nextToken());
      
      //backwards
      long i = 1L;
      long curi = 0L;
      while(curi+i <= m){
         curi+=i;
         i++;
      }
      
      //where to put (i+1)th number
      long diff = m-curi;
      
      ArrayList<Long> answer = new ArrayList<Long>();
      for(long k = i; k >= 1; k--){
         if(k == diff){
            answer.add(i+1);
         }
         answer.add(k);
      }
      
      if(diff == 0 && i+1 <= n) answer.add(i+1);
      for(long k = i+2; k <= n; k++){
         answer.add(k);
      }
      
      StringJoiner sj = new StringJoiner(" ");
      for(long x : answer){
         sj.add("" + x);
      }
      out.println(sj.toString());
      
      
      
      
      
      
      
      out.close();
   }
   
      
}
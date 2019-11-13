//make sure to make new file!
import java.io.*;
import java.util.*;

public class C573{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      long n = Long.parseLong(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      long p = Long.parseLong(st.nextToken());
      
      
      st = new StringTokenizer(f.readLine());
      TreeMap<Long,Integer> tmap = new TreeMap<Long,Integer>();
      tmap.put(0L,0);
      for(int k = 0; k < m; k++){
         long i = Long.parseLong(st.nextToken());
         tmap.put(i,k+1);
      }
      
      long thresh = p;
      int removed = 0;
      int answer = 0;
      while(removed < m){
         int c = tmap.get(tmap.lowerKey(thresh+1));
         if(c - removed == 0){
            thresh = Math.min(n,thresh+p);
         } else {
            thresh += c-removed;
            removed = c;
            answer ++;
         }
      }
      
      out.println(answer);
      
      
      out.close();
   }
   
      
}
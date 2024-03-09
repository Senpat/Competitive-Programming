//make sure to make new file!
import java.io.*;
import java.util.*;

public class C340{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      long n = Long.parseLong(f.readLine());
      
      TreeMap<Long,Long> tmap = new TreeMap<Long,Long>();
      tmap.put(n,1L);
      
      long answer = 0L;
      
      while(tmap.lastKey() > 1L){
         long key = tmap.lastKey();
         long mul = tmap.get(key);
         answer += key*mul;
         tmap.remove(key);
         
         if(key%2 == 0L){
            add(tmap,key/2L,2L*mul);
         } else {
            add(tmap,key/2L,mul);
            add(tmap,key/2L + 1,mul);
         }
      }
      
      out.println(answer);
      
      
      
      
      
      
      
      
      out.close();
   }
   
   
   public static void add(TreeMap<Long,Long> tmap, long key, long value){
      if(tmap.containsKey(key)){
         tmap.put(key,tmap.get(key)+value);
      } else {
         tmap.put(key,value);
      }
   }
      
}
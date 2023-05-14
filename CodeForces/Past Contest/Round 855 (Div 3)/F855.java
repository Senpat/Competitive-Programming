//make sure to make new file!
import java.io.*;
import java.util.*;

public class F855{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      String[] array = new String[n];
      long[] allused = new long[n];
      long[] masks = new long[n];
      for(int k = 0; k < n; k++){
         array[k] = f.readLine();
         long all = 0L;
         long mask = 0L;
         for(int j = 0; j < array[k].length(); j++){
            mask ^= (1L << (array[k].charAt(j)-'a'));
            all |= (1L << (array[k].charAt(j)-'a'));
         }
         masks[k] = mask;
         allused[k] = all;
      }
      
      long answer = 0L;
      //character that's not used
      for(int r = 0; r < 26; r++){
         long target = ((1L << 26) - 1) ^ (1L << r);
         HashMap<Long,Integer> hmap = new HashMap<Long,Integer>();         //maps mask to frequency of that mask 
         for(int k = 0; k < n; k++){
            if((allused[k] & (1L << r)) != 0) continue;
            if(hmap.containsKey(target ^ masks[k])) answer += hmap.get(target ^ masks[k]);
            add(hmap,masks[k]);
         }
      }
      
      out.println(answer);
      
      
      
      
      
      out.close();
   }
   
   public static void add(HashMap<Long,Integer> hmap, long x){
      if(hmap.containsKey(x)) hmap.put(x,hmap.get(x)+1);
      else hmap.put(x,1);
   }
   
      
}
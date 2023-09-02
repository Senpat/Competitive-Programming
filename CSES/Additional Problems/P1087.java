//make sure to make new file!
import java.io.*;
import java.util.*;

public class P1087{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      
      char[] array = f.readLine().toCharArray();
      int n = array.length;
      
      int[] next = new int[n];
      Arrays.fill(next,Integer.MAX_VALUE);
      char[] c = new char[n];
      
      int[] last = new int[4];
      Arrays.fill(last,Integer.MAX_VALUE);
      
      HashMap<Character,Integer> hmap = new HashMap<Character,Integer>();
      hmap.put('A',0);
      hmap.put('C',1);
      hmap.put('G',2);
      hmap.put('T',3);
      char[] chars = new char[]{'A','C','G','T'};
      
      for(int k = n-1; k >= 0; k--){
         int max = -1;
         for(int j = 0; j < 4; j++){
            if(last[j] > max){
               next[k] = last[j];
               c[k] = chars[j];
               max = last[j];
            }
         }
      
         last[hmap.get(array[k])] = k;
      }
      
      StringJoiner sj = new StringJoiner("");
      int i = -1;
      char curc = '?';
      int max = -1;
      for(int j = 0; j < 4; j++){
         if(last[j] > max){
            i = last[j];
            curc = chars[j];
            max = last[j];
         }
      }
      
      sj.add(""+curc);
      while(i != Integer.MAX_VALUE){
         
         sj.add(""+c[i]);
         i = next[i];
      }
      
      out.println(sj.toString());
         
      
      
      
      
      
      
      out.close();
   }
   
      
}
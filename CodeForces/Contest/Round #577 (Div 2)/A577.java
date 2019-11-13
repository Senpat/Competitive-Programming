//make sure to make new file!
import java.io.*;
import java.util.*;

public class A577{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      String[] array = new String[n];
      for(int k = 0; k < n; k++){
         array[k] = f.readLine();
      }
      
      st = new StringTokenizer(f.readLine());
      
      int[] points = new int[m];
      for(int k = 0; k < m; k++){
         points[k] = Integer.parseInt(st.nextToken());
      }
      
      int answer = 0;
      for(int k = 0; k < m; k++){
         HashMap<Character, Integer> hset1 = new HashMap<Character,Integer>();
      
         hset1.put('A',0);
         hset1.put('B',0);
         hset1.put('C',0);
         hset1.put('D',0);
         hset1.put('E',0);
         
         int max  = 0;
         for(int j = 0; j < n; j++){
            char c = array[j].charAt(k);
            hset1.put(c,hset1.get(c) + 1);
            max = Math.max(hset1.get(c),max);
         }
         answer+=max*points[k];
      }
      
      out.println(answer);
         
      
      
      
      
      out.close();
   }
   
      
}
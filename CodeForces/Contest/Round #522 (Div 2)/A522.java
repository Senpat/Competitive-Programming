//make sure to make new file!
import java.io.*;
import java.util.*;

public class A522{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
      
      st = new StringTokenizer(f.readLine());
      
      int max = 1;
      for(int k = 0; k < n; k++){
         int a = Integer.parseInt(st.nextToken());
         
         if(map.containsKey(a)){
            map.put(a,map.get(a)+1);
            max = Math.max(max,map.get(a));
         } else {
            map.put(a,1);
         }
      }
      int total;
      if(max % m > 0) total = m*(max/m+1);
      else total = max;
      
      int answer = 0;
      for(int i : map.keySet()){
         answer+=total-map.get(i);
      }
      
      out.println(answer);
      
      out.close();
   }
   
}
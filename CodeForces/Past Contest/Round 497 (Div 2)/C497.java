//Reorder the Array
import java.io.*;
import java.util.*;

public class C497{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      HashMap<Integer,Integer> hm = new HashMap<Integer,Integer>();
      
      int n = Integer.parseInt(f.readLine());
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      for(int k = 0; k < n; k++){
         int i = Integer.parseInt(st.nextToken());
         if(!hm.containsKey(i)){
            hm.put(i,1);
         } else {
            hm.put(i,hm.get(i)+1);
         }
      }
      
      int max = 1;
      
      for(int i : hm.keySet()){
         max = Math.max(max,hm.get(i));
      }
      
      out.println(n-max);
      out.close();
   }
}
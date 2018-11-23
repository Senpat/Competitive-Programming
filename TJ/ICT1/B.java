//Fibonacci String
import java.io.*;
import java.util.*;

public class B{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      String s = f.readLine();
      
            
      int[] fibo = {1,1,2,3,5,8,13,21,34,55,89};
      
      HashMap<Character,Integer> map = new HashMap<Character,Integer>();
      
      for(int k = 0; k < s.length(); k++){
         if(!map.containsKey(s.charAt(k))){
            map.put(s.charAt(k),1);
         } else {
            map.put(s.charAt(k),map.get(s.charAt(k))+1);
         }
      }
      PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
      for(Character c : map.keySet()){
         pq.add(map.get(c));
      }
      
      int index = 0;
      while(!pq.isEmpty()){
         if(pq.poll()!=fibo[index]){
            out.println("NO");
            out.close();
            System.exit(0);
         }
         index++;
      }
      
      out.println("YES");
      
      
      
      out.close();
   }
   
}
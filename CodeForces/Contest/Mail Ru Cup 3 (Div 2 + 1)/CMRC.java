//make sure to make new file!
import java.io.*;
import java.util.*;

public class CMRC{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      Hero[] array = new Hero[2*n+1];
      
      st = new StringTokenizer(f.readLine());
      for(int k = 1; k <= 2*n; k++){
         int i = Integer.parseInt(st.nextToken());
         array[k] = new Hero(k,i);
      }
      
      HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
      
      for(int k = 0; k < m; k++){
         st = new StringTokenizer(f.readLine());
         
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
         
         int p1 = array[a].power;
         int p2 = array[b].power;
         
         if(p1 > p2){
            array[a].power = 1001;
            array[b].power = -1001;
         } else {
            array[a].power = -1001;
            array[b].power = 1001;
         }
            
         map.put(a,b);
         map.put(b,a);
      }
      int turn = Integer.parseInt(f.readLine());
      
      boolean[] seen = new boolean[2*n+1];
      
      PriorityQueue<Hero> pq = new PriorityQueue<Hero>(Collections.reverseOrder());
      for(int k = 1; k <= 2*n; k++){
         pq.add(array[k]);
      }
      
      
      if(turn == 2){                                           //their turn
         for(int k = 0; k < n; k++){
            int input = Integer.parseInt(f.readLine());
            seen[input] = true;
            if(map.containsKey(input) && !seen[map.get(input)]){
               seen[map.get(input)] = true;
               out.println(map.get(input));
               out.flush();
            } else {
            
               Hero cur = pq.poll();
               while(seen[cur.index]){
                  cur = pq.poll();
               }
               seen[cur.index] = true;
               out.println(cur.index);
               out.flush();
            }
         }
      
         
      } else {                                                //my turn
         int forced = -1;
         for(int k = 0; k < n; k++){
            if(forced != -1 && !seen[forced]){
               seen[forced] = true;
               out.println(forced);
               out.flush();
               forced = -1;
               continue;
            }
            forced = -1;
            Hero cur = pq.poll();
            while(seen[cur.index]){
               cur = pq.poll();
            }
            seen[cur.index] = true;
            out.println(cur.index);
            out.flush();
            
            int input = Integer.parseInt(f.readLine());
            seen[input] = true;
            if(map.containsKey(input)){
               forced = map.get(input);
            }
         }
      
      
      
      }
      
      
      out.close();
   }
   
   public static class Hero implements Comparable<Hero>{
      int index;
      int power;
      public Hero(int a, int b){
         index = a;
         power = b;
      }
      public int compareTo(Hero h){
         return power - h.power;
      }
      
   }
   
}
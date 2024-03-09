//The BOSS Can Count Pairs
import java.io.*;
import java.util.*;

public class D1830{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         int n = Integer.parseInt(f.readLine());
      
         StringTokenizer sta = new StringTokenizer(f.readLine());
         StringTokenizer stb = new StringTokenizer(f.readLine());
         
         Pair[] array = new Pair[n];
         
         for(int k = 0; k < n; k++){
            long a = Long.parseLong(sta.nextToken());
            long b = Long.parseLong(stb.nextToken());
            
            array[k] = new Pair(a,b);
         }
         
         Arrays.sort(array);
         
         ArrayList<HashMap<Long,Long>> vals = new ArrayList<HashMap<Long,Long>>(n+1);
         
         for(int k = 0; k <= n; k++){
            vals.add(new HashMap<Long,Long>());
         }
         
         long answer = 0L;
         
         for(int k = 0; k < n; k++){
            for(int j = 1; j <= array[k].a && j*array[k].a - array[k].b <= n; j++){
               long b = j*array[k].a - array[k].b;
               if(vals.get(j).containsKey(b)) answer += vals.get(j).get(b);
            }
            add(vals.get((int)array[k].a),array[k].b);
         }
         
         out.println(answer);
         
         
         
         
      

      }
      
      
      
      
      out.close();
   }
   
   public static void add(HashMap<Long,Long> hmap, long x){
      if(hmap.containsKey(x)) hmap.put(x,hmap.get(x)+1L);
      else hmap.put(x,1L);
   }
   
   public static class Pair implements Comparable<Pair>{
      long a;
      long b;
      public Pair(long c, long d){
         a = c;
         b = d;
      }
      
      public int compareTo(Pair p){
         if(a > p.a) return 1;
         if(a < p.a) return -1;
         return 0;
      }
   }
      
}
//make sure to make new file!
import java.io.*;
import java.util.*;

public class D777b{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         long x = Long.parseLong(st.nextToken());
         long d = Long.parseLong(st.nextToken());
         
         HashMap<Pair,Integer> hmap = new HashMap<Pair,Integer>();
         
         PriorityQueue<Pair> pq = new PriorityQueue<Pair>();
         hmap.put(new Pair(1,1),1);
         pq.add(new Pair(1,1));
         
         int found = 0;
         
         while(!pq.isEmpty()){
            Pair p = pq.poll();
            
            //out.println(p.val + " " + p.d + " " + hmap.get(p));
            
            if(p.val == x){
               found += hmap.get(p);
               if(found >= 2) break;
               continue;
            }
            
            long newval;
            for(long k = p.d; (newval=p.val*k*d)*(k*d) <= x; k++){
               if(k % d == 0L) continue;
               if(x % newval != 0) continue;
               Pair newp = new Pair(newval, k);
               //out.println(p.val + " -> " + newval + " " + k); 
               if(hmap.containsKey(newp)){
                  hmap.put(newp,Math.min(2,hmap.get(newp)+hmap.get(p)));
               } else {
                  hmap.put(newp,hmap.get(p));
                  pq.add(newp);
               }
            }
            
            //check exactly equals x
            if(x % (p.val * d) != 0) continue;
            long tryk = x/(p.val*d);
            if(tryk % d != 0L && tryk >= p.d){
               Pair newp = new Pair(x,tryk);
               //out.println(p.val + " -> " + x + " " + tryk);
               if(hmap.containsKey(newp)){
                  hmap.put(newp,Math.min(2,hmap.get(newp)+hmap.get(p)));
               } else {
                  hmap.put(newp,hmap.get(p));
                  pq.add(newp);
               }
            }
            
            
         }
         
         if(found >= 2){
            out.println("YES");
         } else {
            out.println("NO");
         }

      }
      
      
      
      
      out.close();
   }
   
   public static class Pair implements Comparable<Pair>{
      long val;
      long d;              //biggest divisor used to get there
      public Pair(long a, long b){
         val = a;
         d = b;
      }
      
      public int hashCode(){
         return (val + " " + d).hashCode();
      }
      public boolean equals(Object o){
         Pair p = (Pair)o;
         return val == p.val && d == p.d;
      }
      
      public int compareTo(Pair p){
         if(val < p.val) return -1;
         if(val > p.val) return 1;
         return 0;
      }
   }
   
      
}
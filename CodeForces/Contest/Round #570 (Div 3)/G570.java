//make sure to make new file!
import java.io.*;
import java.util.*;

public class G570{

   public static int MAX = 200005;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 0; q < t; q++){
      
         int n = Integer.parseInt(f.readLine());
         
         HashMap<Long,Long> hm = new HashMap<Long,Long>();
         HashMap<Long,Long> freq = new HashMap<Long,Long>();
         HashMap<Long,Long> ones = new HashMap<Long,Long>();
         
         for(int k = 0; k < n; k++){
            StringTokenizer st = new StringTokenizer(f.readLine());
            long i = Long.parseLong(st.nextToken());
            long x = Long.parseLong(st.nextToken());
            if(hm.containsKey(i)){
               hm.put(i,hm.get(i)+1);
            } else {
               hm.put(i,1L);
               ones.put(i,0L);
            }
            
            if(x == 1){
               ones.put(i,ones.get(i)+1);
            }
         }
         
         PriorityQueue<Long> pq = new PriorityQueue<Long>(10,Collections.reverseOrder());
         
         PriorityQueue<Long> fi = new PriorityQueue<Long>(10,Collections.reverseOrder());
         HashMap<Long,ArrayList<Long>> alist = new HashMap<Long,ArrayList<Long>>();
         
         for(long i : hm.keySet()){
            long x = hm.get(i);
            
            if(!alist.containsKey(x)) alist.put(x,new ArrayList<Long>());
            alist.get(x).add(i);
            
            
            if(freq.containsKey(x)){
               freq.put(x,freq.get(x)+1);
            } else {
               pq.add(x);
               freq.put(x,1L);
            }
         }
         
         long max = pq.peek();
         long answer1 = 0L;
         long answer2 = 0L;
         for(int k = (int)max; k > 0; k--){
            if(alist.containsKey((long)k)){
               for(long i : alist.get((long)k)){
                  fi.add(ones.get(i));
               }
            }
            if(fi.isEmpty()){
               continue;
            }
            
            answer1 += k;
            answer2 += Math.min(k,fi.poll());
         }
         
         out.println(answer1 + " " + answer2);
      }
      
      
      
      
      out.close();
   }
   
   public static long sum(long a, long b){
      b = Math.min(a,b);
      return (2*a-b+1)*b/2;
   }
   
      
}
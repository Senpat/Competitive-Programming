//make sure to make new file!
import java.io.*;
import java.util.*;
//SOLVES COMPLETELY WRONG PROBLEM
public class D85{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      ArrayList<Long> seg = new ArrayList<Long>();
      seg.add(1L);
      seg.add(2L);
      for(int k = 2; k < 100005; k++){
         seg.add(seg.get(k-1)+(long)(k-1)*2L);
      }
      
      Query[] queries = new Query[t];
      PriorityQueue<Long> pq = new PriorityQueue<Long>();
      for(int q = 0; q < t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         long l = Long.parseLong(st.nextToken());
         long r = Long.parseLong(st.nextToken());
         
         queries[q] = new Query(n,l,r);
         pq.add(l);

      }
      
      HashMap<Long,Integer> hmap = new HashMap<Long,Integer>();
      
      int i = 0;
      
      while(!pq.isEmpty()){
         long cur = pq.poll();
         
         if(cur == 1){
            hmap.put(1L,1);
         } else if(cur == 2 || cur == 3){
            hmap.put(cur,2);
         } else {
            
            while(seg.get(i) <= cur){
               i++;
            }
            
            hmap.put(cur,i);
         }
      }
      
      for(int q = 0; q < t; q++){
         StringJoiner sj = new StringJoiner(" ");
         
         int n = queries[q].n;
         long l = queries[q].l;
         long r = queries[q].r;
         
         long num = l;
         int startseg = hmap.get(l);
         while(num <= r){
            if(num == 1){
               sj.add("" + 1);
               num++;
               startseg++;
               continue;
            }
            
            
            long index = seg.get(startseg-1);
            long curnum = num;
            for(long k = curnum-index; k < (startseg-1)*2 && num <= r; k++){
               if(k%2 == 0){
                  sj.add("" + startseg);
               } else {
                  if(k == (startseg-1)*2-1){
                     sj.add("" + 1);
                  } else {
                     sj.add("" + ((k/2)+2));
                  }
               }
               num++;
            
            }
            
            startseg++;
         }
         
         out.println(sj.toString());
      }
            
         
         
         
         
         
      
      
      
      out.close();
   }
   
   public static class Query{
      int n;
      long l;
      long r;
      public Query(int a, long b, long c){
         a = n;
         l = b;
         r = c;
      }
   }
   
      
}
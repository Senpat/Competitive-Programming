//make sure to make new file!
import java.io.*;
import java.util.*;
//MISREAD PROBLEM
public class B698{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         int m = Integer.parseInt(st.nextToken());
         
         char[] sa = f.readLine().toCharArray();
         char[] sb = f.readLine().toCharArray();
         
         //sort by left
         PriorityQueue<Query> pq = new PriorityQueue<Query>(new Comparator<Query>(){
            public int compare(Query a, Query b){
               return a.l-b.l;
            }
         });
         
         for(int k = 0; k < m; k++){
            st = new StringTokenizer(f.readLine());
      
            int a = Integer.parseInt(st.nextToken())-1;
            int b = Integer.parseInt(st.nextToken())-1;
            
            if(b-a < 2) continue;
            pq.add(new Query(a,b));
         }
         
         //sort by right
         PriorityQueue<Query> active = new PriorityQueue<Query>(new Comparator<Query>(){
            public int compare(Query a, Query b){
               return a.r-b.r;
            }
         });
         
         boolean fail = false;
         for(int k = 0; k < n; k++){
            //put in queries
            while(!pq.isEmpty() && pq.peek().l == k){
               active.add(pq.poll());
            }
            
            //remove queries
            while(!active.isEmpty() && active.peek().r < k){
               active.poll();
            }
            
            if(sa[k] != sb[k]){
               if(active.isEmpty()){
                  fail = true;
                  break;
               }
               
               Query cur = active.poll();
               cur.remaining--;
               if(cur.remaining > 0) active.add(cur);
            }
         }
         
         if(!fail){
            out.println("YES");
         } else {
            out.println("NO");
         }
            

      }
      
      
      
      
      out.close();
   }
   
   public static class Query{
      int l;
      int r;
      int remaining;
      public Query(int a, int b){
         l = a;
         r = b;
         remaining = (b-a+2)/2-1;
      }
   }
   
      
}
//make sure to make new file!
import java.io.*;
import java.util.*;

public class DG2{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      ArrayList<Long> alist = new ArrayList<Long>();
      HashSet<Long> used = new HashSet<Long>();
      for(int k = 0; k < n; k++){
         int i = Long.parseLong(st.nextToken());
         if(!used.contains(i)){
            used.add(i);
            alist.add(i);
         }
      }
      
      Collections.sort(alist);
      
      HashMap<Long,Integer> dfreq = new HashMap<Long,Integer>();
      PriorityQueue<Long> pq = new PriorityQueue<Long>();
      
      for(int k = 1; k < alist.size(); k++){
         int d = alist.get(k)-alist.get(k-1);
         if(dfreq.contains(d)){
            dfreq.put(d,dfreq.get(d)+1);
         } else {
            dfreq.put(d,1);
            pq.add(d);
         }
      }
      
      dfreq.put(1,0);
      
      int last = pq.poll();
         
      
      
      int q = Integer.parseInt(f.readLine());
      
      for(int t = 0; t < q; t++){
         st = new StringTokenizer(f.readLine());
      
         int l = Integer.parseInt(st.nextToken());
         int r = Integer.parseInt(st.nextToken());
         st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      int d = r-l+1;
         
         
      

      
      
      
      
      out.close();
   }
   
      
}
//make sure to make new file!
import java.io.*;
import java.util.*;

public class D86{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      PriorityQueue<Integer> pq = new PriorityQueue<Integer>(10,Collections.reverseOrder());
      st = new StringTokenizer(f.readLine());
      for(int k = 0; k < n; k++){
         pq.add(Integer.parseInt(st.nextToken()));
      }
      
      int[] c = new int[m+1];
      st = new StringTokenizer(f.readLine());
      for(int k = 1; k <= m; k++){
         c[k] = Integer.parseInt(st.nextToken());
      }
      
      
      int[] ctoi = new int[n+2];
      ctoi[n+1] = -1;
      
      for(int k = 1; k <= m; k++){
         ctoi[c[k]] = k;
      }
      
      for(int k = n-1; k >= 1; k--){
         if(ctoi[k] == 0) ctoi[k] = ctoi[k+1];
      }
      
      ArrayList<Integer> maxes = new ArrayList<Integer>();                       //maximum number that test case can hold
      ArrayList<ArrayList<Integer>> answer = new ArrayList<ArrayList<Integer>>();
      
      //add first number
      int first = pq.poll();
      
      answer.add(new ArrayList<Integer>());
      answer.get(0).add(first);
      maxes.add(ctoi[1]-1);
      
      while(!pq.isEmpty()){
         int cur = pq.poll();
         
         //binary search to find position
         int l = 0;
         int r = answer.size()-1;
         int ans = -1;
         
         while(l <= r){
            int mid = l + (r-l)/2;
            
            //if(cur <= maxes.get(mid)){
            if(cur <= ctoi[answer.get(mid).size()+1]){
               ans = mid;
               r = mid-1;
            } else {
               l = mid+1;
            }
         }
         
         if(ans == -1){
            answer.add(new ArrayList<Integer>());
            answer.get(answer.size()-1).add(cur);
            //maxes.add(ctoi[1]-1);
         } else {
            answer.get(ans).add(cur);
            //maxes.set(ans,ctoi[answer.get(ans).size()]-1);
         }
      }
      
      
      out.println(answer.size());
      for(ArrayList<Integer> alist : answer){
         StringJoiner sj = new StringJoiner(" ");
         sj.add("" + alist.size());
         for(int i : alist){
            sj.add("" + i);
         }
         out.println(sj.toString());
      }
      
      
      
      
      
      
      
      
      out.close();
   }
   
      
}
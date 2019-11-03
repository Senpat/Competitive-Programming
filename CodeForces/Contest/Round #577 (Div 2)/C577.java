//make sure to make new file!
import java.io.*;
import java.util.*;

public class C577{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());

      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      st = new StringTokenizer(f.readLine());
      
      int[] array = new int[n];
      for(int k = 0; k < n; k++){
         array[k] = Integer.parseInt(st.nextToken());
      }
      
      shuffleArray(array);
      Arrays.sort(array);
      
      HashMap<Integer,Integer> hmap = new HashMap<Integer,Integer>();
      PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
      
      for(int k = n/2; k<n; k++){
         if(!hmap.containsKey(array[k])){
            pq.add(array[k]);
            hmap.put(array[k],1);
         } else {
            hmap.put(array[k],hmap.get(array[k])+1);
         }
      }
      
      int answer = array[n/2];
      
      int needtoinc = 0;
      while(!pq.isEmpty()){
         int i = pq.poll();
         
         //cost to increase median by 1
         needtoinc += hmap.get(i);
         
         //next increase
         if(pq.isEmpty()){
            answer += m/needtoinc;
         } else if(m/needtoinc > pq.peek()-i){
            m -= needtoinc * (pq.peek()-i);
            answer = pq.peek();
         } else {
            answer += m/needtoinc;
            break;
         }
      }
      
      out.println(answer);
         
      
      
      
      
      out.close();
   }
   
   public static void shuffleArray(int[] arr){
      int n = arr.length;
      Random rnd = new Random();
      for(int i=0; i<n; ++i){
         int tmp = arr[i];
         int randomPos = i + rnd.nextInt(n-i);
         arr[i] = arr[randomPos];
         arr[randomPos] = tmp;
      }   
   }
   
      
}
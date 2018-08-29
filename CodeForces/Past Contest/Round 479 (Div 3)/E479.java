//Cyclic Components
//too slow
import java.io.*;
import java.util.*;

public class E479{

   public static void main(String[] args) throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      HashMap<Integer,ArrayList<Integer>> map = new HashMap<Integer,ArrayList<Integer>>();
      HashSet<Integer> hs = new HashSet<Integer>();
      
      for(int k = 0; k < m; k++){
         
         StringTokenizer st1 = new StringTokenizer(f.readLine());
         
         int a = Integer.parseInt(st1.nextToken());
         int b = Integer.parseInt(st1.nextToken());
         
         if(!map.containsKey(a)) map.put(a,new ArrayList<Integer>());
         if(!map.containsKey(b)) map.put(b,new ArrayList<Integer>());
         
         map.get(a).add(b);
         map.get(b).add(a);
         
         hs.add(a);
         hs.add(b);
      }
      
      int[] array = new int[n];
      int ind = 0;
      for(int i : hs){
         array[ind] = i;
         ind++;
      }
      
      boolean curbool = true;
      int count = 0;
      for(int k = 0; k < n; k++){
         if(hs.contains(array[k])){
            Queue<Integer> q = new LinkedList<Integer>();
         
            q.add(array[k]);
         
            while(!q.isEmpty()){
               int now = q.poll();
               if(map.get(now).size()!=2) curbool = false;
               hs.remove(now);
               for(int i : map.get(now)){
                  if(hs.contains(i)) q.add(i);
               }
            }
         
            if(curbool) count++;
            curbool = true;
         }
      }
      
      System.out.println(count);
   }
}
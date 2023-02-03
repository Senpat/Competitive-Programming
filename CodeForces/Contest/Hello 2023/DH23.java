//make sure to make new file!
import java.io.*;
import java.util.*;

public class DH23{

   public static int[] bits;
   public static int n;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         n = Integer.parseInt(f.readLine());
      
         StringTokenizer st = new StringTokenizer(f.readLine());
         int[] a = new int[n+1];
         for(int k = 1; k <= n; k++){
            a[k] = Integer.parseInt(st.nextToken());
         }
         
         st = new StringTokenizer(f.readLine());
         int[] b = new int[n+1];
         for(int k = 1; k <= n; k++){
            b[k] = Integer.parseInt(st.nextToken());
         }
         
         int r = Integer.parseInt(f.readLine());
         HashMap<Integer,Integer> razors = new HashMap<Integer,Integer>();
         st = new StringTokenizer(f.readLine());
         for(int k = 0; k < r; k++){
            int i = Integer.parseInt(st.nextToken());
            if(razors.containsKey(i)) razors.put(i,razors.get(i)+1);
            else razors.put(i,1);
         }
         
         boolean fail = false;
         PriorityQueue<Integer> bpq = new PriorityQueue<Integer>(10,Collections.reverseOrder());
         HashMap<Integer,ArrayList<Integer>> indices = new HashMap<Integer,ArrayList<Integer>>();
         for(int k = 1; k <= n; k++){
            if(a[k] < b[k]){
               fail = true;
               break;
            }
            
            if(!indices.containsKey(b[k])){
               bpq.add(b[k]);
               ArrayList<Integer> temp = new ArrayList<Integer>();
               temp.add(k);
               indices.put(b[k],temp);
            } else {
               indices.get(b[k]).add(k);
            }
         }
         
         bits = new int[n+1];
         
         while(!bpq.isEmpty()){
            int x = bpq.poll();
            
            int target = 0;
            if(razors.containsKey(x)) target = razors.get(x);
            
            int startstreak = -1;
            int need = 0;
            
            for(int i : indices.get(x)){
               if(a[i] == b[i]) continue;
               if(startstreak == -1){
                  startstreak = i;
                  need++;
               } else {
                  //check if you can continue the streak
                  if(psum(i) != psum(startstreak-1)){
                     startstreak = i;
                     need++;
                  }
               }
            }
            
            if(need > target){
               fail = true;
               break;
            }
            
            for(int i : indices.get(x)){
               update(i,1);
            }
            
         }
         
         if(fail){
            out.println("NO");
         } else {
            out.println("YES");
         }
      }
      
      
      
      
      out.close();
   }
   
   
   
   public static void update(int i, int x){
      for(; i <= n; i += i&-i){
         //System.out.println(i);
         bits[i]+=x;
      }
   
   }
   
   public static int psum(int i){
      int sum = 0;
      for(; i > 0; i -= i&-i){
         //System.out.println(i);
         sum += bits[i];
      }
      return sum;
   
   }
      
}
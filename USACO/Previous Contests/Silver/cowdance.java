import java.io.*;
import java.util.*;

class cowdance{

   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new FileReader("cowdance.in"));
      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cowdance.out")));
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int maxT = Integer.parseInt(st.nextToken());
      
      int[] array = new int[n];
      for(int k = 0; k < n; k++){
         array[k] = Integer.parseInt(f.readLine());
      }
      
      int min = 1;
      int max = n;
      while(min!=max){
         int mid = (min + max)/2;
         if(possible(array,mid,maxT)){
            max = mid;
         } else {
            min = mid+1;
         }
      }
      
      out.println(max);
      out.close();
      
      
      
   }
   
   
   public static boolean possible(int[] array, int num, int t){
      int lastTime = 0;
      PriorityQueue<Integer> p = new PriorityQueue<Integer>();
      
      for(int k = 0; k < array.length; k++){
         if(p.size() == num){
            lastTime = p.poll();
         }
         
         if(lastTime + array[k] > t){
            return false;
         }
         p.add(lastTime + array[k]);
      }
      
      return true;  
      
   }
   
   
   
   
   
}
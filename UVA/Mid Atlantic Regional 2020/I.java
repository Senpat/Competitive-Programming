//make sure to make new file!
import java.io.*;
import java.util.*;

public class I{

   public static int[] bits;
   public static int n;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      n = Integer.parseInt(f.readLine());
      
      //ONE-INDEXED
      int[] array = new int[n+1];
      Integer[] sort = new Integer[n+1];
      sort[0] = -1;
      for(int k = 1; k <= n; k++){
         array[k] = Integer.parseInt(f.readLine());
         sort[k] = array[k];
      }
      
      //compress
      Arrays.sort(sort);
      HashMap<Integer,Integer> hmap = new HashMap<Integer,Integer>();         //number, index of number in sort
      for(int k = 1; k <= n; k++){
         hmap.put(sort[k],k);
      }
      
      bits = new int[n+1];
      
      int[] numleft = new int[n+1];                        //number of numbers that are bigger to the left
      
      for(int k = 1; k <= n; k++){
         numleft[k] = psum(n) - psum(hmap.get(array[k]));
         update(hmap.get(array[k]),1);
      }
      
      bits = new int[n+1];
      
      int[] numright = new int[n+1];
      
      for(int k = n; k >= 1; k--){
         numright[k] = psum(n) - psum(hmap.get(array[k]));
         update(hmap.get(array[k]),1);
      }
      
      long answer = 0L;
      for(int k = 1; k <= n; k++){
         answer += (long)Math.min(numleft[k],numright[k]);
      }
      
      out.println(answer);
      
      
      
      
      
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
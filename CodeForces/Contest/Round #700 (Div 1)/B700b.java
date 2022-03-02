//make sure to make new file!
import java.io.*;
import java.util.*;
//2nd subtask
public class B700b{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      int[] array = new int[n];
      for(int k = 0; k < n; k++){
         array[k] = Integer.parseInt(st.nextToken());
      }
      
      HashSet<Integer> hset = new HashSet<Integer>();
      int last = -1;
      int minus = 0;
      for(int k = 0; k < n; k++){
         if(array[k] == last){
            minus++;
         } else if(hset.contains(array[k])){
            minus++;
            hset.clear();
            if(last != -1) hset.add(last);
            last = array[k];
         } else {
            if(last != -1) hset.add(last);
            last = array[k];
         }
      }
      
      out.println(n-minus);
         
      
      
      
      
      
      
      
      
      
      
      out.close();
   }
   
      
}
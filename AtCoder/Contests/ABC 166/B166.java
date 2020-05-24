//make sure to make new file!
import java.io.*;
import java.util.*;

public class B166{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      
      HashSet<Integer> hset = new HashSet<Integer>();
      
      for(int k = 0; k < m; k++){
         int d = Integer.parseInt(f.readLine());
         st = new StringTokenizer(f.readLine());
         
         for(int j = 0; j < d; j++){
            hset.add(Integer.parseInt(st.nextToken()));
         }
      }
      
      out.println(n-hset.size());
      
      
      
      
      
      
      out.close();
   }
   
      
}
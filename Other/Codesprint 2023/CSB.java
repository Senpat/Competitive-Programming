//make sure to make new file!
import java.io.*;
import java.util.*;

public class CSB{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      HashSet<Integer> hset = new HashSet<Integer>();
      int max = 0;
      for(int k =  0; k < n; k++){
         int i = Integer.parseInt(st.nextToken());
         hset.add(i);
         max = Math.max(max,i);
      }
      
      if(hset.contains(max/2)){
         out.println("yes");
      } else {
         out.println("no");
      }
      
      
      
      
      
      
      
      
      
      
      out.close();
   }
   
      
}
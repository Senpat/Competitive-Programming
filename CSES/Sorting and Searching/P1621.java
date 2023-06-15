//make sure to make new file!
import java.io.*;
import java.util.*;

public class P1621{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      HashSet<Integer> hset = new HashSet<Integer>();
      for(int k = 0; k < n; k++){
         hset.add(Integer.parseInt(st.nextToken()));
      }
      
      out.println(hset.size());
      
      
      
      
      
      
      
      out.close();
   }
   
      
}
//make sure to make new file!
import java.io.*;
import java.util.*;

public class D{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      HashSet<Integer> hs = new HashSet<Integer>();
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      for(int k = 0; k < n; k++){
         hs.add(Integer.parseInt(st.nextToken()));
      }
      
      int answer = n-hs.size();
      out.println(answer);
      
      
      
      
      out.close();
   }
   
      
}
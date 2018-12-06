//make sure to make new file!
import java.io.*;
import java.util.*;

public class AMRC{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      HashSet<Integer> prev = new HashSet<Integer>();
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int m = Integer.parseInt(st.nextToken());
      for(int k = 0; k < m; k++){
         prev.add(Integer.parseInt(st.nextToken()));
      }
      
      for(int k = 1; k < n; k++){
         
         HashSet<Integer> cur = new HashSet<Integer>();
         st = new StringTokenizer(f.readLine());
      
         m = Integer.parseInt(st.nextToken());
         
         for(int j = 0; j < m; j++){
            int i = Integer.parseInt(st.nextToken());
            if(prev.contains(i)) cur.add(i);
         }
         
         prev = cur;
      
      }
      
      for(int i : prev){
         out.print(i + " ");
      }
      
      out.close();
   }
   
}
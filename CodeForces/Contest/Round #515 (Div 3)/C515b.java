//make sure to make new file!
import java.io.*;
import java.util.*;

public class C515b{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
      int min = 0;
      int max = 1;
      
      
      for(int k = 0; k < n; k++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         char c = st.nextToken().charAt(0);
         int m = Integer.parseInt(st.nextToken());
      
         if(c == 'L'){
            map.put(m,min);
            min--;
         } else if(c == 'R'){
            map.put(m,max);
            max++;
         } else {
            out.println(Math.min(map.get(m)-min-1,max-map.get(m)-1));
         }
      }
      
      
      
      out.close();
   }
   
}
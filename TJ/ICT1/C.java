//Candy Fat
import java.io.*;
import java.util.*;

public class C{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      HashMap<String, Integer> map = new HashMap<String,Integer>();
      
      for(int k = 0; k < n; k++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         String s = st.nextToken();
         int m = Integer.parseInt(st.nextToken());
         
         map.put(s,m);
      
      }
      
      StringBuilder cur = new StringBuilder("");
      
      String big = f.readLine();
      
      int answer = 0;
      
      for(int k = 0; k < big.length(); k++){
         cur.append(big.charAt(k));
         if(map.containsKey(cur.toString())){
            answer+=map.get(cur.toString());
            cur = new StringBuilder("");
         }
      }
      
      out.println(answer);
      
      out.close();
   }
   
}
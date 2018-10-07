//Cashier
import java.io.*;
import java.util.*;

public class A514{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      int a = Integer.parseInt(st.nextToken());
      
      HashMap<Integer,Integer> need = new HashMap<Integer,Integer>();
      
      for(int k = 0; k < n; k++){
         st = new StringTokenizer(f.readLine());
         
         int c = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
      
         need.put(c,b);
      }
      
      
      int smokes = 0;
      int counter = 0;
      for(int k = 0; k < m; k++){
         if(!need.containsKey(k)){
            counter++;
            if(counter == a){
               smokes++;
               counter = 0;
            }
         } else {
            k = need.get(k);
            counter = 0;
         }
      }
      
      out.println(smokes);
      
      
      out.close();
   }
   
}
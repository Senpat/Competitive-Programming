//make sure to make new file!
import java.io.*;
import java.util.*;

public class A125{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      HashSet<Integer> squares = new HashSet<Integer>();
      for(int k =0; k <= 100; k++) squares.add(k*k);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int x = Integer.parseInt(st.nextToken());
         int y = Integer.parseInt(st.nextToken());
         
         if(x == 0 && y == 0){
            out.println(0);
            continue;
         }
         
         if(squares.contains(x*x+y*y)){
            out.println(1);
            continue;
         }
         
         out.println(2);
         

      }
      
      
      
      
      out.close();
   }
   
      
}
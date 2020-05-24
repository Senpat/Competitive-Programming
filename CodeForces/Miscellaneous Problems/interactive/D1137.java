//Cooperative Game
import java.io.*;
import java.util.*;

public class D1137{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      String s;
      String dummy;
      while(true){
         //move 0 once and 1 twice
         out.println("next 1");
         out.flush();
         
         dummy = f.readLine();
         
         out.println("next 0 1");
         out.flush();
         
         s = f.readLine();
         
         if(check01(s)){
            break;
         }
      }
      
      //01 somewhere in loop, 23456789 at start
      
      //move all until there they are in one group
      
      while(true){
         out.println("next 0 1 2 3 4 5 6 7 8 9");
         out.flush();
         
         s = f.readLine();
         
         if(s.substring(0,2).equals("1 ")){
            break;
         }
      }
      
      out.println("done");
      
      
      
      
      
      
      
      out.close();
   }
   
   public static boolean check01(String s){
      StringTokenizer st = new StringTokenizer(s);
      int i = Integer.parseInt(st.nextToken());
      
      for(int k = 0; k < i; k++){
         String cur = st.nextToken();
         if(cur.contains("0") && cur.contains("1")) return true;
         else if(cur.contains("0")) return false;
         else if(cur.contains("1")) return false;
      }
      //shouldn't get here
      return false;
   }
         
      
}
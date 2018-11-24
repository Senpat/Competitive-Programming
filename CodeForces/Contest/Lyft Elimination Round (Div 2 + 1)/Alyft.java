//make sure to make new file!
import java.io.*;
import java.util.*;

public class Alyft{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int ax = Integer.parseInt(st.nextToken());
      int ay = Integer.parseInt(st.nextToken());
      
      st = new StringTokenizer(f.readLine());
      
      int bx = Integer.parseInt(st.nextToken());
      int by = Integer.parseInt(st.nextToken());
      
      st = new StringTokenizer(f.readLine());
      
      int cx = Integer.parseInt(st.nextToken());
      int cy = Integer.parseInt(st.nextToken());
      
      
      // //check is c is on diagonal
//       if(cx-ax == cy-ay){
//          out.println("NO");
//          out.close();
//          System.exit(0);
//       }
      
      if(bx>ax){
         if(cx<ax){  
            out.println("NO");
            out.close();
            System.exit(0);
         }
      }
      if(bx<ax){
         if(cx>ax){  
            out.println("NO");
            out.close();
            System.exit(0);
         }
      }
      if(by>ay){
         if(cy<ay){  
            out.println("NO");
            out.close();
            System.exit(0);
         }
      }
      if(by<ay){
         if(cy>ay){  
            out.println("NO");
            out.close();
            System.exit(0);
         }
      }   
      
      
      out.println("YES");
      
      out.close();
   }
   
}
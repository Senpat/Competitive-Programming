//make sure to make new file!
import java.io.*;
import java.util.*;

public class K{

   //public static PrintWriter out;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      //out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      for(int k = 0; k < n; k++){
         send(5,1,5,6);
         
         while(true){
            String s = f.readLine();
            if(s.equals("GAME")) break;
            
            StringTokenizer st = new StringTokenizer(s);
            st.nextToken();
            
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            
            if(x1 == 5) x1--;
            if(x2 == 5) x2--;
            
            send(5-x2,7-y2,5-x1,7-y1);
         }
         
      
      }
      
      
      
      
      
      
      
      //out.close();
   }
   
   
   public static void send(int x1, int y1, int x2, int y2) throws IOException{
      System.out.println("" + x1 + " " + y1 + " " + x2 + " " + y2);
      System.out.flush();
   }
      
}
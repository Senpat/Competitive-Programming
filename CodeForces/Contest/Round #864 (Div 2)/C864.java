//make sure to make new file!
import java.io.*;
import java.util.*;

public class C864{
   
   public static BufferedReader f;
   public static PrintWriter out;
   
   public static void main(String[] args)throws IOException{
      f = new BufferedReader(new InputStreamReader(System.in));
      out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         int m = Integer.parseInt(st.nextToken());
         
         int q1 = query(1,1);
         
         int x2 = 1+q1;
         int y2 = 1+q1;
         
         if(x2 > n || y2 > m){
            if(x2 > n){
               int q2 = query(1,y2);
               
               out.println("! " + (1+q2) + " " + y2);
            } else {
               int q2 = query(x2,1);
               
               out.println("! " + x2 + " " + (1+q2));
            }
            
         } else {
            int q2 = query(x2,y2);
            
            //either (x2-q2,y2) or (x2,y2-q2)
            int q3 = query(x2-q2,y2);
            if(q3 == 0){
               out.println("! " + (x2-q2) + " " + y2);
            } else {
               out.println("! " + x2 + " " + (y2-q2));
            }
         }
         
         out.flush();
         
      }
      
      
      
      
      out.close();
   }
   
   
   public static int query(int x, int y) throws IOException{
      out.println("? " + x + " " + y);
      out.flush();
      
      return Integer.parseInt(f.readLine());
   }
   
      
}
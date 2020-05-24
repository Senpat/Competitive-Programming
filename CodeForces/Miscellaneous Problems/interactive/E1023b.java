//Down or Right
import java.io.*;
import java.util.*;
//
public class E1023b{

   public static BufferedReader f;
   public static PrintWriter out;
   public static int n;
   
   public static void main(String[] args)throws IOException{
      f = new BufferedReader(new InputStreamReader(System.in));
      out = new PrintWriter(System.out);
      
      n = Integer.parseInt(f.readLine());
      
      int x;
      int y;
      
      
      StringBuilder sbf = new StringBuilder();
      //get to diagonal
      x = 1;
      y = 1;
      while(x+y <= n){
         if(in(x,y+1) && query(x,y+1,n,n)){
            y++;
            sbf.append("R");
         } else {
            x++;
            sbf.append("D");
         }
      }
      
      StringBuilder sbb = new StringBuilder();
      x = n;
      y = n;
      while(x+y >= n+2){
         if(in(x-1,y) && query(1,1,x-1,y)){
            x--;
            sbb.append("D");
         } else {
            y--;
            sbb.append("R");
         }
      }
      
      out.println("! " + sbf + sbb.reverse());
      
      
      
      
      
      
      
      
      
      
      out.close();
   }
   
   public static boolean in(int x, int y){
      return x >= 1 && x <= n && y >= 1 && y <= n;
   }
   
   public static boolean query(int a, int b, int c, int d) throws IOException{
      out.println("? " + a + " " + b + " " + c + " " + d);
      out.flush();
      
      String s = f.readLine();
      return s.equals("YES");
   }
   
      
}
//make sure to make new file!
import java.io.*;
import java.util.*;
//semi-t
public class D1088{
   
   public static BufferedReader f;
   public static PrintWriter out;
   
   public static void main(String[] args)throws IOException{
      f = new BufferedReader(new InputStreamReader(System.in));
      out = new PrintWriter(System.out);
      
      boolean largera = query(0,0)==1;
      
      int cura = 0;
      int curb = 0;
      
      int i = 1<<29;
      
      while(i > 0){
         int x1 = query(cura+i,curb);
         int x2 = query(cura,curb+i);
         
         if(x1 != x2){
            if(x1 == -1){
               cura += i;
               curb += i;
            }
         } else {
            if(largera){
               cura += i;
            } else {
               curb += i;
            }
            if(x1 == 1){
               largera = true;
            } else {
               largera = false;
            }
         }
         
         i >>= 1;
      }
      
      out.println("! " + cura + " " + curb);
      
      
      
      
      
      
      
      
      out.close();
   }
   
   public static int query(int a, int b)throws IOException{
      out.println("? " + a + " " + b);
      out.flush();
      
      return Integer.parseInt(f.readLine());
   }
   
      
}
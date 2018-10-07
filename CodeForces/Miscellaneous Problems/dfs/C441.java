//Valeria and Tubes
import java.io.*;
import java.util.*;

public class C441{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      int t = Integer.parseInt(st.nextToken());
      
      int curx = 1;
      int cury = 1;
      int xdir = 1;
      
      for(int k = 0; k < t-1; k++){
         out.print("2 " + cury + " " + curx);
         if(curx == m && xdir == 1){
            cury++;
            xdir = -1;
         } else if(curx == 0 && xdir == -1){
            cury++;
            xdir = 1;
         } else { 
            curx += xdir;
         }
         out.print(" " + cury + " " + curx + "\n");
         if(curx == m && xdir == 1){
            cury++;
            xdir = -1;
         } else if(curx == 1 && xdir == -1){
            cury++;
            xdir = 1;
         } else { 
            curx += xdir;
         }
      }
      
      //print last
      int lastsize = n*m-2*(t-1);
      out.print(lastsize);
      
      for(int k = 0; k < lastsize; k++){
         out.print(" " + cury + " " + curx);
         if(curx == m && xdir == 1){
            cury++;
            xdir = -1;
         } else if(curx == 1 && xdir == -1){
            cury++;
            xdir = 1;
         } else { 
            curx += xdir;
         }
      }
      
      
      
      
      
      out.close();
   }
   
}
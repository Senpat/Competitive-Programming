//make sure to make new file!
import java.io.*;
import java.util.*;

public class C575{

   public static int MAX = 100000;
   public static int MIN = -100000;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int q = Integer.parseInt(f.readLine());
      
      for(int t = 0; t < q; t++){
      
         int n = Integer.parseInt(f.readLine());
         
         
         int greaterx = MIN;
         int lesserx = MAX;
         int greatery = MIN;
         int lessery = MAX;
         
         
         for(int k = 0; k < n; k++){
         
            StringTokenizer st = new StringTokenizer(f.readLine());
         
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            
            int newgx  = MIN;
            int newlx  = MAX;
            int newgy  = MIN;
            int newly  = MAX;
            
            if(a == 0) newgx = x;
            if(b == 0) newly = y;
            if(c == 0) newlx = x;
            if(d == 0) newgy = y;
            
            greaterx = Math.max(greaterx,newgx);
            lesserx = Math.min(lesserx,newlx);
            greatery = Math.max(greatery,newgy);
            lessery = Math.min(lessery,newly);
            
            
         }
         
         if(lesserx < greaterx || lessery < greatery){
            out.println(0);
         } else {
            out.println("1 " + greaterx + " " + greatery);
         }
      
      }
      
      
      
      
      out.close();
   }
   
      
}
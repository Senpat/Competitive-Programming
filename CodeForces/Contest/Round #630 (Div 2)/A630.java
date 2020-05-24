//make sure to make new file!
import java.io.*;
import java.util.*;

public class A630{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
         int c = Integer.parseInt(st.nextToken());
         int d = Integer.parseInt(st.nextToken());
         
         st = new StringTokenizer(f.readLine());
      
         int x = Integer.parseInt(st.nextToken());
         int y = Integer.parseInt(st.nextToken());
         int x1 = Integer.parseInt(st.nextToken());
         int y1 = Integer.parseInt(st.nextToken());
         int x2 = Integer.parseInt(st.nextToken());
         int y2 = Integer.parseInt(st.nextToken());
         
         if(x>=x1 && x <= x2 && y >= y1 && y <= y2){
            //if((((x-a)>=x1 && (x-a)<=x2) || ((x+b)>=x1 && (x+b)<=x2)) && (((y-c)>=y1 && (y-c)<=y2) || ((y+d)>=y1 && (y+d)<=y2))){
            if((in(x-a+b,x1,x2) && (in(x-1,x1,x2) || in(x+1,x1,x2) || (a==0&&b==0))) && (in(y-c+d,y1,y2) && (in(y-1,y1,y2) || in(y+1,y1,y2) || (c==0&&d==0)))){
               out.println("Yes");
            } else {
               out.println("No");
            }
         } else {
            out.println("No");
         }
      }
      
      
      out.close();
   }
   
   public static boolean in(int i, int x1, int x2){      
      return i >= x1 && i <= x2;
   }
      
}
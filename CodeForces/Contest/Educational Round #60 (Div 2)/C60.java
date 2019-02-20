//make sure to make new file!
import java.io.*;
import java.util.*;

public class C60{
   
   public static int MAX = 20005;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      out.println("start");
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int x1 = Integer.parseInt(st.nextToken());
      int y1 = Integer.parseInt(st.nextToken());
      
      st = new StringTokenizer(f.readLine());
      
      int x2 = Integer.parseInt(st.nextToken());
      int y2 = Integer.parseInt(st.nextToken());
      
      int n = Integer.parseInt(f.readLine());
      
      String s = f.readLine();
      
      int[] array = new int[4];                  //0-U 1-D 2-L 3-R
      
      for(int k = 0; k < n; k++){
         if(s.charAt(k) == 'U'){
            array[0]++;
         }
         if(s.charAt(k) == 'D'){
            array[1]++;
         }
         if(s.charAt(k) == 'L'){
            array[2]++;
         }
         if(s.charAt(k) == 'R'){
            array[3]++;
         }
      }
      
      int l = 0;
      int r = MAX;
      out.println(l + " " + r);
      while( l < r ){
         int mid = (l+r-1)/2;
         out.println(l + " " + r);
         int curx = x1;
         int cury = y1;
         
         curx += array[3]*(mid/n);
         curx -= array[2]*(mid/n);
         cury += array[0]*(mid/n);
         cury -= array[1]*(mid/n);
         
         for(int k = 0; k < mid%n; k++){
            if(s.charAt(k) == 'U'){
               cury++;
            }
            if(s.charAt(k) == 'D'){
               cury--;
            }
            if(s.charAt(k) == 'L'){
               curx--;
            }
            if(s.charAt(k) == 'R'){
               curx++;
            }
         }
         
         int mand = Math.abs(curx-x2) + Math.abs(cury-y2);
         out.println(mand);
         if(mand == mid){
            out.println("hi");
            out.println(mand);
            System.exit(0);
         } else if (mand > mid){
            l = mid+1;
         } else {
            r = mid-1;
         }
         
         
      }
      
      out.println(-1);
         
      out.close();
   }
   
      
}
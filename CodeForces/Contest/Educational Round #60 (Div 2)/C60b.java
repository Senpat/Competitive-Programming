//make sure to make new file!
import java.io.*;
import java.util.*;

public class C60b{
   
   public static long MAX = 100000000000005L;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      //System.out.println("start");
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      long x1 = Long.parseLong(st.nextToken());
      long y1 = Long.parseLong(st.nextToken());
      
      st = new StringTokenizer(f.readLine());
      
      long x2 = Long.parseLong(st.nextToken());
      long y2 = Long.parseLong(st.nextToken());
      
      int n = Integer.parseInt(f.readLine());
      
      String s = f.readLine();
      
      long[] array = new long[4];                  //0-U 1-D 2-L 3-R
      
      HashSet<Character> seen = new HashSet<Character>();
      
      for(int k = 0; k < n; k++){
         if(s.charAt(k) == 'U'){
            array[0]++;
            seen.add('U');
         }
         if(s.charAt(k) == 'D'){
            array[1]++;
            seen.add('D');
         }
         if(s.charAt(k) == 'L'){
            array[2]++;
            seen.add('L');
         }
         if(s.charAt(k) == 'R'){
            array[3]++;
            seen.add('R');
         }
      }
      
      long dy = y2-y1;
      long dx = x2-x1;
      
      if(!possible(dx,dy,seen)){
         System.out.println(-1);
         System.exit(0);
      }
      
      
      
      long l = 0;
      long r = MAX-1;
      //out.println(l + " " + r);
      long min = MAX;
      
      HashSet<Long> seenmid = new HashSet<Long>();
      
      while( l < r ){
         long mid = (l+r-1)/2;
         
         if(seenmid.contains(mid)) break;
         seenmid.add(mid);
         
         //out.println(l + " " + r);
         long curx = x1;
         long cury = y1;
         
         curx += array[3]*(mid/(long)n);
         curx -= array[2]*(mid/(long)n);
         cury += array[0]*(mid/(long)n);
         cury -= array[1]*(mid/(long)n);
         
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
         
         long mand = Math.abs(curx-x2) + Math.abs(cury-y2);
         //out.println(mand);
         /*if(mand == mid){
            System.out.println("hi1");
            System.out.println(mand);
            System.exit(0);
         } else */if (mand > mid){
            l = mid+1;
         } else {
            r = mid-1;
            min = Math.min(min,mid);
         }
         
         
      }
      //out.println("hi");
      if(min == MAX) out.println(-1);
      else out.println(min);
      
      out.close();
   }
   
   public static boolean possible(long dx, long dy, HashSet<Character> seen){
      if(dy > Math.abs(dx) && !seen.contains('U')){
         return false;
      }
      if(dy < -1*Math.abs(dx) && !seen.contains('D')){
         return false;
      }
      if(dx > Math.abs(dy) && !seen.contains('R')){
         return false;
      }
      if(dx < -1*Math.abs(dy) && !seen.contains('L')){
         return false;
      }
      if(dy > 0 && dy == dx && !seen.contains('U') && !seen.contains('R')){
         return false;
      }
      if(dy < 0 && dy == dx && !seen.contains('D') && !seen.contains('L')){
         return false;
      }
      if(dy > 0 && dy == -1*dx && !seen.contains('U') && !seen.contains('L')){
         return false;
      }
      if(dy < 0 && dy == -1*dx && !seen.contains('D') && !seen.contains('R')){
         return false;
      }
      return true;
   }
      
}
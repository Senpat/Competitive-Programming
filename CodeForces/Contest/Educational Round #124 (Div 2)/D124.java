//make sure to make new file!
import java.io.*;
import java.util.*;

public class D124{

   public static int mul2 = 18;
   public static int mask = (1 << 18)-1;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
         
      
      int n = Integer.parseInt(f.readLine());
      
      long[] array = new long[n];
      HashSet<Long> hset = new HashSet<Long>();
         
      for(int k = 0; k < n; k++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
         
         long a = Long.parseLong(st.nextToken());
         long b = Long.parseLong(st.nextToken());
            
         array[k] = hash(a,b);
         hset.add(array[k]);
      }
         
         
         
      StringJoiner sj = new StringJoiner("\n");
      
      long[] dx = new long[]{-1,1,1,-1};
      long[] dy = new long[]{-1,-1,1,1};
      
      for(int q = 0; q < n; q++){
         
         long curx = getx(array[q]);
         long cury = gety(array[q]);
         
         
         long dist = 1;
         long answer = -1;
         while(answer == -1){
            //check all points on that distance
            
            long checkx = curx;
            long checky = cury+dist;
            
            outer:
            for(int side = 0; side < 4; side++){
               for(int k = 0; k < dist; k++){
                  if(!hset.contains(hash(checkx,checky))){
                     answer = hash(checkx,checky);
                     break outer;
                  }
                  
                  checkx += dx[side];
                  checky += dy[side];
               }
            }
            
            dist++;
         }
         
         sj.add("" + getx(answer) + " " + gety(answer));
      }
      
      out.println(sj.toString());  
      
      
      
      
      
      out.close();
   }
   
   public static long hash(long x, long y){
      return (x << mul2) + y;
   }
   
   public static long getx(long x){
      return x >> mul2;
   }
   
   public static long gety(long x){
      return x & mask;
   }
      
}
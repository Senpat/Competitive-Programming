//make sure to make new file!
import java.io.*;
import java.util.*;

public class C552{

   public static int[] days = {0,1,2,0,2,1,0,0,1,2,0,2,1,0};
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int ff = Integer.parseInt(st.nextToken());
      int rs = Integer.parseInt(st.nextToken());
      int cs = Integer.parseInt(st.nextToken());
      
      
      
      //calc weeks
      int weeks = Math.min(ff/3,Math.min(rs/2,cs/2));
      ff-=weeks*3;
      rs-=weeks*2;
      cs-=weeks*2;
      
      //bf which day
      int max = 0;
      for(int k = 0; k < 7; k++){
         max = Math.max(max,calc(k,ff,rs,cs));
      }
      
      int answer = weeks*7 + max;
      out.println(answer);
      
      
      out.close();
   }
   
   public static int calc(int i,int ff, int rs, int cs){
      int count = 0;
      int[] array = {ff,rs,cs};
      while(true){
         array[days[i+count]]--;
         if(array[days[i+count]] < 0) return count;
         count++;
      }
   }
   
      
}
//make sure to make new file!
import java.io.*;
import java.util.*;

public class J{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int xs = Integer.parseInt(st.nextToken());
      int ys = Integer.parseInt(st.nextToken());
      int xt = Integer.parseInt(st.nextToken());
      int yt = Integer.parseInt(st.nextToken());
      
      StringJoiner sj = new StringJoiner("\n");
      //go to 0 0
      for(int k = 0; k < xs; k++){
         sj.add("up");
      }
      for(int k = 0; k < ys; k++){
         sj.add("right");
      }
      
      //zig zag
      for(int k = 0; k <= 30; k++){
         if(k%2 == 0){
            for(int j = 0; j < 30; j++){
               sj.add("right");
            }
         } else {
            for(int j = 0; j < 30; j++){
               sj.add("left");
            }
         }
         if(k < 30){
            sj.add("up");
         }
      }
      
      //go from 30,30 to xt, yt
      for(int k = 0; k < 30-xt; k++){
         sj.add("left");
      }
      for(int k = 0; k < 30-yt; k++){
         sj.add("down");
      }
      
      out.println(sj.toString());
      
      
      
      
      
      
      
      
      out.close();
   }
   
      
}
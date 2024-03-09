//make sure to make new file!
import java.io.*;
import java.util.*;

public class Jb{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      String up = "up";
      String down = "down";
      String left = "left";
      String right = "right";
         
      int BIG = 50;
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int xs = Integer.parseInt(st.nextToken());
      int ys = Integer.parseInt(st.nextToken());
      int xt = Integer.parseInt(st.nextToken());
      int yt = Integer.parseInt(st.nextToken());
      
      StringJoiner sj = new StringJoiner("\n");
      //go to xs, -1
      for(int k = 0; k < xs+1; k++){
         sj.add(left);
      }
      
      //go "around"
      sj.add(up);
      for(int k = 0; k < BIG; k++){
         sj.add(left);
      }
      sj.add(down);
      for(int k = 0; k < BIG; k++){
         sj.add(right);
      }
      
      //go to -1,-1
      for(int k = 0; k < ys+1; k++){
         sj.add(down);
      }
      
      //make all vals >= yt
      for(int col = 0; col <= 30; col++){
         sj.add(right);
         for(int k = 0; k < yt; k++){
            sj.add(up);
         }
         for(int k = 0; k < yt; k++){
            sj.add(down);
         }
      }
      
      //you are at 30, -1
      //go to -1, yt
      for(int k = 0; k < 31; k++){
         sj.add(left);
      }
      for(int k = 0; k < yt+1; k++){
         sj.add(up);
      }
      
      for(int k = BIG; k >= 0; k--){
         //at -1, yt
         for(int j = 0; j < BIG; j++){
            sj.add(right);
         }
         sj.add(down);
         for(int j = 0; j < BIG; j++){
            sj.add(right);
         }
         sj.add(up);
         for(int j = 0; j < BIG; j++){
            sj.add(left);
         }
         for(int j = 0; j < BIG; j++){
            sj.add(left);
         }
         
         //push the rows down
         //at -1, yt
         //go to -1, k higher
         for(int j = 0; j < k; j++){
            sj.add(up);
         }
         for(int j = 0; j < BIG; j++){
            sj.add(right);
            sj.add(down);
            sj.add(up);
         }
         for(int j = 0; j < BIG; j++){
            sj.add(left);
         }
         for(int j = 0; j < k; j++){
            sj.add(down);
         }
      }
      
      out.println(sj.toString());
      
      
      
      
      
      
      out.close();
   }
   
      
}
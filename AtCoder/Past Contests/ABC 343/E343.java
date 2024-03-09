//make sure to make new file!
import java.io.*;
import java.util.*;

public class E343{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int v1 = Integer.parseInt(st.nextToken());
      int v2 = Integer.parseInt(st.nextToken());
      int v3 = Integer.parseInt(st.nextToken());
      /*
      if(v1 + 2*v2 + 3*v3 != 343*3){
         out.println("No");
         out.close();
         return;
      }*/
      
      //first cube at 0,0,0
      for(int x1 = 0; x1 <= 14; x1++) for(int y1 = 0; y1 <= 14; y1++) for(int z1 = 0; z1 <= 14; z1++)
      for(int x2 = -7; x2 <= 14; x2++) for(int y2 = -7; y2 <= 14; y2++) for(int z2 = -7; z2 <= 14; z2++){
      
         //overlap of all 3
         int minx = Math.max(x1,x2);
         int maxx = 7+Math.min(0,Math.min(x1,x2));
         int miny = Math.max(y1,y2);
         int maxy = 7+Math.min(0,Math.min(y1,y2));
         int minz = Math.max(z1,z2);
         int maxz = 7+Math.min(0,Math.min(z1,z2));
         
         int o3 = 0;
         if(maxx > minx && maxy > miny && maxz > minz) o3 = (maxx-minx) * (maxy-miny) * (maxz-minz);
         
         int o2 = o2(0,0,0,x1,y1,z1) + o2(0,0,0,x2,y2,z2) + o2(x1,y1,z1,x2,y2,z2) - 3*o3;
         
         int o1 = 3*343 - 2*o2 - 3*o3;
         
         if(o3 == v3 && o2 == v2 && o1 == v1){
            out.println("Yes");
            out.println("0 0 0 " + x1 + " " + y1 + " " + z1 + " " + x2 + " " + y2 + " " + z2);
            out.close();
            return;
         }
      }
      
      
      out.println("No");
      
      
      
      
      
      
      out.close();
   }
   
   public static int o2(int x1, int y1, int z1, int x2, int y2, int z2){
      int minx = Math.max(x1,x2);
      int maxx = 7+Math.min(x1,x2);
      int miny = Math.max(y1,y2);
      int maxy = 7+Math.min(y1,y2);
      int minz = Math.max(z1,z2);
      int maxz = 7+Math.min(z1,z2);
      
      if(maxx > minx && maxy > miny && maxz > minz) return (maxx - minx) * (maxy - miny) * (maxz - minz);
      return 0;
   }
   
      
}
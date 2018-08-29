/*
TASK: split
LANG: JAVA
*/
//not done
import java.io.*;
import java.util.*;

class split{
   
   public static void main(String[] args) throws IOException{
      BufferedReader f = new BufferedReader(new FileReader("split.in"));
      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("split.out")));
      
      int n = Integer.parseInt(f.readLine());
      
      int[] x = new int[n];
      int[] y = new int[n];
      
      int minx = Integer.MAX_VALUE;
      int miny = Integer.MAX_VALUE;
      int maxx = 0;
      int maxy = 0;
      
      for(int k = 0; k < n; k++){
         StringTokenizer st = new StringTokenizer(f.readLine());
         x[k] = Integer.parseInt(st.nextToken());
         y[k] = Integer.parseInt(st.nextToken());
         minx = Math.min(minx,x[k]);
         maxx = Math.max(maxx,x[k]);
         miny = Math.min(miny,y[k]);
         maxy = Math.max(maxy,y[k]);
      }
      
      
      out.close();
   }
}
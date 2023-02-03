/*
TASK: ratios
LANG: JAVA
*/

import java.io.*;
import java.util.*;

class ratios{
   
   public static void main(String[] args) throws IOException{
      BufferedReader f = new BufferedReader(new FileReader("ratios.in"));
      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("ratios.out")));
      
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int xt = Integer.parseInt(st.nextToken());
      int yt = Integer.parseInt(st.nextToken());
      int zt = Integer.parseInt(st.nextToken());
      
      int[] x = new int[3];
      int[] y = new int[3];
      int[] z = new int[3];
      
      for(int k = 0; k < 3; k++){
         st = new StringTokenizer(f.readLine());
         
         x[k] = Integer.parseInt(st.nextToken());
         y[k] = Integer.parseInt(st.nextToken());
         z[k] = Integer.parseInt(st.nextToken());
      }
      
      int minsum = Integer.MAX_VALUE;
      int a = -1;
      int b = -1;
      int c = -1;
      int d = -1;
            
      for(int k = 0; k <= 100; k++){
         for(int j = 0; j <= 100; j++){
            for(int h = 0; h <= 100; h++){
               if(k == 0 && j == 0 && h == 0) continue;
               
               int curx = x[0]*k + x[1]*j + x[2]*h;
               int cury = y[0]*k + y[1]*j + y[2]*h;
               int curz = z[0]*k + z[1]*j + z[2]*h;
               
               int mul = 1;
               if(xt == 0) {
                  if(curx != 0) continue;
               } else {
                  if(curx % xt == 0) mul = curx/xt;
                  else continue;
               }
               if(yt == 0) {
                  if(cury != 0) continue;
               } else {
                  if(cury % yt == 0) mul = cury/yt;
                  else continue;
               }
               if(zt == 0) {
                  if(curz != 0) continue;
               } else {
                  if(curz % zt == 0) mul = curz/zt;
                  else continue;
               }
               
               
               if(xt*mul == curx && yt*mul == cury && zt*mul == curz){
                  int sum = k+j+h;
                  if(minsum > sum){
                     minsum = sum;
                     a = k;
                     b = j;
                     c = h;
                     d = curx/xt;
                  }
               }
            }   
         }
      }
      
      if(minsum == Integer.MAX_VALUE){
         out.println("NONE");
      } else {
         out.println(a + " " + b + " " + c + " " + d);
      }
      
      out.close();
   }
      
}
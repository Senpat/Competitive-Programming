/*
TASK: fence9
LANG: JAVA
*/

import java.io.*;
import java.util.*;

class fence9{
   
   public static void main(String[] args) throws IOException{
      BufferedReader f = new BufferedReader(new FileReader("fence9.in"));
      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("fence9.out")));
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      long x = Long.parseLong(st.nextToken());
      long y = Long.parseLong(st.nextToken());
      long p = Long.parseLong(st.nextToken());
      
      long answer = 0L;
      if(p >= x){
         //first do 0 to x, then do x to p
         for(long k = 1; k < x; k++){
            long upper = y*k/x;
            if((y*k)%x == 0L){
               upper--;
            }
            upper = Math.max(0,upper);
            answer += upper;
            //System.out.println(k + " " + upper);
         }
         
         //make sure vertical line containing x is not on the edge of the triangle
         if(x != 0 && x != p){
            answer += y-1;
            //System.out.println(x + " " + (y-1));
         }
         
         long dx = p-x;
         long dy = y;
         for(long k = x+1; k < p; k++){
            long cury = -(dy*(k-x)/dx) + y;
            cury--;
            answer += Math.max(0,cury);
            //System.out.println(k + " " + cury);
         }
      } else {
         for(int k = 1; k <= p; k++){
            long upper = y*k/x;
            if((y*k)%x == 0L){
               upper--;
            }
            upper = Math.max(0,upper);
            answer += upper;
         }
         
         long dx = x-p;
         long dy = y;
         for(long k = p+1; k < x; k++){
            //highest point under upper curve
            long upper = y*k/x;
            if((y*k)%x == 0L){
               upper--;
            }
            
            //highest point under or on lower curve
            long cury = dy*(k-p)/dx;
            answer += Math.max(0,upper-cury);
         }
      }
      
      out.println(answer);
        
      out.close();
   }
      
}
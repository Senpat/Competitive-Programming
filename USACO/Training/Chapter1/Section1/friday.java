/*
USER: patrickgzhang
LANG: JAVA
TASK: friday
*/

import java.io.*;
import java.util.*;

class friday{
   public static void main(String[] args) throws IOException{
      BufferedReader f = new BufferedReader(new FileReader("friday.in"));
      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("friday.out")));
      
      int[] changesnl = {3,3,0,3,2,3,2,3,3,2,3,2};
      int[] changesyl = {3,3,1,3,2,3,2,3,3,2,3,2};
      
      int[] days = new int[7];
      
      for(int k = 0; k < days.length; k++){
         days[k] = 0;
      }
      
      int cur = -3;
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      int np = Integer.parseInt(st.nextToken());
      for(int k = 0; k < np; k++){
         for(int j = 0; j < changesnl.length; j++){
            if((1900+k)%4 == 0 && (1900 + k)%100 != 0){
               cur += changesyl[j];
            } else if((1900+k)%400 == 0){
               cur += changesyl[j];
            } else {
               cur += changesnl[j];
            }
            days[cur%7]++;
         }
      }
      
      for(int k = 0; k < days.length; k++){
         out.print(days[k]);
         if(k < days.length-1){
            out.print(" ");
         }
      }
      out.println();
      out.close();
   }
}
      
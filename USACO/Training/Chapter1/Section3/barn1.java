/*
USER: patrickgzhang
TASK: barn1
LANG: JAVA
*/

import java.io.*;
import java.util.*;

class barn1{

   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new FileReader("barn1.in"));
      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("barn1.out")));
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int m = Integer.parseInt(st.nextToken());
      int s = Integer.parseInt(st.nextToken());
      int c = Integer.parseInt(st.nextToken());
      
      boolean[] cowstall = new boolean[s];
      boolean[] covered = new boolean[s];
      
      for(int k = 0; k < c; k++){
         cowstall[Integer.parseInt(f.readLine())-1] = true;
      }
      Arrays.fill(covered,true);
      
      int counter = 0;
      while(cowstall[counter] == false){
         covered[counter] = false;
         counter++;
      }
      
      counter = s-1;
      while(cowstall[counter] == false){
         covered[counter] = false;
         counter--;
      }
      
      for(int k = 1; k < m; k++){
         int maxlength = 0;            //calculate where largest gap is
         int maxstart = 0;
         
         int startindex = -1;
         int curlength = 0;
         for(int j = 0; j < s; j++){
            if(!cowstall[j] && covered[j]){
               if(curlength == 0){
                  startindex = j;
                  //System.out.println(startindex);
               }
               curlength++;
            } else {
               if(curlength > maxlength){
                  maxstart = startindex;
                  maxlength = curlength;
               }
               curlength = 0;
            }
         }
         System.out.println(maxstart + " " + maxlength);
         
         for(int j = maxstart; j < maxstart + maxlength;j++){
            covered[j] = false;
         }
      }
      
      int count = 0;
      for(int k = 0; k < s; k++){
         if(covered[k]){
            count++;
         }
      }
      printarray(cowstall);
      printarray(covered);
      
      
      System.out.println(count);
      out.println(count);
      out.close();
   }
   
   public static void printarray(boolean[] array){
      for(int k = 0; k < array.length; k++){
         System.out.print(" " + k + array[k]);
      }
      System.out.println();
   }
   
}
         
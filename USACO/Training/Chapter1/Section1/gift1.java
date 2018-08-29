/*
ID: patrickgzhang
LANG: JAVA
TASK: gift1
*/

import java.util.*;
import java.io.*;

class gift1{
   public static void main(String[] args) throws IOException{
      BufferedReader f = new BufferedReader(new FileReader("gift1.in"));
      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("gift1.out")));
      
      StringTokenizer st1 = new StringTokenizer(f.readLine());
      int num = Integer.parseInt(st1.nextToken());
      String[] names = new String[num];
      int[] money = new int[num];
      
      for(int k = 0; k < num; k++){
         StringTokenizer st2 = new StringTokenizer(f.readLine());
         names[k] = st2.nextToken();
         money[k] = 0;
      }
      while(f.ready()){
      StringTokenizer st3 = new StringTokenizer(f.readLine());
      
         String first = st3.nextToken();
         int firstindex = Arrays.asList(names).indexOf(first);
         StringTokenizer st4 = new StringTokenizer(f.readLine());
         int m1 = Integer.parseInt(st4.nextToken());
         int m2 = Integer.parseInt(st4.nextToken());
         for(int i = 0; i < m2; i++){
            StringTokenizer st5 = new StringTokenizer(f.readLine());
            if(m2 != 0){
               money[Arrays.asList(names).indexOf(st5.nextToken())] += m1/m2;
            }
         }
         money[firstindex] -= m1;
         if(m2 != 0){
         money[firstindex] += m1%m2;
         }
         for(int k = 0; k < money.length; k++){
            System.out.print(money[k] + " ");
         }
         System.out.println();
         //st3 = new StringTokenizer(f.readLine());
      }
      
      for(int k = 0 ;k < names.length; k++){
         out.println(names[k] + " " + money[k]);
      }
      out.close();
   }
}
/*
USER: patrickgzhang
TASK: crypt1
LANG: JAVA
*/

import java.io.*;
import java.util.*;

class crypt1{

   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new FileReader("crypt1.in"));
      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("crypt1.out")));
      
      int n = Integer.parseInt(f.readLine());
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      HashSet<Integer> hs = new HashSet<Integer>();
      
      for(int k = 0; k < n; k++){
         hs.add(Integer.parseInt(st.nextToken()));
      }
      
      int count = 0;
      for(int k = 100; k <=999; k++){
         if(has(k,hs)){
            for(int d = 0; d <= 9; d++){
               if(hs.contains(d)){
                  for(int e = 0; e <= 9; e++){
                     if(hs.contains(e)){
                        if(has(k*d,hs) && k*d < 1000 && has(k*e,hs) && k*e < 1000 && has(k*(d*10+e),hs) && k*(d*10+e) < 10000){
                           count++;
                        }
                     }
                  }
               }
            }
         }
      }
      
      out.println(count);
      out.close();
      
      
   }
   
   public static boolean has(int k, HashSet<Integer> hs){
      if(k<1000)
         return hs.contains(k/100) && hs.contains((k-(k/100)*100)/10) && hs.contains((k-(k/10)*10));
      if(k<10000)
         return hs.contains(k/1000) && hs.contains((k-(k/1000)*1000)/100) && hs.contains((k-(k/100)*100)/10) && hs.contains((k-(k/10)*10));
      return hs.contains(k/10000) && hs.contains((k-(k/10000)*10000)/1000) && hs.contains((k-(k/1000)*1000)/100) && hs.contains((k-(k/100)*100)/10) && hs.contains((k-(k/10)*10));
   
   }
   
}
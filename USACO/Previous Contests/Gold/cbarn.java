/*
TASK: cbarn
LANG: JAVA
*/
//semi-tutorial
//bad
import java.io.*;
import java.util.*;

class cbarn{
   
   public static void main(String[] args) throws IOException{
      BufferedReader f = new BufferedReader(new FileReader("cbarn.in"));
      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cbarn.out")));
      
      int n = Integer.parseInt(f.readLine());
      
      int maxind = 0;
      int maxn = 0;
      
      int[] array = new int[n];
      for(int k = 0; k < n; k++){
         array[k] = Integer.parseInt(f.readLine());
         if(array[k] > maxn){
            maxn = array[k];
            maxind = k;
         }
      }
      
      
      Queue<Integer> q = new LinkedList<Integer>();
      int count = 0;
      for(int k = 0; k < n; k++){
         int i = (maxind+k)%n;
         //add all but 1 if q is empty
         if(q.isEmpty()){
            for(int j = 0; j < array[i]; j++){
               q.add(i);
            }
         } else {
            for(int j = 0; j < array[i]; j++){
               q.add(i);
            }
            int cur = q.remove();
            cur=i-cur;
            if(cur<0) cur+=n;
            count+=Math.pow(cur,2);
         }
      }
      
      System.out.println(count);
      out.println(count);
      
      
      
      out.close();
   }
}
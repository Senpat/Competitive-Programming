/*
ID: patrickgzhang
LANG: JAVA
TASK: shuffle
*/

import java.io.*;
import java.util.*;

class shuffle{

   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new FileReader("shuffle.in"));
      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("shuffle.out")));
      
      int num = Integer.parseInt(f.readLine());
      
      int[] initial = new int[num];
      int[] one = new int[num];
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      for(int k = 0; k < num; k++){
         initial[k] = Integer.parseInt(st.nextToken())-1;
         one[initial[k]]++;
      }
      
      int answer = 0;
      LinkedList<Integer> q = new LinkedList<Integer>();
      
      for(int k = 0; k < num; k++){
         if(one[k] == 0){
            q.add(one[k]);
            answer++;
         }
      }
      
      while(!q.isEmpty()){
         int curr = q.removeFirst();
         if(--one[curr]==0){
            answer++;
            q.add(initial[curr]);
         }
      }
      
      out.println(answer);
      out.close();
   }
}
            
         
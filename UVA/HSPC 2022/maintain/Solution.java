//make sure to make new file!
import java.io.*;
import java.util.*;

public class Solution{
   
   public static void main(String[] args)throws IOException{
      //BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      //PrintWriter out = new PrintWriter(System.out);
      BufferedReader f = new BufferedReader(new FileReader("maintain.judge.in"));
      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("maintain.judge.out")));
      
      int Q = Integer.parseInt(f.readLine());
      
      HashMap<Integer,Integer> hmap = new HashMap<Integer,Integer>();
      int[] bits = new int[31];
      
      for(int k = 0; k < Q; k++){
         StringTokenizer st = new StringTokenizer(f.readLine());
         
         char ch = st.nextToken().charAt(0);
         int a = Integer.parseInt(st.nextToken());
         
         if(ch == '+'){
            if(hmap.containsKey(a)){
               hmap.put(a,hmap.get(a)+1);
            } else {
               hmap.put(a,1);
            }
         } else {
            if(!hmap.containsKey(a) || hmap.get(a) == 0){
               System.out.println("ALERT ALERT");
            } else {
               hmap.put(a,hmap.get(a)-1);
            }
         }
         
         for(int i = 0; i <= 30; i++){
            if((a & (1 << i)) != 0){
               if(ch == '+') bits[i]++;
               else bits[i]--;
            }
         }
         
         int answer = 0;
         for(int i = 0; i <= 30; i++){
            if(bits[i] > 0){
               answer += (1 << i);
            }
         }
         /*
         if(answer == 0){
            System.out.println(k);
         }*/
         out.println(answer);
      }

      
      
      out.close();
   }
   
      
}
//Stages
import java.io.*;
import java.util.*;

public class A499{

   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int i = Integer.parseInt(st.nextToken());
      
      String s = f.readLine();
      
      PriorityQueue<Character> pq = new PriorityQueue<Character>();
      
      for(int k = 0; k < n; k++){
         pq.add(s.charAt(k));
      }
      
      char cur = pq.poll();
      int ans = ctoi(cur);
      int count = 1;
      
      for(int k = 1; k < n && count < i; k++){
         char c = pq.poll();
         if((int)c-(int)cur>=2){
            count++;
            ans+=ctoi(c);
            cur = c;
         }
      }
      
      if(count < i){
         System.out.println(-1);
      } else {
         System.out.println(ans);
      }
      
      
      
   }
   
   public static int ctoi(char c){
      return (int)c - 96;
   }    
   
}
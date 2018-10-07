//Palindrome Dance
import java.io.*;
import java.util.*;

public class A507{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int w = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());
                  
      int answer = 0;
      
      HashMap<Integer,Integer> ez = new HashMap<Integer,Integer>();
      ez.put(0,w);
      ez.put(1,b);
      
      int[] array = new int[n];
      
      st = new StringTokenizer(f.readLine());
      for(int k = 0; k < n; k++) array[k] = Integer.parseInt(st.nextToken());
      
      for(int k = 0; k < n/2; k++){
         if(array[k]==2 && array[n-1-k]==2){
            answer+=Math.min(w,b)*2;
         }
         else if(array[k]!=array[n-1-k]){
            if(array[k]==2){
               answer+=ez.get(array[n-1-k]);
            } else if(array[n-1-k]==2){
               answer+=ez.get(array[k]);
            } else {
               out.println("-1");
               out.close();
               System.exit(0);
            }
         }
      }
      
      if(n%2 == 1 && array[n/2] == 2){
         answer+=Math.min(w,b);
      }
      
      out.println(answer);
      
      out.close();
   }
   
}
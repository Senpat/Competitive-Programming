//make sure to make new file!
import java.io.*;
import java.util.*;

public class B550{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      int[] array = new int[n];
      ArrayList<Integer> odd = new ArrayList<Integer>();
      ArrayList<Integer> even = new ArrayList<Integer>();
                  
      for(int k = 0; k < n; k++){
         array[k] = Integer.parseInt(st.nextToken());
         if(array[k]%2 == 0){
            even.add(array[k]);
         } else {
            odd.add(array[k]);
         }
      }
      
      Collections.sort(even);
      Collections.sort(odd);
      
      int answer = 0;
      if(odd.size() > even.size()){
         for(int k = 0; k < odd.size()-even.size()-1; k++){
            answer += odd.get(k);
         }
      } else {
         for(int k = 0; k < even.size()-odd.size()-1; k++){
            answer += even.get(k);
         }
      }
      out.println(answer);
      
      
      
      
      
      
      
      
      
      out.close();
   }
   
      
}
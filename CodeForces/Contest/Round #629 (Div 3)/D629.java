//make sure to make new file!
import java.io.*;
import java.util.*;

public class D629{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         int n = Integer.parseInt(f.readLine());
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int[] array = new int[n];
         HashMap<Integer,Integer> freq = new HashMap<Integer,Integer>();
         //boolean repeats = false;
         for(int k = 0; k < n; k++){
            array[k] = Integer.parseInt(st.nextToken());
            if(freq.containsKey(array[k])){
               freq.put(array[k],freq.get(array[k])+1);
               //repeats = true;
            } else {
               freq.put(array[k],1);
            }
         }
         
         StringJoiner sj = new StringJoiner(" ");
         int answer = -1;
         if(freq.size() == 1){
            answer = 1;
            for(int k = 0; k < n; k++){
               sj.add("1");
            }
         }
         else if(n%2 == 0){
            answer  = 2;
            for(int k = 0; k < n/2; k++){
               sj.add("1 2");
            }
         } else {
            //find adjacent equals
            int i = -1;
            
            for(int k = 0; k < n; k++){
               if(array[k] == array[(k+1)%n]){
                  i = k;
                  break;
               }
            }
            
            if(i == n-1){
               answer = 2;
               for(int k = 0; k < n; k++){
                  sj.add(""+((k%2)+1));
               }
            }
            else if(i != -1){
               answer = 2;
               int m2 = 0;
               for(int k = 0; k < n; k++){
                  sj.add(""+((k+m2)%2+1));
                  if(k == i){
                     m2++;
                  }
               }
            } else {
               answer = 3;
               for(int k = 0; k < n-1; k++){
                  sj.add(""+((k%2)+1));
               }
               sj.add("3");
            }
         }
         
         
         out.println(answer);
         out.println(sj.toString());
      }
      
      
      
      
      out.close();
   }
   
      
}
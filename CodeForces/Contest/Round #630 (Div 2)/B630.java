//make sure to make new file!
import java.io.*;
import java.util.*;

public class B630{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         int n = Integer.parseInt(f.readLine());
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int[] array = new int[n];
         for(int k = 0; k < n; k++){
            array[k] = Integer.parseInt(st.nextToken());
         }
         
         int[] primes = {2,3,5,7,11,13,17,19,23,29,31};
         int[] answer = new int[n];
         
         boolean[] seen = new boolean[11];
         
         for(int k = 0; k < n; k++){
            for(int j = 0; j < 11; j++){
               if(array[k]%primes[j] == 0){
                  seen[j] = true;
                  answer[k] = j;
                  break;
               }
            }
         }
         
         //compress
         int[] compress = new int[11];
         int i = 0;
         for(int k = 0; k < 11; k++){
            if(seen[k]){
               compress[k] = i;
               i++;
            }
         }
         
         out.println(i);
         StringJoiner sj = new StringJoiner(" ");
         for(int k = 0; k < n; k++){
            sj.add("" + (compress[answer[k]] + 1));
         }
         out.println(sj.toString());
            

      }
      
      
      
      
      out.close();
   }
   
      
}
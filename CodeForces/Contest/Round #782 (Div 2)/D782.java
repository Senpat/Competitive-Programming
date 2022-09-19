//make sure to make new file!
import java.io.*;
import java.util.*;
//in-contest failed attempt
public class D782{
   
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

         int[] answer = new int[n];;
         int i = 0;
         int answeri = 0; 
         
         while(answeri < n){
            int streak = array[i];
            for(int k = 0; k+answeri < n && k < streak; k++){
               answer[k+answeri] = 1;
               array[k+answeri] -= (k+answeri+2);
            }
            if(answeri+streak < n) answer[answeri+streak] = 0;
            answeri += streak+1;
            
            i++;
         }  
         
         StringJoiner sj = new StringJoiner(" ");
         for(int k = 0; k < n; k++){
            sj.add("" + answer[k]);
         }
         out.println(sj.toString());
      

      }
      
      
      
      
      out.close();
   }
   
      
}
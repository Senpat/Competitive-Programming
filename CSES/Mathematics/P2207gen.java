//make sure to make new file!
import java.io.*;
import java.util.*;
//generate grundy numbers and use sprague-grundy theorem
public class P2207gen{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(new FileWriter("P2207.txt"));
      
      int N = 1000;
      
      int[] array = new int[N+1];
      array[1] = 0;
      array[2] = 0;
      for(int k = 3; k <= N; k++){
         boolean[] b = new boolean[2*N+1];
         for(int j = 1; k-j > j; j++){
            b[array[j] ^ array[k-j]] = true;
         }
         for(int j = 0; j < 2*N; j++){
            if(!b[j]){
               array[k] = j;
               break;
            }
         }
      }
      
      
      for(int k = 1; k <= N; k++){
         if(array[k] == 0){
            out.println(k + " " + array[k] + " ZERO");
         } else {
            out.println(k + " " + array[k]);
         }
      }
      
      
      
      
      
      
      out.close();
   }
   
      
}
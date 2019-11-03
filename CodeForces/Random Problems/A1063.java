//Oh Those Palindromes
import java.io.*;
import java.util.*;
//epic problem
public class A1063{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      char[] array = f.readLine().toCharArray();
      shuffleArray(array);
      Arrays.sort(array);
      
      for(int k = 0; k < n; k++){
         out.print(array[k]);
      }
      

      
      
      
      
      
      out.close();
   }
   
   public static void shuffleArray(char[] arr){
        int n = arr.length;
        Random rnd = new Random();
        for(int i=0; i<n; ++i){
            char tmp = arr[i];
            int randomPos = i + rnd.nextInt(n-i);
            arr[i] = arr[randomPos];
            arr[randomPos] = tmp;
        }   
   }
   
      
}
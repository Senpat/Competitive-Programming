//make sure to make new file!
import java.io.*;
import java.util.*;

public class C555{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int[] array = new int[n];
      for(int k = 0; k < n; k++){
         array[k] = Integer.parseInt(st.nextToken());
      }
      
      int last = -1;
      int l = 0;
      int r = n-1;
      ArrayList<Character> answer = new ArrayList<Character>();
      for(int k = 0; k < n; k++){
         if(array[l] < array[r]){
            if(array[l] < last){
               if(array[r] < last){
                  break;
               } else {
                  last = array[r];
                  answer.add('R');
                  r--;
               }
            } else {
               last = array[l];
               answer.add('L');
               l++;
            }
         } else {
            if(array[r] < last){
               if(array[l] < last){
                  break;
               } else {
                  last = array[l];
                  answer.add('L');
                  l++;
               }
            } else {
               last = array[r];
               answer.add('R');
               r--;
            }
         
         }
      }
      

      out.println(answer.size());
      for(char c : answer){
         out.print(c);
      }
      
      
      out.close();
   }
   
      
}
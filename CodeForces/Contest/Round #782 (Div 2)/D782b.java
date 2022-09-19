//make sure to make new file!
import java.io.*;
import java.util.*;
//upsolve
public class D782b{
   
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

         int[] answer = new int[n];
         Arrays.fill(answer,-1);
         
         int r = 0;
         int added = 0;
         for(int k = 0; r < n && k < n; k++){
            int i = array[k];
            if(answer[k] == -1){
               if(i == 0){
                  answer[k] = 0;
                  r++;
               } else {
                  i -= k;
                  for(int j = 0; j < i; j++){
                     answer[r] = 1;
                     added++;
                     r++;
                  }
                  if(r < n){
                     answer[r] = 0;
                     r++;
                  }
               }
            } else {
               if(answer[k] == 1) i -= k;
               
               int top = i-added;
               for(int j = 0; j < top; j++){
                  answer[r] = 1;
                  added++;
                  r++;
               }
               if(r < n){
                  answer[r] = 0;
                  r++;
               }
            }
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
//make sure to make new file!
import java.io.*;
import java.util.*;

public class B908{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         int m = Integer.parseInt(st.nextToken());
         
         int[] a = new int[n];
         st = new StringTokenizer(f.readLine());
         boolean lisg1 = false;            //has lis greater than 1
         for(int k = 0; k < n; k++){
            a[k] = Integer.parseInt(st.nextToken());
            if(k > 0 && a[k] > a[k-1]){
               lisg1 = true;
            }
         }
         
         Integer[] b = new Integer[m];
         st = new StringTokenizer(f.readLine());
         for(int k = 0; k < m; k++){
            b[k] = Integer.parseInt(st.nextToken());
         }
         Arrays.sort(b);
         
         ArrayList<Integer> answer = new ArrayList<Integer>();
         if(!lisg1){
            for(int k = 0; k < n; k++) answer.add(a[k]);
            for(int k = 0; k < m; k++) answer.add(b[k]);
            Collections.sort(answer,Collections.reverseOrder());
         } else {
            int ai = 0;
            int bi = m-1;
            
            while(ai < n || bi >= 0){
               if(ai >= n){
                  answer.add(b[bi]);
                  bi--;
               } else if(bi < 0){
                  answer.add(a[ai]);
                  ai++;
               } else {
                  if(a[ai] > b[bi]){
                     answer.add(a[ai]);
                     ai++;
                  } else {
                     answer.add(b[bi]);
                     bi--;
                  }
               }
            }
         }
         
         StringJoiner sj = new StringJoiner(" ");
         for(int i : answer){
            sj.add("" + i);
         }
         out.println(sj.toString());
         

      }
      
      
      
      
      out.close();
   }
   
      
}
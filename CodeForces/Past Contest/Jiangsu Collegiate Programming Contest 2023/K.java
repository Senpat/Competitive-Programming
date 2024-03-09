//make sure to make new file!
import java.io.*;
import java.util.*;

public class K{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      int x = Integer.parseInt(st.nextToken());
      
      if(m == 0){
         if(n <= 26){
            out.println("Yes");
            StringJoiner sj = new StringJoiner("\n");
            for(int k = 0; k < n; k++){
               StringJoiner line = new StringJoiner("");
               for(int j = 0; j < x; j++){
                  line.add("" + (char)('a'+k));
               }
               sj.add(line.toString());
            }  
            out.println(sj.toString());
         } else {
            out.println("No");
         }
      } else if(x == m){
         //n>=2
         out.println("No");
      } else {
         out.println("Yes");
         StringJoiner sj = new StringJoiner("\n");
         int added = 0;
         for(char c1 = 'a'; c1 <= 'z'; c1++){
            for(char c2 = (char)(c1+1); c2 <= 'z'; c2++){
               StringJoiner line = new StringJoiner("");
               for(int j = 0; j < m-1; j++){
                  line.add("z");
               }
               //alternate
               for(int j = m-1; j < x; j++){
                  if(j%2 == (m-1)%2){
                     line.add("" + c1);
                  } else {
                     line.add("" + c2);
                  }
               }
               sj.add(line.toString());
               
               added++;
               if(added >= n) break;
            }
            if(added >= n) break;
         }
         
         out.println(sj.toString());
      }
      
      
      
      
      
      
      
      
      out.close();
   }
   
      
}
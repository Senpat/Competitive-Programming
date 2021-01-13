//make sure to make new file!
import java.io.*;
import java.util.*;

public class A692{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         int m = Integer.parseInt(st.nextToken());
         
         Rook[] rooks = new Rook[m];
         int[] row = new int[n+1];                 //row[i] is col that rook on row i is on
         for(int k = 0; k < m; k++){
            st = new StringTokenizer(f.readLine());
      
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            
            rooks[k] = new Rook(a,b);
            row[a] = b;
         }
         
         HashSet<Integer> seen = new HashSet<Integer>();          //stores row of rook
         
         int answer = 0;
         for(int k = 0; k < m; k++){
            if(seen.contains(rooks[k].x) || rooks[k].x == rooks[k].y) continue;
            
            //get component
            int currow = rooks[k].y;
            int compsize = 1;
            seen.add(rooks[k].x);
            while(true){
               if(row[currow] == 0){
                  seen.add(currow);
                  break;
               }
               if(currow == rooks[k].x){
                  compsize++;
                  break;
               }
               if(seen.contains(currow)) break;
               
               seen.add(currow);
               currow = row[currow];
               compsize++;
            }
            
            answer += compsize;
         }
         
         out.println(answer);
      }
      
      
      
      
      out.close();
   }
   
   public static class Rook{
      int x;
      int y;
      public Rook(int a, int b){
         x = a;
         y = b;
      }
   }
   
      
}
//make sure to make new file!
import java.io.*;
import java.util.*;

public class C862{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         int m = Integer.parseInt(st.nextToken());
         
         Long[] lines = new Long[n];
         ArrayList<Long> pos = new ArrayList<Long>();
         ArrayList<Long> neg = new ArrayList<Long>();
         for(int k = 0; k < n; k++){
            lines[k] = Long.parseLong(f.readLine());
            
            if(lines[k] >= 0) pos.add(lines[k]);
            else neg.add(lines[k]);
         }
         
         Collections.sort(pos);
         Collections.sort(neg);
         
         for(int k = 0; k < neg.size(); k++){
            pos.add(neg.get(k));
         }
         
         long p1 = pos.get(0);
         long p2 = pos.get(pos.size()-1);
         
         for(int k = 0; k < m; k++){
            st = new StringTokenizer(f.readLine());
      
            long a = Long.parseLong(st.nextToken());
            long b = Long.parseLong(st.nextToken());
            long c = Long.parseLong(st.nextToken());
            
            //try p1
            if((b-p1)*(b-p1) - 4L*a*c < 0){
               out.println("YES");
               out.println(p1);
               continue;
            }
            
            //try minneg
            if((b-p2)*(b-p2) - 4L*a*c < 0){
               out.println("YES");
               out.println(p2);
               continue;
            }
            
            out.println("NO");
         }

      }
      
      
      
      
      out.close();
   }
   
      
}
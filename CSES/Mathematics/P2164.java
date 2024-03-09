//make sure to make new file!
import java.io.*;
import java.util.*;

public class P2164{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int q = Integer.parseInt(f.readLine());
      
      StringJoiner sj = new StringJoiner("\n");
      for(int t = 0; t < q; t++){
         StringTokenizer st = new StringTokenizer(f.readLine());
         int n = Integer.parseInt(st.nextToken());
         int m = Integer.parseInt(st.nextToken());
         
         //process first half
         int used1 = n/2;
         if(m <= used1){
            sj.add("" + m*2);
            continue;
         }
         
         m -= used1;
         
         int mod = 4;         //mod goes up by powers of 2
         
         int cur = 1;
         int othercur = 3;
         if(n%2 == 0 && n >= 3){
            cur = 3;
            othercur = 1;
         }
         
         while(true){
            //delete all x where x%mod = cur
            if(cur > n){
               //m must be 1
               sj.add("" + othercur);
               break;
            }
            int maxi = (n-cur)/mod;
            int used = maxi + 1;
            
            if(m <= used){
               int answer = cur + (m-1) * mod;
               sj.add("" + answer);
               break;
            }
            
            m -= used;
            
            //decide between othercur and othercur+mod
            
            if(maxi * mod + cur + mod/2 > n){
               //no extra
               cur = othercur+mod;
               othercur = othercur;
            } else {
               cur = othercur;
               othercur = othercur+mod;
            }
            
            mod <<= 1;
         }
      }
      
      out.println(sj.toString());
      
      
      
      
      
      
      
      
      out.close();
   }
   
      
}
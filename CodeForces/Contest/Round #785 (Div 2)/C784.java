//make sure to make new file!
import java.io.*;
import java.util.*;

public class C784{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      ArrayList<Integer> plist = new ArrayList<Integer>();
      
      //1 and 2 digits
      for(int k = 1; k <= 9; k++){
         plist.add(k);
         plist.add(k*11);
         
         //3 and 4 digits
         for(int j = 0; j <= 9; j++){
            plist.add(k*100+j*10+k);
            plist.add(k*1000 + j*100 + j*10 + k);
            //5 digits
            for(int h = 0; h <= 9; h++){
               plist.add(k*10000 + j*1000 + h*100 + j*10 + k);
            }
         }
         
      }
      
      Collections.sort(plist);
      
      int N = 40005;
      long MOD = 1000000007L;
      long[] precomp = new long[N];
      precomp[0] = 1L;
      for(int i : plist){
         for(int k = i; k < N; k++){
            precomp[k] = (precomp[k] + precomp[k-i] + MOD)%MOD;
         }
      }
      
      
      for(int q = 1; q <= t; q++){

         int n = Integer.parseInt(f.readLine());
      
         out.println(precomp[n]);

      }
      
      
      
      
      out.close();
   }
   
      
}
//make sure to make new file!
import java.io.*;
import java.util.*;

public class F764{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      int l = 1;
      int r = n-1;
      
      while(l != r){
         int mid = l + (r-l)/2;
         //make the mid equal to (multiple of n)-1
         int curdiv = mid/n;
         int val = (mid/n+1)*n-1-mid;
         
         out.println("+ " + val);
         out.flush();
         
         int i = Integer.parseInt(f.readLine());
         
         if(i == curdiv){
            l = l+val;
            r = mid+val;
         } else {
            l = mid+val+1;
            r = r+val;
         }
      }
      
      
      out.println("! " + l);
      
      
      
      
      
      
      out.close();
   }
   
      
}
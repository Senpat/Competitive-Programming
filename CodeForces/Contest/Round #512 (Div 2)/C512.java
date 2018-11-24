//make sure to make new file!
import java.io.*;
import java.util.*;

public class C512{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      String s = f.readLine();
      
      ArrayList<Integer> alist = new ArrayList<Integer>(n);
      
      for(int k = 0; k < n; k++){
         if(Character.getNumericValue(s.charAt(k)) > 0) alist.add(Character.getNumericValue(s.charAt(k)));
      }
      
      
      if(alist.size()==0){
         out.println("YES");
         out.close();
         System.exit(0);
      }
      
      int startsum = 0;
      for(int k = 0; k < alist.size()-1; k++){
         startsum += alist.get(k);
         
         int cursum = 0;
         for(int j = k+1; j < alist.size(); j++){
            cursum += alist.get(j);
            if(cursum == startsum){
               cursum = 0;
               if(j==alist.size()-1){
                  out.println("YES");
                  out.close();
                  System.exit(0);
               }
            } else if(cursum > startsum){
               break;
            }
         }
         
      }
      
      out.println("NO");
      
      
      
      out.close();
   }
   
}
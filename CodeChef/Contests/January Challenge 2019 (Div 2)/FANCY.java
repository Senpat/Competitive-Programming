//FANCY
import java.io.*;
import java.util.*;

public class FANCY{
   
   public static void main(String[] args)throws java.lang.Exception{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      for(int k = 0; k < n; k++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
         
         boolean bool = false;
         while(st.hasMoreTokens()){
            String s = st.nextToken();
            if(s.equals("not")){
               out.println("Real Fancy");
               bool = true;
               break;
            }
         }
         
         if(!bool){
            out.println("regularly fancy");
         }
      
      
      }
      out.close();
   }
   
}
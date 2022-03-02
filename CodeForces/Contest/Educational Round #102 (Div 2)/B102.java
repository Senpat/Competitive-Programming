//make sure to make new file!
import java.io.*;
import java.util.*;

public class B102{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
         
         char[] a = f.readLine().toCharArray();
         char[] b = f.readLine().toCharArray();
         
         int arep = findrep(a);
         int brep = findrep(b);
         
         if(equalrep(a,arep,b,brep)){
            String repstring = getrep(a,arep);
            
            int an = a.length/arep;
            int bn = b.length/brep;
            
            int lcm = an*bn/gcd(an,bn);
            
            StringBuilder sb = new StringBuilder();
            for(int k = 0; k < lcm; k++){
               sb.append(repstring);
            }
            out.println(sb.toString());
            
         } else {
            out.println(-1);
         }

      }
      
      
      
      
      out.close();
   }
   
   public static int gcd(int x, int y){
      
      if(x > y){
         if(y == 0) return x;
         return gcd(x%y,y);
      } else if(x < y){
         if(x == 0) return y;
         return gcd(x,y%x);
      }
      return x;
   }
   
   public static String getrep(char[] a, int x){
      String ret = "";
      for(int k = 0; k < x; k++) ret+=a[k];
      return ret;
   }
   
   public static boolean equalrep(char[] a, int x, char[] b, int y){
      if(x!=y) return false;
      for(int k = 0; k < x; k++) if(a[k] != b[k]) return false;
      return true;
   }
   
   public static int findrep(char[] a){
      for(int k = 1; k <= a.length; k++){
         if(a.length%k != 0) continue;
         boolean fail = false;
         for(int j = 0; j < a.length; j++){
            if(a[j] != a[j%k]) fail = true;
         }
         if(!fail) return k;
      }
      return -1;        //won't happen
   }
      
}
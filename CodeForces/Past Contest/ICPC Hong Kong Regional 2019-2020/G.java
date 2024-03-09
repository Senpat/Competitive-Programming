//make sure to make new file!
import java.io.*;
import java.util.*;

public class G{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int x = Integer.parseInt(f.readLine());
      
      if(x == 1){
         out.println("2");
         out.println("1");
         out.println("1 2");
         out.close();
         return;
      }
      
      int sqrt = 0;
      while(sqrt*sqrt <= x){
         sqrt++;
      }
      sqrt--;
      
      int a = sqrt;
      int b = sqrt;
      if(a*(b+1) <= x) b++;
      
      int rem = x-a*b;
      
      ArrayList<Integer> parent = new ArrayList<Integer>();
      parent.add(-1);
      parent.add(-1);
      
      ArrayList<Integer> c = new ArrayList<Integer>();
      c.add(-1);
      if(rem == 0){
         c.add(3);
         rem=1;
      } else {
         c.add(2);
      
         for(int k = 2; k <= rem; k++){
            parent.add(k-1);
            c.add(2);
         }
      }
      
      for(int k = rem+1; k <= rem+a; k++){
         parent.add(k-1);
         c.add(1);   
      }
      
      int p = rem;
      for(int k = rem+a+1; k <= rem+a+b; k++){
         parent.add(p);
         c.add(1);
         p = k;
      }
      
      out.println(parent.size()-1);
      StringJoiner psj = new StringJoiner(" ");
      for(int k = 2; k < parent.size(); k++){
         psj.add("" + parent.get(k));
      }
      out.println(psj.toString());
      StringJoiner csj = new StringJoiner(" ");
      for(int k = 1; k < c.size(); k++){
         csj.add("" + c.get(k));
      }
      out.println(csj.toString());
      
      
      
      
      
      out.close();
   }
   
      
}
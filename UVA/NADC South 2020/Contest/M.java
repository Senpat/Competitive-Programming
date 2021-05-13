//make sure to make new file!
import java.io.*;
import java.util.*;

public class M{

   public static BufferedReader f;
   public static PrintWriter out;
   
   public static void main(String[] args)throws IOException{
      //f = new BufferedReader(new InputStreamReader(System.in));
      f = new BufferedReader(new FileReader("mtest.in"));
      out = new PrintWriter(System.out);
      
        
      if(run()){
         out.println(1);
      } else {
         out.println(0);
      }
      
      
      
      
      
      
      
      out.close();
   }
   
   public static boolean run()throws IOException{
      //Scanner sc = new Scanner(System.in);
      ArrayList<Long> alist = new ArrayList<Long>();
      
      while(f.ready()){
         String line1 = f.readLine();
         //out.println(line1);
         StringTokenizer st = new StringTokenizer(line1);
      //while(sc.hasNextLine()){
         //StringTokenizer st = new StringTokenizer(sc.nextLine());
         while(st.hasMoreTokens()){
            String s = st.nextToken();
            
            if(s.length() > 10 || !checknumber(s)){
               return false;
            }
            
            long i = Long.parseLong(s);
            alist.add(i);
         }
      }
      
      if(alist.size() != 3) 
         return false;
      if(alist.get(0) <= 3 || alist.get(0) > 1000000000) 
         return false;
      if(alist.get(1) <= 1 || alist.get(1) > alist.get(0)) 
         return false;
      if(alist.get(2) <= 1 || alist.get(2) > alist.get(0)) 
         return false;
      
      if(!isprime(alist.get(1)) || !isprime(alist.get(2))) 
         return false;
      return alist.get(1) + alist.get(2) == alist.get(0);
   }
    
   
   public static boolean checknumber(String s){
      if(s.length() == 0) 
         return false;
      
      if(s.charAt(0) == '0') 
         return false;
      
      for(int k = 0; k < s.length(); k++){
         if(!Character.isDigit(s.charAt(k))) 
            return false;
      }
      
      return true;
   }
   
   public static boolean isprime(long x){
      
      long i = 2;
      while(i*i <= x){
         if(x*i == 0) 
            return false;
         i++;
      }
      
      return true;
   }
         
           
}
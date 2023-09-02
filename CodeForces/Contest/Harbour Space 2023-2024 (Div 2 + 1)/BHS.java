//make sure to make new file!
import java.io.*;
import java.util.*;

public class BHS{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         int m = Integer.parseInt(st.nextToken());
      
         String s = f.readLine();
         StringJoiner sj = new StringJoiner("");
            
         if(m % 2 == 1){
            //sort odds and evens
            ArrayList<Character> a1 = new ArrayList<Character>();
            ArrayList<Character> a2 = new ArrayList<Character>();
            
            for(int k = 0; k < n; k++){
               if(k % 2 == 0){
                  a1.add(s.charAt(k));
               } else {
                  a2.add(s.charAt(k));
               }
            }
            
            Collections.sort(a1);
            Collections.sort(a2);
            
            for(int k = 0; k < a1.size(); k++){
               sj.add("" + a1.get(k));
               if(k < a2.size()){
                  sj.add("" + a2.get(k));
               }
            }
         } else {
            ArrayList<Character> a = new ArrayList<Character>();
            for(int k = 0; k < n; k++){
               a.add(s.charAt(k));
            }
            
            Collections.sort(a);
            
            for(int k = 0; k < n; k++){
               sj.add("" + a.get(k));
            }
         }
         
         out.println(sj.toString());
      }
      
      
      
      
      out.close();
   }
   
      
}
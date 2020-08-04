//make sure to make new file!
import java.io.*;
import java.util.*;

public class C83{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         int m = Integer.parseInt(st.nextToken());
         
         
         
         
         String[] array = new String[n];
         
         st = new StringTokenizer(f.readLine());
         for(int k = 0; k < n; k++){
            array[k] = Long.toString(Long.parseLong(st.nextToken()),m);
            //out.println(array[k]);
         }
         
         
         if(check(n,m,array)){
            out.println("YES");
         } else {
            out.println("NO");
         }
      }
      
      
      
      
      out.close();
   }
   
   public static boolean check(int n,int m,String[] array){
      HashSet<Integer> used = new HashSet<Integer>();
         
      for(int k = 0; k < n; k++){
         int i = array[k].length()-1;
            
         for(int j = 0; j <= i; j++){
            if(array[k].charAt(j) == '0'){
               continue;
            }else if(array[k].charAt(j) == '1'){
               if(used.contains(i-j)){
                  return false;
               } else {
                  used.add(i-j);
               }
            }
            else {
               return false;
            }
         }
            
      }
      return true;
   }
         
         


   
      
}
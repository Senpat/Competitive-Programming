//make sure to make new file!
import java.io.*;
import java.util.*;
//upsolve, danny hints
public class F779{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         int m = Integer.parseInt(st.nextToken());
      
         String s = f.readLine();
         int[] array = new int[n];
         int b = 0;                          //num black
         for(int k = 0; k < n; k++){
            array[k] = Character.getNumericValue(s.charAt(k));
            if(array[k] == 1) b++;
         }
         
         if((long)b*(long)m % (long)n != 0){
            out.println(-1);
            continue;
         }
         
         int bt = (int)((long)b*(long)m / (long)n);               //black target value
         
         //check 1
         //get initial
         int curb = 0;
         for(int k = 0; k < m; k++) curb += array[k];
         if(curb == bt){
            out.println("1");
            out.println("1 " + m);
            continue;
         }
         
         boolean found = false;
         for(int k = m; k < n; k++){
            curb-=array[k-m];
            curb+=array[k];
            
            if(curb == bt){
               out.println("1");
               out.println((k-m+2) + " " + (k+1));
               found = true;
               break;
            }
         }
         
         if(found){
            continue;
         }
         
         
         for(int k = 0; k < m; k++){
            curb += array[k];
            curb -= array[n-m+k];
            
            if(curb == bt){
               out.println("2");
               out.println("1 " + (k+1));
               out.println((n-m+k+2) + " " + n);
               found = true;
               break;
            }
         }
         
         if(!found){
            out.println(-1);
         }
         
         
         
         

      }
      
      
      
      
      out.close();
   }
   
      
}
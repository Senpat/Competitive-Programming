import java.io.*;
import java.util.*;

public class lovetriangle{
   public static void main(String[] args){
      Scanner sc = new Scanner(System.in);
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(sc.nextLine());
   
      
      int[] love = new int[n];
      for(int k = 0; k < n; k++){
         love[k] = sc.nextInt();
      }
      
      boolean[] checked = new boolean[n];
      Arrays.fill(checked,false);
      
      for(int k = 0; k < n; k++){
         if(checked[k] == false){
            int first = k;
            if(love[love[love[k]-1]-1]==first+1){
               //System.out.println(k+1 + " " + (love[k]) + " " + (love[love[k]-1]));
               if(k+1 != love[k]){
                  out.print("YES");
                  out.close();
                  System.exit(0);
               } else if(k+1 == love[love[k]-1]){
                  checked[k] = true;
                  checked[love[k]-1] = true;
               }
               else {
                  checked[k] = true;
               }
            } 
            else {
               checked[k] = true;
               checked[love[k]-1] = true;
            }
         }
      }
      
      out.print("NO");
      out.close();
   }
}
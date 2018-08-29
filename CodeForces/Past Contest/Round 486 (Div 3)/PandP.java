//Round #486 (Div 3) D
//Points and Powers of 2
import java.io.*;
import java.util.*;
import java.math.*;

public class PandP{
   
   public static void main(String[] args)throws IOException{
      Scanner sc = new Scanner(System.in);
      
      int n = Integer.parseInt(sc.nextLine());
      
      int[] array = new int[n];
      int[] sort = new int[n];
      
      StringTokenizer st = new StringTokenizer(sc.nextLine());
      
      for(int k = 0; k < n; k++){
         array[k] = Integer.parseInt(st.nextToken());
         sort[k] = array[k];
      }
      
      Arrays.sort(sort);
      
      int maxdiff = sort[n-1]-sort[0];
      
      //check 3
      for(int k = 0; Math.pow(2,k)<= maxdiff; k++){
         for(int j = 1; j < n-1; j++){
            if(Arrays.binarySearch(sort,sort[j]+(int)Math.pow(2,k))>=0 && Arrays.binarySearch(sort,sort[j]-(int)Math.pow(2,k))>=0){
               System.out.println(3);
               System.out.println(sort[j] + " " + (sort[j]+(int)Math.pow(2,k)) + " " + (sort[j]-(int)Math.pow(2,k)));
               System.exit(0);
            }
         }
      }
      
      //check 2
      for(int k = 0; Math.pow(2,k)<= maxdiff; k++){
         for(int j = 0; j < n-1; j++){
            if(Arrays.binarySearch(sort,sort[j]+(int)Math.pow(2,k))>=0){
               System.out.println(2);
               System.out.println(sort[j] + " " + (sort[j]+(int)Math.pow(2,k)));
               System.exit(0);
            }
         }
      }
      System.out.println(1);
      System.out.println(sort[0]);
   }
}
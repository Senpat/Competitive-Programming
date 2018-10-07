//make sure to make new file!
import java.io.*;
import java.util.*;

public class C514b{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      
      if(n==1) out.print(1);
      else if(n==2) out.print("1 2");
      else if(n==3) out.print("1 1 3");
      else if(n==6) out.print("1 1 1 2 2 6");
      else if(n==7) out.print("1 1 1 1 2 2 6");
      else{
         
         
         HashSet<Integer> hs = new HashSet<Integer>();
         for(int k = 2; k < 21; k++){
            hs.add((int)(Math.pow(2,k) + Math.pow(2,k-1)));
            
            hs.add((int)(Math.pow(2,k) + Math.pow(2,k-1))+1);
         }
         
         
         int[] array = new int[n+1];
      
         for(int k = 1; k <= n; k++){
            array[twos(k)]++;
         }
      
         for(int k = 1; k < n; k++){
            if(k==n-1 && hs.contains(k)){
               if(k%2==0) out.print(k);
               else out.print(k-1);
            } else {
               for(int j = 0; j < array[k]; j++){
                    
                  out.print((int)Math.pow(2,(k-1)) + " ");
               }
            }
         }
      }
      
      out.close();
   }
   
   public static int twos(int x){
      int index = 1;
      while(true){
         if(x%(int)(Math.pow(2,index))!=0){
            return index;
         }
         index++;
      }
   }
}
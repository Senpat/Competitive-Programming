//make sure to make new file!
import java.io.*;
import java.util.*;

public class C673{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int[] array = new int[n];
      for(int k = 0; k < n; k++){
         array[k] = Integer.parseInt(st.nextToken());
      }
      
      long numinversions = 0L;
      int num = 0;
      HashMap<Integer,ArrayList<Integer>> hmap = new HashMap<Integer,ArrayList<Integer>>();
      for(int i = 30; i >= 1; i--){
         //see if ith decimal should be flipped
         //divide every number by 1 << i, so >> i
         //group into arraylist
         
         int ishifti1 = 1 << (i-1);
         
         
         for(int k = 0; k < n; k++){
            int shift = array[k] >> i;
            if(!hmap.containsKey(shift)){
               hmap.put(shift,new ArrayList<Integer>());
            }
            
            if((array[k] & (ishifti1)) > 0){
               hmap.get(shift).add(1);
            } else {
               hmap.get(shift).add(0);
            }
            
         }
         
         long flip = 0L;
         long noflip = 0L;
         for(int x : hmap.keySet()){
            long num0 = 0L;
            long num1 = 0L;
            for(int v : hmap.get(x)){
               if(v == 1){
                  flip += num0;
                  num1++;
               } else {
                  noflip += num1;
                  num0++;
               }
            }
         }
         
         if(flip < noflip){
            num += ishifti1;
         }
         
         numinversions += Math.min(flip,noflip);
         hmap.clear();
      }
      
      out.println(numinversions + " " + num);
   
      
      
      
      
      
      
      
      out.close();
   }
   
      
}
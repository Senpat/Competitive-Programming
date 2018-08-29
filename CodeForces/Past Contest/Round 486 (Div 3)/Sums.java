//Round #486 (Div 3) C
//Equal Sums
import java.io.*;
import java.util.*;
import java.math.*;

public class Sums{
   
   public static void main(String[] args)throws IOException{
      Scanner sc = new Scanner(System.in);
      
      int n = Integer.parseInt(sc.nextLine());
      
      int[] sums = new int[n];
      
      ArrayList<ArrayList<Integer>> lists = new ArrayList<ArrayList<Integer>>(n);
      ArrayList<ArrayList<Integer>> sorts = new ArrayList<ArrayList<Integer>>(n);
      
      Collections.fill(lists,new ArrayList<Integer>());
      Collections.fill(sorts,new ArrayList<Integer>());
      
      StringTokenizer st;
      for(int k = 0; k < n; k++){
         int m = Integer.parseInt(sc.nextLine());
         st = new StringTokenizer(sc.nextLine());
         ArrayList<Integer> temp = new ArrayList<Integer>();
         for(int j = 0; j < m; j++){
            int i = Integer.parseInt(st.nextToken());
            temp.add(i);
            sums[k]+=i;
         }
         lists.add(temp);
         Collections.sort(temp);
         sorts.add(temp);
      }
      
      for(int k = 1; k < n; k++){
         for(int j = 0; j < k; j++){
            int diff = sums[k]-sums[j];
            
            for(int x = 0; x < lists.get(k).size(); x++){
               int bs = Collections.binarySearch(sorts.get(j),lists.get(k).get(x)-diff);
               if(bs>-1){
                  //System.out.println(k + " " + j + " " + bs);
                  System.out.println("YES");
                  System.out.println((k+1) + " " + (lists.get(k).indexOf(sorts.get(k).get(x))+1));
                  System.out.println((j+1) + " " + (lists.get(j).indexOf(sorts.get(j).get(bs))+1));
                  System.exit(0);
               }
            }
         }
      }
      
      System.out.println("NO");
   }
}

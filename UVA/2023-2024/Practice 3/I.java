//make sure to make new file!
import java.io.*;
import java.util.*;

public class I{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      HashMap<Integer,Integer> hmap = new HashMap<Integer,Integer>();
      
      int[][] perm = genperms();
      
      for(int t = 0; t < n; t++){
         int[] d = new int[6];
         StringTokenizer st = new StringTokenizer(f.readLine());
         for(int k = 0; k < 6; k++){
            d[k] = Integer.parseInt(st.nextToken())-1;
         }
         
         int[] hashes = gethashes(d,perm);
         
         int found = -1;
         for(int hash : hashes){
            if(hmap.containsKey(hash)){
               found = hash;
               break;
            }
         }
         
         if(found == -1){
            hmap.put(hashes[0],1);
         } else {
            hmap.put(found,hmap.get(found)+1);
         }
         
      }
      
      int answer = 0;
      for(int i : hmap.keySet()){
         answer = Math.max(answer,hmap.get(i));
      }
      
      out.println(answer);
      
      
      
      
      
      
      
      
      out.close();
   }
   
   public static int[] gethashes(int[] dice, int[][] perm){
      int[] hashes = new int[24];
      
      for(int k = 0; k < 24; k++){
         int[] curd = new int[6];
         for(int j = 0; j < 6; j++){
            curd[j] = dice[perm[k][j]];
         }
         
         hashes[k] = gethash(curd);
      }
      
      return hashes;
   }
   
   public static int gethash(int[] dice){
      int p = 1;
      int hash = 0;
      for(int k = 0; k < 6; k++){
         hash += dice[k] * p;
         p *= 6;
      }
      
      return hash;
   }
   
   public static int[][] genperms(){
      int[][] ret = new int[][]{
         {0,1,2,5,3,4},
         {0,1,5,3,4,2},
         {0,1,3,4,2,5},
         {0,1,4,2,5,3},
         {1,0,4,3,5,2},
         {1,0,3,5,2,4},
         {1,0,5,2,4,3},
         {1,0,2,4,3,5},
          
         {2,3,1,5,0,4},
         {2,3,5,0,4,1},
         {2,3,0,4,1,5},
         {2,3,4,1,5,0},
         {3,2,4,0,5,1},
         {3,2,0,5,1,4},
         {3,2,5,1,4,0},
         {3,2,1,4,0,5},
         
         {5,4,0,2,1,3},
         {5,4,2,1,3,0},
         {5,4,1,3,0,2},
         {5,4,3,0,2,1},
         {4,5,3,1,2,0},
         {4,5,1,2,0,3},
         {4,5,2,0,3,1},
         {4,5,0,3,1,2}
      };
      
      return ret;
   }
      
}
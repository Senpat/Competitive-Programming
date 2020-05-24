//make sure to make new file!
import java.io.*;
import java.util.*;
//P1 uses efficient memory usage dp
public class miners2{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      char[] array = f.readLine().toCharArray();
      
      int[][][][] prev = new int[4][4][4][4];
      int[][][][] cur = new int[4][4][4][4];
      
      for(int q = 0; q < 4; q++){
         for(int w = 0; w < 4; w++){
            for(int e = 0; e < 4; e++){
               for(int r = 0; r < 4; r++){
                  prev[q][w][e][r] = -1;
                  cur[q][w][e][r] = -1;
               }
            }
         }
      }
      
      HashMap<Character,Integer> cv = new HashMap<Character,Integer>();       //convert
      cv.put('M',1);
      cv.put('B',2);
      cv.put('F',3);
      
      //added array[0] to one of the sides
      prev[cv.get(array[0])][0][0][0] = 1;
      HashSet<Integer> repeat = new HashSet<Integer>();
      for(int k = 1; k < n; k++){
         for(int q = 0; q < 4; q++){
            for(int w = 0; w < 4; w++){
               for(int e = 0; e < 4; e++){
                  for(int r = 0; r < 4; r++){
                     if(prev[q][w][e][r] == -1) 
                        continue;
                     //add array[k+1] to side 1
                     
                     repeat.add(cv.get(array[k]));
                     if(q > 0) repeat.add(q);
                     if(w > 0) repeat.add(w);
                     
                     cur[cv.get(array[k])][q][e][r] = Math.max(cur[cv.get(array[k])][q][e][r], prev[q][w][e][r]+repeat.size());
                     
                     repeat.clear();
                     
                     //add array[k+1] to side 2
                     repeat.add(cv.get(array[k]));
                     if(e > 0) repeat.add(e);
                     if(r > 0) repeat.add(r);
                     
                     cur[q][w][cv.get(array[k])][e] = Math.max(cur[q][w][cv.get(array[k])][e], prev[q][w][e][r]+repeat.size());
                     
                     repeat.clear();
                  }
               }
            }
         }
         
         //move cur to prev and set cur to -1
         for(int q = 0; q < 4; q++){
            for(int w = 0; w < 4; w++){
               for(int e = 0; e < 4; e++){
                  for(int r = 0; r < 4; r++){
                     prev[q][w][e][r] = cur[q][w][e][r];
                     cur[q][w][e][r] = -1;
                  }
               }
            }
         }
         
         
      }
       
       
      int answer = 0;
       
      for(int q = 0; q < 4; q++){
         for(int w = 0; w < 4; w++){
            for(int e = 0; e < 4; e++){
               for(int r = 0; r < 4; r++){
                  answer = Math.max(answer,prev[q][w][e][r]);
               }
            }
         }
      }
      
      out.println(answer);
      
      
      
      
      
      
      
      out.close();
   }
   
      
}
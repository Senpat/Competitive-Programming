import java.io.*;
import java.util.*;

class multimoo{
  
   public static boolean[][] seen,seen2;
   public static int[][] values;
   public static int n,max1,max2,next;
   
   public static int curx,cury;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new FileReader("multimoo.in"));
      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("multimoo.out")));
      
      n = Integer.parseInt(f.readLine());
      
      values = new int[n][n];
      
      Map<Integer,ArrayList<Integer>> tm = new TreeMap<Integer,ArrayList<Integer>>();
      HashMap<Integer,Integer> hm = new HashMap<Integer,ArrayList<Integer>>();
   
      
      for(int k = 0; k < n; k++){
         StringTokenizer st = new StringTokenizer(f.readLine());
         for(int j = 0; j < n; j++){
            values[k][j] = Integer.parseInt(st.nextToken());
            tm.put(values[k][j],new ArrayList<Integer>());
            if(!hm.contains(values[k][j])){
               hm.put(values[k][j],new ArrayList<Integer,Integer>());
            }
         }
      }
      
      for(int k = 0; k < n; k++){
         for(int j = 0; j < n; j++){
            if(k >= 1){
               if(values[k-1][j] != values[k][j] && !tm.get(values[k][j]).contains(values[k-1][j])){
                  tm.get(values[k][j]).add(values[k-1][j]);
               }
            }
            if(k < n-1){
               if(values[k+1][j] != values[k][j] && !tm.get(values[k][j]).contains(values[k+1][j])){
                  tm.get(values[k][j]).add(values[k+1][j]);
               }
            }
            if(j >= 1){
               if(values[k][j-1] != values[k][j] && !tm.get(values[k][j]).contains(values[k][j-1])){
                  tm.get(values[k][j]).add(values[k][j-1]);
               }
            }
            if(j < n-1){
               if(values[k][j+1] != values[k][j] && !tm.get(values[k][j]).contains(values[k][j+1])){
                  tm.get(values[k][j]).add(values[k][j+1]);
               }
            }
         }
      }
      //System.out.println(tm);
      seen = new boolean[n][n];
      
            
      max1 = 0;
      curx = 0;
      cury = 0;
      while(!remaining(seen)){
         //System.out.println(curx + " " + cury);
         int abc = search1(curx,cury,values[curx][cury]);
         
         max1 = Math.max(max1,abc);
         
                 
      }
      
      
      
      seen2 = new boolean[n][n];
      max2 = 0;
      for(int key : tm.keySet()){
         for(int k = 0; k < n; k++){
            for(int j = 0; j < n; j++){
               if(values[k][j] == key){
                  for(int other : tm.get(key)){
                     //System.out.println(k + " " + j);
                     max2 = Math.max(max2,search2(k,j,key,other));
                     seen2 = new boolean[n][n];
                  }
               }
            }
         }
      }
      
                     
            
      System.out.println(max1);
      System.out.println(max2);
      out.println(max1);
      out.println(max2);
      out.close();
      
      
   }
   
   public static boolean remaining(boolean[][] matrix){
      for(int k = 0; k < n; k++){
         for(int j = 0; j < n; j++){
            if(!matrix[k][j]){
               curx = k;
               cury = j;
               return false;
            }
         }
      }
      
      return true;
   }
   
   public static int search2(int a, int b, int value, int other){
      //System.out.println(a + " " + b +" " + value + " " + other);
      if(inBounds(a,b)){
         
         if((values[a][b] == value || values[a][b] == other) && seen2[a][b] == false){
            //System.out.println(a + " " + b +" " + value + " " + other);
            seen2[a][b] = true;
            return 1 + search2(a-1,b,value,other) + search2(a+1,b,value,other) + search2(a,b+1,value,other) + search2(a,b-1,value,other);
         }
      
      
      }
      return 0;
      
   }
   
   public static int search1(int a, int b, int value){
      if(inBounds(a,b)){
         if(values[a][b] == value && seen[a][b] == false){
            seen[a][b] = true;
            return 1 + search1(a-1,b,value) + search1(a+1,b,value) + search1(a,b+1,value) + search1(a,b-1,value);
         }
      
      
      }
      return 0;
      
   }
   
   public static boolean inBounds(int a, int b){
      return a>=0 && b>=0 && a < n && b < n;
   }
      
   
}
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
      
      for(int k = 0; k < n; k++){
         StringTokenizer st = new StringTokenizer(f.readLine());
         for(int j = 0; j < n; j++){
            values[k][j] = Integer.parseInt(st.nextToken());
         }
      }
      
      seen = new boolean[n][n];
      
      max1 = 0;
      curx = 0;
      cury = 0;
      while(!remaining(seen)){
         //System.out.println(curx + " " + cury);
               
         max1 = Math.max(max1,search1(curx,cury,values[curx][cury]));
         
                 
      }
      
      
      
      seen2 = new boolean[n][n];
      max2 = 0;
      curx = 0;
      cury = 0;
      for(int k = 0; k < n; k++){
         for(int j = 0; j < n; j++){
         //System.out.println(curx + " " + cury);
            next = -1;
            max2 = Math.max(max2,search2(k,j,values[k][j]));
            seen = new boolean[n][n];
         }
      }
      System.out.println(max1);
      System.out.println(max2-1);
      out.println(max1);
      out.println(max2-1);
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
   
   public static int search2(int a, int b, int value){
      if(inBounds(a,b)){
         if(seen2[a][b] == false){
            if(next == -1){
               if(values[a][b]!=value){
                  next = values[a][b];
                 
               }
               seen2[a][b] = true;
               
               if(value == 9)
                  System.out.println(value + " " + next + " " + a + " " + b);
               return 1 + search2(a,b+1,value) + search2(a+1,b,value) + search2(a,b-1,value) + search2(a-1,b,value);
            
            } 
            else if(next != -1){
               if(values[a][b] == value || values[a][b] == next){
                  seen2[a][b] = true;
                  
                  if(value == 9)
                     System.out.println(value + " " + next + " " + a + " " + b);
                  return 1 + search2(a,b+1,value) + search2(a+1,b,value) + search2(a,b-1,value) + search2(a-1,b,value);
               }
            }
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
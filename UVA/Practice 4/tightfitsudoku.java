//make sure to make new file!
import java.io.*;
import java.util.*;

public class tightfitsudoku{
   
   public static Cell[][] board;
   public static HashSet<Integer>[] row;
   public static HashSet<Integer>[] col;
   public static HashSet<Integer>[] box;
   
   public static boolean solved;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      board = new Cell[6][6];
      row = new HashSet[6];
      col = new HashSet[6];
      box = new HashSet[6];
      
      for(int k = 0; k < 6; k++){
         row[k] = new HashSet<Integer>();
         col[k] = new HashSet<Integer>();
         box[k] = new HashSet<Integer>();
      }
      
      
      for(int k = 0; k < 6; k++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
         for(int j = 0; j < 6; j++){
            board[k][j] = new Cell(st.nextToken(),k,j);
            
            if(board[k][j].a[0] != 0){
               row[k].add(board[k][j].a[0]);
               col[j].add(board[k][j].a[0]);
               box[board[k][j].boxi].add(board[k][j].a[0]);
            }
            
            if(board[k][j].a[1] != 0){
               row[k].add(board[k][j].a[1]);
               col[j].add(board[k][j].a[1]);
               box[board[k][j].boxi].add(board[k][j].a[1]);
            }
            
            
         }
      }
      
      solved = false;
      dothing(0,0,0);
      
      for(int k = 0; k < 6; k++){
         for(int j = 0; j < 6; j++){
            out.print(board[k][j] + " ");
         }
         out.println();
      }
      //out.println(solved);
      
      
      
      
      
      out.close();
   }
   
   public static void dothing(int x, int y, int i){
      
      if(x >= 6 || y >= 6) solved = true;
      if(solved) return;
      
      int newx;
      int newy;
      int newi;
      
      if(board[x][y].one || i == 1){      
         newx = x+1;
         newy = y;
         if(newx >= 6){
            newx = 0;
            newy++;
         }
         newi = 0;
      } else {
         newx = x;
         newy = y;
         newi = 1;
      }
      //System.out.println(x + " " + y + " " + i);
      //System.out.println("new: "+ newx + " " + newy + " " + newi);
      
      if(board[x][y].a[i] != 0){
         dothing(newx,newy,newi);
         return;
      }
      
      int low = 1;
      int high = 9;
      
      if(!board[x][y].one){
         if(i == 0){
            if(board[x][y].a[1] != 0){
               high = board[x][y].a[1]-1;
            }
         } else {
            low = board[x][y].a[0]+1;
         }
      }
      
      //find eligible
      int boxi = board[x][y].boxi;
      for(int k = low; k <= high; k++){
         if(!row[x].contains(k) && !col[y].contains(k) && !box[boxi].contains(k)){
            board[x][y].a[i] = k;
            row[x].add(k);
            col[y].add(k);
            box[boxi].add(k);
            dothing(newx,newy,newi);
            if(solved) return;
            board[x][y].a[i] = 0;
            row[x].remove(k);
            col[y].remove(k);
            box[boxi].remove(k);
         }
      }
      
   }
   
      
   public static class Cell{
      boolean one;
      int[] a;
      
      int boxi;
      /* box numbering:
      0 1
      2 3
      4 5
      */
      public Cell(String s, int x, int y){
         a = new int[2];
         if(s.length() == 1){
            one = true;
            a[0] = s.charAt(0) == '-' ? 0 : Character.getNumericValue(s.charAt(0));
            a[1] = 0;
         } else {
            one = false;
            a[0] = s.charAt(0) == '-' ? 0 : Character.getNumericValue(s.charAt(0));
            a[1] = s.charAt(2) == '-' ? 0 : Character.getNumericValue(s.charAt(2));
         }
         
         boxi = x/2*2 + y/3;
         
         if(a[0] != 0){
            row[x].add(a[0]);
            col[y].add(a[0]);
            box[boxi].add(a[0]);
         }
         if(a[1] != 0){
            row[x].add(a[1]);
            col[y].add(a[1]);
            box[boxi].add(a[1]);
         }
            
      }
      
      public String toString(){
         if(one) return "" + a[0];
         else return a[0] + "/" + a[1];
      }
   }
   
      
}
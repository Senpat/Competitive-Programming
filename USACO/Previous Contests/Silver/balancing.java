import java.io.*;
import java.util.*;

class balancing{
   public static int[] xcord,xsort,ycord,ysort;
   public static int num;
   public static void main(String[] args)throws IOException{
      
      BufferedReader f = new BufferedReader(new FileReader("balancing.in"));
      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("balancing.out")));
      
      num = Integer.parseInt(f.readLine());
      
      xcord = new int[num];
      xsort = new int[num];
      ycord = new int[num];
      ysort = new int[num];
      
      for(int k = 0; k < num; k++){
         StringTokenizer st = new StringTokenizer(f.readLine());
         int xcoordinate = Integer.parseInt(st.nextToken());
         int ycoordinate = Integer.parseInt(st.nextToken());
         
         xcord[k] = xcoordinate;
         xsort[k] = xcoordinate;
         
         ycord[k] = ycoordinate;
         ysort[k] = ycoordinate;
      }
      
      Arrays.sort(xsort);
      Arrays.sort(ysort);
      
      int min = Math.min(dothing(1,1),Math.min(dothing(1,-1),Math.min(dothing(-1,1), dothing(-1,-1))));
      System.out.println(min);
      out.println(min);
      out.close();
      
   }

   public static int dothing(int p, int e){
      int xline = xsort[xsort.length/2]+p;
      
      
      int yline = ysort[ysort.length/2]+e;
      
      int q1 = 0;
      int q2 = 0;
      int q3 = 0;
      int q4 = 0;
      for(int k = 0; k < num; k++){
         if(xcord[k] > xline && ycord[k] > yline){
            q1++;
         }
         if(xcord[k] < xline && ycord[k] > yline){
            q2++;
         }
         if(xcord[k] < xline && ycord[k] < yline){
            q3++;
         }
         if(xcord[k] > xline && ycord[k] < yline){
            q4++;
         }
      }
      return Math.max(q1,Math.max(q2,Math.max(q3,q4)));
   
   }
}
//make sure to make new file!
import java.io.*;
import java.util.*;

public class F784{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int t = Integer.parseInt(st.nextToken());
      
      
      int[][] hori = new int[n][n-1];
      int[][] vert = new int[n-1][n];
      
      int sum = 0;
      
      int next = 1;
      int dif = 2;
      for(int k = 0; k < n-1; k++){
         if(hori[0][k] != 0) 
            continue;
         for(int x = 0; x < n; x++){
            for(int y = k; y < n-1; y += dif){
               hori[x][y] = next;
               vert[y][x] = next*2;
               
               sum += next;
               sum += next*2;
            }
         }
         next *= 4;
         dif *= 2;
      }
      
      StringJoiner sj = new StringJoiner("\n");
      for(int k = 0; k < n; k++){
         StringJoiner row = new StringJoiner(" ");
         for(int j = 0; j < n-1; j++){
            row.add("" + hori[k][j]);
         }
         sj.add(row.toString());
      }
      for(int k = 0; k < n-1; k++){
         StringJoiner row = new StringJoiner(" ");
         for(int j = 0; j < n; j++){
            row.add("" + vert[k][j]);
         }
         sj.add(row.toString());
      }
      out.println(sj.toString());
      out.flush();
      
      int horimask = 1365;          //10101010101
      int vertmask = 2730;          //101010101010
      int x = 0;
      int y = 0;
      for(int q = 0; q < t; q++){
         int i = Integer.parseInt(f.readLine());
         
         int horitarget = i & horimask;
         int verttarget = i & vertmask;
         
         int hitx = -1;
         int curxor = 0;
         int curx = x;
         while(curx >= 0){
            if(curxor == horitarget){
               hitx = curx;
               break;
            }
            if(curx == 0) 
               break;
            curxor ^= hori[0][curx-1];
            curx--;
         }
         
         curx = x;
         curxor = 0;
         while(curx < n){
            if(curxor == horitarget){
               hitx = curx;
               break;
            }
            if(curx == n-1) 
               break;
            curxor ^= hori[0][curx];
            curx++;
         }
         
         int hity = -1;
         int curyor = 0;
         int cury = y;
         while(cury >= 0){
            if(curyor == verttarget){
               hity = cury;
               break;
            }
            if(cury == 0) 
               break;
            curyor ^= vert[cury-1][0];
            cury--;
         }
         cury = y;
         curyor = 0;
         while(cury < n){
            if(curyor == verttarget){
               hity = cury;
               break;
            }
            if(cury == n-1) 
               break;
            curyor ^= vert[cury][0];
            cury++;
         }
         
         
         x = hitx;
         y = hity;
         
         out.println((y+1) + " " + (x+1));
         out.flush();
      
      }
      
      
      
      
      
      
      
      out.close();
   }
   
      
}
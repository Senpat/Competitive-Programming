//make sure to make new file!
import java.io.*;
import java.util.*;

public class C910{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         int m = Integer.parseInt(st.nextToken());
         int x = Integer.parseInt(st.nextToken());
         
         int min = (n-1) + (m-1);
         if(x%2 != min%2 || x < min){
            out.println("NO");
         } else {
            char[][] hori = new char[n][m-1];
            char[][] vert = new char[n-1][m];
            for(int k = 0; k < n; k++) Arrays.fill(hori[k],'R');
            for(int j = 0; j < n-1; j++) Arrays.fill(vert[j],'R');
            
            hori[0][0] = 'R';
            hori[1][0] = 'R';
            vert[0][0] = 'B';
            vert[0][1] = 'B';
            
            hori[0][1] = 'R';
            for(int k = 2; k < m-1; k++){
               hori[0][k] = other(hori[0][k-1]);
            }
            for(int k = 1; k < n-1; k++){
               vert[k][0] = other(vert[k-1][0]);
            }
            
            vert[0][m-1] = other(hori[0][m-2]);
            for(int k = 1; k < n-1; k++){
               vert[k][m-1] = other(vert[k-1][m-1]);
            }
            
            hori[n-1][0] = other(vert[n-2][0]);
            for(int k = 1; k < m-1; k++){
               hori[n-1][k] = other(hori[n-1][k-1]);
            }
            
            out.println("YES");
            for(int k = 0; k < n; k++){
               for(int j = 0; j < m-1; j++){
                  out.print(hori[k][j] + " ");
               }
               out.println();
            }
            for(int k = 0; k < n-1; k++){
               for(int j = 0; j < m; j++){
                  out.print(vert[k][j] + " ");
               }
               out.println();
            }
            
         }

      }
      
      
      
      
      out.close();
   }
   
   public static char other(char c){
      if(c == 'R') return 'B';
      else return 'R';
   }
   
      
}
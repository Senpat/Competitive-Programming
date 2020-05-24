import java.util.Scanner;

public class MoleInTheCompany {
	static Scanner s = new Scanner(System.in);
	
	public static void main(String[] args) {
        int n = Integer.parseInt(s.nextLine());
		int[][] code = new int[n][n];					   // a nxn array of ints.
		
		// The following code will read in the ints from the input stream and fill the 2d array.
        for (int i = 0; i < n; i++) {
            String[] row = s.nextLine().split(" ");
            
            for (int j = 0; j < n; j++) {
                code[i][j] = Integer.parseInt(row[j]);
            }
        }

		// code to solve the problem.  You can write and call other methods as well.
		
      
      int answer = dothing(code,0,0,n-1,n-1);
      
		System.out.println(answer);                                 // print your answer and just your answer.
	}
   
   
   public static int dothing(int[][] code, int x1, int y1, int x2, int y2){
      if(x1==x2 && y1==y2){
         return code[x1][y1];
      }
      
      return (dothing(code,x1,y1,x2-1,y2-1) + dothing(code,x1+1,y1+1,x2,y2)) - (dothing(code,x1+1,y1,x2,y2-1) + dothing(code,x1,y1+1,x2-1,y2));
   }
   
   
   
   
}

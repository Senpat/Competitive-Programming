import java.util.Scanner;

public class PasswordCracking {
	static Scanner s = new Scanner(System.in);
	
	public static void main(String[] args) {
		int guess = s.nextInt();              // A five-digit integer.
		int lower = s.nextInt();			  // The lower bound.
		int upper = s.nextInt();			  // The upper bound.
		int answer = s.nextInt();             // The correct password.

		// code to solve the problem.  You can write and call other methods as well.
		
      int zeros = 0;
      
      String guesss = Integer.toString(guess);
      String answers = Integer.toString(answer);
      boolean fail = false;
      for(int k = 0; k < guesss.length(); k++){
         if(guesss.charAt(k) == '0'){
            zeros++;
            if(Character.getNumericValue(answers.charAt(k)) < lower ||  Character.getNumericValue(answers.charAt(k)) > upper){
               fail = true;
               break;
            }
         } else {
            if(answers.charAt(k) != guesss.charAt(k)){
               fail = true;
               break;
            }
         }
      }
      
      if(fail){
         System.out.println("0");
      } else if(zeros == 0){
         System.out.println("100");
      } else {
         double answer1 = 100.0/((double)(Math.pow((upper-lower+1),zeros)));
         System.out.println(answer1);
      }
      
	}
}

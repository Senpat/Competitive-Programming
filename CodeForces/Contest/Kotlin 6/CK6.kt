import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	for(q in 1..f.readLine().toInt()){
		var (n,x,y) = f.readLine().split(" ").map{it.toInt()}
		if(x > y){
			val temp = y
			y = x
			x = temp
		}

		fun calc(l : Int, r : Int, i : Int) : Int{
			//check left then right
			var a1 = i-l + (r-l)
			//check right then left
			var a2 = r-i + (r-l)
			return min(a1,a2)
		}

		var answer = Int.MAX_VALUE
		for(k in x until y){
			//x gets [1,k] and y gets [k+1,n]

			answer = min(answer,max(calc(1,k,x),calc(k+1,n,y)))
		}

		println(answer)
	}
}

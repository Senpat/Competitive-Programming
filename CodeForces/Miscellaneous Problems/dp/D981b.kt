import java.io.*
import java.util.*
import kotlin.math.*
//semi-t
fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	val (n,m) = f.readLine().split(" ").map{it.toInt()}
	val array = f.readLine().split(" ").map{it.toLong()}

	fun check(pcheck : Long) : Boolean{
		val dp = Array(n){BooleanArray(m+1)}

		//initial
		var sum = 0L
		for(k in 0 until n){
			sum += array[k]
			if(pcheck and sum >= pcheck){
				dp[k][1] = true
			}
		}

		for(k in 0 until n-1){
			for(j in 0 until m){
				if(!dp[k][j]) continue
				sum = 0L
				for(h in k+1 until n){
					sum += array[h]
					if(pcheck and sum >= pcheck){
						dp[h][j+1] = true
					}
				}
			}
		}

		return dp[n-1][m]

	}

	var answer : Long = 0L
	for(i in 60 downTo 0){
		val pcheck : Long = answer + (1L shl i)

		//check if it is possible
		if(check(pcheck)){
			answer += (1L shl i)
		}
	}

	println(answer)



}

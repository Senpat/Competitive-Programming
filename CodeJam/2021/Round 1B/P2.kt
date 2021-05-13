import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	fun gcd(x : Int, y : Int) : Int{
		if(x == y) return x
		if(x < y){
			if(x == 0) return y
			return gcd(y%x,x)
		} else {
			if(y == 0) return x
			return gcd(x%y,y)
		}
	}

	val MAX = 51

	val fibo = LongArray(MAX){0L}
	fibo[1] = 1L
	fibo[2] = 1L
	for(k in 3 until MAX){
		fibo[k] = fibo[k-1]+fibo[k-2]
	}

	for(q in 1..f.readLine().toInt()){
		val (n,a,b) = f.readLine().split(" ").map{it.toInt()}
		val array = LongArray(1) + f.readLine().split(" ").map{it.toLong()}.toLongArray()

		val gcd = gcd(a,b)
		var fail = false
		for(k in 1..n){
			if(array[k] > 0L && k%gcd != 0){
				fail = true
				break
			}
		}

		if(fail){
			println("Case #$q: IMPOSSIBLE")
			continue
		}

		var fibosum = 0L
		for(k in 1..n){
			fibosum += array[k]*fibo[k]
		}

		var answer = -1
		for(k in 1 until MAX){
			answer = k
			if(fibo[k] >= fibosum) break
		}

		println("Case #$q: $answer")

	}
}

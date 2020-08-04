import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	val (n,m) = f.readLine().split(" ").map{it.toInt()}

	val array = f.readLine().split(" ").map{it.toLong()}

	val MOD : Long = 1000000007L

	fun exp(base : Long, power : Long) : Long{
		if(power == 0L) return 1L
		if(power == 1L) return (base + MOD)%MOD
		var ans = 1L
		var b = base
		var p = power
		while(p > 0L){
			if(p and 1L == 1L) ans = (ans*b+MOD)%MOD
			p = p shr 1
			b = (b*b + MOD)%MOD
		}
		return ans
	}
	fun modInverse(n : Long, p : Long) : Long{
		return exp(n,p-2)
	}


	val pos = mutableListOf<Int>()
	val neg = mutableListOf<Int>()
	var num0 = 0
	for(k in 0 until n){
		if(array[k] < 0) neg.add(array[k])
		if(array[k] > 0) pos.add(array[k])
		if(array[k] == ) num0++
	}

	pos.sort()			//1..10000
	neg.sort()			//-1000..-1

	var answer = 1L
	if(m == n){
		for(k in 0 until n){
			answer = ((answer * array[k])%MOD + MOD)%MOD
		}
	} else if(pos.size == 0){
		if(m%2 == 1){
			if(num0 > 0) answer = 0L
			else{
				for(k in 0 until m){
					answer = ((answer * neg[neg.size-k-1])%MOD + MOD)%MOD
				}

			}
		} else {
			if(m > neg.size) answer = 0L
			else{
				for(k in 0 until m){
					answer = ((answer * neg[k])%MOD + MOD)%MOD
				}
			}
		}
	} else {
		for(k in 0 until num0) neg.add(0)

		//start with as many pos as possible
		if(m%2 == 0){
			//as many even as possible
			var np = min(m,pos.size%2 == 0 ? pos.size : pos.size-1)
			var nn = m-np

			//starting
			var max = 1L

			for(k in 0 until np){
				max = (max * )
			}
		}
	}

}

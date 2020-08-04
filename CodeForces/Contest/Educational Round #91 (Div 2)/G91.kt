import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	val MOD = 998244353L

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

	val n = f.readLine().toInt()
	val modin = modInverse(n.toLong(),MOD)
	val array = LongArray(1) + f.readLine().split(" ").map{it.toLong()}.toLongArray().sorted()

	val psums = LongArray(n+1){0L}
	psums[0] = array[0]
	for(k in 1..n){
		psums[k] = (psums[k-1]+array[k] + MOD)%MOD
	}

	val answer = LongArray(n)

	for(k in 1..n){
		for(j in n-k downTo 0 step k){
			answer[k-1] = (answer[k-1] + psums[j] + MOD)%MOD
		}
	}

	for(k in 0 until n){
		answer[k] = (answer[k]*modin+MOD)%MOD
	}

	println(answer.joinToString(" "))

}

import java.io.*
import java.util.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	val MOD : Long = 998244353L
	val fac = LongArray(500005)
	fac[0] = 1L
	for(k in 1 until 500005){
		fac[k] = (fac[k-1]*k.toLong() + MOD)%MOD
	}

	fun exp(base : Long, power : Long) : Long{
		if(power == 0L) return 1L
		if(power == 1L) return (base + MOD)%MOD
		var ans = exp(base,power/2)
		ans = (ans*ans + MOD)%MOD
		if(power%2 == 1L) ans = (ans*base + MOD)%MOD
		return (ans + MOD)%MOD
	}
	fun modInverse(n : Long, p : Long) : Long{
		return exp(n,p-2)
	}
	fun choose (n : Long, r : Long) : Long{
		if(r == 0L) return 1L
		return (((fac[n.toInt()] * modInverse(fac[r.toInt()], MOD) + MOD) % MOD) * modInverse(fac[(n-r).toInt()], MOD) + MOD) % MOD;
	}

	val (n,m) = f.readLine().split(" ").map{it.toInt()}

	var answer : Long = 0L
	for(k in 1..n){
		val next = k*2

		val num = ((n-next)/k+1).toLong()

		if(num < m-1) break

		answer = (answer + choose(num,(m-1).toLong()) + MOD)%MOD
	}

	println(answer)

}

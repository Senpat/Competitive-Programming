import java.io.*
import java.util.*
import kotlin.math.*
//FINAL WORKING SOL
fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	val i = f.readLine().toInt()
	val s = f.readLine()
	val n1 = s.length

	//CHECK THE MOD!!!
	val MOD : Long = 1000000007L
	val fac = LongArray(2000005)
	fac[0] = 1L
	for(k in 1 until 2000005){
		fac[k] = (fac[k-1]*k.toLong() + MOD)%MOD
	}

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
	fun choose (n : Int, r : Int) : Long{
		if(r == 0) return 1L
		if(n < r) return 0L
		return (((fac[n.toInt()] * modInverse(fac[r.toInt()], MOD) + MOD) % MOD) * modInverse(fac[(n-r).toInt()], MOD) + MOD) % MOD;
	}

	val pow25 = LongArray(1000005)
	val pow26 = LongArray(1000005)

	pow25[0] = 1L
	pow26[0] = 1L

	for(k in 1 until 1000005){
		pow25[k] = (pow25[k-1]*25L + MOD)%MOD
		pow26[k] = (pow26[k-1]*26L + MOD)%MOD
	}

	var answer = 0L

	for(k in 0..i){
		val prod1 = (pow26[i-k]*pow25[k] + MOD)%MOD
		val prod2 = (prod1 * (choose(k+n1-1,k)) + MOD)%MOD
		answer = (answer + prod2 + MOD)%MOD
	}

	println(answer)

}

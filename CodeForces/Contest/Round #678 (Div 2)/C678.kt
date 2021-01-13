import java.io.*
import java.util.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	//CHECK THE MOD!!!
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

	val fac = LongArray(1005)
	val ifac = LongArray(1005)
	fac[0] = 1L
	ifac[0] = 1L
	for(k in 1 until 1005){
		fac[k] = (fac[k-1]*k.toLong() + MOD)%MOD
		ifac[k] = modInverse(fac[k],MOD)
	}




	val (n,x,pos) = f.readLine().split(" ").map{it.toInt()}
	var numgreater = 0
	var numless = 0

	var l = 0
	var r = n
	while(l < r){
		val mid = (l+r)/2
		if(mid == pos) l = mid+1
		if(mid < pos){
			numless++
			l = mid+1
		} else {
			numgreater++
			r = mid
		}
	}
	//println("$numgreater $numless")
	if(numgreater > n-x || numless > x-1){
		println("0")
	} else {
		val greater = (fac[n-x]*ifac[n-x-numgreater] + MOD)%MOD
		//println("${fac[n-x]} ${ifac[n-x-numgreater]}")
		val less = (fac[x-1]*ifac[x-1-numless] + MOD)%MOD
		//println("${fac[x-1]} ${ifac[x-1-numless]}")
		var answer = (greater*less + MOD)%MOD
		//println("$numgreater $numless $answer")
		answer = (answer*fac[n-1-numless-numgreater] + MOD)%MOD
		println(answer)
	}

}

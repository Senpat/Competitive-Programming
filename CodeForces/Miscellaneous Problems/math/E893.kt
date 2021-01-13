import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	val MOD = 1000000007L
	/*
	val N = 1000005
	val prime = BooleanArray(N+1){true};

	var p = 2
	while(p*p <= N)
	{
	   if(prime[p])
	   {
			 for(i in (p*p)..N step p)
	           prime[i] = false
	   }
		p++
	}

	val primes = mutableListOf<Int>()
	for(for i in 2..n)
	{
	   if(prime[i] == true) primes.add(i)
	}
*/
	val N = 1001000
	val fac = LongArray(N)
	val ifac = LongArray(N)

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

	fac[0] = 1L
	ifac[0] = -1L
	for(k in 1 until N){
		fac[k] = (k.toLong()*fac[k-1] + MOD)%MOD
		ifac[k] = modInverse(fac[k],MOD)
	}

	val pow2 = LongArray(N)
	pow2[0] = 1L
	for(k in 1 until N){
		pow2[k] = (2L*pow2[k-1]+MOD)%MOD
	}

	fun choose (n : Int, r : Int) : Long{
		if(r == 0 || n==r) return 1L
		return (((fac[n] * ifac[r] + MOD) % MOD) * ifac[n-r] + MOD) % MOD;
	}

	for(q in 1..f.readLine().toInt()){
		var (x,y) = f.readLine().split(" ").map{it.toInt()}
		var answer = 1L

		var p = 2
		while(p*p <= x){
			var count = 0
			while(x%p == 0){
				x /= p
				count++
			}
			if(count > 0){
				answer = (answer * choose(y+count-1,count) + MOD)%MOD
			}
			p++
		}

		if(x > 1){
			answer = (answer * y.toLong() + MOD)%MOD
		}

		answer = (answer * pow2[y-1] + MOD)%MOD
		println(answer)
	}





}

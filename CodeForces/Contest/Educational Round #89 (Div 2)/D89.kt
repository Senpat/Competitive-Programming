import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	//SIEVE
	val primes = mutableListOf<Int>()
	val primesset = HashSet<Int>()

	fun sieve(x : Int){
		val prime = BooleanArray(x+1){true}

		var p = 2
		while(p*p <= x){
			if(prime[p]){
				for(i in (p*p)..x step p){
					prime[i] = false
				}
			}
			p++
		}

		for(i in 2..x){
			if(prime[i]){
				primes.add(i)
				primesset.add(i)
			}
		}

	}

	sieve(10000005)

	//println("done with sieve")
	System.out.flush()

	val n = f.readLine().toInt()
	val array = f.readLine().split(" ").map{it.toInt()}

	val d1 = IntArray(n)
	val d2 = IntArray(n)

	for(k in 0 until n){
		var i = array[k]

		d1[k] = -1
		d2[k] = -1

		if(primesset.contains(i)){
			continue
		}

		if(i%2 == 0){
			//find odd divisor
			while(i%2 == 0){
				i/=2
			}

			if(i != 1){
				d1[k] = 2
				d2[k] = i
			}
		} else {
			for(p in primes){
				if(p*p > i) break
				if(i % p == 0){
					if(d1[k] != -1){
						d2[k] = p
						break
					} else {
						d1[k] = p
						while(i % p == 0){
							i/=p
						}
					}
				}
			}

			if(i != 1 && d1[k] != -1 && d2[k] == -1) d2[k] = i
			if(d2[k] == -1) d1[k] = -1
		}
	}

	println(d1.joinToString(" "))
	println(d2.joinToString(" "))


}

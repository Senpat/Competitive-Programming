import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	//sieve
	val MAXN = 100005
	val isprime = BooleanArray(MAXN+1){true}
	for(k in 2..MAXN){
		if(isprime[k]){
			for(j in (2*k)..MAXN step k){
				isprime[j] = false
			}
		}
	}

	val primes = mutableListOf<Int>()
	for(k in 2..MAXN){
		if(isprime[k]) primes.add(k)
	}

	val primeindex = HashMap<Int,Int>()
	for(k in 0 until primes.size){
		primeindex.put(primes[k],k)
	}

	val (n,q) = f.readLine().split(" ").map{it.toInt()}
	val array = f.readLine().split(" ").map{it.toInt()}

	val end = IntArray(n){n}				//stores index of start of range starting at that index
	val minprime = IntArray(primes.size){n}			//stores minimum index that contains that prime
	for(k in n-1 downTo 0){

		//prime fac
		val primefac = mutableListOf<Int>()
		var x = array[k]
		for(pi in 0 until primes.size){
			//println(primes[pi])
			if(primes[pi]*primes[pi] > x) break
			if(x%primes[pi] == 0){
				primefac.add(pi)
				while(x % primes[pi] == 0){
					x /= primes[pi]
				}
			}

		}
		//println(x)
		if(x > 1){
			primefac.add(primeindex.get(x)!!)
		}

		var minindex = n
		//println(primefac.joinToString(" "))
		for(j in 0 until primefac.size){
			minindex = min(minindex,minprime[primefac[j]])
		}

		end[k] = minindex

		for(j in 0 until primefac.size){
			minprime[primefac[j]] = k
		}

	}

	//adjust end
	var suffixmin = n
	for(k in n-1 downTo 0){
		end[k] = min(suffixmin,end[k])
		suffixmin = min(suffixmin,end[k])
	}

	val numtoend = IntArray(n){0}
	for(k in n-1 downTo 0){
		if(end[k] == n) numtoend[k] = 1
		else{
			numtoend[k] = numtoend[end[k]]+1
		}
	}

	//println(end.joinToString(" "))
	//println(numtoend.joinToString(" "))

	for(k in 0 until q){
		var (l,r) = f.readLine().split(" ").map{it.toInt()}
		l--
		r--

		val answer = numtoend[l]-numtoend[r]+1
		println(answer)
	}



}

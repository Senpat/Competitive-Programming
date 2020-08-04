import java.io.*
import java.util.*
import kotlin.math.*
fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	val (n,m) = f.readLine().split(" ").map{it.toInt()}
	val array = intArrayOf(0) + f.readLine().split(" ").map{it.toInt()}.toIntArray()

	//fenwick tree, to keep sum - 1 indexed
	val bits = LongArray(n+1)

	fun update(ii : Int, x : Long){
		var i = ii
		while(i <= n){
			bits[i] += x
			i += (i and -i)
		}
	}

	fun psum(ii : Int): Long{
		var i = ii
		var sum : Long = 0L
		while(i > 0){
			sum += bits[i]
			i -= (i and -i)
		}
		return sum
	}


	//seg tree, keep range max
	val t = IntArray(4*n+5)

	fun build(a : IntArray, v : Int, tl : Int, tr : Int){
		if(tl == tr){
			t[v] = a[tl]
		} else {
			val tm = (tl + tr)/2
			build(a,v*2,tl,tm)
			build(a,v*2+1,tm+1,tr)
			t[v] = max(t[v*2],t[v*2+1])
		}
	}

	//to call: v=1, tl = 0, tr = n-1
	fun segmax(v : Int, tl : Int, tr: Int, l : Int, r : Int) : Int{
		if(l > r) return 0
		if(l == tl && r == tr) return t[v]
		val tm = (tl+tr)/2
		return max(segmax(v*2, tl, tm, l, min(r, tm)),segmax(v*2+1, tm+1, tr, max(1, tm+1), r))
	}

	//to call: v=1, tl = 0, tr = n-1
	fun segupdate(v : Int, tl : Int, tr : Int, pos : Int, new_val : Int){


		if(segmax(1,1,n,tl,tr) <= 2) return


		if(tl == tr){
			t[v] = new_val
		} else {
			val tm = (tl+tr)/2
			if(pos <= tm) segupdate(v*2, tl, tm, pos, new_val)
			else segupdate(v*2+1, tm+1, tr, pos, new_val)
			t[v] = max(t[v*2],t[v*2+1])
		}
	}
	/*
	//SIEVE
	val primes = mutableListOf<Long>()

	fun sieve(x : Int){
		val prime = BooleanArray(x+1){true}

		var p = 2
		while(p*p <= x){
			if(prime[p]){
				for(i in (p*p)..n step p){
					prime[i] = false
				}
			}
			p++
		}

		for(i in 2..x){
			if(prime[i]) primes.add(i.toLong())
		}

	}

	sieve(1000005)

	fun calcD(x : Long) : Long{
		if(x == 1L) return 1L
		if(x == 2L) return 2L
		var factors = 1L
		var cur = x
		for(p in primes){
			if(p*p > cur) break
			var count = 0L
			while(cur % p == 0L){
				cur/=p
				count++
			}
			factors *= count+1
		}
		if(cur != 1L) factors *= 2L

		//println("$x $factors")
		return factors
	}
*/
	//build fenwick and D
	//val D = LongArray(n+1)
	for(i in 1..n){
		update(i,array[i].toLong())
		//find d

		//D[i+1] = calcD(array[i])
	}

	val D = IntArray(1000005){0}
	for(k in 1 until 1000005){
		for(j in k until 1000005 step k){
			D[j]++
		}
	}


	build(array,1,1,n)
	val sb = StringBuilder()
	for(q in 1..m){
		val (type,l,r) = f.readLine().split(" ").map{it.toInt()}

		if(type == 1){
			if(segmax(1,1,n,l,r) > 2){
				for(k in l..r){
					val newD = D[array[k]]
					segupdate(1,1,n,k,newD)
					update(k,(newD-array[k]).toLong())
					array[k] = newD
				}
			}


		} else {
			val answer = psum(r)-psum(l-1)
			sb.appendln(answer)


		}
	}

	println(sb)

}

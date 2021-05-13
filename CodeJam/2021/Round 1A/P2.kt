import java.io.*
import java.util.*
import kotlin.math.*
//first 2 subtasks
fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	val MAX : Long = 50000L
	val maxprime = 500

	val prime = BooleanArray(maxprime+1){true}
	for(k in 2..maxprime){
		if(!prime[k]) continue
		for(j in (2*k)..maxprime step k){
			prime[j] = false
		}
	}

	val primes = mutableListOf<Long>()
	for(k in 2..maxprime){
		if(prime[k]) primes.add(k.toLong())
	}


	for(q in 1..f.readLine().toInt()){
		val n = f.readLine().toInt()
		val array = LongArray(n)
		val nums = LongArray(n)

		var sum : Long = 0L

		for(k in 0 until n){
			val (a,b) = f.readLine().split(" ").map{it.toLong()}
			array[k] = a
			nums[k] = b

			sum += a*b
		}

		fun test(xx : Long) : Boolean{

			var x = xx
			var cursum = sum

			for(k in 0 until n){
				if(x == 1L) break
				var count = 0
				while(x % array[k] == 0L){
					count++
					x /= array[k]
				}

				if(count > nums[k]) return false
				cursum -= array[k]*count
			}

			if(x != 1L) return false
			return xx == cursum
		}


		var answer : Long = 0L
		for(k in MAX downTo 2){
			if(test(k)){
				answer = k
				break
			}
		}

		println("Case #${q}: $answer")

	}
}

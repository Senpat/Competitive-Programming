import java.io.*
import java.util.*
import kotlin.math.*
//completely wrong, TlE
fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	for(q in 1..f.readLine().toInt()){

		val MOD = 1000000007L
		fun exp(base : Long, power : Int) : Long{
			if(power == 0) return 1L
			if(power == 1) return (base + MOD)%MOD
			var ans = exp(base,power/2)
			ans = (ans*ans + MOD)%MOD
			if(power%2 == 1) ans = (ans*base + MOD)%MOD
			return (ans + MOD)%MOD
		}




		val (n,mi) = f.readLine().split(" ").map{it.toInt()}
		val m = mi.toLong()

		val array = f.readLine().split(" ").map{it.toLong()}.toLongArray()

		if(m==1L){
			println(n%2)
			continue
		}

		val freq = LongArray(1000001)
		for(k in 0 until n){
			freq[array[k].toInt()]++
		}
		//get first odd number
		var start = -1

		var answer1 = 0L
		var answer2 = 0L

		var need = -1L			//how much you need of the next one to cancel
		for(k in 1000000 downTo 0){
			//if(k < 102) println("$k $start $need")
			if(start == -1){
				if(freq[k]%2 == 1L){
					start = k
					answer1 = exp(m,k)
					need = m
				}
			} else {
				if(need != Long.MAX_VALUE && freq[k] >= need){
					freq[k] -= need
					if(freq[k]%2 == 1L){
						start = k
						answer1 = exp(m,k)
						need = m
					} else {
						start = -1
					}
				} else {
					if(need != Long.MAX_VALUE){
						need = exp((need-freq[k]),mi)
						if(need > 1000000 || need < 0) need = Long.MAX_VALUE
					}

					val add = (exp(m,k)*freq[k] + MOD)%MOD
					//if(k < 2) println("$k $add")
					answer2 = (answer2 + add + MOD)%MOD
				}
			}
		}

		val answer = ((answer1-answer2 + MOD)%MOD + MOD)%MOD
		println(answer)
	}
}

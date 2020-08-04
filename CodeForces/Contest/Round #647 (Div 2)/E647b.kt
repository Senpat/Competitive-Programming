import java.io.*
import java.util.*
import kotlin.math.*
//upsolve, semi-t
fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	for(q in 1..f.readLine().toInt()){

		val MOD = 1000000007L
		fun exp(base : Long, power : Long) : Long{
			if(power == 0L) return 1L
			if(power == 1L) return (base + MOD)%MOD
			var ans = exp(base,power/2)
			ans = (ans*ans + MOD)%MOD
			if(power%2 == 1L) ans = (ans*base + MOD)%MOD
			return (ans + MOD)%MOD
		}




		val (n,mi) = f.readLine().split(" ").map{it.toInt()}
		val m = mi.toLong()

		val array = f.readLine().split(" ").map{it.toLong()}.toLongArray().sortedDescending()

		if(m==1L){
			println(n%2)
			continue
		}


		//find "log"
		var max = 0
		var cur = 1L
		while(cur * m < 1000000007L){
			cur*=m
			max++
		}

		var answer = 0L
		var exact = 0L

		for(k in 0 until array.size){
			if(k == 0){
				exact = 1L
				answer = exp(m,array[0])
			} else {
				if(exact != Long.MAX_VALUE){
					//update exact
					val dif = array[k-1] - array[k]

					for(j in 0 until min(dif,20)){
						exact *= m
						if(exact > n){
							exact = Long.MAX_VALUE
							break
						}
					}

					/*
					if(dif > max){
						exact = Long.MAX_VALUE
					} else {
						exact *= exp(m,dif)
						if(exact > n){
							exact = Long.MAX_VALUE
						}
					}*/
					/*
					for(j in array[k] until array[k-1]){
						exact *= m
						if(exact > n){
							exact = Long.MAX_VALUE
							break
						}
					}*/
				}

				if(exact != Long.MAX_VALUE){
					if(exact > 0L){
						exact--
						answer = ((answer - exp(m,array[k]))%MOD + MOD)%MOD
					} else {
						exact++
						answer = (answer + exp(m,array[k]) + MOD)%MOD
					}
				} else {
					answer = ((answer - exp(m,array[k]))%MOD + MOD)%MOD
				}

			}
		}

		println(answer)

	}
}

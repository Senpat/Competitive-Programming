import java.io.*
import java.util.*
import kotlin.math.*
import java.math.BigInteger

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	for(q in 1..f.readLine().toInt()){
		var (n,m) = f.readLine().split(" ").map{it.toInt()}

		if(m == 0){
			val num9 = n/9
			val non9 = n%9

			val sj = StringJoiner("")
			if(non9 != 0) sj.add("$non9")
			for(k in 0 until num9) sj.add("9")
			println(sj)
			continue
		}


		var min = BigInteger("99999999999999999999")
		m++
		//how many 9s in a row starting from 10s digit
		for(n9 in 0..18){
			//for all ones digits
			for(k in 0..9){
				var sumadd = 0

				var curadd = 0
				for(j in 1 until m){
					if(k+j == 10){
						curadd -= 8 + 9*(n9)
					} else {
						curadd += 1
					}
					sumadd += curadd
				}
				if(n-sumadd < 0 || (n-sumadd) % m != 0) continue

				val locksum = 9*n9 + k

				var newdigitssum = (n-sumadd)/m - locksum
				if(newdigitssum < 0) continue

				val numnew9s = newdigitssum/9
				val non9 = newdigitssum % 9

				val sj1 = StringJoiner("")
				for(j in 0 until numnew9s) sj1.add("9")
				sj1.add("$non9")
				for(j in 0 until n9) sj1.add("9")
				sj1.add("$k")

				val answer1 = sj1.toString().toBigInteger()
				//println("$n $m $n9 $k $answer")
				min = min.min(answer1)


				if(newdigitssum >= 8){

					newdigitssum -= 8

					val numnew9s8 = newdigitssum/9
					val non98 = newdigitssum%9

					val sj2 = StringJoiner("")
					sj2.add("$non98")
					for(j in 0 until numnew9s8) sj2.add("9")
					sj2.add("8")
					for(j in 0 until n9) sj2.add("9")
					sj2.add("$k")

					val answer2 = sj2.toString().toBigInteger()
					min = min.min(answer2)

				}


			}
		}

		if(min.equals("99999999999999999999".toBigInteger())) println(-1)
		else println(min)
	}
}

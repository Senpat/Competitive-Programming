import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	for(q in 1..f.readLine().toInt()){
		val (n,m) = f.readLine().split(" ").map{it.toLong()}
		val (cs,cw) = f.readLine().split(" ").map{it.toLong()}
		val (s,w) = f.readLine().split(" ").map{it.toLong()}

		var answer = 0L

		for(k in 0L..cs){
			//how many swords the first person holds
			if(k*s > n) break

			//add k swords to first person
			val swords1 = k

			val swordsremaining = cs-k
			val nremaining = n-k*s

			//fill axe in 1
			val addaxe1 = min(cw,nremaining/w)

			val axeremaining = cw-addaxe1

			//second guy gets most of remaining
			if(s < w){
				var capacity2 = m
				val addsword2 = min(swordsremaining,capacity2/s)

				capacity2 -= addsword2*s

				val addaxe2 = min(axeremaining,capacity2/w)

				answer = max(answer,swords1+addaxe1+addsword2+addaxe2)
			} else {
				var capacity2 = m
				val addaxe2 = min(axeremaining,capacity2/w)

				capacity2 -= addaxe2*w

				val addsword2 = min(swordsremaining,capacity2/s)

				answer = max(answer,swords1+addaxe1+addsword2+addaxe2)
			}
		}

		println(answer)
	}
}

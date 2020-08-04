import java.io.*
import java.util.*
import kotlin.math.*
fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	for(q in 1..f.readLine().toInt()){
		val (h,c,t) = f.readLine().split(" ").map{it.toDouble()}

		if(t == h){
			println(1)
			continue
		} else if(t <= (h+c)/2){
			println(2)
			continue
		}

		var n = (t-h)/(c+h-2*t)
		n = n*2.0+1.0
		var mindif = -1.0
		var value = -1.0

		if(abs(t-h) <= abs(t-((h+c)/2))){
			mindif = abs(t-h)
			value = 1.0
		} else {
			mindif = abs(t-((h+c)/2))
			value = 2.0
		}

		for(k in max(3,n.toInt()-1000)..(n.toInt()+1000)){
			if(k%2 == 0) continue
			val i = k.toDouble()
			if(abs((floor(i/2)*c+ceil(i/2)*h)/(i) - t) < mindif){
				mindif = abs((floor(i/2)*c+ceil(i/2)*h)/(i) - t)
				value = i
			}
		}

		println(value.toInt())
	}
}

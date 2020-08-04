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
		n = n*2.0 + 1.0

		//try round down and round up
		var floorn = floor(n)
		//var floor = ((floorn/2)*c + (floorn/2+1)*h)/floorn

		var ceiln = ceil(n)
		//var ceil = ((ceiln/2)*c + (ceiln/2+1)*h)/ceiln

		var mindif = -1.0
		var value = -1.0

		if(abs(t-h) <= abs(t-((h+c)/2))){
			mindif = abs(t-h)
			value = 1.0
		} else {
			mindif = abs(t-((h+c)/2))
			value = 2.0
		}

		if(floorn%2 == 1.0){

			for(k in max(floorn-5.0,3.0).toInt() until (floorn+5.0).toInt() step 2){
				val i = k.toDouble()
				if(abs((floor(i/2)*c+ceil(i/2)*h)/(i) - t) < mindif){
					mindif = abs((floor(i/2)*c+ceil(i/2)*h)/(i) - t)
					value = i
				}
			}


			println(value.toInt())
		} else {
			for(k in max(ceiln-5.0,3.0).toInt() until (ceiln+5.0).toInt() step 2){
				val i = k.toDouble()
				if(abs((floor(i/2)*c+ceil(i/2)*h)/(i) - t) < mindif){
					mindif = abs((floor(i/2)*c+ceil(i/2)*h)/(i) - t)
					value = i
				}
			}


			println(value.toInt())
		}
	}
}

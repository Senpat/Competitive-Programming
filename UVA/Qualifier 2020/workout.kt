import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	val jtimes = f.readLine().split(" ").map{it.toInt()}

	val others = Array<Triple<Int,Int,Int>>(10){
		val (a,b,c) = f.readLine().split(" ").map{it.toInt()}
		Triple(a,b,c)
	}

	val u = IntArray(10){others[it].first}
	val r = IntArray(10){others[it].second}
	val s = IntArray(10){others[it].third}

	var t = 0

	for(loop in 0 until 3){
		for(machine in 0 until 10){
			if(t >= s[machine] && (t-s[machine])%(u[machine]+r[machine]) < u[machine]){
				t += u[machine] - (t-s[machine])%(u[machine]+r[machine])
			}

			if(s[machine] < t+jtimes[machine*2]){
				if((t-s[machine])/(u[machine]+r[machine]) != (t+jtimes[machine*2]-s[machine])/(u[machine]+r[machine])){
					s[machine] = t+jtimes[machine*2]
				} else if((t-s[machine])%(u[machine]+r[machine]) < u[machine] || (t+jtimes[machine*2]-s[machine])%(u[machine]+r[machine]) < u[machine]){
					s[machine] = t+jtimes[machine*2]
				}
			}

			t += jtimes[machine*2] + jtimes[machine*2+1]

			//println(t)
		}
	}

	t -= jtimes[19]
	println(t)
}

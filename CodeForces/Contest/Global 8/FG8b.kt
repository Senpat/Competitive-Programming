import java.io.*
import java.util.*
import kotlin.math.*
import kotlin.random.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	val n = f.readLine().toInt()
	/*
	if(n==3){
		println("0")
		return
	} else if(n==4 || n==5){
		println("2 1 3")
		val nothing = f.readLine().toInt()
		println("0")
		return
	}*/

	val random = Random(100)

	val lamps = BooleanArray(n+1)

	for(k in 0 until 10000){
		//pick two numbers
		val elig = mutableListOf<Int>()
		for(j in 1..n){
			if(!lamps[j]) elig.add(j)
		}

		var a = elig[random.nextInt(1,elig.size)]
		var b = elig[random.nextInt(1,elig.size)]
		while(b==a) b = elig[random.nextInt(1,elig.size)]

		lamps[a] = true
		lamps[b] = true

		println("2 $a $b")
		System.out.flush()

		val v = f.readLine().toInt()
		lamps[v] = false
		if(v == n) lamps[1] = false
		else lamps[v+1] = false
	}

	println(0)
}

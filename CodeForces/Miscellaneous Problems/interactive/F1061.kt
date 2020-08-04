//Lost Root
import java.io.*
import java.util.*
import kotlin.math.*
import kotlin.random.*
//WA doesn't work
fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	val (n,m) = f.readLine().split(" ").map{it.toInt()}

	val freqs = IntArray(n+1){0}

	val random = Random(1023)

	for(k in 0 until 60){
		var a = 0
		var b = 0
		while(a == b){
			a = random.nextInt(1,n+1)
			b = random.nextInt(1,n+1)
		}

		for(j in 1..n){
			if(j == a || j == b) continue
			println("? $a $j $b")
			System.out.flush()

			val s = f.readLine()
			if(s.equals("Yes")) freqs[j]++
		}
	}

	var max = 0
	var answer = 0

	for(k in 1..n){
		if(freqs[k] > max){
			max = freqs[k]
			answer = k
		}
	}

	println("! $answer")
}

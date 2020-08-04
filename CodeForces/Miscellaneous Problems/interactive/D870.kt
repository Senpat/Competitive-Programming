import java.io.*
import java.util.*
import kotlin.math.*
//tutorial
fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	val n = f.readLine().toInt()

	val a1 = IntArray(n)
	val a2 = IntArray(n)

	for(k in 0 until n){
		println("? 0 $k")
		System.out.flush()

		a1[k] = f.readLine().toInt()

		println("? $k 0")
		System.out.flush()
		a2[k] = f.readLine().toInt()
	}

	//try all b0

	fun check(p : IntArray, b : IntArray) : Boolean{
		for(k in 0 until n){
			if(a1[k] != p[0] xor b[k]) return false
		}
		return true
	}

	var num = 0
	val answer = mutableListOf<Int>()

	for(k in 0 until n){

		//make p and b
		val p = IntArray(n)
		val b = IntArray(n)

		var fail = false
		for(j in 0 until n){
			p[j] = k xor a2[j]
			if(p[j] >= n){
				fail = true
				break
			}
			b[p[j]] = j
		}

		if(fail) continue

		if(check(p,b)){
			if(answer.size == 0){
				for(j in 0 until n) answer.add(p[j])
			}
			num++
		}



	}

	println("!")
	println(num)
	println(answer.joinToString(" "))

}

import java.io.*
import java.util.*
import kotlin.math.*
//finds lcs, WRONG
fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	val n = f.readLine().toInt()
	val a1 = f.readLine().map{Character.getNumericValue(it)}.toIntArray()
	val b1 = f.readLine().map{Character.getNumericValue(it)}.toIntArray()

	val a = mutableListOf<Int>()
	val b = mutableListOf<Int>()
	var counter = 0

	for(k in 0 until n){
		if(a1[k] != b1[k]){
			a.add(a1[k])
			b.add(b1[k])
			counter += a1[k]*2-1
		}
	}

	if(counter != 0){
		println(-1)
		return
	}

	if(a.size == 0){
		println(0)
		return
	}
	var curc = a[0]
	var maxdis = 1
	var curdis = 1

	for(k in 1 until a.size){
		if(a[k] == curc){
			curdis++
			maxdis = max(maxdis,curdis)
		} else {
			curc = a[k]
			curdis = 1
		}
	}

	println(maxdis)

}

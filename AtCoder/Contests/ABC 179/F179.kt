import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	val (n,q) = f.readLine().split(" ").map{it.toInt()}

	var stones = (n-2).toLong()*(n-2).toLong()

	val tset1 = TreeSet<Int>()
	val tset2 = TreeSet<Int>()

	tset1.add(n)
	tset2.add(n)

	val array1 = IntArray(n+1)
	val array2 = IntArray(n+1)

	array[n] = n
	array[n] = n

	for(k in 0 until q){
		val (i,x) = f.readLine().split(" ").map{it.toInt()}

	}
}

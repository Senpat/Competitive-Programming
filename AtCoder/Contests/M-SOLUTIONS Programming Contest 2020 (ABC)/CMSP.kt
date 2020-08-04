import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	val (n,m) = f.readLine().split(" ").map{it.toInt()}
	val array = f.readLine().split(" ").map{it.toInt()}

	for(k in m until n){
		if(array[k] > array[k-m]) println("Yes")
		else println("No")
	}

	
}

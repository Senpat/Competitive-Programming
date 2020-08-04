import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	for(q in 1..f.readLine().toInt()){
		val n = f.readLine().toInt()

		if(n==1){
			println(1)
		} else if(n==2){
			println(1)
		} else if(n==3){
			println(2)
		} else {
			println((n+1)/2)
		}
	}
}

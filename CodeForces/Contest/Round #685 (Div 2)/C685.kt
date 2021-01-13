import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	for(q in 1..f.readLine().toInt()){
		val (n,m) = f.readLine().split(" ").map{it.toInt()}
		val a = f.readLine().toCharArray().sorted().toCharArray()
		val b = f.readLine().toCharArray().sorted().toCharArray()

		fun increase(x : Int) : Boolean{
			for(k in x until x+m){
				if(a[k] != a[x]) return false
			}

			for(k in x until x+m){
				a[k] = b[x]
			}
			return true
		}

		fun checkequal() : Boolean{
			for(k in 0 until n){
				if(a[k] != b[k]) return false
			}
			return true
		}

		var fail = false
		for(k in 0..(n-m)){
			if(a[k] > b[k]){
				fail = true
				break
			} else if(a[k] < b[k]){
				if(!increase(k)){
					fail = true
					break
				}
			}
		}
		
		if(fail || !checkequal()){
			println("No")
		} else {
			println("Yes")
		}
	}
}

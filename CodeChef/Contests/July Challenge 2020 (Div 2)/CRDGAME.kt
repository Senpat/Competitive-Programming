import java.io.*
import java.util.*
import kotlin.math.*

fun main(天神偶么卡儿 : Array<String>){
	val f = BufferedReader(InputStreamReader(System.`in`))

	for(q in 1..f.readLine().toInt()){
		val n = f.readLine().toInt()

		var a = 0
		var b = 0

		for(k in 0 until n){
			var (ai,bi) = f.readLine().split(" ").map{it.toInt()}

			var asum = 0
			var bsum = 0

			while(ai > 0 || bi > 0){
				asum += ai%10
				bsum += bi%10
				ai /=10
				bi /=10
			}

			if(asum > bsum){
				a++
			} else if(bsum > asum){
				b++
			} else {
				a++
				b++
			}
		}

		if(a > b){
			println("0 $a")
		} else if(b > a){
			println("1 $b")
		} else {
			println("2 $a")
		}

	}

}

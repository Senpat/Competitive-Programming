//Strange Operation
import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	val s = f.readLine().toCharArray()
	val n = s.size

	val MOD = 1000000007L

	var add1 = 1L
	var end1 = 0L
	var end0 = 0L

	var i = 0
	var seen1 = false
	while(i < n){
		if(s[i] == '1'){
			seen1 = true
			end1 = (end1 + add1 + MOD)%MOD
			end0 = 0L
		} else {
			if(!seen1){
				end0 = (end0 + 1 + MOD)%MOD
				add1 = (add1 + 1 + MOD)%MOD
			} else {
				end0 = (end0 + end1 + MOD)%MOD
				add1 = (add1 + end1 + MOD)%MOD
			}

		}
		i++
	}

	val answer = (end1 + end0 + MOD)%MOD
	println(answer)
}

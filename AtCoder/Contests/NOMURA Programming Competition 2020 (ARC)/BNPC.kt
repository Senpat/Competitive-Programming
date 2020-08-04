import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	val s = f.readLine()
	for(i in s){
		if(i == '?') print('D')
		else print(i)
	}
}

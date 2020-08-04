import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	var (a,b,c) = f.readLine().split(" ").map{it.toLong()}

	println("Second")
	System.out.flush()

	var prev = -1
	while(true){
		var y = f.readLine().toLong()
		if(y == 0L || y == -1L) break

		var min = Long.MAX_VALUE
		var id = -1

		//check a
		if(prev != 1){
			if(a+y != b && a+y != c){
				if(b > c){
					if(a+y != c + (b-c)*2){
						if(a+y < min){
							min = a+y
							id = 1
						}
					}
				} else {
					if(a+y != b + (c-b)*2){
						if(a+y < min){
							min = a+y
							id = 1
						}
					}
				}
			}
		}

		//check b
		if(prev != 2){
			if(b+y != a && b+y != c){
				if(a > c){
					if(b+y != c + (a-c)*2){
						if(b+y < min){
							min = b+y
							id = 2
						}
					}
				} else {
					if(b+y != a + (c-a)*2){
						if(b+y < min){
							min = b+y
							id = 2
						}
					}
				}
			}
		}

		//check c
		if(prev != 3){
			if(c+y != a && c+y != b){
				if(a > b){
					if(c+y != b + (a-b)*2){
						if(c+y < min){
							min = c+y
							id = 3
						}
					}
				} else {
					if(c+y != a + (b-a)*2){
						if(c+y < min){
							min = c+y
							id = 3
						}
					}
				}
			}
		}

		println(id)
		System.out.flush()

		prev = id
		if(id == 1) a+=y
		if(id == 2) b+=y
		if(id == 3) c+=y
	}

}

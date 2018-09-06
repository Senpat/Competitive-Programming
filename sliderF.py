import sys
import time
import random

globnscount = 0
def neighbors(puzzle):
	look = [[1,3],[0,2,4],[1,5],[0,4,6],[1,3,5,7],[2,4,8],[3,7],[4,6,8],[5,7]]
	ind = puzzle.index("_")
	lis = []
	for x in look[ind]:
		lis.append(swap(puzzle,ind,x))

	#print(puzzle + " " + str(lis))
	return lis



def swap(puzzle,a,b):
    ret = puzzle
    #print(ret + " " + str(a) + " " + str(b))
    ret = ret[:a] + puzzle[b] + ret[a+1:]
    ret = ret[:b] + puzzle[a] + ret[b+1:]

    return ret

def solve(puzzle,finalpuzzle):
    global globnscount
    #print(puzzle)
    queue = [puzzle]
    seendict = {puzzle:None}
    cur = ""
    answerfound = False

    while(len(queue)>0):
        cur = queue.pop(0)

        if cur == finalpuzzle:
			#answer(seendict)
            answerfound = True
            break
        nei = neighbors(cur)

        for i in nei:
            if(i not in seendict):
                queue.append(i)
                seendict[i] = cur

    if not answerfound:
        globnscount = globnscount+1
        print("NO SOLUTIONS")


def answer(dict):
	alist = []
	cur = finalstr
	alist.append(finalstr)
	while(cur != firststr):
		cur = dict[cur]
		alist.insert(0,cur)


def printlist(list,num):
	rows = len(list)//num + 1

	print("\n\n")															#just spacing
	print("Number of Moves: " + str(len(list)-1))							#-1 to exclude original board

	for i in range(rows):													#how many rows
		for k in range(3):													#every row of board has 3 rows of numbers
			for j in range(num):											#how many boards in every column
				if(i*num+j<len(list)):
					for l in range(3):										#3 numbers per board
						print(str(list[i*num+j][k*3+l]) + " ",end = "")
					print("\t\t",end = "")
			print("\n")
		print("\n")

def generate(num):
    list = []

    for i in range(num):
        curlist = random.sample(range(9),9)
        st = "";
        for j  in curlist:
            if j==0:
                st = st + "_"
            else :
                st = st + str(j)

        list.append(st)

    return list


if __name__ == "__main__":
    start_time = time.time()

    finalstr = "12345678_"

    for i in generate(100):
        solve(i,finalstr)


    print(globnscount)
    elapsed_time = time.time()-start_time
    print("Runtime (seconds): ",elapsed_time)

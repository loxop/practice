import sys
import random

millions = 1
if len(sys.argv) > 1:
	millions = int(sys.argv[1])
else:
	print("Usage: python populate.py [N]")
	print("\t1 <= N <= 1000, in millon words.")
	print("\tDefault is 1 million, 1000 x 1000 words.")
	print("*Caution: when N=100, size of the output file will be about 2.xGB.")

in_f = open('words1000.txt', 'r')
out_f = open('words%dm.txt' % millions, 'w')

words = []
while True:
	word = in_f.readline()
	if not word:
		break
	words.append(word.rstrip().capitalize())

infixes = []
if millions > 1:
	for i in range(millions):
		while True:
			infix = words[random.randrange(len(words))]
			if infix not in infixes:
				break;
		infixes.append(infix)
	infixes.sort()
else:
	infixes.append('')

for prefix in words:
	for infix in infixes:
		for postfix in words:
			out_f.write(prefix + infix + postfix + '\n')

in_f.close()
out_f.close()


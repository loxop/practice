in_f = open('words1000_short.txt', 'r')
out_f = open('words1000_short_filtered.txt', 'w')

words = []
while True:
	line = in_f.readline()
	if not line:
		break
	word = line.rstrip()
	if len(word) < 5 and word not in words:
		words.append(word)

for word in words:
	out_f.write(word + '\n')

in_f.close()
out_f.close()


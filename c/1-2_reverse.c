#include <stdio.h>
#include <string.h>

void reverse_string(char* str)
{
	int length = strlen(str);
	int i, t;
	if (length <= 0)
		return;
	for(i = 0; 2 * i < length - 1; i++)
	{
		t = str[i];
		str[i] = str[length-i-1];
		str[length-i-1] = t;
	}
}

int main(int argc, char** argv)
{
	if (argc < 2)
		return 1;
	printf("original: %s\n", argv[1]);
	reverse_string(argv[1]);
	printf("reversed: %s\n", argv[1]);
	return 0;
}

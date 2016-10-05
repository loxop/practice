#include <stdio.h>
#include <string.h>

#define CHARSET_SIZE 128

int is_anagram(char *a, char *b)
{
	// init variables.
	int i;
	int bucket_a[CHARSET_SIZE] = {0,}, bucket_b[CHARSET_SIZE] = {0,};
	/*
	for (i = 0; i < CHARSET_SIZE; i++){
		bucket_a[i] = 0;
		bucket_b[i] = 0;
	}
	*/

	// exception check
	if (a == 0 && b == 0) {
		return 1;
	}
	if (a == 0 || b == 0) {
		return 0;
	}

	// default check
	if (strlen(a) != strlen(b)){
		return 0;
	}

	// fill the buckets
	for (i = 0; i < strlen(a); i++) {
		bucket_a[a[i]]++;
		bucket_b[b[i]]++;
	}

	// check bucket identity
	for (i = 0; i < CHARSET_SIZE; i++) {
		if (bucket_a[i] != bucket_b[i]) {
			return 0;
		}
	}
	return 1;
}

int main(int argc, char** argv) {
	int result;
	if (argc < 3){
		return 1;
	}

	result = is_anagram(argv[1], argv[2]);
	if (result){
		printf("Anagram!\n");
	} else {
		printf("Not an Anagram!\n");
	}
	return 0;
}

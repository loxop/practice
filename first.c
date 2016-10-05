#include <stdio.h>
#include <string.h>

int main(int argc, char** argv){
	char *string = argv[1];
	int arr[26];
	for (int i = 0; i < 26; i++){
		arr[i] = 0;
	}
	for (int i = 0; i < strlen(string); i++){
		if (arr[string[i] - 'a'] > 0){
			printf("not unique!: %c\n", string[i]);
			return 0;
		}
		arr[string[i] - 'a'] = 1;
	}
	printf("unique!\n");
	return 0;
}

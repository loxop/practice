#include <stdio.h>
#include <string.h>

void remove_dup(char *str)
{
	// init
	int i, k; // for indices
	long long f = 0, s = 0; // two 64-bit variables to cover entire ASCII chars

	if (strlen(str) < 2)
		return;

	// Check and mark duplicated chars.
	for (i = 0; i < strlen(str); i++)
	{
		if (str[i] < 64)
		{
			if ((f >> str[i]) % 2 == 0)
			{ // first occurence of the letter
				f |= (1ll << str[i]); // since str[i] can be > 32, '1ll' is needed.
			}
			else // duplicated
			{
				str[i] = -1;
			}
		}
		else // str[i] >= 64
		{
			if ((s >> (str[i]-64)) % 2 == 0)
			{
				s |= (1ll << (str[i] - 64));
			}
			else
			{
				str[i] = -1;
			}
		}
	}

	// Remove duplicated chars
	k = 0; // index for next character location.
	for (i = 0; i < strlen(str); i++)
	{
		if (str[i] < 0) // duplicated char.
		{
			continue;
		}
		if (k < i)
		{
			str[k] = str[i];
		}
		k++;
	}
	str[k] = '\0';
}

int main(int argc, char** argv)
{
	if (argc < 2)
	{
		return 1;
	}

	printf("original string: %s\n", argv[1]);
	remove_dup(argv[1]);
	printf("removed string : %s\n", argv[1]);
	return 0;
}


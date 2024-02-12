#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// Split string at delim into n pieces
char** split(char* string, char* delim, int n) {
    int i = 0;
    char *p = strtok(string, delim);
    char **array = (char **)malloc(sizeof(char *) * (n + 1));

    while (p != NULL)
    {
        array[i] = p;
        i++;
        p = strtok(NULL, delim);
    }
    return array;
}

// Removes all instances of symbol and inserts new
void replace(char* string, char symbol, char new) {
    for(int i = 0; i < strlen(string); i++) {
        if(string[i] == symbol) {
            string[i] = new;
        }
    }
}

// Returns how many times symbol occurs in string
int count_symbol(char* string, char symbol) {
    int result = 0;
    for(int i = 0; i < strlen(string); i++) {
        if(string[i] == symbol) {
            result += 1;
        }
    }
    return result;
}

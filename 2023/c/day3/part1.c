#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "../libs/list.h"

int main() {
    char buf[64];
    long sum = 0;
    int rows = 0;
    list_t* list = NULL;

    // get how many rows first... feels inefficient
    while(fgets(buf,1,stdin) != NULL) {
        rows++;
    }

    array_of_strings = malloc(rows * sizeof(char*));

    printf("The sum is: %ld\n", sum);
    return 0;
}

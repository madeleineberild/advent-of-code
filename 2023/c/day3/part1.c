#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main() {
    char buf[64];
    long sum = 0;
    int rows = 0;

    while(fgets(buf,1,stdin) != NULL) {
        rows++;
    }

    printf("The sum is: %ld\n", sum);
    return 0;
}

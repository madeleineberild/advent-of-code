#include <stdio.h>
#include <stdlib.h>

int main() {
    char buf[64];
    long max = 0;
    long sum = 0;
    while(fgets(buf,sizeof(buf),stdin) != NULL) {
        if (buf[0] == '\n') {
            if (sum > max) {
                max = sum;
            }
            sum = 0;
            continue;
        }

        sum += strtol(buf,NULL,10);
    }

    printf("The maximum amount of calories is: %ld cals.\n", max);
    return 0;
}

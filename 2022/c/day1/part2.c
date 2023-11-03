#include <stdio.h>
#include <stdlib.h>

void updateTopThree(long *max, long sum) {
    for(int i = 0; i < 3; i++) {
        if(sum <= max[i]) {
            continue;
        }
        for(int j = 2; j > i; j--) {
            max[j] = max[j-1];
        }
        max[i] = sum;
        break;
    }
}

int main() {
    char buf[64];
    long max[3] = {0, 0, 0};
    long sum = 0;
    while(fgets(buf,sizeof(buf),stdin) != NULL) {
        if (buf[0] == '\n') {
            updateTopThree(max, sum);
            sum = 0;
            continue;
        }

        sum += strtol(buf,NULL,10);
    }
    updateTopThree(max, sum);

    long result = max[0] + max[1] + max[2];
    printf("The top three amount of calories is: %ld cals.\n", result);
    return 0;
}

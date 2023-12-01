#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>

int start_word(char c) {
    char valid[5] = {'o', 't', 'f', 's', 'e'};
    for(int i = 0; i < 5; i++) {
        if(valid[i] == c) {
            return 1;
        }
    }
    return 0;
}

// one
// two
// three
// four
// five
// six
// seven

int main() {
    char buf[64];
    int set_first = 0;
    long first_digit = 0;
    long second_digit = 0;
    long sum = 0;
    long digit = 0;

    int started_word = 0;
    char* first_two[2];
    int* pos = 0;

    while(fgets(buf,sizeof(buf),stdin) != NULL) {
        for(int i = 0; i < 64; i++) {
            if(buf[i] == '\0') {
                break; // string terminator does not count as digit
            }
            if(!isdigit(buf[i])) {
                if(started_word) {
                    started_word = continue_word
                } else {
                    started_word = start_word(buf[i]);
                }
            } else {
                digit = (long)buf[i] - 48; //char 0 has ascii value 48
                if(!set_first) {
                    first_digit = digit * 10;
                    second_digit = digit; // in case there is only one digit
                    set_first = 1;
                } else {
                    second_digit = digit;
                }
            }

        }

        set_first = 0;
        sum += first_digit + second_digit;
    }

    printf("The sum of the calibration values is: %ld\n", sum);
    return 0;
}

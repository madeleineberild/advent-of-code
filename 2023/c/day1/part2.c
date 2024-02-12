#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>

int set_first = 0;
long first_digit = 0;
long second_digit = 0;

void set_digit(int digit) {
    if(!set_first) {
        first_digit = digit * 10;
        second_digit = digit; // in case there is only one digit
        set_first = 1;
    } else {
        second_digit = digit;
    }
}

int word_loop(char* string, int i) { //i hate this, change as soon as it works
    switch(string[i]) {
        case 'o': //one
            i++;
            if(string[i] == 'n') {
                i++;
                if(string[i] == 'e') return 1;
            }
            break;

        case 't': //two, three
            i++;
            if(string[i] == 'w') { //two
                i++;
                if(string[i] == 'o') return 2;
            }
            else if(string[i] == 'h') { //three
                i++;
                if(string[i] == 'r') {
                    i++;
                    if(string[i] == 'e') {
                        i++;
                        if(string[i] == 'e') return 3;
                    }
                }
            }
            break;

        case 'f':
            i++;
            if(string[i] == 'o') { //four
                i++;
                if(string[i] == 'u') {
                    i++;
                    if(string[i] == 'r') return 4;
                }
            }
            else if(string[i] == 'i') { //five
                i++;
                if(string[i] == 'v') {
                    i++;
                    if(string[i] == 'e') return 5;
                }
            }
            break;

        case 's': //six, seven
            i++;
            if(string[i] == 'i') { //six
                i++;
                if(string[i] == 'x') return 6;
            }
            else if(string[i] == 'e') { //seven
                i++;
                if(string[i] == 'v') {
                    i++;
                    if(string[i] == 'e') {
                        i++;
                        if(string[i] == 'n') return 7;
                    }
                }
            }
            break;

        case 'e': //eight
            i++;
            if(string[i] == 'i') {
                i++;
                if(string[i] == 'g') {
                    i++;
                    if(string[i] == 'h') {
                        i++;
                        if(string[i] == 't') return 8;
                    }
                }
            }
            break;

        case 'n': //nine
            i++;
            if(string[i] == 'i') {
                i++;
                if(string[i] == 'n') {
                    i++;
                    if(string[i] == 'e') return 9;
                }
            }
            break;

        default:
            return -1;
    }
    return -1;
}

int main() {
    char buf[64];
    long sum = 0;
    long digit = 0;

    while(fgets(buf,sizeof(buf),stdin) != NULL) {
        for(int i = 0; i < 64; i++) {
            if(buf[i] == '\0') {
                break; // string terminator does not count as digit
            }
            if(!isdigit(buf[i])) {
                digit = word_loop(buf, i);
                if(digit != -1){
                    set_digit(digit);
                }
            } else {
                digit = (long)buf[i] - 48; //char 0 has ascii value 48
                set_digit(digit);
            }
        }

        set_first = 0;
        sum += first_digit + second_digit;
    }

    printf("The sum of the calibration values is: %ld\n", sum);
    return 0;
}

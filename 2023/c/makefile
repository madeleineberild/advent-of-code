DAY=day3
INPUT=../input/$(DAY).txt
TESTINPUT=../input/testinput.txt

both: part1 part2

testpart1: compilelibs
	gcc -o target/$(DAY)_part1 $(DAY)/part1.c target/stringlib.o target/list.o
	./target/$(DAY)_part1 < $(TESTINPUT)

testpart2: compilelibs
	gcc -o target/$(DAY)_part2 $(DAY)/part2.c target/stringlib.o target/list.o
	./target/$(DAY)_part2 < $(TESTINPUT)

part1: compilelibs
	gcc -o target/$(DAY)_part1 $(DAY)/part1.c target/stringlib.o target/list.o
	./target/$(DAY)_part1 < $(INPUT)

part2: compilelibs
	gcc -o target/$(DAY)_part2 $(DAY)/part2.c target/stringlib.o target/list.o
	./target/$(DAY)_part2 < $(INPUT)

compilelibs:
	gcc -o target/stringlib.o -c libs/stringlib.c
	gcc -o target/list.o -c libs/list.c

clean:
	rm -rf target/*

DAY=day2
INPUT=../input/$(DAY).txt
TESTINPUT=../input/testinput.txt

both: part1 part2

testpart1:
	gcc -o target/$(DAY)_part1 $(DAY)/part1.c
	./target/$(DAY)_part1 < $(TESTINPUT)

testpart2:
	gcc -o target/$(DAY)_part2 $(DAY)/part2.c
	./target/$(DAY)_part2 < $(TESTINPUT)

part1:
	gcc -o target/$(DAY)_part1 $(DAY)/part1.c
	./target/$(DAY)_part1 < $(INPUT)

part2:
	gcc -o target/$(DAY)_part2 $(DAY)/part2.c
	./target/$(DAY)_part2 < $(INPUT)

clean:
	rm -rf target/*

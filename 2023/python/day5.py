from util import parse


def partOne(input):
    pass


def partTwo(input):
    pass


def main():
    input = "../input/testinput.txt"
    # input = "../input/day5.txt"
    scratchcards = parse(input)

    output1, cards = partOne(scratchcards)
    print(f"Part one: The scratchcards are worth in total: {output1}")

    output2 = partTwo(cards)
    print(f"Part two: The total number of scratchcards is: {output2}")


if __name__ == "__main__":
    main()

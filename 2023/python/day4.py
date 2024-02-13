from pathlib import Path


def parse(path: str) -> list[str]:
    file = open(Path(path), "r")
    inputStrings = list()
    for line in file:
        inputStrings.append(line)

    return inputStrings


class Card:
    def __init__(self, winningNumbers, numbers):
        self.winningNumbers = winningNumbers
        self.numbers = numbers


def partOne(scratchcards):
    sum = 0
    cards = list()

    for line in scratchcards:
        valueLines = line.split(':')
        numberLines = valueLines[1].split('|')
        winningStrings = numberLines[0].split(' ')
        winning = list(map(lambda x: int(x),
                           filter(lambda x: x != '', winningStrings)))
        numberStrings = numberLines[1].split(' ')
        numbers = list(map(lambda x: int(x),
                           filter(lambda x: x != '', numberStrings)))
        cards.append(Card(winning, numbers))

    for card in cards:
        partSum = 0
        for win in card.winningNumbers:
            if win in card.numbers:
                if partSum == 0:
                    partSum = 1
                else:
                    partSum = partSum * 2
        sum += partSum

    return sum


def main():
    # test = "../input/testinput.txt"
    input = "../input/day4.txt"
    scratchcards = parse(input)

    output1 = partOne(scratchcards)
    print(f"Part one: The scratchcards are worth in total: {output1}")

    # output2 = partTwo(inputStrings)
    # print(f"Part two: The sum of all the gears is: {output2}")


if __name__ == "__main__":
    main()

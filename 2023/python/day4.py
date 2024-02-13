from util import parse


class Card:
    def __init__(self, winningNumbers, numbers):
        self.winningNumbers = winningNumbers
        self.numbers = numbers
        self.matchingNumbers = 0


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
                card.matchingNumbers += 1
                if partSum == 0:
                    partSum = 1
                else:
                    partSum = partSum * 2
        sum += partSum

    return sum, cards


def partTwo(cards):
    copies = [1 for x in range(len(cards))]
    for index, card in enumerate(cards):
        n = card.matchingNumbers
        for i in range(1, n+1):
            for instance in range(copies[index]):
                copies[index+i] += 1

    return sum(copies)


def main():
    # test = "../input/testinput.txt"
    input = "../input/day4.txt"
    scratchcards = parse(input)

    output1, cards = partOne(scratchcards)
    print(f"Part one: The scratchcards are worth in total: {output1}")

    output2 = partTwo(cards)
    print(f"Part two: The total number of scratchcards is: {output2}")


if __name__ == "__main__":
    main()

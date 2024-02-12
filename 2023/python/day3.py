from pathlib import Path


input = "../input/day3.txt"


def parse() -> list[str]:
    file = open(Path(input), "r")
    inputStrings = list()
    for line in file:
        inputStrings.append(line)

    return inputStrings


def isSymbol(i, j, matrix, symbols) -> bool:
    # out of bounds
    # print(f"i: {i}, j: {j}")
    if i < 0 or i >= len(matrix) or j < 0 or j >= len(matrix[0]):
        return False
    else:
        if matrix[i][j] in symbols:
            return True

    return False


# Checks surrounding six positions
def adjacentSymbols(x, y, matrix, symbols) -> bool:
    for otherX in range(x-1, x+2):
        for otherY in range(y-1, y+2):
            if x == otherX and y == otherY:
                pass
            if isSymbol(otherX, otherY, matrix, symbols):
                return True

    return False


def partOne(inputStrings, symbols) -> int:
    sum = 0
    number = -1
    isAdjacent = False

    for i, line in enumerate(inputStrings):
        for j, char in enumerate(line):
            if char.isdigit():
                if number == -1:  # beginning of number
                    number = int(char)
                    isAdjacent = adjacentSymbols(i, j,
                                                 inputStrings, symbols)
                else:  # number continues
                    number = number * 10 + int(char)
                    if isAdjacent:
                        continue
                    else:
                        isAdjacent = adjacentSymbols(i, j,
                                                     inputStrings, symbols)
            else:
                if number != -1 and isAdjacent:  # Finish a number
                    sum += number
                number = -1
                isAdjacent = False

    return sum


def findAdjacentNumbers(i, j, inputStrings) -> list[int]:
    return list(0)


def partTwo(inputStrings) -> int:
    sum = 0

    for i, line in enumerate(inputStrings):
        for j, char in enumerate(line):
            if char == "*":
                numbers = findAdjacentNumbers(i, j, inputStrings)
                if len(numbers) == 2:
                    sum += numbers[0] * numbers[1]

    return sum


def main():
    inputStrings = parse()

    output1 = partOne(inputStrings, "!#$%&'()*+,-/:;<=>?@[\]^_`{|}~")
    print(f"Part one: The sum of all the part numbers is: {output1}")

    output2 = partTwo(inputStrings)
    print(f"Part two: The sum of all the gears is: {output2}")


if __name__ == "__main__":
    main()

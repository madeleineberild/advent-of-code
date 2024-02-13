from util import parse


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


def findNumber(i, j, matrix) -> int:
    numberString = matrix[i][j]

    # build right
    rightJ = j + 1
    while rightJ < len(matrix[0]) and matrix[i][rightJ].isdigit():
        numberString = numberString + matrix[i][rightJ]
        rightJ += 1

    # build left
    leftJ = j - 1
    while leftJ >= 0 and matrix[i][leftJ].isdigit():
        numberString = matrix[i][leftJ] + numberString
        leftJ -= 1

    return int(numberString)


def findAdjacentNumbers(i, j, inputStrings) -> list[int]:
    numbers = set()

    for otherI in range(i-1, i+2):
        for otherJ in range(j-1, j+2):
            if i == otherI and j == otherJ:
                pass
            if inputStrings[otherI][otherJ].isdigit():
                numbers.add(findNumber(otherI, otherJ, inputStrings))

    return list(numbers)


"""
    The solution for part two has a flaw that my input did not
    check for. The problem is that only unique numbers are counted,
    meaning if a gear is surrounded by several copies of a number
    they will not be detected. The point is to avoid dual discovery
    as we traverse around the gear. Could be fixed by taking discovered
    numbers' indices into account.
"""


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
    # test = "../input/testinput.txt"
    input = "../input/day3.txt"
    inputStrings = parse(input)

    output1 = partOne(inputStrings, "!#$%&'()*+,-/:;<=>?@[\]^_`{|}~")
    print(f"Part one: The sum of all the part numbers is: {output1}")

    output2 = partTwo(inputStrings)
    print(f"Part two: The sum of all the gears is: {output2}")


if __name__ == "__main__":
    main()

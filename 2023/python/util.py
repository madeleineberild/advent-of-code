from pathlib import Path


def parse(path: str) -> list[str]:
    file = open(Path(path), "r")
    inputStrings = list()
    for line in file:
        inputStrings.append(line)

    return inputStrings


def extractNumbersArray(string: str):
    numbers = []

    splitString = string.replace("\n", "").split(" ")
    for s in splitString:
        if s.isdigit():
            numbers.append(int(s))

    return numbers

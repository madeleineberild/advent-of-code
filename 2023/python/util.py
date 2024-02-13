from pathlib import Path


def parse(path: str) -> list[str]:
    file = open(Path(path), "r")
    inputStrings = list()
    for line in file:
        inputStrings.append(line)

    return inputStrings
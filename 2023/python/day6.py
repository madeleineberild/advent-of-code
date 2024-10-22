from util import parse, extractNumbersArray


def race(holdTime, totalTime, distance) -> bool:
    timeToMove = totalTime - holdTime
    distanceCovered = holdTime * timeToMove
    return distanceCovered > distance


def partOne(records) -> int:
    times = []
    distances = []

    times = extractNumbersArray(records[0])
    distances = extractNumbersArray(records[1])

    numberOfWaysToWin = [0] * len(times)

    for i in range(0, len(times)):
        for holdTime in range(0, times[i]):
            if race(holdTime, times[i], distances[i]):
                numberOfWaysToWin[i] += 1

    result = 1
    for way in numberOfWaysToWin:
        result *= way

    return result


def partTwo(records) -> int:
    time = int(records[0].replace(" ", "").replace("\n", "").split(":")[1])
    distance = int(records[1].replace(" ", "").replace("\n", "").split(":")[1])

    print(time)
    print(distance)

    numberOfWaysToWin = 0
    for holdTime in range(0, time):
        if race(holdTime, time, distance):
            numberOfWaysToWin += 1

    return numberOfWaysToWin


def main():
    # input = "../input/testinput.txt"
    input = "../input/day6.txt"
    records = parse(input)

    output1 = partOne(records)
    print(f"Part one: The number you get is: {output1}")

    output2 = partTwo(records)
    print(f"Part two: The number of ways to break the record is: {output2}")


if __name__ == "__main__":
    main()

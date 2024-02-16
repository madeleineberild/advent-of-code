from util import parse


def getSeeds(almanac):
    seeds = []
    index = 0

    while almanac[index] != "\n":
        words = almanac[index].replace("\n", "").split(" ")
        for word in words:
            if word.isdigit():
                seeds.append(int(word))
        index += 1

    return seeds, index + 1


def getMap(almanac, index):
    mapping = []
    index += 1  # mapping starts below the title line

    while index < len(almanac) and almanac[index] != "\n":
        words = almanac[index].replace("\n", "").split(" ")
        destinationStart = int(words[0])
        sourceStart = int(words[1])
        rangeLength = int(words[2])

        mapping.append([sourceStart, destinationStart, rangeLength])

        index += 1

    return mapping, index + 1


def getValue(mapping, key):
    for line in mapping:
        sourceStart = line[0]
        destinationStart = line[1]
        rangeLength = line[2]
        if key >= sourceStart and key < sourceStart + rangeLength:
            diff = key - sourceStart
            return destinationStart + diff

    return key


def partOne(almanac):
    seeds, index = getSeeds(almanac)
    seedToSoil, index = getMap(almanac, index)
    soilToFertilizer, index = getMap(almanac, index)
    fertilizerToWater, index = getMap(almanac, index)
    waterToLight, index = getMap(almanac, index)
    lightToTemperature, index = getMap(almanac, index)
    temperatureToHumidity, index = getMap(almanac, index)
    humidityToLocation, index = getMap(almanac, index)

    mappings = [seedToSoil, soilToFertilizer, fertilizerToWater, waterToLight,
                lightToTemperature, temperatureToHumidity, humidityToLocation]
    lowestLocation = -1

    for seed in seeds:
        location = seed
        for mapping in mappings:
            location = getValue(mapping, location)

        if lowestLocation != -1:
            if location < lowestLocation:
                lowestLocation = location
        else:
            lowestLocation = location

    return lowestLocation


def partTwo(input):
    pass


def main():
    # input = "../input/testinput.txt"
    input = "../input/day5.txt"
    almanac = parse(input)

    output1 = partOne(almanac)
    print(f"Part one: the lowest location number that corresponds to any of the initial seed numbers is: {output1}")

    # output2 = partTwo(cards)
    # print(f"Part two: The total number of scratchcards is: {output2}")


if __name__ == "__main__":
    main()

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


def getBackwardsValue(mapping, location):
    for line in mapping:
        destinationStart = line[0]  # because we are going backwards
        sourceStart = line[1]
        rangeLength = line[2]
        if location >= sourceStart and location < sourceStart + rangeLength:
            diff = location - sourceStart
            return destinationStart + diff

    return location


def findSeed(seedRanges, seed):
    for seedRange in seedRanges:
        start = seedRange[0]
        rangeLength = seedRange[1]
        if seed >= start and seed < start + rangeLength:
            return True

    return False


def getMappings(almanac, index):
    seedToSoil, index = getMap(almanac, index)
    soilToFertilizer, index = getMap(almanac, index)
    fertilizerToWater, index = getMap(almanac, index)
    waterToLight, index = getMap(almanac, index)
    lightToTemperature, index = getMap(almanac, index)
    temperatureToHumidity, index = getMap(almanac, index)
    humidityToLocation, index = getMap(almanac, index)

    mappings = [seedToSoil, soilToFertilizer, fertilizerToWater, waterToLight,
                lightToTemperature, temperatureToHumidity, humidityToLocation]

    return mappings


def partOne(almanac):
    seeds, index = getSeeds(almanac)
    mappings = getMappings(almanac, index)

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


# Try to map everything backwards from location -> seed
# and take the first seed that is found in the original ranges
def partTwo(almanac):
    seeds, index = getSeeds(almanac)
    seedRanges = []
    i = 0
    while i + 1 < len(seeds):
        start = seeds[i]
        rangeLength = seeds[i + 1]
        seedRanges.append([start, rangeLength])
        i += 2

    mappings = getMappings(almanac, index)
    backwardMappings = list(reversed(mappings))
    foundSeed = False

    lowestLocation = 0

    while not foundSeed and lowestLocation:
        print(lowestLocation)
        seed = lowestLocation
        for mapping in backwardMappings:
            seed = getBackwardsValue(mapping, seed)

        foundSeed = findSeed(seedRanges, seed)
        if foundSeed:
            return lowestLocation
        else:
            lowestLocation += 1


def main():
    # input = "../input/testinput.txt"
    input = "../input/day5.txt"
    almanac = parse(input)

    output1 = partOne(almanac)
    print("Part one: the lowest location number that corresponds to any of " +
          f"the initial seed numbers is: {output1}")

    output2 = partTwo(almanac)
    print("Part two: the lowest location number that corresponds to any of " +
          f"the initial seed numbers: {output2}")


if __name__ == "__main__":
    main()

import matplotlib.pyplot as plt

fig, ax = plt.subplots(2)


with open("hi.txt") as count:
    lines = sorted(count.readlines())[3:100]
    words = []
    counts = []
    for line in lines:
        # extract all characters from the string, except the last character
        # then split on the space.
        word, count = line[:-1].split(" ")
        words.append(word)
        counts.append(float(count))
    ax[0].bar(words, counts)
    ax[0].set_title("Output Word Count File")

with open("hi_out.txt") as count:
    lines = sorted(count.readlines())[3:100]
    words = []
    counts = []
    for line in lines:
        # extract all characters from the string, except the last character
        # then split on the space.
        word, count = line[:-1].split(" ")
        words.append(word)
        counts.append(float(count))
    ax[1].bar(words, counts)
    ax[1].set_title("Input Word Count File")

plt.tight_layout()
plt.show()
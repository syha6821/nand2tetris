def remove_space(line):
    line = line.replace(" ", "")
    line = line.replace("\t", "")
    return line


def remove_comment(line):
    index = line.find("//")
    if index != -1:
        line = line[:index]
    return line


def remove_next_line(line):
    line = line.replace("\n", "")
    return line


def remove_empty_line(source_file):
    return [line for line in source_file if line and line != "\n"]


def remove_whitespace(source_file):
    for index, line in enumerate(source_file):
        source_file[index] = remove_comment(source_file[index])
        source_file[index] = remove_space(source_file[index])
        source_file[index] = remove_next_line(source_file[index])
    source_file = remove_empty_line(source_file)
    return source_file

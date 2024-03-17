variable_dict = {}


def make_variable_dict(source_file):
    variable_counter = 0
    for index, line in enumerate(source_file):
        if line[0] == "@":
            if line[1:] not in variable_dict.keys() and not line[1:].isnumeric():
                variable_dict.update({line[1:]: str(variable_counter + 16)})
                variable_counter = variable_counter + 1
    return source_file


def compile(source_file):
    make_variable_dict(source_file)
    for index, line in enumerate(source_file):
        if line[0] == "@":
            if line[1:] in variable_dict.keys():
                source_file[index] = "@" + variable_dict.get(line[1:])
    return source_file

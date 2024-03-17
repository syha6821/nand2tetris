def add_new_line_at_end(source_file):
    for index, line in enumerate(source_file):
        source_file[index] = source_file[index] + "\n"
    return source_file

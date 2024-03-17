label_dict = {}

def make_label_dict(source_file):
    for index,line in enumerate(source_file):
        print("index "+ str(index) + " " + line)
        if(line[0]=="("):
            if line[1:-1] not in label_dict.keys():
                label_dict.update({line[1:-1]:str(index-len(label_dict))})
                # source_file.pop(index)
                source_file[index] = " "
    return source_file

def compile(source_file):
    make_label_dict(source_file)
    for index,line in enumerate(source_file):
        if(line[0]=="@"):
            if line[1:] in label_dict.keys():
                source_file[index] = "@" + label_dict.get(line[1:])
    return [line for line in source_file if line != " "]
    # return source_file

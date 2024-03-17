import a_instruction_parser
import c_instruction_parser


def binary_instruction(instruction):
    if instruction[0] == "@":
        return a_instruction_parser.a_binary_instruction(instruction)

    else:
        return c_instruction_parser.c_binary_instruction(instruction)

def binary_instruction_linebyline(source_file):
    for index,line in enumerate(source_file):
        source_file[index] = binary_instruction(source_file[index])
    return source_file

test = "D+1;"

print(binary_instruction(test))

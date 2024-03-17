import re

# dest = comp ; jump
# 111    a c1 c2 c3 c4 c5 c6    d1 d2 d3    j1 j2 j3

# def decompose_c_instruction(instruction):
#     decomposed_symbolic_instruction = re.split('=|;',instruction)
#     # decomposed_symbolic_instruction_dict = {
#     # "dest":decomposed_symbolic_instruction[0],
#     # "comp":decomposed_symbolic_instruction[1],
#     # "jump":decomposed_symbolic_instruction[2],
#     # }
#     return decomposed_symbolic_instruction


def decompose_c_instruction(instruction):
    decomposed_symbolic_instruction = [""] * 3

    if "=" not in instruction and ";" not in instruction:
        # decomposed_symbolic_instruction[0] = ""
        decomposed_symbolic_instruction[1] = instruction
        # decomposed_symbolic_instruction[2] = ""

    elif "=" not in instruction and ";" in instruction:
        # decomposed_symbolic_instruction[0] = ""
        decomposed_symbolic_instruction[1] = instruction.split(";")[0]
        decomposed_symbolic_instruction[2] = instruction.split(";")[1]

    elif "=" in instruction and ";" not in instruction:
        decomposed_symbolic_instruction[0] = instruction.split("=")[0]
        decomposed_symbolic_instruction[1] = instruction.split("=")[1]
        # decomposed_symbolic_instruction[2] = ""

    elif ("=" and ";") in instruction:
        decomposed_symbolic_instruction = re.split("=|;", instruction)
    return decomposed_symbolic_instruction


def dest_to_binary(dest):
    match dest:
        case "":
            return "000"
        case "M":
            return "001"
        case "D":
            return "010"
        case "MD":
            return "011"
        case "A":
            return "100"
        case "AM":
            return "101"
        case "AD":
            return "110"
        case "AMD":
            return "111"


def jump_to_binary(jump):
    match jump:
        case "":
            return "000"
        case "JGT":
            return "001"
        case "JEQ":
            return "010"
        case "JGE":
            return "011"
        case "JLT":
            return "100"
        case "JNE":
            return "101"
        case "JLE":
            return "110"
        case "JMP":
            return "111"


def comp_to_binary(comp):
    match comp:
        case "0":
            return "0101010"
        case "1":
            return "0111111"
        case "-1":
            return "0111010"
        case "D":
            return "0001100"
        case "A":
            return "0110000"
        case "!D":
            return "0001101"
        case "!A":
            return "0110001"
        case "-D":
            return "0001111"
        case "-A":
            return "0110011"
        case "D+1":
            return "0011111"
        case "A+1":
            return "0110111"
        case "D-1":
            return "0001110"
        case "A-1":
            return "0110010"
        case "D+A":
            return "0000010"
        case "D-A":
            return "0010011"
        case "A-D":
            return "0000111"
        case "D&A":
            return "0000000"
        case "D|A":
            return "0010101"
        case "M":
            return "1110000"
        case "!M":
            return "1110001"
        case "-M":
            return "1110011"
        case "M+1":
            return "1110111"
        case "M-1":
            return "1110010"
        case "D+M":
            return "1000010"
        case "D-M":
            return "1010011"
        case "M-D":
            return "1000111"
        case "D&M":
            return "1000000"
        case "D|M":
            return "1010101"


def c_binary_instruction(instruction):
    decomposed_instruction = decompose_c_instruction(instruction)
    binary_instruction = (
        "111"
        + comp_to_binary(decomposed_instruction[1])
        + dest_to_binary(decomposed_instruction[0])
        + jump_to_binary(decomposed_instruction[2])
    )

    return binary_instruction

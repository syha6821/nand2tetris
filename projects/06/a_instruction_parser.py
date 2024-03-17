def a_to_binary_15digit(instruction):
    decimal_num = int(instruction[1:])
    binary_num = bin(decimal_num)[2:]

    required_zero_len = 15 - len(binary_num)

    fifteen_digit_binary = "0"*required_zero_len + binary_num

    return fifteen_digit_binary

def a_binary_instruction(instruction):
    binary_instruction = "0" + a_to_binary_15digit(instruction)
    return binary_instruction

import sys
import whitespace
import parser
import postprocessor
import handling_symbols

# read argument and naming files
arg = sys.argv
source_file_name = arg[1]
result_file_name = source_file_name.split(".")[0] + ".hack"

# read source_file
with open("./{fileName}".format(fileName=source_file_name), "r") as sf:
    print("\n********* source_file *********\n")
    source_file = sf.readlines()

# print source_file
print(source_file)


# assmeble the source_file
def assemble(source_file):
    result_file = whitespace.remove_whitespace(source_file)
    print("\n******* after whitespace *******\n")
    print(result_file)
    result_file = handling_symbols.compile(result_file)
    print("\n******* after symbol *******\n")
    print(result_file)
    result_file = parser.binary_instruction_linebyline(result_file)
    result_file = postprocessor.add_new_line_at_end(result_file)
    return result_file


assembled_code = assemble(source_file)
print("\n********* assembled_code *********\n")
print(assembled_code, "\n")

# write to result_file
with open("./{fileName}".format(fileName=result_file_name), "w") as tf:
    tf.writelines(assembled_code)

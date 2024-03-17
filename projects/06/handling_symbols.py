import predefined_symbols
import label_symbols
import variable_symbols

def compile(source_file):
    result_file = predefined_symbols.compile(source_file)
    result_file = label_symbols.compile(result_file)
    result_file = variable_symbols.compile(result_file)

    return result_file

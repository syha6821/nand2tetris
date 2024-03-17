test = [1,2,3]

for index,item in enumerate(test):
    print(item)
    if index == 0 : test.pop(index)

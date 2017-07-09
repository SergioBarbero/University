def solveSubGame(matrix):
    bestD2 = 0
    minList = []
    for index in range(len(matrix[0])):
        minList.append(min(column(matrix, index)))
    j = minList.index(max(minList))

    maxList = []
    for index2 in range(len(matrix)):
        #print(matrix[index2])
        maxList.append(max(matrix[index2]))
    i = maxList.index(min(maxList))

    solution = matrix[i][j]

    #print("matrix[",j,"][",i,"]","=", solution)
    return solution

def column(matrix, i):
    return [row[i] for row in matrix]

def solveGame(list): #5th structure game
    tree1 = [[list[0], list[1]],[list[2], list[3]]]
    tree2 = [[list[4], list[5]],[list[6], list[7]]]
    tree3 = [[list[8], list[9]],[list[10], list[11]]]
    tree4 = [[list[12], list[13]],[list[14], list[15]]]
    tree5 = [[list[16], list[17]],[list[18], list[19]]]
    tree6 = [[list[20], list[21]],[list[22], list[23]]]

    sol1 = solveSubGame(tree1)
    sol2 = solveSubGame(tree2)
    sol3 = solveSubGame(tree3)
    sol4 = solveSubGame(tree4)
    sol5 = solveSubGame(tree5)
    sol6 = solveSubGame(tree6)

    newTree = [[sol1, sol2, sol3],[sol4, sol5 ,sol6]]
    solution = solveSubGame(newTree)

    print("solution: ", solution)

    return solution

leaves = [3, -2, -1, 0, 4, 6, 2, -1, 5, 3, -2, 6, 3, 5, -4, 15, 8, 10, 2, 3, 0, 2, -2, -4]
solveGame(leaves)



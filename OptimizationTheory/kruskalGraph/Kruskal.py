def kruskalAlgorithm(matrix):
    path = ''

    x = 0
    y = 0
    for iteration in range(len(matrix) - 1):
        min = 1000000
        for i in range(len(matrix)):
            for j in range(len(matrix)):
                if matrix[i][j] < min and not (str(i) in path and str(j) in path):
                    min = matrix[i][j]
                    x = i
                    y = j
        path += str(x)
        path += str(y)
        matrix[x][y] = 10000
        matrix[y][x] = 10000
    print(path)

matrix =  [[10000, 5, 4, 10000],[5, 10000, 2, 2],[4, 2, 10000, 2],[10000, 2, 2, 100000]]
matrix1 = [[10000, 4, 2, 10000],[4, 10000, 3, 10000],[2, 3, 10000, 2],[10000, 2, 2, 100000]]
print(matrix)
kruskalAlgorithm(matrix)





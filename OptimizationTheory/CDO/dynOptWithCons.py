class CDO(object):

    def __init__(self, initial):
        self.initial = initial
        self.U = []
        self.X = []
        self.J = []


    def dynamicOptimization(self, A, B, Q, R, x0, n, k):
        t1 = 1.31
        t2 = 2.31
        toptimal = 0.001
        v1 = -1
        v2 = 2
        x = [[0 for x in range(n)] for y in range(k)]
        u = [[0 for x in range(n)] for y in range(k)]
        b = [[0 for x in range(n)] for y in range(k)]
        p = [[0 for x in range(n+1)] for y in range(k)]
        Ji = [0 for x in range(k)]
        u[0] = self.initial
        for i in range(0, k):
            x[i][0] = x0
            for j in range(1, n):
                x[i][j] = x[i][j - 1] + u[i][j - 1]

            for j in reversed(range(0, n+1)):
                if j == n :
                    p[i][j] = 0
                else:
                    p[i][j] = 2*Q*x[i][j] + p[i][j+1]*2*A*x[i][j]

            for j in range(0, n):
                b[i][j] = 2*R*u[i][j] + t1*(2*R*u[i][j] - 2*R*v1) + t2*(2*R*u[i][j] - 2*R*v2) + p[i][j + 1]
            for j in range(0, n):
               if i + 1 < k:
                   u[i + 1][j] = u[i][j] - toptimal * b[i][j]

            sum = 0
            for j in range(n):
                sum += x[i][j]**2 + u[i][j]**2 + t1*(u[i][j] - v1)*max(0, u[i][j] - v1)+ t2*(-1*u[i][j]-v2)*max(0, -1*u[i][j]- v2)
            Ji[i] = sum

            #print("U: ", *u[i] , "x: ", *x[i], "J: ", Ji[i])

        self.U = u
        self.J = Ji
        self.X = x

def writeToFile(self, inControl):
    file = open("inform2.csv","w")
    file.write("")

    problem.dynamicOptimization(0.5, 1.5, 1.5, 2, 1, 4, 15)

    print("i,u1,u2,u3,u4,x1,x2,x3,x4,J")
    file.write("i,u1,u2,u3,u4,x1,x2,x3,x4,J\n")
    for i in range(len(self.U)):
        file.write("%d," % i)
        print(i, end=" ")
        for j in range(len(self.U[0])):
            file.write("%.2f," % self.U[i][j])
            print("%.2f " % self.U[i][j], end=" ")
        for j in range(len(self.U[0])):
            file.write("%.2f," % self.X[i][j])
            print("%.2f " % self.X[i][j], end=" ")

        file.write("%.2f," % self.J[i])
        file.write("\n")
        print("%.2f" % self.J[i])

inControl = [0, 0, 1, 2]
problem = CDO(inControl)
writeToFile(problem, inControl)
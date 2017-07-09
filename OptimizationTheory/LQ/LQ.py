def linearQuadratic(A, B, Q, R, F, N, x0):
    K = [0 for x in range(N+1)]
    u = [0 for x in range(N)]
    x = [0 for x in range(N)]
    x[0] = x0
    K[N] = F
    for j in reversed(range(0, N)):
        K[j] = A*(K[j+1] - K[j+1]*B*1/(R+B*K[j+1]*B)*B*K[j+1])*A+Q
        #print(K[j])
    J0 = 1/2*x0*K[0]*x0
    for j in range(0, N):
        u[j] = -1/(R + B * K[j+1] * B)*B*K[j+1]*A*x[j]
        if(j < N-1):
            #x[j+1] = A*x[j]+B*u[j]
            x[j+1] = (1 - 1/(R + B*K[j+1]*B)*B*K[j+1])*A*x[j]
        print("U: ", u[j], "\tx: ", x[j], "\tK: ", K[j])

    print("Jo = ",J0)
    print(K[N])



linearQuadratic(4, 3, 2, 2, 0, 4, 9);


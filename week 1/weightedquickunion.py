class WQuickUnionUF:
    def __init__(self, N):
        self.id = [i for i in range(N + 1)]
        self.sz = [1 for i in range(N + 1)]

    def root(self, p):
        while p != self.id[p]:
            p = self.id[p]
        return p

    def connected(self, p, q):
        return self.root(p) == self.root(q)

    def union(self, p, q):
        pr = self.root(p)
        qr = self.root(q)
        if pr == qr:
            return
        if self.sz[pr] < self.sz[qr]:
            self.id[pr] = qr
            self.sz[qr] += self.sz[pr]
        else:
            self.id[qr] = pr
            self.sz[pr] += self.sz[qr]

class QuickUnionUF:
    def __init__(self, N):
        self.id = [i for i in range(N + 1)]

    def root(self, p):
        while p != self.id[p]:
            p = self.id[p]
        return p

    def connected(self, p, q):
        return self.root(p) == self.root(q)

    def union(self, p, q):
        pr = self.root(p)
        qr = self.root(q)
        self.id[pr] = self.id[qr]

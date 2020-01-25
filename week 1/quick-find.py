class QuickFindUF:
    def __init__(self, n):
        self.id = []
        for i in range(n+1):
            self.id.append(i)

    def isConnected(self, p, q):
        return self.id[p] == self.id[q]

    def union(self, p, q):
        pid = self.id[p]
        qid = self.id[q]
        for i in range(len(self.id)):
            if self.id[i] == pid:
                self.id[i] = qid


list = 3

esto = QuickFindUF(list)

esto.union(0,2)

esto.union(2,3)

print(esto.isConnected(0,2))

print(esto.isConnected(0,1))

print(esto.id)

from weightedquickunion import WQuickUnionUF


MEMBERS = [
    'Rafa',
    'Longo',
    'Uje',
    'Fito',
    'Ivan',
    'Pepe'
]


FRIENDSHIP_REQUESTS_BY_DATE = [
    ('Rafa', 'Longo'),
    ('Uje', 'Fito'),
    ('Ivan', 'Uje'),
    ('Fito', 'Longo'),
    ('Rafa', 'Ivan'),
    ('Pepe', 'Ivan')
]


qf = WQuickUnionUF(len(MEMBERS)-1)
date = -1
for i, union in enumerate(FRIENDSHIP_REQUESTS_BY_DATE):
    qf.union(MEMBERS.index(union[0]), MEMBERS.index(union[1]))
    if max(qf.sz) == len(MEMBERS):
        date = i
        break
print(date)

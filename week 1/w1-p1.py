from weightedquickunion import WQuickUnionUF


MEMBERS = [
    'Rafa',
    'Longo',
    'Uje',
    'Fito',
    'Ivan'
]

FRIENDSHIP_REQUESTS_BY_DATE = [
    ('Rafa', 'Longo'),
    ('Uje', 'Fito'),
    ('Ivan', 'Uje'),
    ('Fito', 'Longo'),
    ('Rafa', 'Ivan')
]

qf = WQuickUnionUF(4)
for union in FRIENDSHIP_REQUESTS_BY_DATE:
    qf.union(MEMBERS.index(union[0]), MEMBERS.index(union[1]))
    if qf.sz
print(qf.id)
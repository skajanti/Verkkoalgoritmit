# Tehokkuustestaus

Aika on keskiarvo sadasta testistä ~kartan kulmasta kulmaan.
Kartta | Koko | Dijkstra | A* | Jump Point
------------ | -------------
BigGameHunters | 512*512 | 27902331ns | 15172393ns | 102387200ns
Aftershock | 512*512 | 25929503ns | 22875471ns | 238310811ns
ArcticStation | 768*768 | 64130720ns | 59037074ns | 510574351ns
AcrosstheCape | 768*768 | 65304574ns | 57363407ns | 735822884ns
Cauldron | 1024*1024 | 124411020ns | 117931004ns | 953946097ns

Huomataan, että Dijkstra ja A* ovat saavuttaneet tehokkuustavoitteensa. Jump point taas kärsii niin absoluuttisesta kuin suhteellisesta hitaudesta.
# Testausdokumentti

Tällä hetkellä vain tietorakenteet ovat kattavasti yksikkötestattuja, niissä on sekä odotettu käyttäytyminen että virhetapauksia käsitelty.
Algoilla käydään läpi yksinkertainen kartta, mikä kertoo lähinnä sen verran pahista virheistä, että ne muutenkin huomattaisiin.

Käsin toki tulee työskennellessä testailtua, tosin ainoa mitä on juurikaan mitattu on algojen aikaa (~1min Dijkstralle, ~30sec A*:lle ja ~5sec Jump pointille 512*512 kartalla (oliot maksavat kahdella ensimmäisellä huomattavasti enemmän)),
ja käsiteltyjen solmujen suhdetta solmujen kokonaismäärään (Dijkstra 0,6; A* 0,3; Jump pointilla 0,1 tosin ei täysin mielekästä vertailla, sillä aikaa käytetään hieman eri tavalla)
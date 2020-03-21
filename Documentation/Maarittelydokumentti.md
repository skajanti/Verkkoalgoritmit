# Määrittelydokumentti

Projektissa toteutetaan javalla tasapainoisten ruutuverkkojen navigaatio puhtaalla Dijkstralla, A*:lla ja Jump pointilla. Verkko tulee olemaan [movingai:n sivulta](https://www.movingai.com/benchmarks/grids.html) saatava tai vastaavan tyyppinen joissa päästään liikkumaan kahdeksaan suuntaan. Dijkstrahan saadaan aikatehokkuudeltaan luokkaan O(m+nlogn), mitä huonommaksi eivät A* ja jump point sen optimointeina mene. Aikatehokkuus riippuu heuristiikan laadusta, missä O(m+n) olisi teroeettinen maksimi. Nähdään pian, kuinka hyvä toteutus on.

Algoritmien toteutukseen tarvitaan myös ainakin jonkin lainen binäärikeko.

#### Kivoja lisäyksiä jos jää aikaa.
* Graafinen esitys verkon ratkaisemisesta.
* Labyrintin ratkaisu.
* "Jatkuvan" alan navigaatio.

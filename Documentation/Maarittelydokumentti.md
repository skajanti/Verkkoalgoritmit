# Määrittelydokumentti

Projektissa toteutetaan javalla tasapainoisten ruutuverkkojen navigaatio puhtaalla Dijkstralla, A*:lla ja Jump pointilla. Verkko tulee olemaan [movingai:n sivulta](https://www.movingai.com/benchmarks/grids.html) saatava tai vastaavan tyyppinen joissa päästään liikkumaan kahdeksaan suuntaan. Dijkstrahan saadaan aikatehokkuudeltaan luokkaan O(m+nlogn), mitä huonommaksi eivät A* ja jump point sen optimointeina mene. Aikatehokkuus riippuu heuristiikan laadusta, missä O(m+n) olisi teroeettinen maksimi. Jump point on oikein toteutettuna ainakin muutama kertaa A*:a tehokkaampi. Nähdään pian, kuinka hyvä toteutus on.

Itse algoritmien lisäksi projektissa tullaan toteuttamaan binäärikeko ja jokin Map:ia vastaava tietorakenne. Myös kasvavasta listasta lienee hyötyä. Tietorakenteiden lisäksi ohjelman tulee myös pystyä lukemaan kartan tiedostolta ja tekemään siitä verkon.

#### Kivoja lisäyksiä jos jää aikaa.
* RSR tai joku muu etukäteisoptimointi.
* Graafinen esitys verkon ratkaisemisesta.
* Labyrintin ratkaisu.
* "Jatkuvan" alan navigaatio.

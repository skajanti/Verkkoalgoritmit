# Määrittelydokumentti

* Projekti tehdään javalla.
* Tarkoituksena on löytää tehokkain kahden pisteen välinen reitti painotetussa verkossa.
* Tarkastellaan erityisesti pelinavigaatiota.

#### Minimum viable product
* Projekti toteuttaa seuraavat algoritmit seuraavilla tehokkuustavoitteilla:
  * Bellman-Ford, aika: O(nm), tila: O(n)
  * Dijkstra, aika: O(m+nlog(n)), tila: O(n)
  * A*, Aika: O(m), tila: O(n), missä n on solmujen määrä ja m on kaarien määrä.
  * Jokin A*:a erikoistuneempi algoritmi. Jump point?
* Jokin tapa lisätä tai luoda verkkoja.
* Graafinen käyttöliittymä.

#### Lisäominaisuuksia
* Erikoituneiden verkkojen generointi.
* Graafinen esitys verkon ratkaisemisesta.
* Labyrintin ratkaisu.
* Verkkojen tallennus tietokantaan tai tekstitiedostolle.
* "Jatkuvan" alan navigaatio.

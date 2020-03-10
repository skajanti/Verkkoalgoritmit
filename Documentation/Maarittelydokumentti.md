# Määrittelydokumentti

* Projekti tehdään javalla.
* Tarkoituksena on löytää tehokkain kahden pisteen välinen reitti painotetussa verkossa.
* Verrataan algoritmeja eri kohdilta historiaa.

#### Minimum viable product
* Projekti toteuttaa seuraavat algoritmit seuraavilla tehokkuustavoitteilla:
  * Bellman-Ford, aika: O(nm), tila: O(n)
  * Dijkstra, aika: O(m+nlog(n)), tila: O(n)
  * A*, Aika: O(m), tila: O(n), missä n on solmujen määrä ja m on kaarien määrä.
* Jokin tapa lisätä tai luoda verkkoja.
* Ajanmittaus: 
```java
System.nanoTime()
```
* Graafinen käyttöliittymä.

#### Lisäominaisuuksia
* Suoritinajan mittaus.
* Jokin A*:a edistyneempi algoritmi.
* Esim. tiekarttaa matkiva verkon generointi.
* Graafinen esitys verkon ratkaisemisesta.
* Labyrintin ratkaisu.
* Verkkojen tallennus tietokantaan tai tekstitiedostolle.

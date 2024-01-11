
# 1. Ievads
## 1.1. Problēmas nostādne
Izstrādājot pieeju grāmatu drukāšanas izpildei, ir nepieciešamība maksimizēt peļņu un efektivitāti, ņemot vērā dažādus faktorus, tādus kā - peļņu par vienību, grāmatu skaitu, iekārtu skaitu un veiktspēju, kā arī, resursu lietderīgu izmantošanu.
## 1.2. Darba un novērtēšanas mērķis
Izstrādāt efektīvu pieeju peļņas un efektivitātes maksimizēšanai drukāšanas pasūtījumu izpildē, kā arī periodiski veikt datu analīzi, lai pārliecinātos par grāmatu drukāšanas plāna atbilstošu veiktspēju uzstādītajiem darba uzdevumiem.
# 2. Līdzīgo risinājumu pārskats
## 2.1. Tabula ar līdzīgajiem risinājumiem

|Līdzīgs risinājums|Apraksts|Priekšrocības un trūkumi|Svarīgas iezīmes/informācijas avoti|
|:---:|:---:|:---|:---:|
|Katana Clound Inventory | Uzņēmuma resursu plānošanas programmatūra (Enterprise Resource Planning)  | Priekšrocības: <ul><li> Pirkuma pasūtījumu vadība, pamatojoties uz precīziem nosacījumiem/ierobežojumiem</li> <li>Ražošanas operāciju vadība</li></ul> Trūkumi: <ul><li>Nav informācijas par to, kā tieši šis programmatūras algoritmi darbojas.</li><li>Varētu būt tā, ka nav tieši saistīts ar to, ko mēs meklējām/vēlamies realizēt.</li></ul>  |  Ir algoritmi, kas palīdz veikt lēmumus peļņu maksimizēšanai (iekļaujot papildus nosacījumi). <br> Avots:[ katanamrp.com](https://katanamrp.com/)|
|Dynamics 365 Business Central  |  Uzņēmuma resursu plānošanas programmatūra  | Priekšrocības: <ul><li>Ietver noderīgas funkcijas ražošanai, biznesa analīzei, prognozēšanai un projektu vadībai.</li> </ul>Trūkumi:<ul><li> Varētu būt tā, ka nav tieši saistīts ar to, ko mēs meklējām/vēlamies realizēt.</li></ul> | Ir algoritmi, kas palīdz veikt lēmumus peļņu maksimizēšanai (iekļaujot papildus nosacījumi).<br> Avots:[ dynamics.microsoft.com](https://dynamics.microsoft.com/en-us/business-central/overview/)|
|Jelgavas tipogrāfija|Tipogrāfijas uzņēmums|Priekšrocības: <ul><li>Dažāda veida sējumu drukas piedāvājums</li><li>Pieejama failu sagatavošanas pamācība</li><li>Pieejama piegāde</li><li>Videi draudzīga tipogrāfija</li><li>Failus var iesūtīt tipogrāfijas mājaslapā</li></ul>Trūkumi:<ul><li>Nav pieejama informācija par izmaksām pirms pasūtījuma veikšanas</li></ul>| Klientam ir iespēja norādīt sējuma veidu, bloka izmēru un tirāžu, kā arī grāmatas specifikācijas, bet nav redzama informācija par izmaksām. <br>Avots:[Jelgavas tipogrāfija](https://jt.lv/)|
# 3. Tehniskais risinājums
## 3.1. Prasību formulēšana
### 3.1.1. Lietotāju stāsti
|Nr.|Lietotāju stāsts|Prioritāte|
|:--:|--|:--:|
|1.|Klients vēlas pierakstīties pakalpojuma saņemšanai, jo tad viņš var saņemt gatavu produktu.|1|
|2.|Uzņēmums vēlas izvairīties no "logiem", jo tie var izraisīt zaudējumus.|5|
|3.|Privātpersona vēlas publicēt grāmatu, jo vēlas iegūt peļņu.|9|
|4.|Kompānijas vadītāji vēlas redzēt saprotamā veidā informāciju par pasūtījumiem, jo tad varētu pieņemt vislabākos lēmumus par to izpildi.|3|
|5.|Meistari vēlas saņemt precīzu informāciju par konkrēta pasūtījuma grāmatu drukāšanu, jo grib izpildīt darbu kvalitatīvi un nepārtērēt izmantotos resursus.|2|
|6.|Klients vēlas saņemt pasūtījumu nepārsniedzot noteiktu pasūtījuma izpildījuma termiņu, jo tad var bez aizkavējumiem turpināt savus plānus, kuri ir ieplānoti ar grāmatām.|6|
|7.|Uzņēmums vēlas palielināt peļņu, jo tad ir iespēja iegādāties kvalitatīvākus materiālus un tehniku.|7|
|8.|Lietotājs vēlas valodas izvēles pieejamību mājaslapā, jo viņš nezina, piemēram, latviešu valodu.|8|
|9.|Vadītājam ir nepieciešams saņemt informāciju par plānotajiem drukāšanas darbiem, jo ir nepieciešams saorganizēt iekārtu darbību turpmākam darbam.|4|

## 3.2. Algoritms
### Pseidokods
```
MAINPROGRAM
IF order in queue
   add all queued orders in list
ELSE
   END MAINPROGRAM
REPEAT 
   calculate price per book
   include price in a list
   include  machine time in a list
UNTIL all orders are processed
REPEAT
   add constraints to book amounts
UNTIL all orders are processed
   add constraints to machine time
   add objective function
   adjust goal type and constraints
   create the solver
   calculate the amount of books to print and profit
   output result
END MAINPROGRAM
```
### Peļņas maksimizācija
```
Maximize Profit = X * B1 + Y * B2 + ... + M * Book N

Restrictions: 
Cutting  = a * B1 + b * B2 + ... + J * B N <= 16 h;
Binding  = c * B1 + d * B2 + ... + k * B N <= 16 h;
Covering = e * B1 + f * B2 + ... + p * B N <= 16 h;

B1 <= 233;
B2 <= 114;
B3 <= 144;
B N <= W;
```
## 3.3. Konceptu modelis
![svarigakie_koncepti](/images_doc/attels1_updated.png)
![konceptu_modelis](/images_doc/Konceptu_modelis_final.png)

## 3.4. Tehnoloģiju steks
![tehnoloģiju steks](/images_doc/tehn_steks.png)
## 3.5. Programmatūras apraksts

# 4. Novērtējums
## 4.1. Novērtēšanas plāns

### Mērķis

### Ieejas mainīgie

### Novērtēšanas mēri

### Eksperimenta plāns un rezultāti

## 4.2. Novērtēšanas rezultāti

# 5. Secinājumi
## Prasību izpildes kontrolsaraksts
|Nr.|Lietotāja stāsts|Izpildīts(Jā/Nē)|Komentārs|
|:--:|--|--|--|
|1.|Klients vēlas pierakstīties pakalpojuma saņemšanai, jo tad viņš var saņemt gatavu produktu.|Jā||
|2.|Meistari vēlas saņemt precīzu informāciju par konkrēta pasūtījuma grāmatu drukāšanu, jo grib izpildīt darbu kvalitatīvi un nepārtērēt izmantotos resursus.|Jā||
|3.|Kompānijas vadītāji vēlas redzēt saprotamā veidā informāciju par pasūtījumiem, jo tad varētu pieņemt vislabākos lēmumus par to izpildi.|Jā||
|4.|Vadītājam ir nepieciešams saņemt informāciju par plānotajiem drukāšanas darbiem, jo ir nepieciešams saorganizēt iekārtu darbību turpmākam darbam.|Jā|Administrators redz pasūtījumu rindu un var pieņemt lēmumu drukāt vai nedrukāt|
|5.|Uzņēmums vēlas izvairīties no "logiem", jo tie var izraisīt zaudējumus.|Nē||
|6.|Klients vēlas saņemt pasūtījumu nepārsniedzot noteiktu pasūtījuma izpildījuma termiņu, jo tad var bez aizkavējumiem turpināt savus plānus, kuri ir ieplānoti ar grāmatām.|Nē||
|7.|Uzņēmums vēlas palielināt peļņu, jo tad ir iespēja iegādāties kvalitatīvākus materiālus un tehniku.|Jā||
|8.|Lietotājs vēlas valodas izvēles pieejamību mājaslapā, jo viņš nezina, piemēram, latviešu valodu.|Nē||
|9.|Privātpersona vēlas publicēt grāmatu, jo vēlas iegūt peļņu.|Jā||

# Katra grupas dalībnieka veikums
|Dalībnieks|Paveiktais darbs|
|:---:|:---|
|Ņikita|Front-end (html, css, angular)|
|Kristiāns|Atbildīgā persona par Back-end daļu, tehnoloģiju steks|
|Sergejs|Algoritma veidošana, palīdzība informācijas meklēšanā|
|Oļegs|Atbildīgā persona par Front-end daļu|
|Rihards|Konceptu modelis, palīdzība informācijas meklēšanā|
|Katrīna|Dokumentācija, prezentācijas veidošana, palīdzība informācijas meklēšana|
|Ieva|Dokumentācija, prezentācijas veidošana, palīdzība informācijas meklēšanā|
# Plakāts

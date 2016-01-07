# Uvod #

Ovdje ću ukratko objasniti što i kako trebate instalirati. Upute su za Windowse pošto većina vas koji ovo trebate radi na njima.

## Sastojci ##

  * 1 Eclipse - Eclipse je IDE (Integrated development environment)
  * 1 JDK - JDK stoji za Java Development Kit. Dakle to je paket s raznim alatima potrebnim za razvoj Java aplikacija. Npr. kompajler. JRE (Java Runtime Environment) služi samo za pokretanje aplikacija (to vjerojatno imate) i on je uključen u JDK.
  * SVN - vjerojatno ćete koristiti plugin za Eclipse

## Upute ##

  * Skinite [Eclipse Classic 3.5.0 (161 MB)](http://www.eclipse.org/downloads/download.php?file=/eclipse/downloads/drops/R-3.5.1-200909170800/eclipse-SDK-3.5.1-win32.zip) i [JDK 6](https://cds.sun.com/is-bin/INTERSHOP.enfinity/WFS/CDS-CDS_Developer-Site/en_US/-/USD/ViewProductDetail-Start?ProductRef=jdk-6u16-oth-JPR@CDS-CDS_Developer).

  * Instalirajte JDK.
  * Otpakirajte eclipse-SDK-3.5-win32.zip (ili kako se već zove) i preporučeno je prebaciti ga u Program Files te prebaciti shortcut na desktop
  * Pokrenite eclipse i izaberite neki workspace (direktorij gdje će se spremati svi projekti)

Možete koristiti bilo koji SVN klijent, ja imam jedino iskustva s onim "klasičnim" koji koristim na linuxu. Sljedeći odjeljak je o Tortoise SVN, a ovdje je o SVN pluginu za Eclipse.

  * Sada ćemo dodati plugin za Subversion. Izborna traka: Help -> Install new software
    * Stisnite na gumbić Add
    * Name: Subversive, Location: http://download.eclipse.org/technology/subversive/0.7/update-site/
    * Poklikajte dolje kvačice (onaj source ne morate)
    * Next, next, finish (prije toga se složite s nečime)
    * Restart eclipsea
    * Kad ponovno pokrenete Eclipse pitat će vas da instalirate neki Subversion Kit -- izaberite recimo Subversion Kit 3.1 -- next, next, finish... Restart eclipsea
    * Ubijte onaj Welcome screen

  * Sad idite na: File -> New -> Other -> SVN (Project from SVN) -> Next
  * Za URL upišite: http://fer-projects.googlecode.com/svn/trunk/ , zasad, kasnije ćete upisati drugi URL i svoje usernameove i passworde.
  * Next, pa stisnite No, izaberite Head revision i onda Finish
  * Izaberite gore ono prvo i stvorite u sljedećem čarobnjaku projekt s nekim nazivom.

Sad bi se trebao stvoriti projekt u kojem nema ničega i u koji trenutno ne možete pisati. (Nisam ovo sve detaljno isprobavao.)

## Tortoise SVN ##

Skinite klijent [ovdje](http://tortoisesvn.net/downloads). Instalirajte ga i pokrenite. Dobit ćete kratku uputu za korištenje ... uglavnom, otiđite u neki direktoriji i sve naredbe su dostupne na desni klik.

Prvo morate napraviti checkout repozitorija. Unesete URL repozitorija koji možete naći [ovdje](http://code.google.com/p/fer-projects/source/checkout). CP-ate ga u Tortoise i stisnete ok. Nakon toga on napravi "check-out" u direktorij neki (npr. fer-projects).

U tom direktoriju sad možete raditi `update` - osjvežite sadržaj s najnovijim promjenama ili `commit` (ako imate ta prava) - gurnuti gore na server/repozitorij promijene koje ste napravili.

Detaljnije o SVN-u sami potražite.
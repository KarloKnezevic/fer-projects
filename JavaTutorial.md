# Uvod #

Euclid is said to have replied to King Ptolemy's request for an easier way of learning mathematics:
> "_There is no royal road to geometry._"


Ne postoji sličan put ni za ovo. :-) Pokušat ću vam kroz primjere što efikasnije prenjeti neke ideje o OOP i Javi ali morat ćete i sami uključiti mozak. Nisam Java guru ali to nije toliko ni bitno.

Sintaksa Jave i nazovimo ih mogućnosti samog jezika su poprilično jednostavne i prelazak sa C-a što se toga tiče nije težak. No bitno je i razumijevanje ideja OOP-a, poznavanje standardnih biblioteka, poznavanje raznih alata. [Ovdje](http://java.sun.com/javase/6/docs/) je dokumentacija i lijepa slika koja prikazuje što sve obuhvaća "Java".

Ovo je namjenjeno za one koji su samo kodirali u C-u i nisu bili izloženi idejama objektno orjentiranog programiranja.

# Mu #

Krenimo od početka. Otvorite Eclipse i stvorite novi Java projekt: File -> New -> Java project. Tradicionalno nazovite projekt "Hello World". JRE neka bude verzije 1.6 i izaberite ono "Create separate folders for sources and class files". Kliknite finish. U vašem _workspaceu_ će se stvoriti direktorij "Hello World" i u njemu još dva direktorija: `src` i `bin` -- u `src` se nalazi vaš kod, a u `bin` kompajlirane Java datoteke (`.class`) koje su spremene za izvršavanje na JVM-u. Svaki put kad "sejvate" (`Ctrl-S`) kod Eclipse ga automatski kompajlira.

Sad kliknite desnim klikom na projekt i izaberite: New -> Package.

Što je **package**? U drugim jezicima se možete susresti s nazivom _namespace_. To je jedna te ista stvar. Ovaj drugi naziv je puno sugestivniji. Ideja je jednostava: može se dogoditi da dva razreda (eng. class, doći ćemo do toga) imaju _isto ime_ pa mora postojati način da ih razlikujemo -- pogotovo ako ih oboje želimo koristiti. Razlikovat će se ako se nalaze u različitom paketu (packege/namespace). A i očito pomoću njih možemo bolje organizirati naš kod.

Za ime paketa stavite: `hr.fer.hello` U direktoriju `src` će nastati ovakva struktura direktorija: `src/hr/fer/hello` i sav kod iz paketa `hr.fer.hello` će se nalaziti u direktoriju `hello`.

Sad desni klik na taj novonastali prazni paket i izaberite: New -> Class. Za ime upište `HelloWorld` (_konvencija_ je takva da se imena razreda uvijek započinju velikim slovom i da se koristi [CamelCase](http://en.wikipedia.org/wiki/Camelcase), imena metoda započinjite malim slovom.) Izaberite dolje da želite da vam stvori `public static void main(String[] args)` "method stub". Finish.

Hop! Stvorio se nekakav prozor i kod u njemu. Primjetite komentare koji se jednako pišu kao u C-u. Izbrište ih pa bi trebali dobiti samo ovo:
```
package hr.fer.hello;

public class HelloWorld {

	public static void main(String[] args) {

	}

}
```

## Razredi i objekti ##
Prva linija označava u kojem se paketu nalazi ova klasa/razred. Ali što je to uopće **razred** tj. **class**? Paaaa, pokušat ćemo to ovako objasniti. Sjetite se onog `struct` iz C-a. On nam je omogućio da stvaramo složenije tipove podataka (strukture) koji se sastoje od primitivnih (`int`, `float`...) ili od drugih složenih. Npr. željeli smo imati tip podataka `Tocka` koji se zapravo sastoji od x i y koordinate tj. od dva `float`-a.

Ideja razreda i objekta je slična ideji `struct`-a ali mnogo moćnija i više razrađena. U C-u bi definirali novu strukturu sa:
```
struct Tocka {
	float x;
	float y;
};
```
A u Javi bi sličnu stvar napravili ovako:
```
class Tocka {
	float x;
	float y;
}
```
I hej! Pa to je skoro isto :) U C-u smo zatim obično imali nekakve funkcije koje su "operirale" nad našim strukturama. Primjerice, željeli smo funkciju koja će nam vratiti razliku x-a i y-a neke točke:
```
float razlika_x_y(struct Tocka * tocka) { return tocka->x - tocka->y }; 
```
Ta funkcija bi se nalazila negdje u kodu i kad bi je pozivali morali bi joj predati pokazivač na našu varijablu tipa `struct Tocka`.
Tu dolazimo do prve važne ideje OOP-a!
Funkcije koje operiraju nad `Tocka`-ma će postati dio razreda `Tocka` i nazivat ćemo ih **metode**. Evo kako bi to izgledalo u Javi:
```
class Tocka {
	float x;
	float y;
	float razlika() { return this.x - this.y };
}
```
Kako bi sad koristili tu metodu? Evo isječak koda:
```
Tocka mu = new Tocka(); // Tocka je razred, a mu je referenca na objekt koji je _instanca_ tog razreda
mu.x = 10;
mu.y = 21;
float d = mu.razlika(); // metodi pristupamo jednako kao atributima (x i y) objekta, d će poprimiti vrijednost -11
```

Primjetite onaj misteričan `this` koji smo koristili u metodi `razlika()`. To je Java za nas implicitno prosljedila funkciji `razlika()` tu varijablu. Ona je referenca (kao što je `mu`) na objekt nad kojim želimo operirati s tom metodom. U C-u smo u suštini istu stvar radili _ručno_ -- pozvali bi funkciju i jedan od parametara bi bio pokazivač na strukturu. U OO jezicima se to događa u pozadini, implicitno, kad napišemo `mu.razlika()`.

Dakle, vidimo da se razred/class sastoji od atributa (npr. x i y) i metoda (npr. razlika). Razred je kao _nacrt_ (blueprint) po kojem se gradi objekt. Odnosno, objekt je samo jedna od instanci nekog razreda.

Idemo sad dalje pa ćemo vidjeti ostale ideje OOP-a.

## print Hello World; ##

Izmjenimo malo kod:
```
package hr.fer.hello;

public class HelloWorld {

	public static void main(String[] args) {
		for(int k=0; k<100; k++) System.out.print("Hello World!");
	}

}
```
Vidimo da je petlja `for` ista kao u C-u. `System.out` predstavlja standardni izlaz i jedna od metoda koje posjeduje je i ova `print` koja služi za ispis.

Program pokrenite na način da kliknete na onaj zeleni "play" gumbić ili Run -> Run ili `Ctrl-F11`. Ispisat će se 100 puta Hello World -- tko bi rekao.
Java kad pokreće vaš program uvijek potraži metodu `main()` i nju pozove. Main dobije polje Stringova `args` slično kao u C-u. To su npr. paramteri s kojima je program pozvan.

Main metoda ne vraća ništa pa zato piše `void` ali što znači ovo `public` i `static`?
Public označava **scope** (vidljivost) atributa/metode -- ako je `public` onda mu svi mogu pristupiti, a ako je primjerice `private` onda mu možete prisupiti samo unutar razreda (druge metode iz razreda mogu pristupi ali izvana to nije moguće). Npr.
```
class Razred { private int x; }
...
Razred r = new Razred();
int a = r.x; // ups! neće ići...
```
A za `static` evo copy-paste dobrog objašnjenja:
> `static` variables and methods might better have been called perClass variables (_atributi, op.a._) and methods. They inherited this misleading terminology from C++. **They are the opposite of instance variables and methods that work on a particular object.**
> There is nothing static (unchanging) about them. They don’t cling. They are perfectly clear, unlike radio signals garbled by static.
> They are allocated when the class is loaded. **static refers to a method or variable that is not attached to a particular object, but rather to the class as a whole**.

To za posljedicu ima da unutar `static` metode možete pozivati samo druge `static` metode i pristupati drugim `static` atributima. Jer `static` metodi uopće nije predan `this`! pošto ona ni ne pripada nekom određenom objektu već samo razredu. Evo primjer sa našim HelloWorld razredom, isječak:
```
HelloWorld hw = new HelloWorld();
hw.main(null); // ups! neće ići, hw je referenca na objekt i on sam ne sadrži metodu main
HelloWorld.main(null); // to je već bolje ;)
```
Primjetite ovo `null` -- metoda main prima referencu na polje Stringova, no mi smo joj predali samo `null` vrijednost odnosno referencu koja ne pokazuje nigdje.

Uvjerite sami sebe da razumijete ovo pojmove.

## Idemo dalje! ##
Do sad bi trebali razumijeti pojmove poput razreda (class), objekta, metode, atributa, `public`, `static`. Idemo proširiti naš primjer.

Dodajte u naš paket `hr.fer.hello` razred `Stvorenje` (ovaj put bez `main` metode). Neka ima atribute `starost` tipa `int` i `umnost` tipa `boolean`. Oba atributa neka budu `private`. Dodat ćemo mu još neke stvari, evo kako konačno izgleda razred:
```
package hr.fer.hello;

public class Stvorenje {

	private int starost;
	private boolean umnost;
	
	public Stvorenje(int starost, boolean umnost) {
		this.starost = setStarost(starost);
		this.umnost = umnost; // ovdje koristimo this jer inace ne bi bilo jasno na sto mislimo
	}

	public int getStarost() {
		return starost;
	}

	public void setStarost(int starost) throws Exception {
		if(starost < 0) throw new Exception("Starost mora biti pozitivna!");
		this.starost = starost;
	}

	public boolean isUmnost() {
		return umnost; // ovdje nam this ne treba jer je jasno na koju umnost mislimo - na this.umnost
	}

	public void setUmnost(boolean umnost) {
		this.umnost = umnost;
	}	
}
```

Atributi starost i umnost su private pa im ne možemo pristupiti izvana -- zato imamo metode koje će nam služiti za pristup njihovim vrijednostima tzv. getteri i setteri. To je u Javi često praksa. Jedna od prednosti je upravo ovo što smo učinili unutar metode `setStarost` -- prije nego što postavimo starost provjerimo je li nenegativna. Ako nije onda prekinemo tok izvođenja s nečim što se zove **Exception**. O tome više sami saznajte sad nije bitno.

Takva praksa ima i drugih prednosti koje možda sad nisu očite. (Razmislite koje.) Naravno, nedostatak je povećana količina koda odnosno verboznost.

Još jedna nova stvar je ova "metoda" `public Stvorenje(int starost, boolean umnost)` -- to je tzv. **konstruktor** i kad radimo instancu ovog razreda onda se on prvi poziva i služi nam kako bi postavio sve na svoje mjesto prilikom stvaranja objekta. Pogledajmo to u modificranom `HelloWorld` razredu:
```
package hr.fer.hello;

import java.util.Set;

public class HelloWorld {

	public static void main(String[] args) {
		Set<Stvorenje> zivotinjskoCarstvo = new java.util.HashSet<Stvorenje>();
		
		for(int k=0; k<10; k++) {
			/* stvaramo i dodajemo u set novi objekt
			   i prilikom stvaranja pozvat ce se spomenuti
			   konstruktor s parametrima k i true
			*/
			zivotinjskoCarstvo.add(new Stvorenje(k, true)); 
		}
		for(int k=0; k<10; k++) {
			zivotinjskoCarstvo.add(new Stvorenje(k, false));
		}
		
		for(Stvorenje stvor : zivotinjskoCarstvo) {
			if(stvor.isUmnost()) {
				System.out.print(stvor.getStarost());
			}
		}	
	}
}
```

Na kraju će se ispisati starost svih umnih stvorenja. No obratite pažnju da neće nužno istim redosljedom istim kao što smo ih dodavali u `Set` -- jer u skupu poredak nije bitan, postoje implementacije `Set`-a koje će paziti na redosljed, a mogli smo koristiti i `List` u kojoj je redosljed itekako bitan.

Primjetite da smo `Set` prvo importali iz drugog paketa, a `HashSet` smo direktno koristili tako da smo prvo naveli paket, a zatim ime razreda koji želimo.

U Javi postoji dosta gotovih struktura podataka koje su _generične_ dakle možemo spremati sve u njih. Npr. `Stack`, `Queue`, `PriorityQueue` ...

Ta linija koda (1. u main) će postati jasnija malo kasnije. I još obratite pažnju na treću `for` petlju, takvo _iteriranje_ je moguće kroz neke objekte.

## Nasljeđivanje ##

Jedna od bitnih ideja je i nasljeđivanje (eng. **inheritance**). Napravimo novi razred u `hr.fer.hello` i nazovimo ga `Covjek`. Evo koda:
```
package hr.fer.hello;

public class Covjek extends Stvorenje { // obratite paznju na "extends" !
	
	public boolean duh = true; // covjek ima i dodatni atribut

	public Covjek(int starost, boolean umnost) throws Exception {
		super(starost, true);
	}

	public Covjek(int starost) {
		super(starost, true);
	}
	
	public void setUmnost(boolean umnost) {
		return; // ova metoda ne radi nista jer svi ljudi su umni (da bar)
	}

	public String toString() {
		return String.valueOf(getStarost());
	}	
}
```

Dakle, razred `Covjek` **nasljeđuje** (odnosno "extends") razred `Stvorenje`. To znači da će nasljediti sve što ima (metode, atribute) `Stvorenje` ali će dodati i još nešto što možda nemaju sva stvorenja ali ima covjek: novi atribut `duh`. Isto tako će _pregaziti_ neke metode poput `setUmnost` jer ne želimo omogućiti da ljudi budu bezumni. Primjetite i drugi konstruktor koji prima samo jedan parametar, te ključnu riječ `super` koja nam omogućava da dođemo do metoda i konstruktora našeg nadrazreda. Možemo na ovo gledati da je `Covjek` specijalni slučaj `Stvorenje`-a, mogli bi napraviti i druge razrede koji nasljeđuju `Stvorenje` npr. `Ptica` koja bi možda opet imala neke specifične atribute i metode.

Svi razredi u Javi nasljeđuju the razred `Object`. Čak ako i ne kažemo to eksplicitno. Dakle naše `Stvorenje` nasljeđuje `Object`, a `Covjek` nasljeđuje `Stvorenje` pa i `Covjek` ima metode koje ima i `Object`: pogledajte [ovdje](http://java.sun.com/javase/6/docs/api/java/lang/Object.html) koje su to. I čemu služi metoda `toString()`.

Pogledajmo `Covjek`-a u akciji:
```
package hr.fer.hello;

import java.util.LinkedList;
import java.util.List;

public class HelloWorld {

	public static void main(String[] args) {
		Covjek joe = new Covjek(21);
		Covjek teskera = new Covjek(21);
		teskera.duh = false;
		
		List<Covjek> ljudi = new LinkedList<Covjek>();
		ljudi.add(joe);
		ljudi.add(teskera);
		
		for(Covjek x : ljudi) if(x.duh == true) System.out.print(x);
	}
}
```

## Sučelja ##

Svaki razred može implementirati sučelja (eng. **interface**). Sučelje nam kaže koje metode razred **mora** imati ali ih samo sučelje ne implementira - to moramo sami. Razred može implementirati koliko želi sučelja, no može nasljediti samo jedan razred (tj. extendati). Proširit ćemo našeg `Covjek`-a i reći da implementira sučelje `Comparable`. Evo kako sad izgleda:
```
package hr.fer.hello;

public class Covjek extends Stvorenje implements Comparable<Covjek> {
	
	public boolean duh = true;

	public Covjek(int starost, boolean umnost) throws Exception {
		super(starost, true);
	}

	public Covjek(int starost) {
		super(starost, true);
	}
	
	public void setUmnost(boolean umnost) {
		return;
	}

	public String toString() {
		return String.valueOf(getStarost());
	}

	public int compareTo(Covjek o) {
		return o.getStarost() - getStarost();
	}
	
}
```

Zanemarite ovo `<Covjek>` o tome ćemo reći nešto za koji tren. Sučelje `Comparable` nas je tražilo da implementiramo metodu `public int compareTo(Covjek o)`. Pogledajmo sljedeći odsječak u kojem će biti jasna ideja sučelja.
```
List<Covjek> ljudi = new LinkedList<Covjek>();
...
Collections.sort(ljudi);
```

Objekt `ljudi` sadrži listu objekata tipa `Covjek`. `Collections.sort()` je generička metoda u razredu `Collections` koja zna raditi sa `List` (usput `List` je sučelje, a `LinkedList` razred koji ga implementira). No kako će `sort` znati po kojem kriteriju sortirati objekte? Pa znati će sortirati upravo one koji implementiraju sučelje `Comparable` koje će mu reći da objekt sigurno ima metodu `compareTo` i nju će pozivati kad će uspoređivati dva `Covjek`-a. Probajte pogoditi kako će onda soritirati nase "ljude".

Vidite na koji način nam sučelja omogućavaju veću "generičnost".

## Generics ##

Već smo se par puta susreli s `List<Covjek>` (ovo <...> nas mozda zbunjuje) ili necim sličnim. U ovom slučaju to kaže Javi što ćemo spremati u našu `List`-u. Objekte tipa `Covjek`. Da smo željeli listu integera onda bi rekli ` List<Integer> brojevi; ` (postoji razlika između `Integer` i `int`). Logično je da ne želimo za svaki tip podataka implementirati novu listu - to bi bilo glupo. U Javi 1.4 nije bilo potrebno reći što ćemo stavljati u listu, uostalom i sad možemo reći da ćemo stavljati `Object` jer njega svi nasljeđuju. No onda imamo problema kad kasnije uzimamo nešto iz liste. Više ne znamo o kojem tipu podataka se radi pa to postane nepraktično i nesigurno (ako ručno "castamo").

[Ovdje](http://java.sun.com/j2se/1.5.0/docs/guide/language/generics.html) možete pročitati detaljnije o tome.

## Pripazite na... ##
### Integer i int ###

Java ima neke **primitivne tipove** podataka koji nisu objekti. Npr. `int`, `float` ...
Zbog čega onda postoji `Integer`? Pa zato jer npr `List` radi sa objektima, a `int` to nije. Pa je `Integer` samo omotač oko `int`-a i Java za nas radi automatsko zamotavanje i odmotavanje pa možemo raditi ovakve stvari:
```
int x = 5;
Integer y = x; // y je referenca na novi objekt Integer koji ima atribut s vrijednoscu 5
```

U Javi ne postoji "operator overloading" odnosno ==, +, - i ostali operatori uvijek isto znače (za razliku u C++ gdje to možemo promijeniti). Prema tome ako imate ovakav kod:
```
Integer a = 5; // stvorit će se novi objekt u memoriji i a je referenca na njega
Integer b = 5; // stvorit će se novi objekt u memoriji i b je referenca na njega
Integer c = a; // c je referenca na objekt koji već postoji
if(a == b) napraviNesto();
if(c == a) napraviNestoDrugo();
```
U 4. liniji u if naredbi usporedit će se **reference** a ne "vrijednosti" kako bi možda očekivali. A `a` i `b` nisu jednaki jer pokazju na različite stvari. Stoga se `napraviNesto()` NEĆE pozvati ali `napraviNestoDrugo()` hoće!

Ovdje će se stvar ponašati "normalno":
```
int a = 5;
int b = 5;
if(a == b) napraviNesto();
```
jer `a` i `b` nisu objekti (tj. reference). Za onaj prvi slučaj ćemo koristi metodu `compareTo()` koja vraća 0 ako su jednaki objekti (`Integer` implementira `Comparable`!):
```
Integer a = 5; 
Integer b = 5; 
if(a.compareTo(b) == 0) napraviNesto(); // ovaj put ce se pozvati napraviNesto();
```

### Reference, objekti ?? ###

Možda nas malo zbunjuje cijela priča o referencama i objektima i onda još primitivnim tipovima (ne mislim na one iz Srca). Sjetite se onog iz C-a: call-by-value i call-by-reference. U Javi se sa `new` uvijek stvara u _memoriji_ novi objekt i njegovo mjesto se spremi u neku referencu.
```
int x = 10; // u memoriji se stvori jedan integer (int je primitivac)
Integer y; // y je samo referenca i trenutno ne pokazuje na ništa
y = new Integer(); // sad se u memoriji stvara negdje objekt instance razrezda Integer
                   // i y pokazuje na taj objekt
Integer z = y; // z je nova referenca i pokazuje na isti objekt na koji pokazuje y

nekaMetoda(x, y); // x se kopira u memorijski prostor funckije nekaMetoda 
                  // (dakle stvorio se jos jedan int)
                  // y se također kopira i to je nova referenca u funkciji ali pokazuje
                 // na isti objekt (dakle samo je jedan objekt u memoriji)

y = 5; // java će zamotati 5 u Integer i stvoriti novi objekt te će y pokazivati na njega

Integer[] polje = new Integer[x]; // stvorit će polje od 10 referenci! 
                                  // ne objekata, ni jedan objekt se neće stvoriti još
for(int i=0; i<10; i++) polje[i] = new Integer(); // sad će stvoriti i 10 objekata Integer u memoriji

polje[2] = y; // referenca polje[2] pokazuje na objekt na koji je pokazivao y, 
               //a na onaj prijasnji vise nitko ne pokazuje
```


### String ###

U Javi postoji razred `String` - važno je znati da je sadržaj njega nepromjenjiv, dakle:
```
String x = "Hello world";
x = x + "!"; // stvorit će se potpuno novi objekt i sadržaj će mu biti "Hello world!"
```
Zato kad imate puno dodavanja onda koristite `StringBuilder`.

### Garbage collector ###

U Javi ne morate i ne možete ručno uništavati objekte iz memorije. Ako JVM osjeti potrebu pokrene Garbage collector koji izbriše sve objekte na koje nitko ne pokazuje (odnosno do kojih više ne možemo nikako doći). Zbog toga ne trebate briniti o oslobođavanju memorije i zločestim memory leakovima (likovi koji vole igrat memory).
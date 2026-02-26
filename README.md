Proiect: Gestionarea Muzeelor și Grupurilor de Vizitatori

Design Patterns Utilizate

Pentru a structura aplicația într-un mod eficient, scalabil și ușor de întreținut, am folosit mai multe design patterns consacrate în ingineria software. Aceste modele oferă soluții standardizate pentru probleme comune de arhitectură și facilitează dezvoltarea și extinderea proiectului fără a compromite claritatea codului. Mai jos sunt prezentate modelele utilizate, împreună cu motivele pentru care au fost alese și modul în care au fost integrate în aplicație.

1. Command Pattern

Motivație:Am folosit Command Pattern pentru a separa logica diferitelor acțiuni de restul aplicației și pentru a permite tratarea comenzilor ca obiecte independente. Acest model este extrem de util atunci când avem mai multe tipuri de operațiuni care trebuie executate, salvate sau anulate.

Cum a fost folosit în proiect:În cadrul aplicației, Command Pattern a fost folosit pentru a implementa acțiuni precum:

Adăugarea unui muzeu, ghid sau membru: Fiecare adăugare este tratată ca o comandă separată care poate fi executată atunci când este necesar.

Țtegerea unui ghid sau membru: Operațiunile de eliminare sunt de asemenea encapsulate în comenzi independente, permițând o gestionare clară a acestor acțiuni.

Căutarea unui ghid sau membru: Fiecare operațiune de căutare este tratată ca o comandă, oferind flexibilitate în modul în care rezultatele sunt obținute și procesate.

Folosirea acestor comenzi permite extinderea aplicației cu noi funcționalități fără a modifica codul existent, respectând astfel principiul Open/Closed.

Beneficii obținute:

Flexibilitate în adăugarea de noi acțiuni fără modificarea logicii principale.

Reutilizare și modularitate crescută a codului.


2. Builder Pattern

Motivație:Builder Pattern a fost introdus pentru a gestiona crearea obiectelor complexe care conțin parametri opționali. În loc să folosim constructori aglomerați cu mulți parametri sau metode de tip set pentru fiecare atribut, Builder Pattern permite o construire flexibilă și clară a obiectelor. Acest model este esențial atunci când avem obiecte care pot fi configurate în mai multe moduri, dar nu toate atributele sunt obligatorii.

Cum a fost folosit în proiect:Builder Pattern a fost folosit în special pentru clasele Museum și Location. Aceste clase au mai mulți parametri opționali, cum ar fi descrierea muzeului, orarul de funcționare sau coordonatele locației. Prin utilizarea acestui model:

Obiectele Museum și Location pot fi construite pas cu pas, adăugând doar informațiile necesare.

Constructorii rămân simpli și clari, evitând suprasarcina de a gestiona numeroase combinații de parametri.

De exemplu, un muzeu poate fi creat doar cu numele și codul său, iar ulterior se pot adăuga opțional descrierea sau locația exactă, fără a fi nevoie de constructori diferiți pentru fiecare combinație.

Beneficii obținute:

Claritate în procesul de construire a obiectelor, eliminând nevoia de constructori complicați.

Flexibilitate în adăugarea de noi atribute fără a afecta codul existent.

Reducerea erorilor care pot apărea din utilizarea constructorilor cu mulți parametri.

Permite crearea de obiecte immutabile, care sunt mai sigure și mai ușor de testat.

3. Observer Pattern

Motivație:Observer Pattern a fost utilizat pentru a gestiona notificările automate între entitățile din aplicație. În acest caz, muzeele joacă rolul de subiecte care notifică automat ghizii înregistrați atunci când apare un nou eveniment sau o schimbare de program. Ghizii sunt observatori care primesc aceste notificări pentru a putea informa grupurile de vizitatori.

Cum a fost folosit în proiect:Clasa Museum acționează ca un subject care menține o listă de ghizi înregistrați (registeredGuides). De fiecare dată când un muzeu anunță un eveniment nou, notificarea este transmisă automat către toți ghizii înregistrați. Aceștia primesc informațiile și pot reacționa corespunzător, de exemplu prin informarea vizitatorilor despre schimbările survenite.

Beneficii obținute:

Automatizare eficientă a procesului de notificare fără intervenție manuală.

Decuplare între muzeu și ghizi – muzeul nu trebuie să știe detalii despre fiecare ghid, ci doar să trimită notificările.

Extensibilitate – putem adăuga noi tipuri de observatori (de exemplu, aplicații mobile sau alte servicii) fără a modifica logica muzeului.

4. Singleton Pattern

Motivație:Singleton Pattern a fost utilizat pentru a garanta că există o singură instanță a clasei Database în întreaga aplicație. Acest model asigură faptul că toate componentele aplicației accesează aceeași sursă de date, prevenind astfel problemele care pot apărea din cauza accesului concurent sau a gestionării incorecte a resurselor.

Cum a fost folosit în proiect:Clasa Database, responsabilă pentru stocarea și gestionarea informațiilor despre muzee, ghizi și membri, a fost implementată ca un singleton. Astfel:

Toate operațiunile de adăugare, ștergere și căutare se fac pe o singură instanță partajată.

Se evită conflictele sau inconsistențele care ar putea apărea dacă ar exista mai multe instanțe ale bazei de date.

Beneficii obținute:

Acces centralizat și control unic asupra datelor.

Prevenirea creării accidentale a mai multor instanțe care ar putea duce la inconsistențe în date.

Simplificarea configurării și gestionării bazei de date.

Concluzie

Utilizarea acestor design patterns a contribuit la crearea unei aplicații robuste, ușor de extins și întreținut. Fiecare model a fost ales pentru a rezolva o problemă specifică de arhitectură, iar împreună au permis o organizare clară și eficientă a codului. Astfel, aplicația este pregătită pentru a fi scalată și adaptată la cerințe viitoare fără a compromite calitatea și claritatea codului.


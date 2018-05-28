# Documentazione

Seguono brevi commenti ai diagrammi contenuti in questa cartella.

## Diagramma dei Componenti

### Database

([Link](DB_Server_UML_Component_Diagram.pdf))

E' il component diagram che mostra in che modo sono stati utilizzati i framework e le interfacce necessari a comunicare
 con il db. 
 * Hibernate è stato il framework utilizzato come implementazione delle JPA</li>
 * le POJO Entities sono le entità che tramite annotazioni JPA e file di configurazione sono state mappate sul database
 * i DAO sono un'implementazione del pattern Data Access Object (https://it.wikipedia.org/wiki/Data_Access_Object) per ciascuna entità, forniscono semplici operazioni CRUD o anche operazioni più complesse.</li>

## Diagramma ER

([Link](ER_Diagram.pdf))

E' il diagramma entità-relazioni relativo al database utilizzato. Gli attributi in rosso rappresentano le chiavi
primarie mentre quelli in verde sono colonne con una "unique constraints" associata.

## Diagrammi di sequenza

([Link RMI](RMI_Save_Dish_UML_Sequence_Diagram.pdf))
([Link Socket](RMI_Save_Dish_UML_Sequence_Diagram.pdf))

Sono due sequence diagram che rappresentano la serie completa di metodi chiamati durante un'operazione tipo.
In questo caso l'operazione selezionata è la creazione di un piatto per la mensa.
Nel diagramma relativo ad RMI è presente anche la parte relativa all'aggiornamento delle tabelle successivo
al salvataggio del piatto. Tale parte non è stata inclusa nel diagramma socket per problemi di dimensioni
del diagramma stesso.

## Diagrammi delle classi

### RMI

([Link](RMI_UML_Class_Diagram.pdf))

Class diagram che rappresenta le classi coinvolte nella connettività tramite RMI.
Il diagramma è diviso in 3 aree: a sinistra le classi relative al server, a destra quelle relative al client e in mezzo
le classi in comune ad entrambi.
In questo caso sono stati utilizzati due pattern in combinazione:
* il Session Facade pattern (http://www.oracle.com/technetwork/java/sessionfacade-141285.html): per fornire al client un access layer univoco e ben definito al server.</li>
* il Remote Session pattern (specificato nel libro "java.rmi: The Remote Method Invocation Guide" di Kathleen McNiff, Esmond Pitt): per fornire al client un mezzo di autenticazione "vero" e per gestire facilmente le sessioni attive lato server

### Socket

([Link](Socket_UML_Class_Diagram.pdf))

Class diagram che rappresenta le classi coinvolte nella connettività tramite Socket.
A livello di pattern l'architettura è la stessa utilizzata per RMI, tuttavia sono state aggiunte delle classi per encapsulare chiamate e risposte remote, in modo da "mimare" il funzionamento di RMI mediante Socket. 

### Observable

([Link](Remote_Observable_UML_Class_Diagram.pdf))

Class diagram che rappresenta le classi coinvolte nel meccanismo di aggiornamento "push".
E' stato utilizzato il pattern observable (https://it.wikipedia.org/wiki/Observer_pattern) per notificare a tutti i
 client la necessità di aggiornare determinati contenuti. Ciò è stato fatto per evitare stati non consistenti tra client
 che lavorano contemporaneamente sugli stessi dati.

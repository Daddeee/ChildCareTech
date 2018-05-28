Seguono brevi commenti ai diagrammi contenuti in questa cartella.</p>
<hr>
<b>DB_Server_UML_Component_Diagram.pdf</b>
<p>
E' il component diagram che mostra in che modo sono stati utilizzati i framework e le interfacce necessari a comunicare
 con il db. 
 <ul>
  <li>Hibernate è stato il framework utilizzato come implementazione delle JPA</li>
  <li>le POJO Entities sono le entità che tramite annotazioni JPA e file di configurazione sono state mappate sul database</li>
  <li>i DAO sono un'implementazione del pattern Data Access Object (https://it.wikipedia.org/wiki/Data_Access_Object) per ciascuna entità, forniscono semplici operazioni CRUD o anche operazioni più complesse.</li>
 </ul>
</p>
<hr>
<b>ER_Diagram.pdf</b>
<p>
E' il diagramma entità-relazioni relativo al database utilizzato.
</p>
<hr>
<b>RMI_Save_Dish_UML_Sequence_Diagram.pdf<p>Socket_Save_Dish_UML_Sequence_Diagram.pdf</b>
<p>
Sono due sequence diagram che rappresentano la serie completa di metodi chiamati durante un'operazione tipo.
In questo caso l'operazione selezionata è la creazione di un piatto per la mensa.
Nel diagramma relativo ad RMI è presente anche la parte relativa all'aggiornamento delle tabelle successivo
al salvataggio del piatto. Tale parte non è stata inclusa nel diagramma socket per problemi di dimensioni
del diagramma stesso.
</p>
<hr>
<b>RMI_UML_Class_Diagram.pdf</b>
<p>
Class diagram che rappresenta le classi coinvolte nella connettività tramite RMI.<p>
Il diagramma è diviso in 3 aree: a sinistra le classi relative al server, a destra quelle relative al client e in mezzo
le classi in comune ad entrambi.<p>
In questo caso sono stati utilizzati due pattern in combinazione:
<ul>
 <li>il Session Facade pattern (http://www.oracle.com/technetwork/java/sessionfacade-141285.html): per fornire al client un access layer univoco e ben definito al server.</li>
 <li>il Remote Session pattern (specificato nel libro "java.rmi: The Remote Method Invocation Guide" di Kathleen McNiff, Esmond Pitt): per fornire al client un mezzo di autenticazione "vero" e per gestire facilmente le sessioni attive lato server.</li>
</ul>
</p>
<hr>
<b>Socket_UML_Class_Diagram.pdf</b>
<p>
Class diagram che rappresenta le classi coinvolte nella connettività tramite Socket.<p>
A livello di pattern l'architettura è la stessa utilizzata per RMI, tuttavia sono state aggiunte delle classi per encapsulare chiamate e risposte remote, in modo da "mimare" il funzionamento di RMI mediante Socket. 
</p>
<hr>
<b>Remote_Observable_UML_Class_Diagram.pdf</b>
<p>
Class diagram che rappresenta le classi coinvolte nel meccanismo di aggiornamento "push".<p>
E' stato utilizzato il pattern observable (https://it.wikipedia.org/wiki/Observer_pattern) per notificare a tutti i
 client la necessità di aggiornare determinati contenuti. Ciò è stato fatto per evitare stati non consistenti tra client
 che lavorano contemporaneamente sugli stessi dati.
</p>

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

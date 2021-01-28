
# Quizspiel   

`https://opentdb.com/api_config.php`

-Nicole Hammerer(ic19b094)

-Lisa Haas (ic19b060)

##Spielablauf
#####Start/Login
Quiz wird gestartet nachdem sich ein Client zum Server verbunden hat.


Im Login-Fenster muss man sich zuerst neu registieren damit ein Profil angelegt werden kann.
Dafür gibt es ein eigenes RegistrierungsFenster

#####Spielmodi
Es gibt insgesamt 2 Spielmodi
*******
* Singleplayer

  * Die Quiz-Fragen-Collection wird erstellt nachdem Anzahl, Kategorie und Schwierigkeit ausgewählt werden.
  * Bei klicken auf den Start Quiz Button lädt ein neues Fenster mit den Fragen.
  * der Next Button lädt die neue Frage. (Erklärungen zum Joker siehe Abschnitt Joker)



*******
* Custom Game

  * Die Fragen werden vom User selber erstellt.
  * Bei Add Question wird die Frage zum Package hinzugefügt.
  * Sobald der User fertig ist mit den Fragen muss der Button createGame geklickt werden.

Danach kann im Tab Custom Game das soeben erstelle CustomGame ausgewählt werden & gespielt werden.
Ablauf ist gleich wie beim Singleplayer-Gamemode
*******
######Joker
Ein Joker wird erst nach einem abgeschlossenen Spiel random vergeben und im Userprofil gespeichert.
Ein Joker kann nur einmal pro Frage angewendet werden.


Im Tab Profil können eingelesen werden:
 * Name
 * Jokeranzahl
 * Highscore
 * last Highscore
 

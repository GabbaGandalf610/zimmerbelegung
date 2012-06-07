/*******************************************************************************
 * Copyright (c) 2012 Stefan Feilmeier, Roland Kühnel, Franz Wagner.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 ******************************************************************************/

Projektarbeit Hotelreservierung  (Sommersemester 2012)

Projektteam:  
Stefan Feilmeier (Projektleitung)
Franz Wagner
Roland Kühnel

Systemvorraussetzungen:
- Java JRE 6u25 oder höher (empfohlen wird jedoch die aktuellste Version)
- MySql 5.5 (XAMPP)
- Apache TOMCAT 7

1. Auf dem MySql Server (http://localhost/phpmyadmin/) müssen mit den folgenden DDL-Befehlen zwei Datenbanken angelegt werden: 
	1.1. DB Anlage "zimmerbelegung" 
	1.2. DB Anlage "zimmertest"
	(Anlage DB - 
	a) Hierzu muss phpMyAdmin gestartet werden, b) ins Menü "SQL" gewechselt werden und c) der folgende Text kopiert, eingefügt und ausgeführt werden

-- MARKIEREN UND KOPIEREN AB HIER ************************************************************************************************************************************************************
	-- 1.1 DB Anlage "zimmerbelegung"
		 CREATE USER 'zimmerbelegung'@'localhost' IDENTIFIED BY 'zimmerbelegung';
		 GRANT USAGE ON *.* TO 'zimmerbelegung'@'localhost' IDENTIFIED BY 'zimmerbelegung' WITH MAX_QUERIES_PER_HOUR 0 MAX_CONNECTIONS_PER_HOUR 0 MAX_UPDATES_PER_HOUR 0 MAX_USER_CONNECTIONS 0;
		 CREATE DATABASE IF NOT EXISTS `zimmerbelegung`;
		 GRANT ALL PRIVILEGES ON `zimmerbelegung`.* TO 'zimmerbelegung'@'localhost';
	
	-- 1.2. DB Anlage "zimmertest"
		 CREATE USER 'zimmertest'@'localhost' IDENTIFIED BY 'zimmertest';
		 GRANT USAGE ON *.* TO 'zimmertest'@'localhost' IDENTIFIED BY 'zimmertest' WITH MAX_QUERIES_PER_HOUR 0 MAX_CONNECTIONS_PER_HOUR 0 MAX_UPDATES_PER_HOUR 0 MAX_USER_CONNECTIONS 0;
		 CREATE DATABASE IF NOT EXISTS `zimmertest`;
		 GRANT ALL PRIVILEGES ON `zimmertest`.* TO 'zimmertest'@'localhost';
-- MARKIEREN UND KOPIEREN BIS HIERHER*********************************************************************************************************************************************************


2. Nachdem beide Datenbanken angelegt sind, kann/muss das Programm jetzt genutzt/gestartet werden.


3. Simulationsdaten (Optional)
	Zu Simulationszwecken sind zwei DML-Befehle aufgeführt. Nach Ausführung dieser sind erste Testdaten im System enhalten.
	Bevor diese Tabellen jedoch gefüllt werden können, muss das Programm einmalig gestartet sein, da sonst die Tabellen nicht vorhanden sind (Punkt 2).
	a) Zum Füllen dieser Tabellen muss wie in Punkt 1 phpMyAdmin gestartet werden. 
	b) Auswählen der Tabelle "zimmerbelegung"
	c) Ins Menü "SQL" wechseln und den nachfolgenden Anweisungen folgen (markieren, kopieren, einfügen, ausführen).
	d) Programm bzw. Browser Fenster aktualisieren.

-- MARKRIEREN UND KOPIEREN AB HIER ************************************************************************************************************************************************************	
	--	3.1. Zimmertabelle füllen
			INSERT INTO `zimmer` (`id`, `zimmerbeschreibung`, `zimmernummer`, `zimmerpreis`) VALUES
			(1, 'großes DZ', 11, 75),
			(2, 'großes DZ', 21, 75),
			(3, 'großes DZ', 22, 75),
			(4, 'kleines DZ', 23, 65),
			(5, 'kleines DZ', 24, 65),
			(6, 'kleines DZ', 25, 65),
			(7, 'EZ', 26, 50),
			(8, 'EZ', 31, 50),
			(9, 'EZ', 32, 50),
			(10, 'großes DZ', 33, 75);

	--	3.2. Gasttabelle füllen
			INSERT INTO `gast` 
			(`email`, `land`, `name`, `vorname`, `strasse`, `plz`, `ort`) 
			VALUES
			('Stefan@Feilmeier.de','Bayern','Feilmeier','Stefan','Pfeilstr. 1', '11111', 'Passau'),
			('Franz@Wagner.de','Bayern','Wagner','Franz','Wagnerstr. 1', '11111', 'Deggendorf'),
			('Roland@Kuehnel.de','Bayern','Kühnel','Roland','Grundfeld 7', '11111', 'Straubing'),
			('Markus@Nusshart.at','Österreich','Nusshart','Markus','Musterstraße','12345','Wien'),
			('Steffi@Schmitt.de','Deutschland','Schmitt','Steffi','Musterstraße','12345','München'),
			('Steffi@Polifka.de','Deutschland','Polifka','Steffi','Musterstraße','12345','Dingolfing'),
			('Matthias@Tauber.de','Italien','Tauber','Matthias','Musterstraße','12345','Natz-Schabs'),
			('Stefan@Eisenreich.de','Deutschland','Eisenreich','Stefan','Musterstraße','12345','Dingolfing'),
			('David@Farkas.de','Deutschland','Farkas','David','Musterstraße','12345','Passau'),
			('Rainer@Kubiak.de','Deutschland','Kubiak','Rainer','Musterstraße','12345','Passau'),
			('Sabine@Bernhardt.de','Deutschland','Bernhardt','Sabine','Musterstraße','12345','Deggendorf'),
			('Katharina@Kindermann.de','Deutschland','Kindermann','Katharina','Musterstraße','12345','Passau'),
			('gerrit@willam.de','Holland','Willam','Gerrit','Musterstraße','12345','Regensburg');
-- MARKIEREN UND KOPIEREN BIS HIERHER*********************************************************************************************************************************************************




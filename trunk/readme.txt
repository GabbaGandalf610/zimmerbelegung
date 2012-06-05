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
- Java JRE 6u25 oder höher
- MySql 5.5 (XAMPP)
- Apache TOMCAT 7

In der Datenbank MYSQL (http://localhost/phpmyadmin/) sind folgende Datenbanken anzulegen: 
- zimmerbelegung
- zimmertest
************Datenbank zimmerbelegung*********************************************************************************************************************************************************
 CREATE USER 'zimmerbelegung'@'localhost' IDENTIFIED BY 'zimmerbelegung';
 GRANT USAGE ON *.* TO 'zimmerbelegung'@'localhost' IDENTIFIED BY 'zimmerbelegung' WITH MAX_QUERIES_PER_HOUR 0 MAX_CONNECTIONS_PER_HOUR 0 MAX_UPDATES_PER_HOUR 0 MAX_USER_CONNECTIONS 0;
 CREATE DATABASE IF NOT EXISTS `zimmerbelegung`;
 GRANT ALL PRIVILEGES ON `zimmerbelegung`.* TO 'zimmerbelegung'@'localhost';
************Datenbank zimmertest*********************************************************************************************************************************************************
 CREATE USER 'zimmertest'@'localhost' IDENTIFIED BY 'zimmertest';
 GRANT USAGE ON *.* TO 'zimmertest'@'localhost' IDENTIFIED BY 'zimmertest' WITH MAX_QUERIES_PER_HOUR 0 MAX_CONNECTIONS_PER_HOUR 0 MAX_UPDATES_PER_HOUR 0 MAX_USER_CONNECTIONS 0;
 CREATE DATABASE IF NOT EXISTS `zimmertest`;
 GRANT ALL PRIVILEGES ON `zimmertest`.* TO 'zimmertest'@'localhost';
*********************************************************************************************************************************************************************************************
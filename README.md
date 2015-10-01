# Serwis openpkw-dokument-generator

Serwis wchodzcy w skład systemu OpenPKW, odpowiedzialny za generowanie dokumentów.

## Środowiska
* Continuous Delivery: http://dobromir.openpkw.pl:8080/view/openpkw-dokument-generator%20pipeline/
* DEV: http://dobromir.openpkw.pl:9080/openpkw-dokument-generator
* UAT: brak
* PROD: brak

## Lista zadań
* Wszystkie zadania: [Trello](https://trello.com/b/6TXJgOO5/openpkw-dokument-generator)
* [DG-U-1](https://trello.com/c/oEnZ2qYk) Generowanie protokolu komisji obwodowej
* [DG-U-2](https://trello.com/c/MjttfXoa) Kody QR zawierajace podstawowe informacje protokolu

## Jak zbudować projekt?
mvn clean install

## Jak rozproszyć projekt na środowisku lokalnym?
* Zainstalować serwer JBoss
* Utworzyć użytkownika administracyjnego o nazwie 'jenkins' i wybranym haśle
* Wykonać komendę mvn jboss-as:deploy -Dopenpkw-env=local -Djboss.management.password=[tutaj wpisać hasło]

## Jak rozproszyć projekt na środowisku DEV?
* Trzeba znać hasło użytkownika 'jenkins' na serwerze JBoss w środowisku DEV
* Wykonać komendę mvn jboss-as:deploy -Dopenpkw-env=dev -Djboss.management.password=[tutaj wpisać hasło]

## Jak przetestować czy aplikacja rozproszona jest poprawnie?
* Sprawdzenie czy aplikacja webowa działa: http://[nazwa_serwera]:[port]/openpkw-dokument-generator
* Sprawdzanie czy działa web serwis: http://[nazwa_serwera]:[port]/openpkw-dokument-generator/service/protocol

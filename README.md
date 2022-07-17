# Prosty kalkulator walut

###### Autor: Konrad Dumin

### Instrukcja instalacji

Do zainstalowania i uruchomienia programu wymagane są JDK w wersji 1.8 lub wyższej, a także system GIT.

1. W wybranym przez siebie miejscu sklonuj repozytorium z programem

> git clone https://github.com/Condor-Condorrinsky/CurrencyCalculator.git

2. Przejdź do głównego katalogu projektu

> cd CurrencyCalculator

3. Skompiluj pliki *.java* do postaci *.class*

> javac -d build src/CurrCalc/*.java

4. Przejdź do katalogu *build*

> cd build

5. Połącz pliki .class i inne pliki znajdujące się w build do <br> jednego pliku *jar*.

> jar -cfe CurrencyCalculator.jar CurrCalc.Main CurrCalc/*

W wyniku w folderze *build* otrzymasz gotowy plik wykonywalny *CurrencyCalculator.jar*. <br> Możesz go przenieść w dowolne wybrane miejsce na dysku; nie wpłynie to na jego działanie.

### Uruchomienie

Z poziomu tego samego katalogu, w którym znajduje się uzyskany wcześniej plik *jar*, należy wywołać:

> java -jar CurrencyCalculator.jar [KWOTA_DO_PRZELICZENIA] [WALUTA]

Np. *java -jar CurrencyCalculator.jar 537.74 GBP*

**KWOTA_DO_PRZELICZENIA** to dowolna kwota w EUR (również ujemna), którą pragniemy przeliczyć na inną walutę. <br> Jako separator należy używać kropki. <br> Jeśli podana liczba będzie posiadała więcej, jak 2 cyfry po przecinku, to program zaokrągli ją do 2 miejsc po przecinku zgodnie z regułami matematycznymi.

**WALUTA** to trzyliterowy ciąg znaków reprezentujący walutę, na jaką chcemy przeliczyć naszą kwotę. <br> Do wyboru mamy następujące waluty:

- USD
- JPY
- BGN
- CZK
- DKK
- GBP
- HUF
- PLN
- RON
- SEK
- CHF
- ISK
- NOK
- HRK
- TRY
- AUD
- BRL
- CAD
- CAD
- CNY
- HKD
- IDR
- ILS
- INR
- KRW
- MXN
- MYR
- NZD
- PHP
- SGD
- THB
- ZAR

Przy podawaniu nazwy waluty wielkość liter nie ma znaczenia tzn. program zaakceptuje PLN, Pln, pln, pLN etc.

Po naciśnięciu klawisza *Enter* program przeliczy podaną kwotę w EUR na wybraną walutę i wyświetli wynik w terminalu.

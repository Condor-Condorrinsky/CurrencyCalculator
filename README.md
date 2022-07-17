# Prosty kalkulator walut

###### Autor: Konrad Dumin

### Instrukcja uruchomienia

1. Przejdź do głównego katalogu projektu

> cd /sciezka/gdzie/sklonowales/projekt/CurrencyCalculator

2. Skompiluj pliki *.java* do postaci *.class*

> javac -d build src/CurrCalc/*.java

4. Połącz pliki .class i inne pliki znajdujące się w build do <br> jednego pliku *jar*.

> jar -cfe CurrencyCalculator.jar CurrCalc.Main build/*

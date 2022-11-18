# 
# Lobby

Prosty plugin, który umożliwia stworzenie lobby na serwerze.

Plugin obecnie nie wspiera proxy.




# Wspierane wersje

| Testowano  | Status |
| ------------- | ------------- |
| 1.17.1  | ✅   |
| 1.16.5  | ❌  |

# Permisje

**lobby.setlobby** - permisja do ustawiania lobby

**lobby.lobby** - permisja do teleportacji na lobby

# Komendy

**/setlobby** - ustawia lobby w obecnej lokalizacji gracza

**/lobby** - teleportuje gracza na lobby

# Config / Plik konfiguracyjny

```yaml
# cooldown teleportacji na lobby (komenda /lobby) w sekundach
cooldown: 10 

# wiadomosc do gracza ktory uzywa komend bez permisji
brak-uprawnien: '&cNie masz dostepu do tej komendy!' 

# gdy gracz probuje ustawic lobby na innym swieci niz w: 'lobby-swiat'
inny-swiat: '&cNie mozesz ustawic lobby na tym swiecie!'

# title przy komendzie /setlobby
title-tekst: '&aLOBBY' 

# subtitle przy komendzie /setlobby
subtitle-tekst: '&7Poprawnie ustawiono lobby!'

# nazwa swiata (tylko na nim mozna ustawic lobby)
lobby-swiat: 'world' 

# koordynaty lobby (liczba calkowita)
lobby-x: '0' 
lobby-y: '70'
lobby-z: '0'

# title podczas teleportacji
teleportacja-lobby-title: '&c&lTELEPORTACJA' 

# subtitle gdy gracz sie poruszy
teleportacja-lobby-subtitle: '&cZostala przerwana! (Ruszyles sie!)' 

# subtitle odliczanie do teleportacji (czas dodaje sie sam automatycznie, ty tylko mozesz zmienic jego kolor)
teleportacja-lobby-subtitle-nastapi-za: '&e&lNastapi za: &6&l' 

# title po przeteleportowaniu na lobby
teleportacja-lobby-przeteleportowano-title: '&6&lPRZETELEPORTOWANO!' 

# subtitle po przeteleportowaniu na lobby
teleportacja-lobby-przeteleportowano-subtitle: '&aWitamy na lobby!' 
```


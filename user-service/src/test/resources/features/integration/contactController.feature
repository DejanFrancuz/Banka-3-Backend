Feature: Trestiranje contact kontrolera - integracioni testovi

  Scenario: Admin hoce da vidi sve kontakte i da zatim jedan promeni, a jedan da obrise
    Given Admin logovan
    When Amin trazi sve kontakte od usera sa id "1"
    Then Admin brise kontakt sa id "1"

  Scenario: Korisnik pravi novi kontakt
    When Korisnik kreira kontakt userId "1", myName "Placanje teretane", name "Zikica Zikic", accountNumber "33333333333"
    Then Menja
# ParkingFee — Tarif par tranches horaires

## Description
ParkingFee est une mini-application Android développée en **Kotlin** avec **Jetpack Compose**, qui calcule le prix de stationnement entre une heure de début et une heure de fin, en appliquant un tarif différent selon les tranches horaires (Nuit, Jour, Soir).

L'application offre deux modes de calcul :
- **Proportionnel** : calcul basé sur le temps exact passé dans chaque tranche.
- **Heure entamée** : chaque heure commencée est facturée intégralement.

Elle conserve les 3 derniers calculs dans la mémoire (sans persistance sur disque).

---

## Fonctionnalités

- Saisie de l'heure de début et de fin (format HH:MM).
- Sélection du mode de calcul (Proportionnel ou Heure entamée).
- Calcul automatique du tarif selon les tranches horaires :
    - Nuit : 00:00–07:59 → 4 MAD/h
    - Jour : 08:00–18:59 → 8 MAD/h
    - Soir : 19:00–23:59 → 6 MAD/h
- Affichage de la durée totale, du détail par tranche et du montant total (2 décimales).
- Historique des 3 derniers calculs.
- Interface moderne et colorée avec **Jetpack Compose**.

---

## Structure du projet


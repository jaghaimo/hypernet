# Hyperspace Network

Hyperspace Network, or HyperNET in short, is a sector-wide hyperspace network to query content of known markets.
Using the network one can search for administrators or officers with a given personality, query markets for weapons, fighter wings, modspecs, or blueprints, or locate specific ships for sale.

## Mod Integration

This mod is marked as "utility" mod, meaning it will work with any and all mods, total conversions included.

HyperNET queries ignore markets that are hidden.
Additionally, there are four CSV files to custom tailor market exclusion list:

* `factions.csv` file containing faction IDs to skip,
* `markets.csv` file containing market IDs to skip,
* `systems.csv` file containing star system IDs to skip, and
* `tags.csv` file containing market or star system tags to skip.

To skip any matching markets, add one or more files in your mods `data/config/hypernet/` folder and add appropriate IDs or tags.

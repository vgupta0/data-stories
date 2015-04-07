import json
import re
from toolz import *

MEGA_STONES = set(["Abomasite", "Absolite", "Aerodactylite", "Aggronite", 
  "Alakazite", "Altariaite", "Ampharosite", "Audinoite", "Banettite", "Beedrillite", 
  "Blastoisinite", "Blazikenite", "Cameruptite", "Charizardite", "Diancite", 
  "Galladeite", "Garchompite", "Gardevoirite", "Gengarite", "Glalite", "Gyaradosite", 
  "Heracronite", "Houndoominite", "Kangaskhanite", "Lopunnyite", "Lucarionite", 
  "Manectite", "Mawilite", "Medichamite", "Metagrossite", "Mewtwonite", 
  "Pidgeotite", "Pinsirite", "Sableite", "Salamenceite", "Sceptilite", 
  "Scizorite", "Sharpedoite", "Slowbroite", "Steelixite", "Swampertite", 
  "Tyranitarite", "Venusaurite"])

def strkey(s): return '' if s == '' else ':'+s.replace("'",'').replace(' ', '-')

def parseevs(evs): return get(['hp', 'at', 'df', 'sa', 'sd', 'sp'], evs, 0)

def parseset(pkmn, pset): 
  return [pset['type'].strip(), 
  strkey("Mega "+pkmn if pset.get('item', '') in MEGA_STONES and not pkmn.startswith("Mega ") else pkmn), 
  pset['name'].strip(), 
    [pset.get('item', '')] + list(map(strkey, get(["ability", "nature"], pset, ""))) 
    + [parseevs(pset["evs"])], list(map(strkey,pset["moves"]))]

pkmnsets = json.load(open('setdex_xy.json'))
parsed_sets = list(concat(map(lambda (name, sets): 
  [parseset(name, merge((lambda (kind,name): {"type": kind.strip('['),"name":name})
    (e.split(']') if '[' in e else e.split(" ",1) ), sets[e])) 
  for e in sets], pkmnsets.items())))

for e in parsed_sets: 
  print re.sub(r'"(:[A-Za-z\-]+)"', r'\1', json.dumps(e))


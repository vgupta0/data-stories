#!/usr/local/bin/python

import constants as pkc
import math
from subprocess import call, check_output
import json
from toolz import *
from sys import argv

def dmg(lvl, atk, df, bp, m):
  return m * (2.0 + bp * atk * (2.0*lvl + 10) / 250.0 / df )

def stat(lvl, iv, base, ev, nature):
  return int((((iv + 2*base + (ev/4))*lvl/100) + 5)*nature)

def hpstat(lvl, iv, base, ev):
  return ((iv + 2*base + (ev/4))*lvl/100) + 10 + lvl

typemod = pkc.typemod2

stabmod = lambda(movetype, pkmntypes): 1.5 if movetype in pkmntypes else 1

def stats(pkname, pkmnset):
  pkmndata = pkc.pkmn[pkname]
  return list(map(lambda base,ev,nature: stat(100,31,base,ev,nature),
             pkmndata[1], pkmnset[3], pkc.natures[pkmnset[2]] ))


nko = lambda n,target: int(math.ceil(target/n)) if n is not 0 else 100

# these crazy polynomials are found by running
# SurvivalFunction[#,1]&@UniformSumDistribution[n ,{85/100#,#}]&@d//FullSimplify
# in mathematica with n being # of kos ohko = 1, 2hko = 2, etc.

def chance_ohko(d,target):
  if d > 20.0*target/17: return 1
  elif d > target: return 20*(d-target)/(3*d)
  else: return 0

def chance_2hko(d,target):
  if d > 10.0*target/17: return 1
  elif d > 20.0*target/37: return (-200.0*target*target + (680*target - 569*d)*d)/(9*d*d)
  elif d > 0.5*target: return (200.0* (target - 2*d)**2)/(9*d*d)
  else: return 0

def chance_3hko(d,x):
  if d > 20.0*x/51: return 1
  elif d > 10.0*x/27: return 4919.0/6 - (10*x*(7803*d*d - 3060*d*x + 400*x*x))/81.0/d/d/d
  elif d > 20.0*x/57: return -12577.0/6 + (10*x*(18441*d*d-6660*d*x+800*x*x))/81.0/d/d/d
  elif d > 1.0*x/3: return (4000*(-1 + 3*d)**3)/(81*d*d*d)
  else: return 0

def chanceko(fraction):
  kos = nko(fraction,1)
  if kos is 1:   return chance_ohko(fraction,1)
  elif kos is 2: return chance_2hko(fraction,1)
  elif kos is 3: return chance_3hko(fraction,1)
  else: return 1


def movemap(move):
  rawcat,movetype,bp,acc = pkc.moves[move]
  cat = "Special" if rawcat == "Special-Physical" else rawcat
  physdamage = rawcat == 'Special-Physical'
  return {"bp": bp, "name": move, "type": movetype, "category": cat,
          "dealsPhysicalDamage": physdamage, "isCrit": False, "hits": 1}

def namestats(stats):
  return dict(map(lambda k,v: (k,v), ['hp','at','df','sa','sd','sp'],stats))

def pkmnmap(pkname, pkset, moves, state = [1, "Healthy", [], [0,0,0,0,0,0,0]]):
  types,basestats,weight = pkc.pkmn[pkname]
  item,ability,nature,evs = pkset
  curHP,status,volatile_status,boosts = state

  rawstats = stats(pkname, pkset)
  movelist = list(map(movemap, filter(lambda m: m in pkc.moves, moves)))

  return {
    'name': pkname,
    'type1': types[0],
    'type2': '' if len(types) <= 1 else types[1],
    'level': 100,
    'maxHP': rawstats[0],
    'curHP': float(curHP)*rawstats[0],
    'HPEVs': evs[0],
    'rawStats': namestats(rawstats),
    'stats': {},
    'boosts': namestats(take(6,boosts)),
    'evs':   namestats(evs),
    'nature': nature,
    'ability': ability,
    'item': item,
    'status': status,
    'toxicCounter': 0,
    'moves': movelist,
    'weight': weight}

#pkmnmap('Keldeo', ['Leftovers', 'Justified', 'Timid', [4,0,0,252,0,252]],
#        ['Scald', 'Substitute', 'Calm Mind'])


extract_pkset= lambda x: pkmnmap(x[1], x[3], x[4])

if __name__ == '__main__':
  pksets = list(map(eval, open(argv[1]).readlines()))
  for i in range(len(pksets)):
    attacker = pksets[i]
    a = json.dumps( extract_pkset(attacker))
    for s in pksets:
      pkset = json.dumps( extract_pkset(s) )
      results = eval(check_output(['js', 'damage.js', a , pkset]))
      best = list(map(
          lambda l: max(l, key=second) if len(l) > 0 else ["No Move", 0,0,0],
          results))
      added = list(map(lambda l: l+ [pkc.priority(l[0]), chanceko(l[2]), nko(l[2],1)], best))
      print attacker[1]+", ", attacker[2]+', ', ', '.join(map(str,added[0]))+', ', s[1]+", ", s[2]+', ', ', '.join(map(str,added[1]))






















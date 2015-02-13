import os
from sys import argv
from bottle import route, run, static_file, template
import json
from cytoolz import *

metadata = json.load(open('metadata.json'))

# DEBUG PURPOSES ONLY!
@route('/reloadmetadata')
def reloadmetadata():
  metadata = json.load(open('metadata.json'))
  return 'OK'

@route('/projects')
def projectslist():
  return {'projects': list(map(lambda p: dissoc(p, "posts"), metadata["projects"]))}

@route('/projects/<name>')
def project(name):
  return list(filter(lambda x: x["path"] == name, metadata["projects"]))[0]

@route('/post/<resource:path>')
def staticposts(resource):
  print resource if resource.endswith('.html') else resource + '.html'
  return static_file(
    resource if resource.endswith('.html') else resource + '.html',
    root='static/posts/')

@route('/recentposts')
def recentposts():
  return {'posts': pipe(metadata['projects'],
                        partial(map, lambda x: get('posts', x)),
                        concat,
                        lambda ps: sorted(ps, key=lambda x: x['created'],
                                          reverse=True),
                        partial(take, 10),
                        list)}

# ------------------------------ STATIC ROUTES ---------------------------------

@route('/')
def index():
  return template('static/index.html')

@route('/img/<resource:path>')
def staticimages(resource):
  return static_file(resource, root="static/img/")

@route('/fonts/<resource:path>')
def staticfonts(resource):
  return static_file(resource, root='static/fonts/')

@route('/lib/<resource:path>')
def staticlib(resource):
  return static_file(resource, root='static/lib/')

@route('/js/<resource:path>')
def staticjs(resource):
  return static_file(resource, root='static/js/')

@route('/css/<resource:path>')
def staticcss(resource):
  return static_file(resource, root='static/css/')

@route('/templates/<resource:path>')
def statictemplates(resource):
  return static_file(resource, root='static/templates/')

run(host='0.0.0.0', port=int(os.environ.get("PORT", 5000)) )







from bottle import route, run, static_file, template


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

@route('/posts/<resource:path>')
def staticposts(resource):
  return static_file(
    resource if resource.endswith('.html') else resource + '.html',
    root='static/posts/')

run(host='localhost', port=8080, debug=True)





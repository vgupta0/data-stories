# Data-Stories

The repo container for the website of my blog, Data Stories.

This repo contains both the web code and the raw analysis (mostly done in mathematica.

## File Structure

* `web/` is the web code, it contains the (fully static) webpage of the blog.
* `parse-metadata` is one of the deployment scripts that takes the .json files from each project folder along with the metadata on the top of each file in each posts/ folder and generates a json object that is the skeleton of the metadata of the website. usage: `parse-metadata`. That's it, it takes no arguments and dumps out the website structure that can be put into a file and loaded when the server runs.
* `deploy` is the overall data scrape/parse script. After writing/editing posts, run deploy to generate all html files and put them in the web/static/posts directory and update all the metadata.
* `techniques/` is a special project folder that actually just has posts about the various analysis techniques I use and some discussion of what code goes into making it happen (mostly mathematica at work, though).

* All the other folders are various projects that I'm working/have worked on. Each of these folders has a [folder-name].json file that contains the metadata about that project, an images/ folder that contains the images I took of various graphs/charts, and a posts/ folder that contains the markdown files with the posts. The other files are all for the data analysis. There are various scripts that take the images/metadata/posts respectively and "deploy" them by putting them in the appropriate spot in the web folder.

Markdown is rendered using pandoc (which supports a nice extension allowing for a yaml metatdata block on top of each file, which is where I put the metadata of each post).


## Deployment

Workflow:

* Do some data analysis (in each respective folders, like `./colors`)
* Write/edit posts (in the respective folders, `./colors/posts`)
    - Might also be adding images in the `./colors/images` folder for example
* Optionally edit code if the server needs updating.

Then, run the following commands:

```bash
$ deploy
$ git commit -a -m '[INSERT COMMIT MESSAGE]'
$ git push -u origin master
$ git subtree push --prefix web heroku master
```

or the easy way

```bash
$ deploy
$ push '[INSERT COMMIT MESSAGE]'
```

After heroku finishes redeploying everything the site should update with the posts/metadata of the recently written posts.


## Writing Posts

Posts are written using markdown and most of the analysis is done with mathematica/shell/python. The markdown interpreter is pandoc, which has a little different syntax for things like html blocks (for example `<div> ... </div>` -> `<div><p> ... </p></div>` for some odd reason, which messes with some of my formatting). You may have to be a bit creative with the server.

## Server Testing

Either use `foreman run web` (from heroku) or `python server.py` (both from within the web directory).


# TODO

* majors: post on the idea of major dominance (bar charts), and the mse boundary
	- It's about $$, sadly.
* majors: post on the arbitrary tech coefficients
* majors: post on the techiness based major size consideration (line graph, listplot of norms), tech score over time vs average/median tech score
* majors: post on the "other end" - developing the opposite of STEM (or not)!
* pokemon: post on categorizing pokemon by base stats 
* pokemon: post on the 1v1 damage only simulation, future directions (defensive win conditions, and status considerations).
* add the vizualizations of the crisistextline 
* find new data sets!




















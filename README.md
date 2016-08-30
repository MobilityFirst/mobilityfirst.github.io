# GNS Website

This is the website for the [Global Name Service (GNS)](https://github.com/MobilityFirst/GNS). It is based on version 5 of the [Jekyll Documentation Theme](http://idratherbewriting.com/documentation-theme-jekyll/)  ([Github](https://github.com/tomjohnson1492/documentation-theme-jekyll)). It is modified to suit the needs of this project and to slim down on unneeded functionality.

## Editing content
Jekyll was chosen for this site because a contributer only needs to know Markdown or have a [reference](https://github.com/adam-p/markdown-here/wiki/Markdown-Cheatsheet) handy to make changes. Content is separated from styling which allows contributers to easily create and update content. See CONTRIBUTE.md for details regarding how to edit and add content to the site.

The fasted and simplest way to edit or add content is to use existing content as a guide and use CONTRIBUTE.md and the [Jekyll Documentation](https://jekyllrb.com/docs/home/) for reference.

## Build the site

1. Install dependencies
  * gem (Ruby Gems)
  * nodejs and npm
  * bundler
2. Clone the repository
3. Run `bundler install` from the cli in the root directory of the project
4. Run `bundler exec jekyll serve` to start the web server
5. View your local site at http://localhost:4000/

As you save modified files Jekyll will continue to rebuild the site, allowing you to refresh the webpage to see them. Note changes to `_config.yml` require a restart of `bundler exec jekyll serve`.

If you wish to change more than the just the content of the site you should be familiar with [Jekyll](https://jekyllrb.com/). This site has several deviations from the original theme that it is based on, such as using JSON instead of YAML in data files and removal of unused features such as a printer friendly version of the site.

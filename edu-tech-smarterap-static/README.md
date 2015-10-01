# SmarterAP

## How to build:
  1. First, make sure you have the following tools install on to your computer:
    1. Node - http://nodejs.org/
    2. Yeoman - http://yeoman.io/
    3. Bower - http://bower.io/
    4. Grunt - http://gruntjs.com/ 
  2. Make sure to pull the folders from the git repo. 
  3. In the `gui` folder, run via command line `npm install -g grunt-cli bower` to be able to use Grunt/Bower commands.  
  4. Then run `bower install` to make sure all the necessary third-party modules are properly installed.
  5. After running `grunt serve`.  
  6. Go to the port specified, the app should be running successfully.  

## Notes
  * No need to touch index.html, it should be all set up.
  * Avoid using jquery, AngularJS has jqueryLite
  * Create a new file for each directive : read up on directives
  * Create a new file for each service : read up on services/factories
  * Each template should be named 'component.tpl.html'
  * Each controller should be named 'component.controller.js'

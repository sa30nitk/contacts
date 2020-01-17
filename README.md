# contacts

A simple contacts management system written in clojure.

## Installation

``` sh
brew cask install java
brew install leiningen
brew install postgresql
make db-setup
make db-migrate
```
## Generating standalone uber jar
 
```shell script
make uberjar
```
Uber jars will be generate in target/uberjar folder
## Usage

Run below command for executing the app. 
version number relies on your project settings
For db migration use "migrate" as an arugment on below command 


    $ java -jar contacts-0.1.0-standalone.jar [args]

## License

Copyright © 2019

This program and the accompanying materials are made available under the
terms of the Eclipse Public License 2.0 which is available at
http://www.eclipse.org/legal/epl-2.0.

This Source Code may also be made available under the following Secondary
Licenses when the conditions for such availability set forth in the Eclipse
Public License, v. 2.0 are satisfied: GNU General Public License as published by
the Free Software Foundation, either version 2 of the License, or (at your
option) any later version, with the GNU Classpath Exception which is available
at https://www.gnu.org/software/classpath/license.html.

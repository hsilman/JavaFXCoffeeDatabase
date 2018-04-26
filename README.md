# JavaFXCoffeeDatabase
Simple coffee bean tracker, using an editable tableview for the GUI and Amazon RDS MySQL instance for data storage.

This is my first project to help learn how to connect to a remote database and use SQL queries in Java. I also designed the GUI using JavaFX.
It is very simple, and reminds me of a business application from the late 90s/early 00s.

It also links to a single databse, so anyone who uses it will modify that table.

There are add/delete buttons on the bottom of the interface for those actions, and you can edit entries by double clicking the cells.

Edits are committed when you hit the enter key.

I think that's about it for this project. If I was to keep working on it, I think version 2.0 would improve the
Database Connection class and seperate out functionality a bit better. I think I create too many DB connections.
There's probably a way to create a single one to be used throughout the program, and then close it on program exit.
Also, I've repeated the lines to refresh the list of coffees from the database table a lot, it can probably be improved.

Thanks for checking this out! I learned a LOT doing this project.

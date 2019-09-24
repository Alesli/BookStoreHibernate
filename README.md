# BookStoreHibernate

**Objective**: Implement the functionality of buying a book in a store.

The application displays the result of the queries in the console. 
**Technologies used:** Hibernate, PostgreSQL 9.4, Lombok. 

1)	 Mandatory tables: 
- ***Book*** (id, name, author, description), 
- ***Shop*** (id, name, cash, book), 
- ***User*** (id, pass, username, cash, book (purchased book)). 

A store can contain several copies of one book, 
just like a user can contain several copies of a purchased book.

2)	*Scenario*: interaction occurs through the console 
(_implemented a simple console menu_). 
First, enter your username and password. And then, 
on behalf of this user, you can make purchases. (the database is filled in advance).
3)	*The logic of the purchase*: we withdraw money from the client’s account, replenish
 the cash desk of the store, lower the counter in the store, add it to the client.

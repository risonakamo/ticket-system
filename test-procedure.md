# running
1. open postman
2. run the server
3. open the database

# database sort order
recommended sort order for ticket table
1. id
2. last update time
3. severity
4. title
5. description
6. comments
7. impacted locations

sort by last update time

# how to login and pass to postman
1. go to login page http://localhost:8080/login
2. do login. look at employee db if needed for usernames. all passwords are `password`
3. once logged in, open cookies and copy the jsession cookie
4. paste into postman
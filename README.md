The API that we are using for this app has the following url: http://147.83.7.155:8080/dsaApp/ 
Explanation about API:

Services:

1. Tracks /tracks
Methods:
	1. GET:
		- Get all Tracks in the list
		- Get a single Track given its ID /:id
	2. POST:
		- Create a new Track given all its parameters
	3. PUT: 
		- Update a Track given all its parameters
	4. DELETE:
		- Delete a Track /:id

Models:

1. Track:
	- Integer id
	- String title
	- String singer
	
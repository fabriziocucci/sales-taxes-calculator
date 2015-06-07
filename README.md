# DESCRIPTION
Simple Java application that takes a cart in JSON format as input, applies some tax to the contained items and produces a receipt in JSON format as output.

# INPUT/OUTPUT EXAMPLE
This simple program requires two arguments to run:  
1. an input path to the file that contains the cart in JSON format;  
2. an output path to the file that will contain the receipt in JSON format.

Example of input cart:
```json
{
	"items": [
		{
			"name":"book",
			"type": "BOOK",
			"quantity": 1,
			"price": 12.49,
			"imported": false
		},
		{
			"name":"music CD",
			"type": "OTHER",
			"quantity": 1,
			"price": 14.99,
			"imported": false
		},
		{
			"name":"chocolate bar",
			"type": "FOOD",
			"quantity": 1,
			"price": 0.85,
			"imported": false
		}
	]
}
```

Example of output receipt:
```json
{
	"purchases":[
		{
			"item":{
				"name":"book",
				"type":"BOOK",
				"quantity":1,
				"price":12.49,
				"imported":false
			},
			"taxes":0.00,
			"total":12.49
		},
		{
			"item":{
				"name":"music CD",
				"type":"OTHER",
				"quantity":1,
				"price":14.99,
				"imported":false
			},
			"taxes":1.50,
			"total":16.49
		},
		{
			"item":{
				"name":"chocolate bar",
				"type":"FOOD",
				"quantity":1,
				"price":0.85,
				"imported":false
			},
			"taxes":0.00,
			"total":0.85
		}
	],
	"taxes":1.50,
	"total":29.83
}
```

# NOTE
No syntactic or semantic validation is performed on the input file.
const http = require('http');
const fs = require('fs');
const qs = require('querystring');
const proc = require('child_process');

fs.readFile('questions.html', function(err, file){
	if(err) throw err;

	http.createServer(function(request, response){
		// questions page
		if(request.url === '/questions'){
			response.writeHead(200, {'Content-Type':'text/html'});
			response.write(file);
			response.end();
		}
		// page after submit button is clicked
		else if(request.method === "POST" && request.url === '/submit'){
			// get request data
			let body = '';
			request.on('data', (chunk) => {
				body += chunk;
			}).on('end', () => {
				var post = qs.parse(body);

				response.writeHead(200, {'Content-Type':'image/png'});

				// write to the answers file
				fs.writeFileSync('answers.txt', '', (err) => {if(err) throw err;});
				for(var count = 1; count < 13; count++){
					let entry = ' ';
					if(post['q'+count]) entry = post['q'+count];
					fs.appendFileSync('answers.txt', entry, (err) =>{
						if(err) throw err;
					});
				}

				// run AutoArt JAR on answers file
				proc.execSync('java -jar AutoArt.jar answers.txt', (err, stdout, stderr) => {
					if(err) throw err;
					console.log(stdout);
					console.log(stderr);
				});

				// display the image
				var image = fs.readFileSync('aa_image.png');
				response.write(image,'binary');

				response.end();
			});
		}
		// default page
		else{
			response.statusCode = 404;
			response.writeHead(200, {'Content-Type':'text/html'});
			response.write('Please navigate to the /questions page first.')
			response.end()
		}
	}).listen(8888,'127.0.0.1');
});

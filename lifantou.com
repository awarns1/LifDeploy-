server {
        listen 80 default_server;
        listen [::]:80 default_server;
        root /var/www/lifantou.com/html;
        server_name lifantou.com www.lifantou.com;
        location / {
             proxy_pass http://localhost:8083;
             proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
             proxy_set_header X-Forwarded-Proto $scheme;
             proxy_set_header X-Forwarded-Port $server_port;
        }
}
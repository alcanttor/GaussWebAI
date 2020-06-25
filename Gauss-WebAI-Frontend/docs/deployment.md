# Gauss WebAI Frontend

This is a document for you to successfully run/deploy this angular application. Let's get started with the
tools and software we need to run locally.

<br />

### Local

##### Dependencies

1. Nodejs `>` v12.10.0

   You can find the same here - https://nodejs.org/en/download/
   This installs node and npm. Node is the env and npm is the package manager for node

2. Angular CLI

   You can find exact download instructions here - https://angular.io/cli

<br />

That's it ~! You have the all the dependencies to run the app.

##### Running the App

`$ cd <project-folder>/GAUSS-WEBAI-FRONTEND`

`$ ng serve`

<br />

### Production Deployment

##### Dependencies

1. Nginx

   You can find the installation instructions here - https://docs.nginx.com/nginx/admin-guide/installing-nginx/installing-nginx-open-source/#prebuilt_redhat

##### Enabling Nginx to access the folder

`$ sudo setsebool -P httpd_can_network_connect on`

Check if selinux is enforcing

`$ getenforce`

If it's enforcing

`$ chcon -Rt httpd_sys_content_t /home/ec2-user/dist/Gauss-WebAi-Frontend`

##### Nginx Conf

You can find the nginx conf here -

`$ cd /etc/nginx`

`$ cat nginx.conf`

You will have to set that file to the following conf

```
# For more information on configuration, see:
#   * Official English Documentation: http://nginx.org/en/docs/
#   * Official Russian Documentation: http://nginx.org/ru/docs/

user ec2-user;
worker_processes auto;
error_log /var/log/nginx/error.log;
pid /run/nginx.pid;

# Load dynamic modules. See /usr/share/doc/nginx/README.dynamic.
include /usr/share/nginx/modules/*.conf;

events {
    worker_connections 1024;
}

http {
    log_format  main  '$remote_addr - $remote_user [$time_local] "$request" '
                      '$status $body_bytes_sent "$http_referer" '
                      '"$http_user_agent" "$http_x_forwarded_for"';

    access_log  /var/log/nginx/access.log  main;

    sendfile            on;
    tcp_nopush          on;
    tcp_nodelay         on;
    keepalive_timeout   65;
    types_hash_max_size 2048;

    include             /etc/nginx/mime.types;
    default_type        application/octet-stream;

    # Load modular configuration files from the /etc/nginx/conf.d directory.
    # See http://nginx.org/en/docs/ngx_core_module.html#include
    # for more information.
    include /etc/nginx/conf.d/*.conf;

    server {
        listen       80 default_server;
        listen       [::]:80 default_server;
        server_name  _;
        root         /home/ec2-user/dist/Gauss-WebAi-Frontend;
        index index.html;
        # Load configuration files for the default server block.
        include /etc/nginx/default.d/*.conf;

        location / {
          #root /home/ec2-user/dist/Gauss-WebAi-Frontend;
          #index  index.html;
          try_files $uri /index.html;
          #proxy_pass http://127.0.0.1:3000;
        }

        #error_page 404 /404.html;
        #    location = /40x.html {
        #}

        #error_page 500 502 503 504 /50x.html;
        #    location = /50x.html {
        #}
    }

# Settings for a TLS enabled server.
#
#    server {
#        listen       443 ssl http2 default_server;
#        listen       [::]:443 ssl http2 default_server;
#        server_name  _;
#        root         /usr/share/nginx/html;
#
#        ssl_certificate "/etc/pki/nginx/server.crt";
#        ssl_certificate_key "/etc/pki/nginx/private/server.key";
#        ssl_session_cache shared:SSL:1m;
#        ssl_session_timeout  10m;
#        ssl_ciphers PROFILE=SYSTEM;
#        ssl_prefer_server_ciphers on;
#
#        # Load configuration files for the default server block.
#        include /etc/nginx/default.d/*.conf;
#
#        location / {
#        }
#
#        error_page 404 /404.html;
#            location = /40x.html {
#        }
#
#        error_page 500 502 503 504 /50x.html;
#            location = /50x.html {
#        }
#    }

}
```

##### Deployment

Build the frontend project in the Gauss-WebAI-Frontend folder

`$ ng build --prod`

Copy the dist folder generated to the ec2 machine

`$ scp -i "<path-to-pem>/GuassAI.pem" -r <path-to-project>/GaussWebAI/Gauss-WebAI-Frontend/dist ec2-user@ec2-15-206-94-60.ap-south-1.compute.amazonaws.com:/home/ec2-user/`

## Voila &nbsp; 🎊🎊🎊

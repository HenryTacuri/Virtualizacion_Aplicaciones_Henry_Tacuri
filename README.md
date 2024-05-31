**Nombre:** Henry Tacuri

<br>

Antes de construir los contenedores crear un archivo .dockerignore con lo siguiente:

AppCrudDocker/node_modules

AppCrudDocker/.git

AppCrudDocker/dist

AppCrudDocker/.vscode

AppCrudDocker/.angular

<br>

Con el archivo **.dockerignore** ignoramos ciertos archivos de la aplicación angular para poder bajar su peso.

<br>

**Construcción de los contenedores:**
1.	Para el proyecto **demoappdocker** con **Maven Build** ejecutamos el comando **clean package docker:build**.
2.	Construimos la aplicación angular (AppCrudDocker) con el comando **npm install**.
3.	En una terminal nos ubicamos en el directorio en el cual se encuentra el archivo **docker-compose.yml**. 
4.	Ejecutamos el comando **docker-compose up** para levantar todos los contenedores.


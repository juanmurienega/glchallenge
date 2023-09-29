# Challenge


![Spring](https://img.shields.io/badge/springboot-%236DB33F.svg?style=for-the-badge&logo=springboot&logoColor=white)


Challenge es un microservicio escrito en SpringBoot / Gradle, para la creación y consulta
de usuarios

## Requisitos

- Usar Java 8
- Testing del service con Spock

## Instalacion
### Prerequisitos
Challenge requiere de:
[Java 8](https://adoptopenjdk.net/)
[Gradle](https://gradle.org/install/#manually)

#### Java
Puede validar si tiene instalado java en su sistema operativo ejecutando:
```sh
java -version
```
Si no se reconoce el comando, se sugiere seguir las  [intrucciones de instalacion](https://parzibyte.me/blog/2017/12/26/instalar-configurar-jdk-compilador-java-windows/)  

#### Gradle
Para instalar gradle, se debe tener en cuenta cual es el sistema operativo donde se va a ejecutar el proyecto

Para usuarios Linux puede usar [SDKMAN](http://sdkman.io/)
```sh
$ sdk install gradle 8.3
```

Para usuarios MAC puede usar [Homebrew](http://brew.sh/)

```sh
$ brew install gradle
```

Para usuarios windows 
- [Descargar] (https://gradle.org/releases) la ultima version binaria estable
- Descomprimir el zip en un directorio a su eleccion
- En el explorador de windows, click derecho sobre _Este Equipo_, luego seleccionar Propiedades -> Configuracion avanzada del sistema -> Variables de entorno. En la seccion Variables del sistema, seleccionar _Path_ y presionar editar. Agregar una nueva entrada a la carpeta bin dentro de la carpeta donde se descomprimio el zip. Luego presione aceptar.
Puede validar si esta instalado gradle ejecutando el siguiente comando:

```sh
$ gradle -v
```
## Ejecucion

Para ejecutar el proyecto debe ejecutar una shell y situarse en el directorio raiz del proyecto. Puede validar si esta en esa carpeta si visualiza el archivo gradlew.
```sh
$ gradlew bootRun
```
Espere hasta que aparezca la leyenda _> :bootRun_
Si el proyecto esta ejecutandose de manera correcta podra visualizar las siguientes paginas:

- [Swagger](http://localhost:8080/swagger-ui/index.html) - Pagina html de la documentacion de la api
- [Consola H2](http://localhost:8080/h2-console/) - Consola de H2

Puede probar la api directamente desde swagger o importar la coleccion a postman.

## Test
Para ejecutar las pruebas de la aplicacion, se debe ejecutar el siguiente comando:
```sh
$ gradlew test -i
```
Al finalizar se puede ver un reporte dentro de la carpeta 

```sh
$ build\reports\tests\test\index.html
```


## Diagramas
En la carpeta \diagramas se encuentran dos imagenes con los diagramas correspondientes a:
- [Diagrama de componentes](https://www.plantuml.com/plantuml/duml/ROz1JyCm38Nl_XMXzrxu1HlJf76RA9pG3Y6rkcYf7Jak0o7-E-cYm2XKFVZvVKzvNYMrejCOu4QFEA9nA5D0I_3eDQ1fkYYUXZjcDUiO6m3Oy1YPaDGqw_rzym76F3ybb0sJ2eU0SZ1jMNKehzxXeI9LL_y18oUlBEyNf4CtYTSYjcyEetgcOaLsfpATxJ2Wp3D4wqvso2stf3dNcEyoPN49x8zCMFKdp7zlrRxOb8ExswjG-k7pE8CxrMZQdi_K_ZaIbYTai5qy_n-oh0HNYGBKCeNxvTHoyxT2wgSnV040)
- [Diagrama de secuencia](https://www.plantuml.com/plantuml/duml/PP31QiCm343l_WgjfuqI1Zlc40hxWtNx05KHYuiZ6qbEsT_FNc1GUdtloSWwcQDwNLDuyeLN1cAjYL72P_J4S3Z7Rnbg0PQvv2X-24YU5RwC5T2WXeBgaM91SNZFuffJsXtTkJFh5ecxi3lnmILRR8_-TATXHiSB6iFori4IGXqc6g5L3g_M5gebq33PyJW2OKf0okXSZVNv1LRs9S_1MZ1257CKOkWIykNAv5tf28RRZeCEq_ANiZfeldNGPfMzge3mRS_Q63tIcP4yRkWCrhUg_ydLia3_J3YnpEt6lm00)

Los mismos fueron generados usando [Plantuml](https://plantuml.com/es/) y se puede verificar su codigo siguiendo el link correspondiente a cada diagrama.


## Postman Collection
[Postman](https://www.postman.com/sagmobile/workspace/test/collection/1695636-930fa264-7c86-4514-9c15-09353583948b?action=share&creator=1695636) Contiene una collection de prueba de la api. Recupera el token luego de hacer sig-up y lo pasa al endpoint login mediante una variable de la coleccion.
